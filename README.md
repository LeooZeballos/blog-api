# Blog API Rest
API REST en Spring Boot con JWT, Spring Security, PostgreSQL y Data JPA

# Functionality
* Entries
* Comments

# API
## '/api/v1/auth'
### '/login'
POST - Login to get a bearer token. Accepts params usernameOrEmail, password.
### '/register'
POST - Register a user. Accepts params name, username, email, password.

## '/api/v1/entries'
GET - List all entries (accepts params: pageNumber {def=0}, pageSize {def=10}, sortBy {def=id} and sortDir {ASC or DESC}
POST - Accepts params title, description, content. Requires ADMIN role.

## '/api/v1/entries/{entryId}'
GET - List entry by id.
PUT - Accepts params title, description, content. Requires ADMIN role.
DELETE - Requires ADMIN role.

## '/api/v1/entries/{entryId}/comments'
GET - List all comments for entry.
POST - Accepts params title, email, comment.

## '/api/v1/entries/{entryId}/comments/{commentId}'
GET - List comment by commentId and entryId.
PUT - Accepts params title, email, comment.
DELETE - No ADMIN role required.
