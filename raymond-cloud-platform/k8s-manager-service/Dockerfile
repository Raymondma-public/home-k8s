FROM debian:buster
RUN apt update && \
      apt install -y curl && \
      curl -LO https://storage.googleapis.com/kubernetes-release/release/`curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt`/bin/linux/amd64/kubectl && \
      chmod +x ./kubectl && \
      mv ./kubectl /usr/local/bin/kubectl
VOLUME /tmp
ADD build/libs/k8s-manager-service-0.0.1-SNAPSHOT.jar app.jar
CMD kubectl get po
ENTRYPOINT ["java","-jar","-Dspring.profiles.active=<SPRING_PROFILES_ACTIVE>","/app.jar"]