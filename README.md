# 🏦 Java Banking System

A full-stack banking application built with **Java Spring Boot** (Backend) and **HTML/CSS/JavaScript** (Frontend). This project simulates core banking operations including account creation, secure login, fund transfers, and balance management.

![Project Status](https://img.shields.io/badge/status-active-success.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.x-green.svg)

## 🌟 Features

* **User Account Management:** Create Savings or Current accounts with validation.
* **Secure Dashboard:** Users must log in with their Account Number to view details.
* **Banking Operations:**
    * 💰 **Deposit & Withdraw:** Real-time balance updates.
    * 💸 **Fund Transfer:** Send money securely between different accounts.
    * ❌ **Delete Account:** Option to close an account permanently.
* **Safety Checks:** Prevents overdrafts and ensures valid transfer targets.
* **Responsive UI:** Modern, clean interface using CSS Grid and Flexbox.

## 🛠️ Tech Stack

* **Backend:** Java 17, Spring Boot (Web, REST API)
* **Frontend:** HTML5, CSS3, Vanilla JavaScript (Fetch API)
* **Architecture:** MVC (Model-View-Controller) pattern
* **Data Storage:** In-Memory (`ArrayList`) for demonstration purposes.

## 🚀 Getting Started

### Prerequisites
* Java Development Kit (JDK) 17 or later.
* Maven.

### Installation & Running
1.  **Clone the repository**
    ```bash
    git clone [https://github.com/yourusername/java-banking-system.git](https://github.com/yourusername/java-banking-system.git)
    ```
2.  **Navigate to the project directory**
    ```bash
    cd java-banking-system
    ```
3.  **Run the Application**
    You can run it via your IDE (IntelliJ/Eclipse) or command line:
    ```bash
    mvn spring-boot:run
    ```
4.  **Access the App**
    Open your browser and visit:
    `http://localhost:8080`

## 📂 Project Structure
  src ├── main │ ├── java │ │ └── com.example.banksystem │ │ ├── controller # REST API Endpoints (BankController) │ │ ├── model # Data Classes (Account, SavAccount, CurAccount) │ │ └── service # Business Logic (BankService) │ └── resources │ └── static # Frontend Files (index.html, style.css, script.js)
