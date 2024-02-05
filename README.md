# Orders Tracking System

## Overview

The Orders Tracking System is a Spring Boot REST API backend application designed to manage customer orders. This system leverages the power of MS SQL Server for data storage and integrates various technologies including Java, Spring Boot, Spring Data, Spring REST, Spring Security, Postman API for testing, and Swagger API for documentation. Key entities such as Customer, Products, Orders (linked to customer ID), and Order Items (containing product ID, customer ID, quantity, and total price) are seamlessly handled to enhance order-related processes.

## Features

- **Customer Management:** Easily handle customer information.
- **Product Management:** Maintain a comprehensive product database.
- **Order Tracking:** Efficiently track orders, associating them with respective customers.
- **Order Items:** Detailed information on each order item, including product ID, customer ID, quantity, and total price.

## Setup

1. Clone the repository.
   ```bash
   git clone https://github.com/your-username/orders-tracking-system.git
   ```

2. Navigate to the project directory.
   ```bash
   cd orders-tracking-system
   ```

3. Configure your MS SQL Server connection in the `application.properties` file.

4. Build and run the application.
   ```bash
   ./mvnw spring-boot:run
   ```

## API Endpoints

- **Customers:**
  - `GET /api/customers`: Retrieve all customers.
  - `GET /api/customers/{id}`: Retrieve customer by ID.
  - ...

- **Products:**
  - `GET /api/products`: Retrieve all products.
  - `GET /api/products/{id}`: Retrieve product by ID.
  - ...

- **Orders:**
  - `GET /api/orders`: Retrieve all orders.
  - `GET /api/orders/{id}`: Retrieve order by ID.
  - ...

- **Order Items:**
  - `GET /api/order-items`: Retrieve all order items.
  - `GET /api/order-items/{id}`: Retrieve order item by ID.
  - ...

## Technologies Used

- **Java:** Programming language.
- **Spring Boot:** Framework for building Java-based applications.
- **Spring Data:** Access and persistence framework.
- **Spring REST:** Facilitates building RESTful APIs.
- **Spring Security:** Ensures secure authentication and authorization.
- **MS SQL Server:** Relational database management system.
- **Postman API:** Used for testing APIs.
- **Swagger API:** Provides API documentation.

## Contribution

Feel free to contribute to enhance the functionality or fix any issues. Follow these steps to contribute:

1. Fork the project.
2. Create your feature branch: `git checkout -b feature/YourFeature`.
3. Commit your changes: `git commit -m 'Add YourFeature'`.
4. Push to the branch: `git push origin feature/YourFeature`.
5. Open a pull request.
