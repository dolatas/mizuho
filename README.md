# Mizuho - Limit Book Order (Java + Spring)
This repository contains a sample project with solution for the technical task assigned by Mizuho. The task was to implement a limit book order, and I was given the freedom to choose the approach I see fit. As a result, I prepared two solutions: one using SpringBoot and Java 17 (this repository), and another in pure Java (separate repository).

The presented microservice has a fully functional REST API for each functionality described in the task. However, this is a showcase app and not a production-ready solution. Therefore, I made some decisions regarding the implementation approach:

- H2 in-memory database: For simplicity and local testing, I used an H2 in-memory database. However, for production environments, I would switch to RDS such as MySQL or PostgreSQL.
- Unit test coverage: While usually I aim for more than 80% test coverage, I decided to write only some unit tests in this case. However, they showcase my way of implementing unit tests. If you require better coverage, please let me know.
- Integration tests: I provided only unit tests, but normally I would also create integration tests. For instance, I would create tests for the repository layer with the use of TestContainers.

Please feel free to explore the project and provide your feedback.
