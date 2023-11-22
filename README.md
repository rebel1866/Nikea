This is the project that represents furniture sales service (for example, like Ikea, that's why project called Nikea).
Brief description of technical details:
- Application consists of 6 microservices
- 3 microservices are supposed to serve business logic - Product service, Order service and Notification service
- Also, there's Service discovery so that services could find each other (implemented as Netflix Eureka Server)
- Spring Cloud configuration server is used for centralized storing of microservices config files
- Spring Cloud Gateway is used for routing and load balancing

Product service is intended for creating, reading, updating and deleting furniture objects.
Order service allows you to create orders - this service interacts with product service via REST.
When order is created, message is put in Kafka message broker, then it is consumed by Notification service, that's used for sending sms notifications
to user ( for example - "Your order was successfully created").

List of basic technologies used in this project:
- Java 17
- Spring Boot
- NoSQL MongoDB database for Order service
- Postgresql for Product service (Hibernate used as ORM framework)

So that you could test this application you have to follow these rules:
- Clone this repository
- execute mvn clean package command in root folder of each microservice (make sure maven using java 17)
- To launch service use java -jar {-executable-file-name}.jar 
- Services have to be run in the specific order - first Service discovery, then Config server, Api Gateway, Order service, Product Service and finally Notification Service
- Make sure MongoDB and postgres databases are launched and running on default ports 