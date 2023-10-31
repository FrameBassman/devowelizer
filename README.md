###  Devowelizer test tool
#### How to run (unix)

- Preinstall
  - Docker
  - Docker Compose version v2.21.0
  - Java 11
  - Gradle 7
  - Make

```bash
make
```

#### How to get a test report
The test report is located in {project-dir}/devowelizer/app/build/reports/tests/runTests/index.html

#### Key points

- A one-line command is required for the project execution
- It is easy to extend the solution due to dependency injection framework
- It is easy to wrap the solution in a Docker container
- The solution is divided into following suites:
  - Smoke tests
  - Functional tests
  - Load tests
- There is a hamcrest library - it is easy to create a custom matcher logic
- There is an AssertWithTimeout logic to catch async or unstable behavior
- The solution includes a logger for the improve debug process
