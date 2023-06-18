# Vending Machine Service

Vending machine service is a sample project designed for demonstrating State Design Pattern.
 
The State Pattern implements the **Open Closed** and **Single Responsibility principles** that are part of the SOLID design principles.

We have designed a simple vending machine which supports 4 states  No_Coin,Contains_Coin,Candy_Dispensed,No_Candy

User and Vendor have their separate endpoints to interact with vending machine.

---
#### User can perform insert a coin , press a button , collect a candy.
#### Vendor can refill the vending machine.
#### Note : There is no UI in application for now. I will implement it later in thymeleaf.

---
### Swagger link
_http://localhost:8080/swagger-ui/index.html_

---
### Tech Stack
Java 17,
SpringBoot, 
H2 database,
OpenApi (Swagger),
SpringBootTest 
---
### Contributing
Not supported for this project
