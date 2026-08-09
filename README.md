# Affable Bean Tutorial

## Demo

Frontend: https://chipper-rugelach-1ca688.netlify.app

Backend: https://afbb-api.onrender.com

Database: [MongoDB Atlas](https://www.mongodb.com/products/platform/atlas-database)

## Local Setup

Host:
- VSCode
- Git
- Incus (type-1 hypervisor)

Guest:
- Mise(-en-place)
- Docker

## Overview

Rewrite [Netbeans e-commerce tutorial](https://netbeans.apache.org/tutorial/main/kb/docs/javaee/ecommerce/intro/) into microservices

Docker compose in this tutorial will create a **swarm of containers** at localhost to demonstrate the microservices:
* `afbb-db`: container serves database and contents, created using MongoDB image
* `afbb-ws`: container serves web APIs, created using GraalVM image
* `afbb-gui`: container serves frontent GUIs, created using NodeJS image

## Directories Structure

`afbb-db`: data and contents
* database seeds & schemas
* media contents

`afbb-ws`: web services and APIs

`afbb-gui`: user interfaces
* `admin`: data management site
* `shop`: data-driven front site


### Create the Swarm

Bring up all containers:
* First time startup: `docker compose up -d` (create/recreate containers, which will download/reinstall vscode extensions for each container; hence, take a while)
* Next times: `docker compose start`

All containers __orderly created__ and share the *same network created by docker-compose*

### Bring up APIs

1. `Ctrl+Shift+N` > `Ctrl+Shift+P` > "Dev Containers: Open Folder in Container..." > select path to an `afbb-ws` folder
2. Wait for container window loading completed, all extensions should be installed (the extension IDs are defined in `.devcontainer.json`)
3. Select "Spring Boot Dashboard" and Run/Debug service
4. At local host, use browser or any HTTP client tool to test APIs, i.e.: `http://localhost:8080/categories`

Run with CLI commands: `./mvnw mn:run -Dmicronaut.environments=local`

Debug with VSCode: run/debug via Micronaut Toolkit for VSCode extension

### Bring up GUIs

1. `Ctrl+Shift+N` > `Ctrl+Shift+P` > "Dev Containers: Open Folder in Container..." > select path to a `afbb-gui` folder
2. Wait for container window loading completed, all extensions should be installed (the extension IDs are defined in `.devcontainer.json`)
3. Go to terminal of the container window, change dir to the front app: admin - `cd admin` or shop - `cd shop`
4. Start the app in dev mode: `npm run dev`
5. Open browser (recommend Chrome) on the host: shop - `http://localhost:3000`; admin - `http://localhost:3001`

Run in production mode: `npm run build && npm run preview`
