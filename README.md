# 🏦 ATM Management System

A full-featured ATM Banking System developed using Java Swing and MySQL. 
Includes GUI screens for Login, Account Creation, Balance Check, Deposits, Withdrawals, 
Transactions History, and Editing User Details. Uses JDBC for database integration and 
follows clean OOP principles.

## ⭐ Overview

ATM Management System is a desktop-based banking simulation built with Java, Swing UI, and MySQL.
It provides real ATM-like functionalities such as:

- Account creation
- User login
- Deposits & withdrawals
- Balance check
- Transaction history
- Editing user data
- Secure PIN verification
- MySQL persistent storage
- Input validation & negative balance protection
- Clean and organized GUI layout

This project is ideal for learning Java OOP, Swing, Database Connectivity (JDBC), and real-world system design.

## 💡 Features
### 👤 User Account Features

- ✔ Create new account
- ✔ Login using ID + PIN
- ✔ Edit personal information
- ✔ PIN code validation
- ✔ Secure password input

### 💰 Banking Operations

- ✔ Deposit money
- ✔ Withdraw money
- ✔ Prevent withdrawing beyond available balance
- ✔ View current balance
- ✔ Real-time balance update

### 📄 Transactions

- ✔ Store each transaction (deposit/withdraw)
- ✔ Unique transaction IDs
- ✔ Date & time recording
- ✔ Transaction history table

### 🗄 Database Features

- ✔ MySQL database
- ✔ JDBC driver integration
- ✔ Users table
- ✔ Transactions table
- ✔ Auto-generated account ID & transaction ID

🎨 User Interface

- ✔ Clean Swing-based UI
- ✔ Custom styling (GUIConstants)
- ✔ Consistent typography
- ✔ Professional layout

## 🛠 Tech Stack

<p>
  <img src="https://img.shields.io/badge/Java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white"/>
  <img src="https://img.shields.io/badge/MySQL-%234479A1.svg?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img src="https://img.shields.io/badge/Swing-008FC7.svg?style=for-the-badge&logo=java&logoColor=white"/>
  <img src="https://img.shields.io/badge/JDBC-323330.svg?style=for-the-badge&logo=databricks&logoColor=white"/>
  <img src="https://img.shields.io/badge/Eclipse-2C2255.svg?style=for-the-badge&logo=eclipse&logoColor=white"/>
</p>

## 📸 Screenshots

<img width="455" alt="Screenshot (1161)" src="https://github.com/user-attachments/assets/84152a37-841b-4b70-a373-598da5362672" /> <img width="455" alt="Screenshot (1162)" src="https://github.com/user-attachments/assets/97bd0cb2-1f83-44e0-83d9-8144b1cc3a74" />
<img width="455" alt="Screenshot (1163)" src="https://github.com/user-attachments/assets/ee50aaa2-d84d-47c4-ad6c-fd7e38c6b2bc" /> <img width="455" alt="Screenshot (1164)" src="https://github.com/user-attachments/assets/5c59502c-01be-404e-b6d0-ab85f5fd37d4" />
<img width="455" alt="Screenshot (1165)" src="https://github.com/user-attachments/assets/9fa1aa6a-1719-47f5-8cfe-20a8b05b8fb8" /> <img width="455" alt="Screenshot (1166)" src="https://github.com/user-attachments/assets/6dc7fe01-271e-4a40-aa21-ad1dd920d018" />
<img width="455" alt="Screenshot (1167)" src="https://github.com/user-attachments/assets/193631a1-4452-483c-ada2-de67ab722bc1" />

## 🗂 Project Structure

```
ATM-Management-System/
│
├── src/
│   └── ATM/
│       ├── Balance.java
│       ├── Database.java
│       ├── GUIConstants.java
│       ├── List.java
│       ├── Login.java
│       ├── Main.java
│       ├── Operation.java
│       ├── Transaction.java
│       ├── Transactions.java
│       ├── TransactionsDatabase.java
│       ├── User.java
│       ├── UserData.java
│       └── UsersDatabase.java
│
├── module-info.java
│
├── Referenced Libraries/
│   └── mysql-connector-j-9.2.0.jar
│
├── README.md
├── .gitignore
└── LICENSE
```

## 🗄 Database Setup (MySQL)

### 1️⃣ Create Database
```
CREATE DATABASE IF NOT EXISTS atm;
USE atm;
```

### 2️⃣ Create Users Table
```
CREATE TABLE IF NOT EXISTS users (
    ID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    BirthDate VARCHAR(20),
    Email VARCHAR(100),
    PhoneNumber VARCHAR(20),
    PINCode INT,
    Balance DOUBLE
);
```
### 3️⃣ Create Transactions Table
```
CREATE TABLE IF NOT EXISTS transactions (
    ID INT PRIMARY KEY,
    Amount DOUBLE,
    DateTime VARCHAR(50),
    UserID INT,
    FOREIGN KEY (UserID) REFERENCES users(ID)
);
```
## ▶️ How to Run the Project

#### Step 1 — Install Requirements

- Install JDK 17+
- Install MySQL Server
- Install MySQL Workbench
- Install MySQL Connector/J (JDBC driver)

#### Step 2 — Add JDBC Driver

- Place: ``` mysql-connector-j-9.x.x.jar ``` in your project's library path.
- In Eclipse: ``` Right-click project → Build Path → Add External Archives → select the JAR ```

#### Step 3 — Configure Database Credentials

- Open Database.java:
```
private String user = "";   // or your DB username
private String pass = "";       // your password
private String url  = "jdbc:mysql://localhost:3306/atm";
```

#### Step 4 — Run Main Class

- Right-click Main.java → Run As → Java Application

## 📜 License

This project is licensed under the MIT License.
