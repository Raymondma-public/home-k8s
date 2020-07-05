version=$1

./gradlew build -Dspring.profiles.active=prod
docker build -t k8s-manager-service:v$version .
docker tag k8s-manager-service:v$version raymondmapublic/k8s-manager-service:v$version
docker push raymondmapublic/k8s-manager-service:v$version
kubectl apply -f k8s-web-manager.yaml
