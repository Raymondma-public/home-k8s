./gradlew build -Dspring.profiles.active=prod
docker build -t k8s-manager-service .
docker tag k8s-manager-service raymondmapublic/k8s-manager-service
docker push raymondmapublic/k8s-manager-service
kubectl apply -f k8s-web-manager.yaml
