## Prerequisites

**Lombok for eclipse/IntelliJ**

You will need to setup lombok for your IDE. it can be done in simple steps demonstrated in given links below

 [Setup Lombok for Eclipse IDE](https://projectlombok.org/setup/eclipse)

[Setup Lombok for IntellliJ](https://projectlombok.org/setup/intellij) 

## How to write tests?

**UI Tests**

Create a new java class under Test package. src/DemoTest1.java contains TestNG tests and tear down methods. Do not change them an copy all tear down methods as is to your newly created class. You can create a new annotation @Test in your new class and start writing your tests.
Leverage Playright API's from Keywords/UI.java class to perform any UI action. Store all locators by creating new classes for each page under Locators package. You can choose your design pattern and build utilities using Playwright API's

**API Tests**

Create a new java class under Test package. src/DemoTest2.java contains TestNG tests and tear down methods. Do not change them an copy all tear down methods as is to your newly created class. You can create a new annotation @Test in your new class and start writing your tests.
Leverage REST API's from Keywords/REST.java class to perform any HTTP call like GET, POST, PUT DELETE etc. Store all your POJO classes under POJO package. One Demo POJO class is created as an example. it leverages Jackson and lombok annotations. You can choose your design pattern and build utilities using Playwright API's