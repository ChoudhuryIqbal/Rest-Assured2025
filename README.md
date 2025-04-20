
# Rest-Assured2025

📦 A modern REST API automation testing framework using **Rest-Assured**, **TestNG**, **ExtentReports**, and **Java 17+**.

---

## 📚 Overview

This project is a sample framework built with the following goals in mind:

- REST API testing using [Rest-Assured](https://rest-assured.io/)
- Data-driven testing support
- Reporting via [ExtentReports](https://extentreports.com/)
- Easy integration with Jenkins and CI/CD pipelines
- Structured Maven project for scalability

---

## 🚀 Technologies Used

| Tool/Library         | Version    |
|----------------------|------------|
| Java                 | 17+        |
| Maven                | Latest     |
| Rest-Assured         | 5.4.0      |
| TestNG               | 7.10.1     |
| ExtentReports        | 5.0.9      |
| Apache POI (Excel)   | 5.2.3+     |
| JavaFaker (Mock Data)| 1.0.2      |
| Log4j2               | 2.24.3     |

---

## 🛠️ Project Structure

```
Rest-Assured2025/
├── src/
│   ├── main/
│   │   └── java/        → Core utilities (Excel, Config, Base setup)
│   └── test/
│       └── java/        → Test classes using Rest-Assured + TestNG
├── testng.xml          → TestNG suite file
├── pom.xml             → Maven config with dependencies
└── README.md
```

---

## ⚙️ How to Run Tests Locally

> **Pre-requisite**: Java 17+ and Maven installed

```bash
# Clone the repo
git clone https://github.com/ChoudhuryIqbal/Rest-Assured2025.git
cd Rest-Assured2025

# Run tests
mvn clean test
```

📝 Reports will be generated in the `test-output` folder or a custom ExtentReports path (if configured).

---

## 🧪 Running with Jenkins

1. **Install Jenkins locally**
2. **Configure JDK in Jenkins**
   - Go to **Manage Jenkins → Global Tool Configuration**
   - Under **JDK**, add a JDK with:
     - Name: `jdk-21` or any alias
     - Uncheck "Install automatically"
     - Java Home: `C:\Program Files\Java\jdk-21`
3. **Create a new Freestyle project**
4. **Source Code Management** → Git
   - Repo URL: `https://github.com/ChoudhuryIqbal/Rest-Assured2025.git`
5. **Build Environment**
   - Check: "Use specific JDK" → Choose the one you configured
6. **Build Steps**
   - Add build step: `Invoke top-level Maven targets`
   - Goal: `clean test`
7. (Optional) Add Post-build Actions
   - **Archive the HTML report**
   - **Publish JUnit test result report**

---

## 📁 Test Data and Reporting

- **Excel Integration:** Apache POI is used for reading/writing Excel test data
- **Logging:** Configured using Log4j2
- **Reports:** Beautiful HTML reports generated via ExtentReports

---

## 📦 Dependency Management

Dependencies are managed in the `pom.xml`. Major ones include:

- `rest-assured`
- `testng`
- `extentreports`
- `poi` (Excel support)
- `json`
- `log4j-api` and `log4j-core`

Run the following to inspect dependencies:

```bash
mvn dependency:tree
```

---

## 🔐 Common Issues

### ❌ Java Version Errors

If you encounter:
```
Fatal error compiling: invalid target release: 17
```
Make sure your environment is using Java 17+ and the `pom.xml` has:

```xml
<properties>
  <maven.compiler.source>17</maven.compiler.source>
  <maven.compiler.target>17</maven.compiler.target>
</properties>
```

### ❌ Lombok Errors

If you see errors like:
```
LombokProcessor cannot access JavacProcessingEnvironment
```
But you’re **not using Lombok**, it may be coming from another dependency (e.g., ExtentReports). Exclude it like:

```xml
<dependency>
  <groupId>com.aventstack</groupId>
  <artifactId>extentreports</artifactId>
  <version>5.0.9</version>
  <exclusions>
    <exclusion>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
    </exclusion>
  </exclusions>
</dependency>
```

---

## 📬 Contributions

Feel free to fork the repo, improve the framework, and raise PRs! Contributions are welcome 😊

---

## 📄 License

This project is created for educational and demo purposes. No license currently applied.

---

## 🙋‍♂️ Author

**Choudhury Iqbal**  
🔗 [GitHub Profile](https://github.com/ChoudhuryIqbal)

---
