# job-vacancy

## How to run 
run in the terminal : ./mvnw spring-boot:run

## Endpoints CURLS

### User register endpoints
curl --location 'http://localhost:8080/api/v1/auth/register' \
--header 'Content-Type: application/json' \
--data-raw '{
"username": "fazar26",
"firstname": "Fazar",
"lastname": "Rahman",
"email": "fazar.rahman@gmail.com",
"password": "123456"
}'

### User Login endpoints
curl --location 'http://localhost:8080/api/v1/auth/login' \
--header 'Content-Type: application/json' \
--data '{
"username": "fazar26",
"password": "123456"
}'

### Get All jobs endpoints 
curl --location 'http://localhost:8080/api/v1/job' \
--header 'Authorization: Bearer {jwt token}'

### Get All jobs endpoints
curl --location 'http://localhost:8080/api/v1/job' \
--header 'Authorization: Bearer {jwt token}'

### Get job by id endpoint
curl --location 'http://localhost:8080/api/v1/job/32bf67e5-4971-47ce-985c-44b6b3860cdb' \
--header 'Authorization: Bearer {jwt token}'