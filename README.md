# VetApp  
Application for appointments registration.

This application has three functionalities:  
* Registration of new appointment by client for certain time and doctor;
* Cancellation of appointment by client;
* Listing all appointments for certain day and doctor.

This application requires mySQL database named db_vet with access for user: name - springuser, password - ThePassword. Repository contains dump.sql file to load tables and content before running the application.

Steps:
* creating the database;
* creating the user;
* granting privileges;
* inserting tables and data;  

For mySQL 8.0:  

```bash
mysql> CREATE DATABASE db_vet;
mysql> create user 'springuser'@'localhost' identified by 'ThePassword';
mysql> grant all on db_vet.* to 'springuser'@'localhost';

mysql -u springuser -p db_vet < VetApp/dump.sql
```  

Running application using Apache Maven 3.6.3:  

```bash
mvn spring-boot:run -f VetApp/pom.xml
```  

After running the application there are three requests than can be proceeded.  
Examples of usage using curl:  
* adding new appointment:
  * possible clientIds and PIN numbers can be checked in the client table in the database;
  * time of the appointment needs to be a date in the future, that is available at chosen doctors schedule;
  * appointment lasts 30 minutes;
  * application returns an id of newly created appointment;  
```bash
curl localhost:8080/appointments/new \
-H "clientId: 1114" \
-H "clientPIN: 4111" \
-H "Content-Type: application/json" \
-d '{"doctorId":"greym","time":"2021-02-02T15:00"}'
```  
* cancellation of appointment:
  * request requires providing an id of the appointment in the path  
```bash
curl -X DELETE localhost:8080/appointments/2 \
-H "clientId: 1114" \
-H "clientPIN: 4111"
```  
* listing appointments:
  * request requires providing doctors id in the path and date in the format "yyyy-MM-dd" as parameter;  
```bash
curl 'localhost:8080/appointments/greym?date=2021-02-01'
```  
