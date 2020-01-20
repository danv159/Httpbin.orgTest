# Httpbin.orgTest
This project includes  9  automated tests that cover the CRUD commands and some other tests by  using;
[Tested Website](http://httpbin.org)

## **Getting Started**

The project can be cloned from GitHub. Since it is a maven project, all the dependencies will be imported easily on your local machine for the enhancing the development and test. Then, you can execute test by using "mvn test".

### **Prerequisites and Installation**

Java installation should be done and added to the PATH. Beside of this MAVEN\_HOME should be added to the path.

Also, Java IDE. I have used IntelliJ Community Edition 2019, but you can choose an IDE whatever you want. For example, Eclipse.

Please note that, after cloning the repository there will be a Jenkins\_Configuration.rar file located in the root. This file includes all the required configuration, and you should import this file to create a job the same as me.

Note: JDK version: jdk1.8.0\_211

## **Running the tests**

### **Jenkins:**

Please read this blog post [Build a maven project to run test](https://medium.com/@anusha.sharma3010/build-a-simple-maven-project-in-jenkins-da7a2a4ae202)

### **IntelliJ:**

-  File -> New -> Project from Version Control -> URL: https://github.com/alipala/Httpbin.orgTest.git

### **Maven:**

- Goto project root folder and for running all tests

```
mvn clean test
```

- for running all tests in a class

```
mvn -Dtest=BookingTest clean test
```

- for a running single test

```
mvn -Dtest=BookingTest#getAllBookings clean test
```

### **Project Structure**

- Constants: Constant values are locate in this class like expected messages and some login credentials

- Listeners: Allure Report Listener to save the screenshot once a test is failed

- Pages: Consists of methods, variables that corresponding Pages

- Test: Functional UI tests in order to verify the functionality of the product

- Utilities: Helper classes to be used for mant times in the test

## **Built With**

- [JAVA](https://docs.oracle.com/javase/8/docs/technotes/guides/language/index.html) - Programming Language
- [TestNG](https://testng.org/doc/) - The testing framework
- [Maven](https://maven.apache.org/) - It is a build automation tool used for Java projects
- [Allure Report](http://allure.qatools.ru/) - Used to create test execution reports
- [Log4J](https://logging.apache.org/log4j/2.x/) - For inserting log statements into code
- [Jenkins](https://jenkins.io/) - It is an open source automation server to build the job periodically with required plugins

## **Methods**

1. Behaviour Driven Development

## **Authors**

- **Ali Pala**  - _See the all repositories_ - [Ali Pala](https://github.com/alipala)

## **License**

This project is licensed under the MIT License - see the [LICENSE.md](https://github.com/alipala/WebUITest/blob/283e4fc3bae135e38fd2d9c6678053ff1c450a8c/LICENSE.md) file for details

## **Acknowledgments**

You can also run the test on Jenkins. 
1. Create a "Maven project" on Jenkins.
2. Check "GitHub project" and give 	Project url: https://github.com/alipala/Httpbin.orgTest
3. Select "Source Code Management" is Git. Give the repository URL: https://github.com/alipala/Httpbin.orgTest.git
4. Select "Branched to build" is */master.
5. Check "Build periodically" under "Build Triggers" section if you want to build preodically. For example: (H */2 * * *) means build for every 2 hours.
6. In the "Build" section, "Root POM" shoul be "pom.xml".
7. Goals and options: clean compile test(If you want any other, it's up to you).
8. If you want to see the Allure Report integration to your pipeline, open "Post-build Actions", select "Allure Report". After that, specify the path "target/allure-results"
9. Apply and save the changes
