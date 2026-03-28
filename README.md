# 🔐 Spring Boot OAuth2 JWT Authentication (Interceptor-Based)
# auth-interceptor-demo

## 📌 Overview

This project demonstrates a **basic OAuth2 Client Credentials flow** using **JWT (JSON Web Token)** in a Spring Boot application.

It includes:

* Token generation API (`/auth/token`)
* Secure API (`/api/secure`)
* JWT validation using interceptor
* Scope-based authorization

---

## 🏗️ Architecture

```
Client (Postman / Curl)
        ↓
POST /auth/token
        ↓
JWT Token Generated
        ↓
GET /api/secure (with Authorization header)
        ↓
Interceptor (validates token)
        ↓
Controller (returns response)
```

---
## High-Level Flow
---------------------------
```
Client (Postman)
   ↓
Generate Token (/auth/token)
   ↓
JWT Token received
   ↓
Call API (/api/secure)
   ↓
Interceptor
   ↓
Token Validation + Scope Check
   ↓
Controller
   ↓
Response ```
----------------------

## ⚙️ Technologies Used

* Java 17
* Spring Boot 3
* Maven
* JJWT (JWT library)

---
## Postman Collections:
---------------------
### 1st API To Generate Token:
```
curl --location 'http://localhost:8099/auth/token' \
--header 'Content-Type: application/x-www-form-urlencoded' \
--data-urlencode 'client_id=my-client' \
--data-urlencode 'client_secret=secret123' \
--data-urlencode 'scope=READ' \
--data-urlencode 'grant_type=client_credentials'

```

### 2nd API: Use above response token and send request:
```
curl --location 'http://localhost:8099/api/secure' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJjbGllbnRJZCI6Im15LWNsaWVudCIsInNjb3BlIjoiUkVBRCIsInN1YiI6Im15LWNsaWVudCIsImlhdCI6MTc3NDY4MjM2OSwiZXhwIjoxNzc0Njg1OTY5fQ.Koh95wDccJhZQfyyvsY-V_5kFHGaVHUf3W_qRFpX5FI'

```

### 3. GET API will Generate token using Oauth2.0 and added token in headers and will get response from API controller 
----------------------------------
``` 
curl --location 'http://localhost:8099/api/secure' \
--header 'Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJjbGllbnRJZCI6Im15LWNsaWVudCIsInNjb3BlIjoiUkVBRCIsInN1YiI6Im15LWNsaWVudCIsImlhdCI6MTc3NDcxNjU2NiwiZXhwIjoxNzc0NzIwMTY2fQ.Nyrnzxp88pr2B15fiNCiFOsNMJpPDJ4S0z4PEen1MHw' \
--header 'Content-Type: application/json' \
--header 'Accept: application/json' \
--header 'Cache-Control: no-cache' \
--header 'Connection: keep-alive'

```

## Oauth2.O
-----------
* Type = OAuth2.0
### Configure New Token

* Grant type = Client Credentials
* Access Token URL = http://localhost:8099/auth/token
* Client ID = my-client
* Client Secret = secret123
* Scope = READ
* Client Authentication = Send client credentials in body

## ⚠️ Important Notes
* This project is for **learning/demo purposes**
* In production:
  * Use Spring Security
  * Use external Auth server like Keycloak/Okta
  * Do not hardcode client credentials


