
# Voting App Microservices Application deployed on Kubernetes

This project demonstrates the Voting App Microservices Application deployed on Kubernetes.





## Architecture

![](voting-app.drawio.png)
## Deployment

To deploy this project follow the below mentioned steps.

### Docker Images

- Build the Docker Images with spring boot maven plugin
   - Go to the voter-registration-service root folder and run the below command
    `./mvnw spring-boot:build-image -DskipTests`
    - Same as previous step for vote-submission-service and election-results-service

- Push Docker images to Docker Hub
    ```
    docker login
    docker push sumitdas28/voter-registration-service:0.0.1-SNAPSHOT
    docker push sumitdas28/vote-submission-service:0.0.1-SNAPSHOT
    docker push sumitdas28/election-results-service:0.0.1-SNAPSHOT
    ```

### Deployment steps for Google Kubernetes Engine (GKE)

- Create GKE standard cluster
- Go to the voter-registration-service root folder and run the below commands to create the deployment.
    ```
    gcloud container clusters get-credentials cluster-1 --zone us-central1-c --project ancient-folio-350713
    kubectl apply -f deployment.yaml
    ```

- Same as previous step for vote-submission-service and election-results-service