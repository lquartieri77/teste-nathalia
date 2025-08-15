# Anagram Generator

[![Ask DeepWiki](https://devin.ai/assets/askdeepwiki.png)](https://deepwiki.com/lquartieri77/teste-nathalia)

This project is a Spring Boot web application that generates all possible letter combinations (anagrams) from a given set of distinct letters.

## Overview

The application exposes a single REST API endpoint that accepts a comma-separated string of letters. It then calculates all unique permutations of these letters and returns them as a list. To prevent potential memory issues with large inputs, the number of generated combinations is capped at 1000.

### Key Features

*   Generates anagrams from a string of comma-separated letters.
*   Validates input to ensure it contains only letters.
*   Rejects inputs with repeated letters.
*   Provides API documentation via Swagger UI.

## Technology Stack

*   **Java 17**
*   **Spring Boot 3.5.4**
*   **Spring Web**
*   **Maven**
*   **Springdoc OpenAPI (Swagger UI)**
*   **JUnit 5**

## API Endpoint

The service provides one primary endpoint for generating anagrams.

### `POST /api/anagramas`

Generates all possible combinations for the given letters.

#### Request Body

The request body should be a JSON object with a single `texto` field.

*   `texto`: A string containing single letters separated by commas.
    *   **Constraints:** Cannot be blank. Must contain only letters separated by commas (e.g., `a,b,c`). Duplicate letters are not allowed.

**Example Request:**

```json
{
  "texto": "a,b,c"
}
```

#### Success Response (200 OK)

Returns a JSON array of strings, where each string is a unique permutation of the input letters.

**Example Response:**

```json
[
    "abc",
    "acb",
    "bac",
    "bca",
    "cab",
    "cba"
]
```

#### Error Response (400 Bad Request)

If the input is invalid (e.g., contains numbers, repeated letters, or is improperly formatted), the API will return a 400 Bad Request with a descriptive error message.

**Example Error Response:**

```json
{
    "type": "https://httpstatuses.com/400",
    "title": "Erro de validação",
    "status": 400,
    "detail": "Texto deve conter apenas letras separadas por vírgula.",
    "instance": "/api/anagramas"
}
```

## Getting Started

### Prerequisites

*   Java Development Kit (JDK) 17 or later
*   Apache Maven

### Running the Application

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/lquartieri77/teste-nathalia.git
    cd teste-nathalia
    ```

2.  **Build the project using Maven:**
    ```bash
    mvn clean install
    ```

3.  **Run the application:**
    ```bash
    java -jar target/Anagramas-teste-0.0.1-SNAPSHOT.jar
    ```

The application will start on the default port `8080`.

### API Documentation

Once the application is running, you can access the interactive Swagger UI for API documentation and testing at:

[http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Running Tests

To run the unit tests for the service, execute the following Maven command:

```bash
mvn test