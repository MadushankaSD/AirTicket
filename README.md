# ✈️ NextFlight - Air Ticketing System 
# GSCOMP280_JJMM_KUMARA

NextFlight is a web-based air ticketing system built with Java (Spring Boot + JSP). It allows users to search and book flights, while providing admins with tools to manage flights, users, bookings, reports, and more.

---

## 🛠️ Tech Stack

- Java 17+
- Spring Boot (MVC, Data JPA)
- JSP + JSTL
- MySQL / PostgreSQL
- Bootstrap 5
- REST API (for selected operations)
- Hibernate ORM

---

## 📦 Modules

### ✅ User Features
- Register/Login
- Search Flights (Direct & Transit)
- View Booking History
- Book Flights

### 🛡️ Admin Features
- Dashboard
- Manage Users (Create, Edit, Enable/Disable)
- Manage Airports & Flights
- View Bookings
- Passenger Manifest (Seat list per flight)
- Operational Reports (Revenue, Performance)

---

## 📁 Project Structure

```properties
src/
├── main/
│ ├── java/com/sl/nextflight/
| │ ├── config/ # Spring Configuration
│ │ ├── controller/ # Web & API Controllers
│ │ ├── entity/ # JPA Entities
│ │ ├── repository/ # Spring Data Repositories
│ │ ├── service/ # Business Logic
│ │ └── dto/ # Data Transfer Objects
│ └── resources/
│   └── application.properties
│ └── webapp/
│   └── resources
│   │     ├── css/ # CSS, JS, Images
│   │     ├── images/ # JSP Pages
│   └── WEB-INF
│         ├── admin/ # JSP Pages
│         ├── common/ # JSP Pages
│         ├── home/ # JSP Pages
│         ├── manager/ # JSP Pages
│         ├── user/ # JSP Pages
└── test/

```


---
| Method | URL                         | Description                  |
| ------ | --------------------------- | ---------------------------- |
| GET    | `/admin/users-management`   | Admin User View Page         |
| POST   | `/admin/users/save`         | Save user via form (AJAX)    |
| GET    | `/admin/users/edit/{id}`    | Edit specific user           |
| GET    | `/admin/bookings/search`    | Find bookings by ID or name  |
| GET    | `/admin/passenger-manifest` | Static flight seat list view |
| GET    | `/admin/reports`            | Revenue and flight summaries |



## ⚙️ Configuration

🔐 Admin Access
Login with default admin credentials:

makefile
Copy
Edit
Username: admin
Password: admin123

### `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/nextflight
spring.datasource.username=root
spring.datasource.password=your_password

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

server.port=8080

```
✍️ Author
Madushanka Kumara |
Sri Lanka | 
LinkedIn







