# Address Book Backend API<hr>
This is a Java Spring Boot back-end application for **user registration** and **contact management**.  
It allows users to **sign up**, **sign in**, and **manage their personal address book** securely.
## Table of Contents

1. [Project Description](#project-description)
2. [Features](#features)
3. [Technologies Used](#technologies-used)

## Project Description

This project is a Java Spring Boot back-end API for user registration and contact management. It allows users to sign up, sign in, and manage their personal address book. Users can securely store and manage contacts with details like name, phone, email, and birthdate. The app includes JWT-based authentication, password hashing, and input validation for enhanced security.

## Features

### 1. **Authentication & Authorization**
- **Register a new user**  
 `POST /api/v1/auth/register`  
  Registers a new user and returns a basic success response.

**📝 Request Example:**
```json
{
  "email": "email@gmail.com",
  "password": "12345678",
  "contacts": [
    {
      "firstName": "your first name",
      "lastName": "your first name",
      "phoneNumber": "01234567899",
      "birthdate": "2003-01-02",
      "emailAddress": "email@gmail.com"
    }
  ]
}
```

**📝 Response Example:**
```json

{
"success": true,
"status": 200,
"data": "User registered successfully"
}
```


- **Login and receive JWT token**  
  `POST /api/v1/auth/login`  
  Authenticates user and returns a JWT token to access protected resources.

**📝 Request Example:**
```json
{
  "email": "mostafa@gmail.com",
  "password": "12345678"
}
```

**📝 Response Example:**
```json
{
  "success": true,
  "status": 200,
  "data": {
    "token": "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJDb3JlbGlhIiwic3ViIjoiOSIsImlhdCI6MTc0NDM4NTM5MywiZXhwIjoxNzQ0OTkwMTkzfQ.tWLzD0mcsLhi7pyfwWWINhz6MNZC5sclw2JaLjYuHDI",
    "user": {
      "id": 9,
      "email": "mostafa@gmail.com",
      "password": "$2a$10$cqHo2B4j5y9rdxJfFFsWkuQZ8EQsGiJCMChk8wY0TvGFjrYwu09v2",
      "contacts": [
        {
          "id": 1,
          "firstName": "mostafa",
          "lastName": "tarek",
          "phoneNumber": "01061082296",
          "emailAddress": "mostafa1@gmail.com",
          "birthdate": "2003-01-02",
          "userId": null
        },
        {
          "id": 2,
          "firstName": "mostafa",
          "lastName": "mostafa",
          "phoneNumber": "mostafa",
          "emailAddress": "mostafa2@gmail.com",
          "birthdate": "2020-01-01",
          "userId": null
        }
      ]
    }
  }
}
```

### 2. **User Contact Management**

- **Add a new contact**  
  `POST /api/v1/user-contacts`  
  Adds a contact for the currently authenticated user.

**📝 Request Example:**
```json
{
  "firstName": "mostafa",
  "lastName": "mostafa",
  "phoneNumber": "mostafa",
  "emailAddress": "mostafa2@gmail.com",
  "birthdate": "2020-01-01",
  "userId": 9
}
```

**📝 Response Example:**
```json
{
  "data": {
    "id": 2,
    "firstName": "mostafa",
    "lastName": "mostafa",
    "phoneNumber": "mostafa",
    "emailAddress": "mostafa2@gmail.com",
    "birthdate": "2020-01-01",
    "userId": null
  },
  "success": true,
  "status": 200
}
```

- **Get all contacts for a user (paginated)**  
  `POST /api/v1/user-contacts/user/{userId}`  
  Retrieves all contacts for the given user with optional search criteria.

**📝 Request Example:**
```json
{
  "pageNumber" : 0,
  "pageSize" : 1,
  "sortableColumn" : "firstName" // lastName,birthdate
}
```

**📝 Response Example:**
```json
{
  "success": true,
  "status": 200,
  "data": {
    "content": [
      {
        "id": 2,
        "firstName": "mostafa",
        "lastName": "mostafa",
        "phoneNumber": "mostafa",
        "emailAddress": "mostafa2@gmail.com",
        "birthdate": "2020-01-01",
        "userId": 9
      },
      {
        "id": 1,
        "firstName": "mostafa",
        "lastName": "tarek",
        "phoneNumber": "01061082296",
        "emailAddress": "mostafa1@gmail.com",
        "birthdate": "2003-01-02",
        "userId": 9
      }
    ],
    "pageable": {
      "pageNumber": 0,
      "pageSize": 10,
      "sort": {
        "sorted": true,
        "empty": false,
        "unsorted": false
      },
      "offset": 0,
      "paged": true,
      "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "first": true,
    "size": 10,
    "number": 0,
    "sort": {
      "sorted": true,
      "empty": false,
      "unsorted": false
    },
    "numberOfElements": 2,
    "empty": false
  }
}
```

- **Get a contact by ID**  
  `GET /api/v1/user-contacts/{contactId}`  
  Retrieves a specific contact by its unique ID.

**📝 Response Example:**
```json
 {
  "data": {
    "id": 2,
    "firstName": "mostafa",
    "lastName": "mostafa",
    "phoneNumber": "mostafa",
    "emailAddress": "mostafa2@gmail.com",
    "birthdate": "2020-01-01",
    "userId": 9
  },
  "success": true,
  "status": 200
}
```

- **Delete a contact**  
  `DELETE /api/v1/user-contacts/{contactId}`  
  Deletes a contact by ID.

**📝 Response Example:**
```json
{
"data": null,
"success": true,
"status": 200
}
```
## Technologies Used

- **Java 17+**
- **Spring Boot**
- **Spring Security (JWT)**
- **Spring Data JPA**
- **Lombok**
- **Hibernate Validator (Jakarta Validation)**
- **MySQL**
- **Maven**
