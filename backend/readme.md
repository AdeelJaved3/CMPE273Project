# HealthConnect Application 

[![Open in GitHub Codespaces](https://github.com/codespaces/badge.svg)](https://github.com/AdeelJaved3/CMPE273Project/tree/main/backend)

This is a web application for managing health records of patients, including appointments, prescriptions, and vaccination records. It allows patients to schedule appointments with doctors, receive prescriptions, and keep track of their vaccination records.


## Understanding the application
<a href="https://sjsu0-my.sharepoint.com/:p:/r/personal/lavanya_lankalapilli_sjsu_edu/_layouts/15/Doc.aspx?sourcedoc=%7B42DADA94-52F7-4085-9057-5B55535443C3%7D&file=CMPE%20273%20-%20Phase%201%201.pptx&action=edit&mobileredirect=true&DefaultItemOpen=1&login_hint=lavanya.lankalapilli%40sjsu.edu&ct=1681764462899&wdOrigin=OFFICECOM-WEB.START.EDGEWORTH&cid=680a2b4a-d7d3-402b-814f-fd39768fe4c6&wdPreviousSessionSrc=HarmonyWeb&wdPreviousSession=423edd93-1400-4edf-b0f5-1f080befcc87">See the presentation here</a>

### Features
- Secure login and registration system for patients and doctors
- Doctors can add/edit patient information, prescriptions, and vaccination records
- Patients can view their medical records, appointment history, and schedule new appointments
- Appointment scheduling system with options for in-person or virtual appointments
- Vaccination management system for doctors to keep track of patients' vaccination records

## Running the application
HealthConnect is a [Spring Boot](https://spring.io/guides/gs/spring-boot) application built using [Maven](https://spring.io/guides/gs/maven/). You can build a jar file and run it from the command line (it should work just as well with Java 17 or newer):

### Prerequisites
- Java 11 or later
- Apache Maven 3.6 or later
- MySQL server

### Getting Started

1. Clone the repository:
```
git clone https://github.com/AdeelJaved3/CMPE273Project.git
```
2. Navigate to the project directory:
```
cd backend
```
3. Open the src/main/resources/application.properties file and enter your MySQL database server credentials:
```
spring.datasource.url=<mysql-url>
spring.datasource.username=<username>
spring.datasource.password=<password>
```
4. Build the application using Maven:
```
mvn clean install
```
5. Run the application using the jar found in the target folder:
```
java -jar target/Healthconnect-<version>.jar
```
6. Open the application in your web browser:
```
http://localhost:8080
```



## Contributing
Contributions to HealthConnect are always welcome! Please create a pull request with your changes.

## License
HealthConnect is released under the MIT License.