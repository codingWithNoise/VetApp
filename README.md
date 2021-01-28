# VetApp  
Application for appointments registration.

This application has three functionalities:  
* Registration of new appointment by client for certain time and doctor;
* Cancellation of appointment by client;
* Listing all appointments for certain day and doctor.

This application requires mySQL database named db_vet with access for user: name - springuser, password - ThePassword.  
Repository contains dump.sql file to load tables and content before running the application. 
```bash
mysql -u springuser -pThePassword db_vet < dump.slq
```
  
After running the application there are three requests than can be proceed.  
Examples of usage using curl:  
```bash
curl localhost:8080/appointments/new \
-H "clientId: 1114" \
-H "clientPIN: 4111" \
-H "Content-Type: application/json" \
-d '{"doctorId":"greym","date":"2021-02-02T12:00:00.000+00:00"}'
```
* adding new appointment:
  * possible clientIds and PIN numbers can be checked in the client table in the database;
  * time of the appointment needs to be a date in the future, that is available at chosen doctors schedule;
  * appointment lasts 30 minutes;
  * application returns an id of newly created appointment;
```bash
curl -X DELETE localhost:8080/appointments/2 \
-H "clientId: 1114" \
-H "clientPIN: 4111"
```
* cancellation of appointment:
  * request requires providing an id of the appointment in the path
```bash
curl localhost:8080/appointments/greym?date=2021-02-01
```
* listing appointments:
  * request requires providing doctors id and date in the format "yyyy-MM-dd" in the path.