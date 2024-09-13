# Banking System API

This is a backend application that handles basic banking operations such as account management, transactions (deposits and withdrawals), and user management. The project is implemented using Spring Boot with a PostgreSQL database.

## Tech Stack

- **Java** (JDK 17)
- **Spring Boot** (version 3.0.0)
  - Spring Web
  - Spring Data JPA
  - Spring Boot Starter Test (for testing)
- **PostgreSQL** (for database management)
- **Maven** (for dependency management)
  
## Features

1. **User Management**  
   - Add, delete, and retrieve user details.
   - Each user can have multiple accounts.

2. **Account Management**  
   - Create accounts with associated account types (Savings, Checking, etc.).
   - Delete and retrieve account information.
   - Each account has an associated user and an account type.

3. **Transaction Management**  
   - Perform deposits and withdrawals.
   - Track transaction history for each account.
   - Validation for insufficient balance during withdrawal operations.

## Prerequisites

1. **Java** (JDK 17 or higher)
2. **PostgreSQL** installed and running
3. **Maven** for managing dependencies
4. **Postman** for API testing

## Setup

### Clone the repository

```bash
git clone https://github.com/your-repo/BankingSystem.git
cd BankingSystem
