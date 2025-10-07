# Joules Test - Serenity BDD Petstore API Tests

This project contains automated acceptance tests for the Petstore API using Serenity BDD framework with Cucumber BDD approach. The tests validate various operations on pets, orders, and store inventory through REST API endpoints.

## Project Overview

The test suite covers comprehensive API testing scenarios including:

- **Pet Operations**: Adding, updating, retrieving, and deleting pets
- **Store Operations**: Order management and inventory retrieval
- **Search Functionality**: Definition lookup capabilities

## Technology Stack

- **Java 8**: Programming language
- **Maven**: Build tool and dependency management
- **Serenity BDD**: Test framework for better reporting
- **Cucumber**: BDD framework for executable specifications
- **Rest Assured**: REST API testing library
- **JUnit**: Unit testing framework
- **Jackson**: JSON processing library

## Dependencies

Major dependencies include:
- Serenity Core v2.3.2
- Serenity Cucumber v2.3.2
- Serenity Rest Assured v2.3.2
- Rest Assured v4.3.3
- Cucumber JVM v7.11.2
- Jackson Databind v2.13.3

## Project Structure

```
src/
├── test/
│   ├── java/
│   │   └── com/velicans/
│   │       ├── models/          # Data model classes (Pet, Order, User, etc.)
│   │       ├── steps/
│   │       │   ├── pet/         # Pet-related step definitions
│   │       │   └── store/       # Store-related step definitions
│   │       └── pages/           # Page objects for UI interactions
│   └── resources/
│       └── features/
│           ├── pet/             # Pet operation scenarios
│           ├── store/           # Store operation scenarios
│           └── search/          # Search functionality scenarios
```

## Test Scenarios

### Pet Operations (`pet_operations.feature`)

- Add new pets (valid and invalid inputs)
- Update existing pets with full data and form data
- Retrieve pets by ID (successful and error cases)
- Find pets by status
- Delete pets (successful and error cases)
- Upload images for pets

### Store Operations (`store_operations.feature`)

- Get store inventory
- Place orders (valid and invalid)
- Retrieve orders by ID
- Delete orders (successful and error cases)

### Search Functionality (`search.feature`)

- Word definition lookup functionality

## Running the Tests

### Prerequisites

- Java 8 or higher
- Maven 3.x

### Execute Tests

Run all tests:
```bash
mvn clean verify
```

Run tests with Serenity reports:
```bash
mvn clean verify serenity:aggregate
```

Run specific test suite:
```bash
mvn test -Dtest=*Test
```

### Test Reports

Serenity generates comprehensive HTML reports in `target/site/serenity/` directory after test execution. Reports include:
- Test execution summary
- Step-by-step test results
- Screenshots (if any UI interactions)
- REST API request/response details

## Configuration

### Serenity Properties

The `serenity.properties` file contains configuration settings for:
- Chrome driver settings
- Base URL for the API
- Report generation settings

### Maven Surefire Plugin

Tests are configured to run during the `integration-test` phase with:
- Memory allocation: -Xmx512m
- Parallel execution capabilities

## API Under Test

This project tests the Swagger Petstore API (v2) endpoints:
- Base URL: `https://petstore.swagger.io/v2`
- Authentication: API key and OAuth2 supported
- Complete API specification available in `swagger.json`

## Development

### Adding New Tests

1. Create feature file in `src/test/resources/features/`
2. Implement step definitions in appropriate steps package
3. Add model classes if needed in `models/` package
4. Run tests and verify reports

### Best Practices

- Use descriptive scenario names
- Parameterize scenarios where possible
- Include both positive and negative test cases
- Maintain clear separation between test layers

## Contributing

1. Fork the repository
2. Create a feature branch
3. Add tests and implementation
4. Run full test suite
5. Submit a pull request

## License

This project is licensed under the Apache License 2.0.
