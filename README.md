# README #
* Version 0.0.1

### Service Partner One Optimizer API ###

Optimization tool for one of our cleaning partners! Our partner has contracts with several structures all around Berlin. 
These structures are all of varying size (measured in rooms). 
The workforce of our partner is made up of Senior Cleaners and Junior Cleaners. Senior Cleaners have a higher work capacity than Junior Cleaners. 
Our partner is free to decide how many Senior and Junior Cleaners are to be sent to clean a structure but there always needs to be at least one Senior cleaner at every structure to lead the juniors. The goal is to minimize overcapacity at every structure.
Given an array of structure sizes (in no. of rooms) as well as work capacities of Junior and Senior Cleaners, your program should present the optimal numbers of Juniors and Seniors for every structure.
Input:
- array of rooms (int) for every structure
- cleaning capacity Junior Cleaner (int)
- cleaning capacity Senior Cleaner (int)

We do not have cleaning providers with more than 100 structures in their portfolio. None of the structures will have more than 100 rooms.
Output:
- array of maps which include the optimal number of Juniors and Seniors for ever structure

Examples:
1)
In: { “rooms”: [35, 21, 17, 28], “senior”: 10, “junior”: 6 }
Out: [ {senior: 3, junior: 1}, {senior: 1, junior: 2}, {senior: 2, junior: 0}, {senior: 1, junior: 3} ]
2)
In: { “rooms”: [24, 28], “senior”: 11, “junior”: 6 }
Out: [ {senior: 2, junior: 1}, {senior: 2, junior: 1} ]


### Set Up ###
* This project uses [Java 8] and [Spring boot]

# API Documentation
* Swagger-UI is accessible on `http://localhost:8082/swagger-ui.html` for API documentation

# Running Unit Tests
* Run `mvn clean compile test`

### Running the project locally ###
* Inside of the project folder, Run `mvn clean install`, get into the target folder and Run `java -jar optimizer-0.0.1-SNAPSHOT.jar`
* And then, the application must run properly. Optionaly, it's possible to access it from: http://localhost:8082/swagger-ui.html#/
* For developing, you should install lombok plugin in your IDE
