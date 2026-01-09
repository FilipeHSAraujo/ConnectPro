# ConnectPro

ConnectPro is an **open-source backend project** inspired by a professional networking platform similar to LinkedIn.  
The goal is to build a **well-structured and maintainable backend application**, applying **software engineering best practices**, **clean architecture**, and **RESTful APIs**.

---

## 📌 Project Overview

**ConnectPro** aims to serve as a foundation for a professional networking platform, allowing user management and future expansion into features such as connections, posts, and interactions.

The project also serves as a practical learning environment, with a strong focus on clean code, security, and open-source collaboration.

---

## 🛠️ Technologies Used

- **Java 8+**
- **Spring Boot**
- **Hibernate / JPA**
- **Maven**
- **RESTful APIs**
- **PostgreSQL** (production)
- **H2 Database** (development)
- **Git & GitHub**

---

## 🧱 Project Structure

The project follows a layered architecture:

- **controller**: Handles HTTP requests and responses  
- **service**: Contains business logic  
- **repository**: Manages data persistence using JPA  
- **model**: Domain entities and enums

## ⚙️ Features

- User registration
- User lookup by ID, username, or email
- List all users
- User deletion
- User role definition
- Foundation prepared for authentication and authorization

---

## 🔗 Main Endpoints

### Users

| Method | Endpoint | Description |
|------|--------|-------------|
| POST | `/users` | Create a user |
| GET | `/users/{id}` | Get user by ID |
| GET | `/users` | List all users |
| DELETE | `/users/{id}` | Delete user |

*(Endpoints may evolve as the project grows)*

---

## ▶️ How to Run the Project

### Prerequisites
- Java 8 or higher
- Maven
- Git

## 💬 Community & Support

Join our GitHub community discussions to share ideas, ask questions, or suggest improvements. Let’s build something great together!

[![Open Issues](https://img.shields.io/badge/Open%20Issues-blue?style=for-the-badge&logo=github)](https://github.com/FilipeHSAraujo/ConnectPro/issues)

Use the issues section to report bugs, suggest improvements, or ask questions about the project.

---

## 👥 Contributors

Special thanks to all the amazing people who have contributed to this project.

<a href="https://github.com/FilipeHSAraujo/ConnectPro/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=FilipeHSAraujo/ConnectPro" />
</a>



## 📄 License

This project is open source and available under the MIT License.
Feel free to use, modify, and distribute it for personal or commercial projects.




