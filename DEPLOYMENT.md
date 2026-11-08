# Elevora VPS Deployment Guide (Hostinger Ubuntu 22.04)

> **Important**: This application is hosted on VPS IP `147.93.107.21` at `elevora.sriddha.com`. It runs on **Port 5090** (isolated from `restrove` on 5088 and `thodu` on 5089).

---

## Part 1: First-Time Setup (Run Once Only)

If the project directory does not yet exist on your VPS, you **must clone it first** before pulling or navigating into subdirectories:

```bash
# 1. Ensure /var/www exists and navigate there
mkdir -p /var/www
cd /var/www

# 2. Clone the repository
git clone https://github.com/doraswamyraju/elevora.git

# 3. Enter project directory
cd /var/www/elevora

# 4. Setup and start Backend (Port 5090)
cd /var/www/elevora/backend
npm install
npm run build
PORT=5090 pm2 start dist/server.js --name "elevora-backend"
pm2 save

# 5. Setup and build Web Frontend
cd /var/www/elevora/web
npm install
npm run build
```

---

## Part 2: Nginx & SSL Configuration (Run Once Only)

Create the isolated Nginx site file:
```bash
cat << 'EOF' > /etc/nginx/sites-available/elevora.sriddha.com
server {
    listen 80;
    server_name elevora.sriddha.com;

    # Frontend Static Single Page Application
    location / {
        root /var/www/elevora/web/dist;
        index index.html;
        try_files $uri $uri/ /index.html;
    }

    # API Reverse Proxy to Port 5090
    location /api/ {
        proxy_pass http://127.0.0.1:5090;
        proxy_http_version 1.1;
        proxy_set_header Upgrade $http_upgrade;
        proxy_set_header Connection 'upgrade';
        proxy_set_header Host $host;
        proxy_cache_bypass $http_upgrade;
    }
}
EOF
```

Enable site and restart Nginx:
```bash
ln -s /etc/nginx/sites-available/elevora.sriddha.com /etc/nginx/sites-enabled/
nginx -t
systemctl reload nginx
```

Generate Free SSL via Certbot:
```bash
certbot --nginx -d elevora.sriddha.com
```

---

## Part 3: Daily Update / Deployment Workflow

Whenever you push new changes from your local machine, run this **failsafe one-liner** or copy-paste the block below:

```bash
cd /var/www/elevora && \
git pull origin main && \
cd /var/www/elevora/backend && \
npm install && \
npm run build && \
pm2 restart elevora-backend && \
cd /var/www/elevora/web && \
npm install && \
npm run build
```

---

## Troubleshooting Common Errors

### 1. `cd: /var/www/elevora: No such file or directory`
**Cause**: The repository has not been cloned to `/var/www` yet.
**Fix**:
```bash
mkdir -p /var/www && cd /var/www && git clone https://github.com/doraswamyraju/elevora.git
```

### 2. `fatal: not a git repository (or any of the parent directories): .git`
**Cause**: You ran `git pull` from your home directory (`/root`) instead of `/var/www/elevora`.
**Fix**: Always make sure you are in `/var/www/elevora` before running `git pull`:
```bash
cd /var/www/elevora && git status
```

### 3. `[PM2][ERROR] Process or Namespace elevora-backend not found`
**Cause**: The PM2 daemon has not been started for the first time yet.
**Fix**:
```bash
cd /var/www/elevora/backend
PORT=5089 pm2 start dist/server.js --name "elevora-backend"
pm2 save
```

### 4. `npm error Tracker "idealTree" already exists`
**Cause**: `npm install` was run in `/root` because the previous `cd` command failed, or a stale lockfile exists.
**Fix**: Clean the lock or ensure you are in the correct directory:
```bash
rm -f /root/package.json /root/package-lock.json
cd /var/www/elevora/backend && npm install
```

---

## App Ports & Credentials
- **Assigned Dedicated Port:** `5090` (Firewall rule active on VPS)
- **Web URL**: `https://elevora.sriddha.com`
- **Learner Login**: `user@elevora.com` / `user123`
- **Trainer/Admin Login**: `admin@elevora.com` / `admin123`
