# Database Setup for WebNotes Application

## MySQL Deployment

### 1. Create MySQL Secret, Storage, Deployment
```bash
kubectl apply -f k8s/mysql-storage.yaml -n webnotes
kubectl apply -f k8s/mysql-deployment.yaml -n webnotes
kubectl apply -f k8s/mysql-secret.yaml -n webnotes
```

### 2. Verify MySQL Deployment
```bash
# Check MySQL pod status
kubectl get pods -n webnotes | grep mysql

# Check storage resources
kubectl get pv,pvc -n webnotes

# Check MySQL service
kubectl get svc -n webnotes | grep mysql
```

### 3. Connect to MySQL and Verify Database
```bash
# Connect to MySQL and check database structure
kubectl exec -it $(kubectl get pods -n webnotes | grep mysql | awk '{print $1}') -n webnotes -- mysql -uuser -ptest1234 -e "USE db; DESCRIBE notes; SELECT * FROM notes;"
```

## Database Schema
The application automatically creates the following table structure:
```sql
CREATE TABLE notes (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    created_at DATETIME(6),
    thumbs_up_count INT
);
```

## Current Status
- Database: Running and accessible
- Table Structure: Created and verified
- Connection: Working properly
- Data: Table is empty and ready for use

## Troubleshooting
1. Check MySQL logs:
```bash
kubectl logs -n webnotes $(kubectl get pods -n webnotes | grep mysql | awk '{print $1}')
```

2. Check application logs:
```bash
kubectl logs -n webnotes $(kubectl get pods -n webnotes | grep webnotes-server | awk '{print $1}')
```

3. Verify database connection:
```bash
kubectl exec -it $(kubectl get pods -n webnotes | grep webnotes-server | awk '{print $1}') -n webnotes -- curl -s http://localhost:9090/actuator/health
```

4. Descirbe and get notes data
kubectl exec -it $(kubectl get pods -n webnotes | grep webnotes-server | awk '{print $1}') -n webnotes -- mysql -uuser -ptest1234 -e "USE db; DESCRIBE notes; SELECT * FROM notes;"