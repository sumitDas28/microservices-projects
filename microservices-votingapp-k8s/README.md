
# Voting App Microservices Application deployed on Kubernetes

This project demonstrates the Voting App Microservices Application deployed on Kubernetes.





## Architecture

Voting App consists of 3 microservices written in Java language that talk to each other over REST.

![](voting-app.drawio.png)

| Service                                              | Language      | Description                                                                                                                                          |
| ---------------------------------------------------- | ------------- | ---------------------------------------------------------------------------------------------------------------------------------------------------- |
| voter-registration-service (VRS)                     | Java          | Stores the voter registration details like Voter ID, Name, DOB, Address, Phone Number etc. and retrieves it.                                         |
| vote-submission-service (VSS)                        | Java          | Validates the voter details by calling VRS and then stores the vote submission details and retrieves it.                                             |
| election-results-service (ESS)                       | Java          | Scheduler job runs every 30 seconds to call the VSS to fetch submitted vote details. Calculates and stores the election results and retrieves it.    |


## Deployment

To deploy this project follow the mentioned steps.

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