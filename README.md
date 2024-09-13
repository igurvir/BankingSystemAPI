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






# API Endpoints

## User Management

### Create a New User

**POST** `/api/users`

**Request Body:**

```json
{
  "name": "Jane Doe",
  "email": "jane@example.com",
  "password": "password123"
}
Response:
Status: 200 OK

Example:

json
Copy code
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane@example.com",
  "accounts": null
}
``` 
### Get All Users
***GET***  `/api/users`
```
Response:
Status: 200 OK

Example:

json
Copy code
[
  {
    "id": 1,
    "name": "Jane Doe",
    "email": "jane@example.com",
    "accounts": []
  }
]
```
 
### Get a Specific User
***GET***  `/api/users/{id}`
```
Response:
Status: 200 OK

Example:

json
Copy code
{
  "id": 1,
  "name": "Jane Doe",
  "email": "jane@example.com",
  "accounts": []
}
```
### Account Management

### Create a New Account for a User
***POST***  `/api/accounts/{userId}/{accountTypeId}`
```
Request Body:

json
Copy code
{
  "balance": 1000.0
}
Response:
Status: 200 OK

Example:

json
Copy code
{
  "id": 1,
  "balance": 1000.0,
  "accountType": {
    "id": 1,
    "typeName": "Savings",
    "interestRate": 0.02
  }
}
```
### Get All Accounts
 ***GET***  `/api/accounts`
```
Response:
Status: 200 OK

Example:

json
Copy code
[
  {
    "id": 1,
    "balance": 1000.0,
    "accountType": {
      "id": 1,
      "typeName": "Savings",
      "interestRate": 0.02
    }
  }
]
```
### Get a Specific Account
***GET*** `/api/accounts/{id}`
```
Response:
Status: 200 OK

Example:

json
Copy code
{
  "id": 1,
  "balance": 1000.0,
  "accountType": {
    "id": 1,
    "typeName": "Savings",
    "interestRate": 0.02
  }
}
```
### Transaction Management

### Make a Deposit/Withdrawal
***POST***  `/api/transactions/{accountId}`
```
Request Body:

json
Copy code
{
  "transactionType": "deposit",
  "amount": 500.0
}
Response:
Status: 200 OK

Example:

json
Copy code
{
  "id": 1,
  "transactionType": "deposit",
  "amount": 500.0,
  "transactionDate": "2024-09-12T17:52:48.346483",
  "account": {
    "id": 1,
    "balance": 1500.0,
    "accountType": {
      "id": 1,
      "typeName": "Savings",
      "interestRate": 0.02
    }
  }
}
```
### Get Transaction History
***GET*** `/api/transactions/{accountId}`
```
Response:
Status: 200 OK

Example:

json
Copy code
[
  {
    "id": 1,
    "transactionType": "deposit",
    "amount": 500.0,
    "transactionDate": "2024-09-12T17:52:48.346483",
    "account": {
      "id": 1,
      "balance": 1500.0,
      "accountType": {
        "id": 1,
        "typeName": "Savings",
        "interestRate": 0.02
      }
    }
  }
]
```
### Running Tests

To run the tests, use:

bash
Copy code
mvn test


### Clone the repository

```bash
git clone https://github.com/your-repo/BankingSystem.git
cd BankingSystem
```

## Connect with Me on LinkedIn
[![LinkedIn](https://img.shields.io/badge/LinkedIn-Connect-blue)](https://www.linkedin.com/in/gurvir-singh-3a98a6255/)
