# 💰 Finance Dashboard System

## 📌 Project Overview

This is a Spring Boot REST API project for managing users and financial records with **Role-Based Access Control (RBAC)** and dashboard analytics.
The system allows different users (Admin, Analyst, Viewer) to interact with financial data based on their permissions.

---

## ⚙️ Tech Stack

* Java 17+
* Spring Boot
* Spring Security
* Spring Data JPA
* PostgreSQL
* Maven

---

## 👥 Roles & Permissions

| Role    | Access                                    |
| ------- | ----------------------------------------- |
| ADMIN   | Full access (Users + Records + Analytics) |
| ANALYST | Read records + Analytics                  |
| VIEWER  | Read-only access                          |

---

## 🚀 Features

### 👤 User Management

* Create User (Admin only)
* Get All Users
* Get User by ID
* Update User Status (Active/Inactive)
* Role-based access control

---

### 💰 Finance Records

* Add Income / Expense
* Update / Delete records
* View all records
* Filter records by:

  * Type (INCOME / EXPENSE)
  * Category
  * Date range

---

### 📊 Dashboard Analytics

* Total Income
* Total Expense
* Net Balance
* Category-wise summary
* Monthly trend analysis

---

## 🔐 Security

* Spring Security enabled
* Basic Authentication
* Role-based authorization using `@PreAuthorize`
* In-memory users for testing

---

## 🔑 Default Credentials

| Role    | Username | Password   |
| ------- | -------- | ---------- |
| ADMIN   | admin    | admin123   |
| ANALYST | analyst  | analyst123 |
| VIEWER  | viewer   | viewer123  |

---

## ▶️ How to Run

```bash
mvn clean install
mvn spring-boot:run
```

---

## 🌐 Base URL

```
http://localhost:8080
```

---

## 📮 API Endpoints

### 👤 User APIs

* POST `/users/createUser`
* GET `/users/getAll`
* GET `/users/{id}`
* PATCH `/users/{id}/status`

---

### 💰 Finance Record APIs

* POST `/records`
* GET `/records`
* GET `/records/{id}`
* PUT `/records/{id}`
* DELETE `/records/{id}`
* GET `/records/type/{type}`
* GET `/records/category/{category}`
* GET `/records/range?start=YYYY-MM-DD&end=YYYY-MM-DD`

---

### 📊 Dashboard APIs

* GET `/dashboard/summary`
* GET `/dashboard/total-income`
* GET `/dashboard/total-expense`
* GET `/dashboard/net-balance`
* GET `/dashboard/category-wise`
* GET `/dashboard/monthly-trend`

---

## ⚠️ Assumptions

* Roles are predefined (ADMIN, ANALYST, VIEWER)
* Authentication is handled using Basic Auth (for simplicity)
* Data persistence uses PostgreSQL
* No frontend included (backend-only system)

---

## 📌 Future Enhancements

* JWT Authentication
* Pagination & Sorting
* Swagger API Documentation
* Global Exception Handling
* Frontend Dashboard (React)

---

## 👩‍💻 Author

Sayali Chavan
Finance Dashboard Backend Project
