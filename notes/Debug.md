# For as long as you need to test:
kubectl -n webnotes port-forward svc/webnotes-server 9090:9090

# List the available RPCs
grpcurl -plaintext localhost:9090 list

# Get grpcurl from
https://github.com/fullstorydev/grpcurl