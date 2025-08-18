# Forex-Excharates-Auth-Microservices

Aggregate forex rates from multiple public APIs, calculate an average, and apply a markup for customer-facing rates.  
This project comprises several microservices.

---

## Instructions to Run the Forex Rate Aggregator Service

1. **Service Registry** (port `8761`)  
   - Assists in showing the status of which services are running.

2. **Config Server** (port `9296`)  
   - Stores common settings of the services in one place.

3. **API Gateway** (port `9191`)  
   - Provides a single entry point to the services.

4. **Auth Service** (port `8081`)  
   - Enables authentication of a user in the application.  
   - Provides **Signup** and **Signin** functionality.

5. **Rate Aggregation Service** (port `8082`)  
   - **On startup**: polls forex rates so that when the user eventually signs in, rates are already available.  
   - **After authentication**:  
     - Once a user signs up and signs in via the Auth Service, they are provisioned with a **JWT token**.  
     - This token is then used to access the Rate Aggregation Service.


How to: 

## Signup: 
    
    - (POST) http://localhost:8081/api/auth/signup
     body:
     {
    "username": "testuser",
    "email": "test@example.com",
    "password": "password123"
    }

## Signin (to get Auth token)

   - (POST) http://localhost:8081/api/auth/signin
    body: 
    {"username": "testuser","password": "password123"}       

## Get Average Rates 
   (*NB: Rates should be already available when the app starts, just not yet accessible)
    
   - (GET) http://localhost:8082/api/rates/average-currency-rates

## Docker Quick Start
```bash
docker pull fmagoge/rates-aggregator-service-suite
docker-compose up -d