# Mini Project Spring Boot - Gym Management System

A comprehensive Spring Boot application for managing a gym or fitness center. This system provides a web interface for administrators to manage members and subscription plans, along with a REST API for handling subscriptions and training sessions.

## 📋 Features

### Web Interface (Thymeleaf + Tailwind CSS)
* **Member Management:**
    * Create, Read, Update, and Delete (CRUD) members.
    * Upload and manage profile pictures.
    * Search members by phone number.
    * Pagination support for member lists.
* **Subscription Plans:**
    * Manage different tiers of subscription plans (e.g., Gold, Silver).
    * Set monthly fees and view plan details.

### REST API
* **Session Management:**
    * Create, view, update, and delete training sessions.
    * Link sessions to specific subscription plans.
* **Subscription Operations:**
    * Subscribe members to plans.
    * Calculate total payments automatically based on duration.
    * Manage active subscriptions.

## 🛠 Technology Stack

* **Backend:** Java 17, Spring Boot 3.4.3
* **Database:** MySQL
* **ORM:** Hibernate / Spring Data JPA
* **Frontend:** Thymeleaf, Tailwind CSS
* **API Documentation:** SpringDoc OpenAPI (Swagger UI)
* **Tools:** Maven, Lombok

## 🚀 Getting Started

### Prerequisites
* Java 17 or higher
* MySQL Server

### Configuration
1.  **Database Setup:**
    The application is configured to use a MySQL database named `bd_gym`. It will automatically create the database if it doesn't exist.
    
    Check `src/main/resources/application.properties` to ensure your credentials match:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost/bd_gym?createDatabaseIfNotExist=true&serverTimezone=UTC
    spring.datasource.username=root
    spring.datasource.password=
    ```
   

2.  **File Uploads:**
    Images are uploaded to `src/main/resources/static/uploads`. The application automatically creates this directory if missing.

### Installation & Running
1.  Clone the repository:
    ```bash
    git clone [https://github.com/your-username/mini_project_spring_boot.git](https://github.com/your-username/mini_project_spring_boot.git)
    cd mini_project_spring_boot
    ```

2.  Run the application using Maven Wrapper:
    * **Windows:**
        ```cmd
        mvnw.cmd spring-boot:run
        ```
    * **Linux/Mac:**
        ```bash
        ./mvnw spring-boot:run
        ```

3.  Access the application:
    * **Home/Members List:** `http://localhost:8080/members`
    * **Swagger API Docs:** `http://localhost:8080/swagger-ui/index.html`

## 🔌 API Endpoints

### Sessions (`/api/sessions`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/sessions` | List all sessions |
| `GET` | `/api/sessions/{id}` | Get session details |
| `POST` | `/api/sessions` | Create a new session |
| `PUT` | `/api/sessions/{id}` | Update a session |
| `DELETE` | `/api/sessions/{id}` | Delete a session |

### Subscriptions (`/api/subscription`)
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/api/subscription` | Create a subscription |
| `GET` | `/api/subscription/{memberId}/{planId}` | Get subscription details |
| `PUT` | `/api/subscription/{memberId}/{planId}` | Update a subscription |
| `DELETE` | `/api/subscription/{memberId}/{planId}` | Delete a subscription |

## 📂 Project Structure

* `src/main/java/org/example/mini_project_spring_boot`
    * `config`: Web and upload configurations.
    * `controller`: MVC and REST controllers.
    * `entities`: Database models (Member, Session, Subscription, etc.).
    * `repository`: Data access layers.
    * `service`: Business logic interfaces and implementations.
