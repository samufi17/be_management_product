# Backend Management Product

This is a backend management system for managing products. The application is built using Java and Spring Boot.

## Features

- Create, update, delete, and retrieve products.
- Approve or reject products.
- View all products or only pending products.

## API Endpoints

- `POST /products`: Create a new product.
- `GET /products`: Retrieve all products.
- `GET /products/pending`: Retrieve all pending products.
- `GET /products/{id}`: Retrieve a product by ID.
- `PUT /products/{id}`: Update a product by ID.
- `DELETE /products/{id}`: Delete a product by ID.
- `PUT /products/{id}/approve`: Approve a product by ID.
- `PUT /products/{id}/reject`: Reject a product by ID.

## Getting Started

### Prerequisites

- Java 11 or higher
- Maven

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/backend-management-product.git
   ```
2. Navigate to the project directory:
   ```bash
   cd backend-management-product
   ```
3. Build the project using Maven:
   ```bash
   ./mvnw clean install
   ```

### Running the Application

To run the application, use the following command:
```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## License

This project is licensed under the Apache License 2.0. See the [LICENSE](LICENSE) file for details.
