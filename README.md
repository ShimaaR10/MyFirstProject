# Framework Practice Project

## Overview
This project is a **Java Test Automation Framework** built with **Selenium WebDriver**, **TestNG**, and **Maven**.  
It demonstrates end-to-end test flows such as **registration, wishlist management, checkout, and profile updates**.  

---

## 🛠️ Tech Stack
- **Java**: 17+ (tested on Java 23 as well)  
- **Maven**: 3.9+  
- **Selenium WebDriver**: 4.x  
- **TestNG**: 7.x  

---
### 🔧 Prerequisites
Before you start, make sure you have the following installed:
- [Java 17+](https://adoptium.net/) (Java 23 recommended)  
- [Maven 3.9+](https://maven.apache.org/download.cgi)  
- [Git](https://git-scm.com/)  

Verify installations:
```bash
java -version
mvn -v
git --version

---
### Steps to Run the Project

#### 1- Clone the repository
```bash
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>

####2- Build the project
mvn clean install

####3️- Run the tests
mvn test

