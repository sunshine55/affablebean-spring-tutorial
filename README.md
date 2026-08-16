# Affable Bean Tutorial

## Demo

Frontend: https://chipper-rugelach-1ca688.netlify.app

Backend: https://afbb-api.onrender.com

Database: [MongoDB Atlas](https://www.mongodb.com/products/platform/atlas-database)

## Local Setup

Containerized development path:
- VSCode
- Git
- Docker

SDD AI-driven development path:
- VSCode
- Git
- Incus
  - Mise(-en-place)
  - Docker

## Overview

Rewrite [Netbeans e-commerce tutorial](https://netbeans.apache.org/tutorial/main/kb/docs/javaee/ecommerce/intro/) into microservices

## Directories Structure

`afbb-db`: data and contents
- database seeds & schemas
- media contents

`afbb-ws`: web services and APIs

`afbb-gui`: user interfaces
- `admin`: data management site
- `shop`: data-driven front site

Debug with VSCode:
- Remote development with Incus container using SSH connection
- Run/debug via Micronaut Toolkit for VSCode extension
- Other extensions are defined in `.devcontainer.json`, but those are used for Docker, need to install them manually in case of Incus container development

Enable mongo docker to run inside incus container, set these config on host machine:
```sh
incus config set <container_name> security.nesting=true
incus config set <container_name> security.syscalls.intercept.mknod=true
incus config set <container_name> security.syscalls.intercept.setxattr=true
```

Expose ports for services running inside Incus container (excute these commands at host machine):
```sh
incus config device add <container_name> port8080 proxy listen=tcp:0.0.0.0:8080 connect=tcp:127.0.0.1:8080
incus config device add <container_name> port3000 proxy listen=tcp:0.0.0.0:8080 connect=tcp:127.0.0.1:3000
incus config device add <container_name> port3001 proxy listen=tcp:0.0.0.0:8080 connect=tcp:127.0.0.1:3001
```

### Bring up DB

Bring up Docker Mongo inside Incus container:
- First time: `docker compose up -d`
- Next times: `docker compose start`

### Bring up APIs

Run with CLI commands: `./mvnw mn:run -Dmicronaut.environments=local`

At local host, use browser or any HTTP client tool to test APIs, i.e.: `http://localhost:8080/categories`

### Bring up GUIs

Change directory to `shop` or `admin` under `afbb-gui` and start the app in dev mode: `npm run dev`

Open browser (recommend Chrome) on the host: shop - `http://localhost:3000`; admin - `http://localhost:3001`

Run in production mode: `npm run build && npm run preview`
