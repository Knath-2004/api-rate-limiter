# api-rate-limiter
API Rate Limiter with Spring Boot and Dashboard UI
# 🚀 API Rate Limiter with Dashboard

A backend-driven API Rate Limiting system built using **Spring Boot**, with a simple **interactive dashboard** to visualize request behavior in real-time.

---

## 📌 Features

- 🔐 API Key validation using HTTP headers
- ⚡ Rate limiting using in-memory token logic
- 🚫 Blocks requests after limit (HTTP 429)
- ❌ Handles missing API keys (HTTP 400)
- ✅ Allows valid requests (HTTP 200)
- 📊 Real-time dashboard using Chart.js
- 📈 Tracks Success vs Blocked requests

---

## 🛠️ Tech Stack

- Java
- Spring Boot
- REST APIs
- HTML, CSS, JavaScript
- Chart.js

---

## 🧠 How It Works

1. User enters API Key in dashboard  
2. Request is sent to backend (`/test`)  
3. Filter intercepts request  
4. System checks:
   - API key present?
   - Rate limit exceeded?
5. If valid → request processed  
6. If not → blocked with error  
7. Dashboard updates graph dynamically  

---

## 📷 Demo

👉 Open in browser:
http://localhost:8080/dashboard.html
