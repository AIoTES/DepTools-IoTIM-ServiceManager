# Service Manager

The Service Manager is a web application that allows the access and the manipulation of the services registered to AIoTES, and more specifically in the [Query component](https://git.activageproject.eu/Deployment/DT-AIOTES_docker/src/master/DL-Query-component) of the Data Lake. The Query component stores all the services in a file. Please make sure that you have already deployed the Query component.

## How to deploy

- Use an operating system with Java 7
- Download and install the following Liferay Portal version: liferay-portal-tomcat-6.1
- Download Liferay SDK and the provided source code. Import the project and create the corresponding WAR file
- Copy the created WAR file in the deploy folder of Liferay Portal and initialise the Tomcat


## Docker Deployment

Download ``Service Manager`` from [DT-AIOTES_docker](https://git.activageproject.eu/Deployment/DT-AIOTES_docker) repo.

In order to deploy the Service Manager using Docker, download the `docker-compose.yml` in a local directory. Modify the environment variables and ports to reflect your configuration and then run the following command from the same directory:

```
docker-compose up -d
```

The application communicates with the Query component through the BACKEND_URL environmental variable. Please modify this variable accordingly. If not environmental variable is provided then as default is use the following: http://localhost:20085/

For more information refer to ``DT-AIOTES_docker`` repo, under [Service Manager](https://git.activageproject.eu/Deployment/DT-AIOTES_docker/src/master/Service%20Manager/README.md) folder.

## Usage
By default, the application will be available from a web browser at the following URL: http://localhost:9082/web/activage/service-manager. In case you use a Windows machine, you may have to replace `localhost` with `192.168.99.100`.

## License

EUPL v1.2