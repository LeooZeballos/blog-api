# Blog API Rest

[![Open in Visual Studio Code](https://img.shields.io/badge/Open%20in-Visual%20Studio%20Code-blue?logo=visual-studio-code)](https://open.vscode.dev/LeooZeballos/blog-api) [![GitHub issues](https://img.shields.io/github/issues/LeooZeballos/blog-api)](https://github.com/LeooZeballos/blog-api/issues) [![GitHub forks](https://img.shields.io/github/forks/LeooZeballos/blog-api)](https://github.com/LeooZeballos/blog-api/network) [![GitHub stars](https://img.shields.io/github/stars/LeooZeballos/blog-api)](https://github.com/LeooZeballos/blog-api/stargazers) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Table of contents
* [General info](#General-info)
* [How to Install and Run the Project](#How-to-Install-and-Run-the-Project)
* [Technologies](#Technologies)
* [Features](#Features)
* [License](#License)

## General info

Welcome to my Blog API Rest project! I've had an amazing learning experience while working on this project, where I built a robust and secure blogging platform using various technologies. Let me share what I've learned and implemented:

To begin with, I got hands-on experience with Spring Boot, a fantastic framework that simplifies Java application development. With Spring Boot, I was able to set up the project quickly and effortlessly. It provided preconfigured dependencies and production-ready features, saving me a lot of time and effort.

One of the key aspects I learned was JWT (JSON Web Tokens), an essential authentication mechanism used in modern web applications. I successfully implemented JWT-based authentication and authorization in my API, ensuring secure transmission of user information between the client and the server in the form of JSON objects.

Another technology I delved into was Spring Security. It's an incredibly versatile security framework that helped me handle authentication and access control in my API. I was able to define fine-grained access rules, ensuring that only authenticated users could access specific endpoints.

For data storage, I integrated my API with a PostgreSQL database. PostgreSQL is a powerful and reliable relational database management system. I used Spring Data JPA, a high-level abstraction over the database, to simplify database access and interact with it using object-oriented concepts. This reduced the amount of boilerplate code I had to write.

Throughout the project, I had the opportunity to work with Apache Maven, a popular build automation and dependency management tool. Maven made it easy for me to manage dependencies and build the project effectively.

Furthermore, I explored Docker, a powerful containerization tool. With Docker, I containerized my application and its dependencies, allowing for consistent deployment across different environments. This simplified the setup process and ensured that my application ran smoothly wherever it was deployed.

Documentation and testing were crucial aspects of my project. I used Postman, an excellent API development environment, to test my API endpoints and interact with them seamlessly. Additionally, I integrated Swagger into my API to provide interactive documentation. It made it easy for me to explore the available endpoints, view request/response examples, and understand the functionality of my API.

Last but not least, I managed my codebase using Git, a widely used version control system. Git helped me track changes, collaborate with other developers, and maintain a reliable history of my project.

Overall, this project has been an enriching learning experience. I've gained practical knowledge in developing RESTful APIs with Spring Boot, implementing JWT-based authentication and authorization, integrating with a PostgreSQL database, and leveraging essential tools for documentation and testing.

## How to Install and Run the Project

### Requirements

* Java 8 or higher
* Apache Maven
* PostgreSQL with a database named `fastfood`

### Steps
1. Clone the repository
```bash
git clone https://github.com/LeooZeballos/fast-food.git
```
2. Open the project in your terminal and run the following command
```bash
mvn spring-boot:run
```
3. Open your browser and go to http://localhost:8080/

### Docker

Alternatively, you can run the project using Docker. To do so, follow these steps:

1. Clone the repository
```bash
git clone https://github.com/LeooZeballos/fast-food.git
```
2. Build the image for the API.
```bash
docker build -t fastfood-api .
```
2. Open the project in your terminal and run the following command
```bash
docker-compose up -d
```
3. Open your browser and go to http://localhost:8080/

## Technologies

[![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.java.com)
[![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)](https://maven.apache.org)
[![Spring](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)](https://spring.io)
[![Spring Data JPA](https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)](https://spring.io/projects/spring-data-jpa)
[![Hibernate](https://img.shields.io/badge/Hibernate-59666C?style=for-the-badge&logo=Hibernate&logoColor=white)](https://hibernate.org)
[![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?style=for-the-badge&logo=PostgreSQL&logoColor=white)](https://www.postgresql.org)
[![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=Spring&logoColor=white)](https://spring.io/projects/spring-security)
[![JWT](https://img.shields.io/badge/JWT-black?style=for-the-badge&logo=JSON%20web%20tokens)](https://jwt.io)
[![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)](https://www.postman.com)
[![Swagger](https://img.shields.io/badge/Swagger-85EA2D?style=for-the-badge&logo=Swagger&logoColor=black)](https://swagger.io)
[![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=Git&logoColor=white)](https://git-scm.com)
[![Lombok](https://img.shields.io/badge/Lombok-BC2E86?style=for-the-badge&logo=Lombok&logoColor=white)](https://projectlombok.org)
[![Docker](https://img.shields.io/badge/Docker-2496ED?style=for-the-badge&logo=Docker&logoColor=white)](https://www.docker.com)
[![Docker Compose](https://img.shields.io/badge/Docker%20Compose-2496ED?style=for-the-badge&logo=Docker&logoColor=white)](https://docs.docker.com/compose)

## Features

| Method | Endpoint                                       | Description                                 | Parameters                                      | Role  |
|--------|------------------------------------------------|---------------------------------------------|-------------------------------------------------|-------|
| POST   | /api/v1/auth/login                             | Login to get a bearer token                 | usernameOrEmail, password                       | ANY   |
| POST   | /api/v1/auth/register                          | Register a user                             | name, username, email, password                 | ANY   |
| GET    | /api/v1/entries                                | List all entries                            | [pageNumber], [pageSize], [sortBy], [sortDir]   | ANY   |
| POST   | /api/v1/entries                                | Create a new entry                          | title, description, content                     | ADMIN |
| GET    | /api/v1/entries/{entryId}                      | Get an entry by ID                          | entryId                                         | ANY   |
| PUT    | /api/v1/entries/{entryId}                      | Update an entry by ID                       | entryId, title, description, content            | ADMIN |
| DELETE | /api/v1/entries/{entryId}                      | Delete an entry by ID                       | entryId                                         | ADMIN |
| GET    | /api/v1/entries/{entryId}/comments             | List all comments for an entry              | entryId                                         | ANY   |
| POST   | /api/v1/entries/{entryId}/comments             | Create a new comment for an entry           | entryId, title, email, comment                  | ANY   |
| GET    | /api/v1/entries/{entryId}/comments/{commentId} | Get a comment by comment ID and entry ID    | entryId, commentId                              | ANY   |
| PUT    | /api/v1/entries/{entryId}/comments/{commentId} | Update a comment by comment ID and entry ID | entryId, commentId, title, email, comment       | ANY   |
| DELETE | /api/v1/entries/{entryId}/comments/{commentId} | Delete a comment by comment ID and entry ID | entryId, commentId                              | ANY   |

## License

MIT License

Copyright (c) 2023 Leonel Zeballos

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
