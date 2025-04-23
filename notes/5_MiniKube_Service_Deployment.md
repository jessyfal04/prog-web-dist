# We start Minikube and enable ingress and dashboard

minikube start --memory 4096 --cpus 4
minikube addons enable dashboard        # nice visual UI
#minikube addons enable ingress          # nginx-ingress but useless car we use istio: ingressgateway
minikube dashboard &

# Server Deployment
```
# k8s/webnotes-server.yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: webnotes-server
spec:
  replicas: 1
  selector:
    matchLabels:
      app: webnotes-server
  template:
    metadata:
      labels:
        app: webnotes-server
    spec:
      containers:
        - name: webnotes-server
          image: jessyfal04/webnotes-server:latest
          ports:
            - containerPort: 9090            # gRPC
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
---
apiVersion: v1
kind: Service
metadata:
  name: webnotes-server
spec:
  selector:
    app: webnotes-server
  ports:
    - name: grpc
      port: 9090
      targetPort: 9090
  type: ClusterIP
```

## We apply the configuration
kubectl apply -f k8s/webnotes-server.yml
kubectl get pods

# Client Deployment
```
# k8s/webnotes-client.yml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: webnotes-client
spec:
  replicas: 1
  selector:
    matchLabels:
      app: webnotes-client
  template:
    metadata:
      labels:
        app: webnotes-client
    spec:
      containers:
        - name: webnotes-client
          image: jessyfal04/webnotes-client:latest
          ports:
            - containerPort: 8080            # REST + UI
          env:
            - name: SPRING_PROFILES_ACTIVE
              value: "k8s"
            - name: GRPC_SERVER_HOST         # internal call
              value: "webnotes-server"
            - name: GRPC_SERVER_PORT
              value: "9090"
---
apiVersion: v1
kind: Service
metadata:
  name: webnotes-client
spec:
  selector:
    app: webnotes-client
  ports:
    - name: http
      port: 8080
      targetPort: 8080
  type: ClusterIP
```

# Apply the configuration
kubectl apply -f k8s/webnotes-server.yml
kubectl apply -f k8s/webnotes-client.yml
kubectl get pods # wait for 2/2

# Note : Make sure the application.properties are up to date
grpc.client.myService.address=${GRPC_SERVER_HOST:localhost}:${GRPC_SERVER_PORT:9090}

server.port=0
grpc.server.port=9090