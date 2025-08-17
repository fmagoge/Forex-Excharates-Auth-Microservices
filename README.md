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
