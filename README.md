# Mini Amazon

Mini Amazon is a simple e-commerce platform built with Java. This project simulates an online shopping experience where users can browse,and make orders.

## Features
- User profile management
- Product management
- Order management
- Product listing and search functionality
- Shopping cart to add and remove items

## Technologies Used
- **Java**: Core language used for backend logic.
- **Maven**: For dependency management and project build.
- **Spring Boot**: To create a web application.

## Setup

### Prerequisites
- Java 8 or higher
- Maven 3.6+
- Git

### Clone the repository
```bash
git clone https://github.com/MohamedYehia20m/mini-amazon.git
```

### Build the project
```bash
mvn clean install
```

### Run the project
```bash
mvn spring-boot:run
```

## Usage
#### API Endpoints 
#### UserController

##### Get All Users
- **URL:** `/api/users`
- **Method:** `GET`
- **Description:** Retrieves a list of all users.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON array of user objects.

##### Get User by ID
- **URL:** `/api/users/{id}`
- **Method:** `GET`
- **Description:** Retrieves a user by their ID.
- **Path Parameters:**
    - `id` (Long): The ID of the user.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the user.

##### Create User
- **URL:** `/api/users`
- **Method:** `POST`
- **Description:** Creates a new user.
- **Request Body:** JSON object of the user to be created.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the created user.

##### Update User
- **URL:** `/api/users/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing user.
- **Path Parameters:**
    - `id` (Long): The ID of the user to be updated.
- **Request Body:** JSON object of the user with updated information.
- **Response:**
    - **Status:** `200 OK` if the user is updated successfully.
    - **Status:** `404 Not Found` if the user is not found.
    - **Body:** JSON object of the updated user.

##### Delete User
- **URL:** `/api/users/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a user by their ID.
- **Path Parameters:**
    - `id` (Long): The ID of the user to be deleted.
- **Response:**
    - **Status:** `204 No Content` if the user is deleted successfully.
    - **Status:** `404 Not Found` if the user is not found.

#### ProductController

##### Get All Products
- **URL:** `/api/products`
- **Method:** `GET`
- **Description:** Retrieves a list of all products.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON array of product objects.

##### Get Product by ID
- **URL:** `/api/products/{id}`
- **Method:** `GET`
- **Description:** Retrieves a product by its ID.
- **Path Parameters:**
    - `id` (Long): The ID of the product.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the product.

##### Add Product
- **URL:** `/api/products`
- **Method:** `POST`
- **Description:** Adds a new product.
- **Request Body:** JSON object of the product to be added.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the added product.

##### Update Product
- **URL:** `/api/products/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing product.
- **Path Parameters:**
    - `id` (Long): The ID of the product to be updated.
- **Request Body:** JSON object of the product with updated information.
- **Response:**
    - **Status:** `200 OK` if the product is updated successfully.
    - **Status:** `404 Not Found` if the product is not found.
    - **Body:** JSON object of the updated product.

##### Delete Product
- **URL:** `/api/products/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a product by its ID.
- **Path Parameters:**
    - `id` (Long): The ID of the product to be deleted.
- **Response:**
    - **Status:** `204 No Content` if the product is deleted successfully.
    - **Status:** `404 Not Found` if the product is not found.

#### ProductController

##### Get All Products
- **URL:** `/api/products`
- **Method:** `GET`
- **Description:** Retrieves a list of all products.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON array of product objects.

##### Get Product by ID
- **URL:** `/api/products/{id}`
- **Method:** `GET`
- **Description:** Retrieves a product by its ID.
- **Path Parameters:**
    - `id` (Long): The ID of the product.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the product.

##### Add Product
- **URL:** `/api/products`
- **Method:** `POST`
- **Description:** Adds a new product.
- **Request Body:** JSON object of the product to be added.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the added product.

##### Update Product
- **URL:** `/api/products/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing product.
- **Path Parameters:**
    - `id` (Long): The ID of the product to be updated.
- **Request Body:** JSON object of the product with updated information.
- **Response:**
    - **Status:** `200 OK` if the product is updated successfully.
    - **Status:** `404 Not Found` if the product is not found.
    - **Body:** JSON object of the updated product.

##### Delete Product
- **URL:** `/api/products/{id}`
- **Method:** `DELETE`
- **Description:** Deletes a product by its ID.
- **Path Parameters:**
    - `id` (Long): The ID of the product to be deleted.
- **Response:**
    - **Status:** `204 No Content` if the product is deleted successfully.
    - **Status:** `404 Not Found` if the product is not found.

#### OrderController

##### Get All Orders
- **URL:** `/api/orders`
- **Method:** `GET`
- **Description:** Retrieves a list of all orders.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON array of order objects.

##### Get Order by ID
- **URL:** `/api/orders/{id}`
- **Method:** `GET`
- **Description:** Retrieves an order by its ID.
- **Path Parameters:**
    - `id` (Long): The ID of the order.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the order.

##### Add Order
- **URL:** `/api/orders`
- **Method:** `POST`
- **Description:** Adds a new order.
- **Request Body:** JSON object of the order to be added.
- **Response:**
    - **Status:** `200 OK`
    - **Body:** JSON object of the added order.

##### Update Order
- **URL:** `/api/orders/{id}`
- **Method:** `PUT`
- **Description:** Updates an existing order.
- **Path Parameters:**
    - `id` (Long): The ID of the order to be updated.
- **Request Body:** JSON object of the order with updated information.
- **Response:**
    - **Status:** `200 OK` if the order is updated successfully.
    - **Status:** `404 Not Found` if the order is not found.
    - **Body:** JSON object of the updated order.

##### Delete Order
- **URL:** `/api/orders/{id}`
- **Method:** `DELETE`
- **Description:** Deletes an order by its ID.
- **Path Parameters:**
    - `id` (Long): The ID of the order to be deleted.
- **Response:**
    - **Status:** `204 No Content` if the order is deleted successfully.
    - **Status:** `404 Not Found` if the order is not found.