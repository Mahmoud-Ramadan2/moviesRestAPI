# Movie Review REST API

## Project Overview
A **Movie Review REST API** built with **Java Spring Boot & MySQL** that allows users to browse movies, submit reviews, and manage authentication securely.

## Features
- **JWT Authentication** for secure user login and role-based access control.
- **Global Exception Handling** for centralized error management.
- **Custom List Conversion** in MySQL using `@Convert`, storing lists as JSON in a `TEXT` column.
- **RESTful API Design** to fetch and manage movie reviews.
- **Spring Data JPA & MySQL** for efficient database management.
- **Data Transfer Objects (DTOs)** for clean data handling between layers.
- **Swagger UI for live API testing**
- **Postman Collection for manual testing**

  
## 📖 API Documentation

This project provides a well-structured backend API built with **Spring Boot**.  
To make API integration easier, we use **Swagger (OpenAPI)** for live documentation and **Postman** for manual API testing.

### 🔹 Swagger UI (Live API Docs)
The API is documented using **Swagger UI**, allowing developers to explore and test endpoints directly in the browser.
- **Access Swagger UI:**  
http://localhost:8080/swagger-ui.html

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/Mahmoud-Ramadan2/moviesRestAPI.git
   ```
2. Navigate to the project folder:
   ```sh
   cd moviesRestAPI
   ```
3. Configure the database in `application.properties`:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/movies_db
   spring.datasource.username=
   spring.datasource.password=your
   ```
4. Run the application:
   ```sh
   mvn spring-boot:run
   ```

## API Endpoints
| Method | Endpoint                 | Description |
|--------|--------------------------|-------------|
| POST   | `/api/auth/register`     | Register a new user |
| POST   | `/api/auth/login`        | Authenticate user and get JWT token |
| GET    | `/api/movies`            | Retrieve all movies |
| GET    | `/api/movies/{id}`       | Get details of a specific movie |
| POST   | `/api/reviews`           | Submit a review |
| GET    | `/api/movies/search`     | Search movies by title and release date |

## Technologies Used
- **Java Spring Boot**
- **Spring Security & JWT**
- **Spring Data JPA & MySQL**
- **DTO Pattern for Data Transfer**
- **Maven**

## Contribution
Feel free to fork this repository and submit pull requests to improve the project.

## License
This project is open-source and available under the MIT License.
