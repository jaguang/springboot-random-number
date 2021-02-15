## Giveaway Random Item Generator API

### What you need

- IntelliJ, Eclipse
- Java Version 11
- Postman application for sending http request and JSON
- MySQL for Database Management System

#### How to Run

```
mvn spring-boot:run
```



### Rest API Description

Documentation : http://localhost:8080/swagger-ui.html

or you can run on Postman : http://localhost:8080

- /items
- /customer  *//you need authenticated to access this path*
- /units
- /quantity

JWT Documentation

http://localhost:8080/register //Register user with full authority

https://github.com/jaguang111/springboot-random-number/blob/master/image-20210215082932853.png?raw=true

http://localhost:8080/authenticate //get token

https://github.com/jaguang111/springboot-random-number/blob/master/image-20210215083014704.png?raw=true

Copy token with Bearer

https://github.com/jaguang111/springboot-random-number/blob/master/image-20210215083336696.png?raw=true
