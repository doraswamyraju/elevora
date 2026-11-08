# Elevora VPS Deployment & Isolation Guide

This application is configured for deployment on Hostinger VPS **147.93.107.21** without interfering with any existing applications (such as Restrove on 5088).

---

## Assigned Port & Host
- **Domain:** `elevora.sriddha.com`
- **Assigned Dedicated Port:** `5089` (Firewall rule already active on VPS)
- **VPS Host:** `147.93.107.21`
- **Application Directory on VPS:** `/var/www/elevora`
- **PM2 Service Name:** `elevora-backend`

---

## 1. Initial VPS Setup & Clone

SSH into your Hostinger VPS from your terminal:
```bash
ssh root@147.93.107.21
```

Clone the Elevora repository:
```bash
cd /var/www
git clone https://github.com/doraswamyraju/elevora.git
cd elevora
```

---

## 2. Start Backend API on Port 5089 (Isolated Daemon)

```bash
cd /var/www/elevora/backend
npm install
npm run build

# Start using PM2 daemon exclusively on port 5089
PORT=5089 pm2 start dist/server.js --name "elevora-backend"
pm2 save
```

Verify backend health check on port 5089:
```bash
curl http://localhost:5089/health
# Response: {"status":"ok","service":"Elevora LMS Backend API","port":5089,...}
```

---

## 3. Build Web Module

```bash
cd /var/www/elevora/web
npm install
npm run build
```
This generates the optimized production build at `/var/www/elevora/web/dist`.

---

## 4. Nginx Configuration for `elevora.sriddha.com`

Create a separate Nginx site configuration so it has zero impact on other sites:
```bash
nano /etc/nginx/sites-available/elevora.sriddha.com
```

Paste the following configuration:
```nginx
server {
    listen 80;
    server_name elevora.sriddha.com;

    # Frontend Static SPA
    location / {
        root /var/www/elevora/web/dist;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    # Reverse Proxy to Elevora Port 5089 Only
    location /api/ {
        proxy_pass http://127.0.0.1:5089;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
```

Enable the site and reload Nginx:
```bash
ln -s /etc/nginx/sites-available/elevora.sriddha.com /etc/nginx/sites-enabled/
nginx -t
systemctl reload nginx
```

Install SSL certificate via Certbot:
```bash
certbot --nginx -d elevora.sriddha.com
```

---

## 5. Ongoing Push-Pull Deployment Workflow

When new course modules or features are committed and pushed locally:
```bash
# 1. On Local Machine:
git add .
git commit -m "feat: added new lessons"
git push origin main

# 2. On VPS (via SSH):
cd /var/www/elevora
git pull origin main

# Rebuild backend & restart PM2 daemon
cd backend
npm install
npm run build
pm2 restart elevora-backend

# Rebuild frontend
cd ../web
npm install
npm run build
```
---

## Credentials Configured
- **Learner:** `user@elevora.com` / `user123`
- **Trainer/Admin:** `admin@elevora.com` / `admin123`

