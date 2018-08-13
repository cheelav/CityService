I have a  basic version of the coding exercise working. 

The code is here:

https://github.com/cheelav/vc/tree/master/add-numbers-service

It should be a public repository.

I took a REST approach to the API, so there are two HTTP verbs:

GET - http://localhost:8080/ | get a security question
PUT - http://localhost:8080/ | validate the answer

Port can be changed in application.properties file

I have included jar file 
add-numbers-service-0.0.1-SNAPSHOT.jar

Copy this jar and run below command from jar folder
java -jar add-numbers-service-0.0.1-SNAPSHOT.jar

I have used Postman to test REST end points
For PUT, include answer in the body.

