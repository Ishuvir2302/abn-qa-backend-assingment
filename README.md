# Backend Test Automation Assignment [![Gitpod Ready-to-Code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/from-referrer/)

<a href="https://gitpod.io/from-referrer/" style="padding: 10px;">
    <img src="https://gitpod.io/button/open-in-gitpod.svg" width="150" alt="Push">
</a>

## Requirements
### Environment
* GitHub account
* Java 11 (JDK) (optionally)
* Maven 3.6+ (optionally)
* Any IDE you comfortable with (eg. IntelliJ, VSCode)

### Skills
* Java 8+ (coding standards)
* Clean Code
* Maven
* Git, GitLab, GitHub

### Instructions
Fork this project
<details>
<summary>Example</summary>

   ![img.png](doc/img/01_fork_project.png)
</details>

#### Working in Web IDE (preferable)

1. Open Project in [GitPod](https://gitpod.io/from-referrer/):
2. Sing-in with GitHub account
3. Create and commit your solution into your forked repository
4. Create documentation in the README.md under the `Documentation` section
5. IMPORTANT: Enable Repository permissions (e.g. git push) for GitPod when coding from Web IDE here:
   https://gitpod.io/integrations
   <details>
   <summary>Details here</summary>

   Edit permission for GitHub:

   ![img.png](doc/img/02_integration_providers.png)

   ![img.png](doc/img/02_enable_repo_permissions.png)
   </details>

## Documentation
Assignment performed using Gitlab Issue API

Functional requirement: To automate GitLab IssueAPI CRUD operations & edge cases
Reference Link : https://docs.gitlab.com/ee/api/issues.html

### Scenario covered

|    CREATE | READ | UPDATE | DELETE |
|-----------|------|--------|--------|
Validating new issue creation in Issue API | Retrieving Issue details with valid token |Issue title update with valid token |Deleting an issue with valid token
Validating new issue creation with invalid OATH2 token |Retrieving project details | Issue title update with invalid token |Deleting an issue with invalid token
Validating issue move to other project |Error validation with invalid token |Issue due date update 
Subscribing & Unscribing to issue |Retrieving group response 
| |Validating issue existence in project
--------------------------------------

###  EDGE CASES COVERED -> Subscribing & Unsubscribing issue, Moving issue to other project, negative scenario with invalid token, validating deleted issues

### Automation Tool 
* RestAssured (used for automating gitlab issue Api)
* Cucumber (BDD Approach)
* Java 11 (Programming language)
* Extent Report (Automation report)
* Maven (Built tool)
* junit
* IDE (Eclipse or Intellij) optional
* Property file (Configuration file for maintaing test data)
* L0g4j (logger)

### Brief Framework description

![image](https://github.com/Ishuvir2302/abn-qa-backend-assingment/assets/135347516/064a633e-0c03-4b1a-a53b-82a6d8fd39a9)

* dataprovider - configuration property reader code
* helper - Logger code 
* runner - test runner used with junit
* stepdefinations - Actual glued code with feature files
* utilities - base request and response class with common methods
* feature - comprises of crud feature files
* properties - comprises of property (OATH2 token, api routes) and log files

### Automation execution  
User can pull/clone the code in their local repo and try to execute using below approach 

* locally can be executed by either running testRunner.java class from any IDE or can be executed by selecting maven test from POM.xml
* using CMD tool by entering below command in you system command prompt 'mvn clean test'

### Automation Report
* Extent Report will be generated under target folder as cucumber_report.html
* Cucumber index.html report get generated under target/reports folder

### Test Data 
* Project, groups, issue are created in gitlab issue API after creating account and oath2 token 


