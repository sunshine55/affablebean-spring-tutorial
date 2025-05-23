# Affable Bean Tutorial
- [Affable Bean Tutorial](#affable-bean-tutorial)
	- [Deployment](#deployment)
	- [Overview](#overview)
	- [Directories Structure](#directories-structure)
	- [Prerequisites](#prerequisites)
		- [Create the Swarm](#create-the-swarm)
		- [Bring up APIs](#bring-up-apis)
		- [Bring up GUIs](#bring-up-guis)
	- [FAQs](#faqs)

## Deployment

Frontend: https://chipper-rugelach-1ca688.netlify.app

Backend: https://afbb-api.onrender.com

Database: [MongoDB Atlas](https://www.mongodb.com/products/platform/atlas-database)

## Overview

Rewrite [Netbeans e-commerce tutorial](https://netbeans.apache.org/tutorial/main/kb/docs/javaee/ecommerce/intro/) into microservices

Docker compose in this tutorial will create a **swarm of containers** at localhost to demonstrate the microservices:
* `afbb-mongo`: container serves database and contents, created using MongoDB image
* `afbb-gradle`: container serves web APIs, created using Gradle image
* `afbb-gui`: container serves frontent GUIs, created using NodeJS image

## Directories Structure

`afbb-db`: data and contents
* database seeds & schemas
* media contents

`afbb-gradle`: web services and APIs

`afbb-gui`: user interfaces
* `admin`: data management site
* `shop`: data-driven front site

## Prerequisites

Prerequisites: Docker, VSCode and Git (either install on OS or another type-2 hypervisor)

If using Linux OS, see `prerequisites` handy scripts for installation

Develop microservices with VSCode and type-2 hypervisor:
* Shared development environment with [VSCode devcontainer](https://code.visualstudio.com/docs/remote/create-dev-container)
* Connect multiple [VSCode devcontainers with Docker Compose](https://code.visualstudio.com/remote/advancedcontainers/connect-multiple-containers)

### Create the Swarm

Bring up all containers:
* First time startup: `docker compose up -d` (create/recreate containers, which will download/reinstall vscode extensions for each container; hence, take a while)
* Next times: `docker compose start`

All containers __orderly created__ and share the *same network created by docker-compose*

### Bring up APIs

1. `Ctrl+Shift+N` > `Ctrl+Shift+P` > "Dev Containers: Open Folder in Container..." > select path to an `afbb-gradle` folder
2. Wait for container window loading completed, all extensions should be installed (the extension IDs are defined in `.devcontainer.json`)
3. Select "Spring Boot Dashboard" and Run/Debug service
4. At local host, use browser or any HTTP client tool to test APIs, i.e.: `http://localhost:8080/categories`

Run with CLI command: `SPRING_PROFILES_ACTIVE=local MONGODB_URI=mongodb://localhost:27017/afbb-db CORS_ALLOWED_ORIGINS=http://localhost:3000 ./gradlew bootRun`

Run with VSCode:
1. At root of workspace folder, find or create `.vscode/launch.json`
2. See VSCode how to add environment variables to `launch.json` instructions [here](https://code.visualstudio.com/docs/debugtest/debugging-configuration#_launchjson-attributes)

### Bring up GUIs

1. `Ctrl+Shift+N` > `Ctrl+Shift+P` > "Dev Containers: Open Folder in Container..." > select path to a `afbb-gui` folder
2. Wait for container window loading completed, all extensions should be installed (the extension IDs are defined in `.devcontainer.json`)
3. Go to terminal of the container window: `npm run dev`
4. Open browser (recommend Chrome) on the host, i.e.: `http://localhost:3000`

Run in production mode: `npm run build && npm run preview`

## FAQs

1. Webpack dev server is significantly slow if using Docker Desktop for Windows

    - Because delay in file processing between Windows host and Linux container guest
	- Workaround:
	    
		* Attempt to cache mounted volumes doesn't improve much (see [Stackoverflow topic](https://stackoverflow.com/questions/49060062/running-webpack-dev-server-in-docker-is-significantly-slower-than-on-local-machi))
		* Set up workspace in [VirtualBox VM](https://www.virtualbox.org/) with Linux distro (i.e.: [Ubuntu MATE](https://ubuntu-mate.org/)); then install Prerequisites

2. Unable to start `mongo:latest` container in VirtualBox VM

    - MongoDB 5 requires a Sandy Bridge or newer CPU [Stackoverflow topic](https://stackoverflow.com/questions/68392064/error-when-running-mongo-image-docker-entrypoint-sh-line-381)
	- Workaround: Avoid MongoDB 5

3. Whenever start containers, internet connection lost

	- Caused by [ConnMan](https://wiki.archlinux.org/title/ConnMan) as explained in [Docker forum topic](https://forums.docker.com/t/solved-no-network-when-running-a-container-in-arch-linux/5494/5)
	- Workaround: solution discussed in [Stackoverflow topic](https://stackoverflow.com/questions/75003625/when-starting-docker-containers-host-machine-loses-internet-connection)

4. VSCode create auto forwarded ports every Spring Boot startup

	- Open the Settings (Ctrl+, or Cmd+, on macOS).
	- Search for `remote.autoForwardPorts`.
	- Uncheck the option `Remote › Ports: Auto Forward Ports.`