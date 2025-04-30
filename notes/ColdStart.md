# Cold Start Procedure

## 1. Delete Existing Minikube Cluster
```bash
minikube delete
```

## 2. Start New Minikube Cluster
```bash
minikube start --memory 2048 --cpus 2
minikube addons enable dashboard
minikube dashboard &
```

## 3. Install Istio
```bash
curl -L https://istio.io/downloadIstio | ISTIO_VERSION=1.25.2 TARGET_ARCH=x86_64 sh -
cd istio-1.25.2
export PATH=$PWD/bin:$PATH
istioctl install --set profile=demo -y
cd ..
```

## 4. Create Namespace and Enable Istio Injection
```bash
kubectl create ns webnotes
kubectl label ns webnotes istio-injection=enabled
```

## 5. Build and Push Docker Images
```bash
# Build project with tests
cd gRPCSpring
./gradlew clean build -x test

# Build and push server image
cd myServiceServer
docker build -t jessyfal04/webnotes-server:latest .
docker push jessyfal04/webnotes-server:latest

# Build and push client image
cd ../myServiceClient
docker build -t jessyfal04/webnotes-client:latest .
docker push jessyfal04/webnotes-client:latest
```

## 6. Deploy MySQL Components
```bash
kubectl apply -f k8s/mysql-storage.yaml -n webnotes
kubectl apply -f k8s/mysql-deployment.yaml -n webnotes
kubectl apply -f k8s/mysql-secret.yaml -n webnotes
```

## 7. Set Up Static Volume
```bash
# Create directory in Minikube VM
minikube ssh "sudo rm -rf /data/static-webnotes && sudo mkdir -p /data/static-webnotes && sudo chmod 777 /data/static-webnotes"

# Apply volume configuration
kubectl apply -f k8s/webnotes-volume.yml

# Copy static files to Minikube VM
```bash
# Copy static files to Minikube VM
minikube cp static/favicon.png /data/static-webnotes/favicon.png
minikube cp static/styles.css /data/static-webnotes/styles.css
```

# Verify volume setup
kubectl get pv
kubectl get pvc -n webnotes
```

## 8. Deploy Application Components
```bash
kubectl apply -f k8s/webnotes-server.yml -n webnotes
kubectl apply -f k8s/webnotes-client.yml -n webnotes
kubectl apply -f k8s/webnotes-gateway.yml -n webnotes
```

## 9. Start Minikube Tunnel
```bash
minikube tunnel &
```

## 10. Verify Deployment

### Get Istio Gateway IP
```bash
kubectl -n istio-system get svc istio-ingressgateway -o jsonpath='{.status.loadBalancer.ingress[0].ip}'
```

### Check Pod Status
```bash
kubectl get pods -n webnotes
```

### Verify Database
```bash
kubectl exec -it $(kubectl get pods -n webnotes | grep mysql | awk '{print $1}') -n webnotes -- mysql -uuser -ptest1234 -e "USE db; DESCRIBE notes; SELECT * FROM notes;"
```

### Verify Volume Mounting
```bash
# Check volume mounting in pods
kubectl exec -n webnotes $(kubectl get pods -n webnotes | grep webnotes-client | head -n 1 | awk '{print $1}') -- ls -la /static
```

### Rollout
```bash
kubectl rollout restart deployment webnotes-server -n webnotes && kubectl rollout restart deployment webnotes-client -n webnotes

kubectl get deployments -n webnotes -o name | xargs -I {} kubectl rollout restart {} -n webnotes
```
