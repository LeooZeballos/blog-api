# Blog API Rest

[![Open in Visual Studio Code](https://img.shields.io/badge/Open%20in-Visual%20Studio%20Code-blue?logo=visual-studio-code)](https://open.vscode.dev/LeooZeballos/blog-api) [![GitHub issues](https://img.shields.io/github/issues/LeooZeballos/blog-api)](https://github.com/LeooZeballos/blog-api/issues) [![GitHub forks](https://img.shields.io/github/forks/LeooZeballos/blog-api)](https://github.com/LeooZeballos/blog-api/network) [![GitHub stars](https://img.shields.io/github/stars/LeooZeballos/blog-api)](https://github.com/LeooZeballos/blog-api/stargazers) [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Table of contents
* [General info](#General-info)
* [How to Install and Run the Project](#How-to-Install-and-Run-the-Project)
* [Technologies](#Technologies)
* [Features](#Features)
* [License](#License)

## General info

API REST en Spring Boot con JWT, Spring Security, PostgreSQL y Data JPA

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

| Endpoint                              | Method | Description                            | Parameters                          | Role  |
|---------------------------------------|--------|----------------------------------------|-------------------------------------|-------|
| /api/v1/auth/login                    | POST   | Login to get a bearer token            | usernameOrEmail, password           | ANY   |
| /api/v1/auth/register                 | POST   | Register a user                        | name, username, email, password     | ANY   |
| /api/v1/entries                       | GET    | List all entries                       | pageNumber, pageSize, sortBy, sortDir| ANY   |
| ^                                     | POST   | Create a new entry                     | title, description, content         | ADMIN |
| /api/v1/entries/{entryId}             | GET    | Get an entry by ID                     |                                     | ANY   |
| ^                                     | PUT    | Update an entry by ID                  | title, description, content         | ADMIN |
| ^                                     | DELETE | Delete an entry by ID                  |                                     | ADMIN |
| /api/v1/entries/{entryId}/comments    | GET    | List all comments for an entry         |                                     | ANY   |
| ^                                     | POST   | Create a new comment for an entry      | title, email, comment               | ANY   |
| /api/v1/entries/{entryId} /comments/{commentId} | GET    | Get a comment by comment ID and entry ID |                                     | ANY   |
| ^                                     | PUT    | Update a comment by comment ID and entry ID | title, email, comment               | ANY   |
| ^                                     | DELETE | Delete a comment by comment ID and entry ID |                                     | ANY   |

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
