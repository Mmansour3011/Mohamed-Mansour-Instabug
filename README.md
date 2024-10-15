# Selenium Automated Testing Project

## Overview

This project is an automated testing framework built using Selenium in Java. It focuses on validating user management scenarios, including logging in, adding/removing items from a shopping cart, and completing the checkout process on a demo e-commerce website.

## Features

- User login functionality
- Adding items to the shopping cart
- Removing items from the shopping cart
- Checkout process validation
- Sorting items by price

## Technologies Used

- Java
- Selenium WebDriver
- Cucumber
- TestNG
- WebDriverManager
- Log4j (for logging)

## Installation

1. **Clone the repository:**

    ```bash
    git clone https://github.com/Mmansour3011/Mohamed-Mansour-Instabug.git
    ```

2. **Navigate to the project directory:**

    ```bash
    cd Instabug_AutomationTask
    ```

3. **Install dependencies:**

    ```bash
    mvn install
    ```
## Usage

 **Run Tests:**
   
  Execute the Cucumber tests using Maven
  
    mvn test
    
  Test reports will be generated in the target/cucumber-reports directory.

## Step Definitions

The `StepDefinitions` class outlines the steps for user management scenarios:

- **Login**
  - Validate user login with username and password.

- **Add to Cart**
  - Checks if items can be added successfully to the cart.

- **Remove from Cart**
  - Ensures that items can be removed from the cart.
- **Checkout**
  -  Validates the checkout process and checks for required user information.
- **Sorting**
  -Tests the sorting functionality of items by price.
