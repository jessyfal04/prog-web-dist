# Kubernetes Volume Setup for Static Images

## Overview
This document describes the setup and configuration of persistent volumes in Kubernetes for serving static images in the WebNotes application.

## Volume Configuration Files

### PersistentVolume (PV)
```yaml
# k8s/webnotes-volume.yml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: webnotes-static-pv
spec:
  capacity:
    storage: 1Gi
  accessModes:
    - ReadOnlyMany
  storageClassName: standard
  hostPath:
    path: /data/static-webnotes
    type: DirectoryOrCreate
```

### PersistentVolumeClaim (PVC)
```yaml
# k8s/webnotes-volume.yml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: webnotes-static-pvc
  namespace: webnotes
spec:
  accessModes:
    - ReadOnlyMany
  storageClassName: standard
  resources:
    requests:
      storage: 1Gi
```

### Client Deployment Volume Mount
```yaml
# k8s/webnotes-client.yml
spec:
  containers:
    - name: webnotes-client
      # ... other container config ...
      volumeMounts:
        - name: static-volume
          mountPath: /static
          readOnly: true
  volumes:
    - name: static-volume
      persistentVolumeClaim:
        claimName: webnotes-static-pvc
```

## Spring Boot Configuration

### application.properties
```properties
# Static resources configuration
spring.web.resources.static-locations=file:/static/
spring.mvc.static-path-pattern=/static/**
```

### HTML Template Configuration
Add the following lines in the `<head>` section of your HTML templates:
```html
<link rel="icon" type="image/png" href="/static/favicon.png">
<link rel="stylesheet" href="/static/styles.css">
```

## Setup Commands

### 1. Clean Up Existing Resources
```bash
# Delete all resources in webnotes namespace
kubectl delete all --all -n webnotes
kubectl delete pvc --all -n webnotes
kubectl delete pv webnotes-static-pv

# Clean up Minikube directory
minikube ssh "sudo rm -rf /data/static-webnotes && sudo mkdir -p /data/static-webnotes && sudo chmod 777 /data/static-webnotes"
```

### 2. Apply Volume Configuration
```bash
# Apply PV and PVC
kubectl apply -f k8s/webnotes-volume.yml

# Verify PV and PVC status
kubectl get pv
kubectl get pvc -n webnotes
```

### 3. Copy Static Files to Minikube
```bash
# Copy static files to Minikube VM
minikube cp static/favicon.png /data/static-webnotes/favicon.png
minikube cp static/styles.css /data/static-webnotes/styles.css
```


### 4. Verify Volume Mounting
```bash
# Check if volume is mounted in pods
kubectl exec -n webnotes $(kubectl get pods -n webnotes | grep webnotes-client | head -n 1 | awk '{print $1}') -- ls -la /static
```

## Debugging Commands

### Check Volume Status
```bash
# Check PV and PVC status
kubectl get pv,pvc -n webnotes

# Describe PV and PVC for detailed information
kubectl describe pv webnotes-static-pv
kubectl describe pvc webnotes-static-pvc -n webnotes
```

### Verify Volume Mounting
```bash
# Check if volume is mounted in pods
kubectl exec -n webnotes $(kubectl get pods -n webnotes | grep webnotes-client | head -n 1 | awk '{print $1}') -- ls -la /static

# Verify file access
kubectl exec -n webnotes $(kubectl get pods -n webnotes | grep webnotes-client | head -n 1 | awk '{print $1}') -- curl -I http://localhost:8080/static/favicon.png
```

### Check Pod Volume Mounts
```bash
# Describe pod to see volume mounts
kubectl describe pod -n webnotes $(kubectl get pods -n webnotes | grep webnotes-client | head -n 1 | awk '{print $1}')
```

## Directory Structure
- Minikube path: `/data/static-webnotes`
- Container path: `/static`
- Current static files:
  - `static/favicon.png`
  - `static/styles.css`
