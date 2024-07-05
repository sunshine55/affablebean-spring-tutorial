# Affable Bean Microservice Tutorial

<!-- vscode-markdown-toc -->
* 1. [Overview](#Overview)
* 2. [Services](#Services)
* 3. [Reference](#Reference)
* 4. [Local Development](#LocalDevelopment)
	* 4.1. [Issues](#Issues)
	* 4.2. [Create the Swarm](#CreatetheSwarm)
	* 4.3. [Bring up the APIs](#BringuptheAPIs)
	* 4.4. [Bring up the GUIs](#BringuptheGUIs)
	* 4.5. [Bring up CDN server](#BringupCDNserver)

<!-- vscode-markdown-toc-config
	numbering=true
	autoSave=true
	/vscode-markdown-toc-config -->
<!-- /vscode-markdown-toc -->

##  1. <a name='Overview'></a>Overview

Breakdown [affablebean-spring-tutorial](https://github.com/sunshine55/affablebean-spring-tutorial) app into microservices and containerize them.

Each microservice has diffrent a tech stack.

Docker compose in this tutorial only works for localhost development only. In order to demo on cloud:
* afbb-db: media files should be served on CDN, at localhost development it should be mounted as part of `afbb-cdn`
* afbb-cdn: not recommended for cloud deployment, use at localhost development as static content service
* afbb-shop: env changes to adapt cloud deployment
* afbb-webflux: use build plugins to automate cloud deployment

##  2. <a name='Services'></a>Services

Databases:
* afbb-cdn: serve static contents for other GUIs
* afbb-db:
  - database scripts (MySQL and MongoDB)
  - media contents fetching CDN

APIs:
* afbb-web: web service for cart & order GUI
* afbb-webflux: web service for shopping GUI

GUIs:
* afbb-admin: metadata management
* afbb-cart: cart rendering and checkout
* afbb-shop: products rendering

##  3. <a name='Reference'></a>Reference

Develop microservices with VSCode and type-2 hypervisor:
* Shared development environment with [VSCode devcontainer](https://code.visualstudio.com/docs/remote/create-dev-container)
* Connect multiple [VSCode devcontainers with Docker Compose](https://code.visualstudio.com/remote/advancedcontainers/connect-multiple-containers)
* It's significantly slow if using Docker Desktop for Windows due to file processing between Windows host and Linux container guests, attempt to cache mounted volumes doesn't improve much (see [Stackoverflow topic](https://stackoverflow.com/questions/49060062/running-webpack-dev-server-in-docker-is-significantly-slower-than-on-local-machi)).
* Workaround: set up workspace in [VirtualBox VM](https://www.virtualbox.org/) with Linux distro (i.e.: [Ubuntu MATE](https://ubuntu-mate.org/)); then install Docker, VSCode...

##  4. <a name='LocalDevelopment'></a>Local Development

Prerequisites: Docker, VSCode and Git (either install on OS or another type-2 hypervisor)

###  4.1. <a name='Issues'></a>Issues

| Issue                                                                        | Cause                                                                           | Workaround                                                                                                                                                                                                        |
| :--------------------------------------------------------------------------- | :------------------------------------------------------------------------------ | :---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| Webpack dev server is significantly slow if using Docker Desktop for Windows | Because delay in file processing between Windows host and Linux container guest | 1. Attempt to cache mounted volumes doesn't improve much (see [Stackoverflow topic](https://stackoverflow.com/questions/49060062/running-webpack-dev-server-in-docker-is-significantly-slower-than-on-local-machi)).<br>2. Set up workspace in [VirtualBox VM](https://www.virtualbox.org/) with Linux distro (i.e.: [Ubuntu MATE](https://ubuntu-mate.org/)); then install Prerequisites |
Unable to start `mongo:latest` container in VirtualBox VM | MongoDB 5+ requires a Sandy Bridge or newer CPU [Stackoverflow topic](https://stackoverflow.com/questions/68392064/error-when-running-mongo-image-docker-entrypoint-sh-line-381) | 1. Use an older version MongoDB v4<br>2. Enable Nested VT-x/AMD-V in VirtualBox


###  4.2. <a name='CreatetheSwarm'></a>Create the Swarm

Bring up all containers:
* First time startup: `docker compose up -d` (create/recreate containers, which will download/reinstall vscode extensions for each container; hence, take a while)
* Next times: `docker compose start`

All containers __orderly created__ and share the *same network created by docker-compose*

###  4.3. <a name='BringuptheAPIs'></a>Bring up the APIs

1. `Ctrl+Shift+N` > `Ctrl+Shift+P` > "Dev Containers: Open Folder in Container..." > select path to an api folder
2. Wait for container window loading completed, all extensions should be installed (the extension IDs are defined in `.devcontainer.json`)
3. Select "Spring Boot Dashboard" and Run/Debug service
4. At local host, use browser or any HTTP client tool to test APIs, i.e.: `http://localhost:8080/category/getAll`

Run with CLI command: `mvn spring-boot:run` or `./gradlew bootRun`

###  4.4. <a name='BringuptheGUIs'></a>Bring up the GUIs

1. `Ctrl+Shift+N` > `Ctrl+Shift+P` > "Dev Containers: Open Folder in Container..." > select path to a gui folder
2. Wait for container window loading completed, all extensions should be installed (the extension IDs are defined in `.devcontainer.json`)
3. Go to terminal of the container window: `npm run dev`
4. Open browser on the host, i.e.: `http://localhost:3001/index.html`

Run in production mode: `npm run prod`

###  4.5. <a name='BringupCDNserver'></a>Bring up CDN server

1. `Ctrl+Shift+N` > `Ctrl+Shift+P` > "Dev Containers: Open Folder in Container..." > select path to nginx folder
2. Wait for container window loading completed, all extensions should be installed (the extension IDs are defined in `.devcontainer.json`)
3. `docker compose exec -it cdn nginx -s reload` or `nginx -s reload` (if inside container) to reload nginx when making changes
4. Open browser on the host to check a sample static content, i.e.: `http://localhost:8000/media/categories/bakery.jpg`