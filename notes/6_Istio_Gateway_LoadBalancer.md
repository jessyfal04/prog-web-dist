
# Install Istio
## Commands
curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.25.2 TARGET_ARCH=x86_64 sh -
cd istio-1.25.2
export PATH=$PWD/bin:$PATH
istioctl install --set profile=demo -y          # installs ingress-gateway, Kiali, Grafana, Jaeger
cd ..

## Create a dedicated namespace for your app and turn on automatic side-car injection:
kubectl create ns webnotes
kubectl label ns webnotes istio-injection=enabled

## Delete the duplicate Deployments & Services in default
kubectl delete deployment webnotes-client webnotes-server -n default
kubectl delete svc webnotes-client webnotes-server -n default

## Apply the changes
kubectl apply -n webnotes -f k8s/webnotes-server.yml
kubectl apply -n webnotes -f k8s/webnotes-client.yml
kubectl rollout restart deploy webnotes-server webnotes-client

kubectl get deploy -A | grep webnotes

# Create Gateway
```
# k8s/webnotes-gateway.yml
# ── Gateway (single entry-point) ───────────────────────────────
apiVersion: networking.istio.io/v1alpha3
kind: Gateway
metadata:
  name: webnotes-gateway
  namespace: webnotes
spec:
  selector:
    istio: ingressgateway
  servers:
  - port:
      number: 80          # <-- external HTTP
      name: http
      protocol: HTTP
    hosts:
    - "*"                 # keep generic in dev; tighten in prod

# ── VirtualService → CLIENT (UI & REST façade) ────────────────
---
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: webnotes-client-vs
  namespace: webnotes
spec:
  hosts:
  - "*"
  gateways:
  - webnotes-gateway
  http:
  - match:
      - uri:
          prefix: /       # everything
    rewrite:
      uri: /
    route:
      - destination:
          host: webnotes-client.webnotes.svc.cluster.local
          port:
            number: 8080  # Service port

# ── VirtualService → SERVER (gRPC)  ───────────────────────────
---
apiVersion: networking.istio.io/v1alpha3
kind: VirtualService
metadata:
  name: webnotes-server-vs
  namespace: webnotes
spec:
  hosts:
  - "*"
  gateways:
  - webnotes-gateway
  tcp:                       # gRPC = HTTP/2 over TCP
  - match:
      - port: 9090
    route:
      - destination:
          host: webnotes-server.webnotes.svc.cluster.local
          port:
            number: 9090
```

## Commands
kubectl apply -f k8s/webnotes-gateway.yml
kubectl get deploy,svc -A | grep webnotes

### Start the load balancer
minikube tunnel&

### Get the assigned address
INGRESS_IP=$(kubectl -n istio-system get svc istio-ingressgateway \
              -o jsonpath='{.status.loadBalancer.ingress[0].ip}')
echo "→ http://${INGRESS_IP}/" 
