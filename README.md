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

1. **Add Customer**
   - Endpoint: `POST /customers/add`

2. **Update Customer**
   - Endpoint: `PUT /customers/update/{customerId}`

3. **Delete Customer**
   - Endpoint: `DELETE /customers/delete/{customerId}`

4. **Add Product**
   - Endpoint: `POST /products/add`

5. **Update Product**
   - Endpoint: `PUT /products/update/{productId}`

6. **Delete Product**
   - Endpoint: `DELETE /products/delete/{productId}`

7. **Add Order**
   - Endpoint: `POST /orders/add`

8. **Delete Order**
   - Endpoint: `DELETE /orders/delete/{orderId}`

9. **Update Order Status**
   - Endpoint: `PUT /orders/update-status/{orderId}`

10. **List Customers**
    - Endpoint: `GET /customers`

11. **List Customers by Page Number**
    - Endpoint: `GET /customers/{pageno}`

12. **List Products**
    - Endpoint: `GET /products`

13. **List Products by Page Number**
    - Endpoint: `GET /products/{pageno}`

14. **Get Orders by Customer**
    - Endpoint: `GET /orders-by-customer/{customerId}`

15. **Get Orders by Status**
    - Endpoint: `GET /orders-by-status/{status}`

16. **List Products Ordered by Customer**
    - Endpoint: `GET /products/products-ordered-by-customer/{customerId}`

17. **Get Orders Between Dates**
    - Endpoint: `GET /orders/between-dates`

18. **Get Orders in Specified Order**
    - Endpoint: `GET /orders/inorder`

19. **Get Products with Given String**
    - Endpoint: `GET /products-with-string/{string}`

20. **Get Product Details**
    - Endpoint: `GET /products/product-details/{productId}`

21. **Get All Order Details**
    - Endpoint: `GET /orders/all-order-details/{orderId}`

22. **Get Order Items**
    - Endpoint: `GET /order-items`
   

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
