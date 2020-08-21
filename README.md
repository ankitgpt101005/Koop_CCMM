# Koopid AACC Gateway #
This is a repository to maintain code for Koopid AACC Gateway Connector.

## Application properties ##

Following are the properties which are configurable :
* ***Server port:***
 **server.port:**
	Port on which gateway service has to run e.g. server.port=9130
* ***EWC:***
* **ewc_override_host:** 
	These headers are required to setup on the request e.g. true
	Note : This is temporary header and will be gone after the actual setup  
* **ewc_host:** 
	EWC client which is white listed e.g. 192.168.210.48:8081
	Note : This is temporary header and will be gone after the actual setup
* **ewc_origin:** 
	EWC Server IP address e.g. http://192.168.210.52:8080
	Note : This is temporary header and will be gone after the actual setup

## Configure Logs ##

* **logging.config:** 
	Mention mount path inside 'logback.xml' where all the logs will be produced. e.g. logs/connector.log

## Database Configuration ##

* **spring.datasource.driver-class-name:** 
	Database driver class name e.g. For Postgres database - org.postgresql.Driver
* **spring.datasource.url:** 
	Database connection url e.g. jdbc:postgresql://localhost:5432/AvayaConnectorDB?currentSchema=aacc
	Here, connection url depicts following details - 
	*postgresql* - database for which connection is to be established
	*localhost* - domain/IP where database is hosted
	*5432* - port on which postgres services are ON
	*AvayaConnectorDB* - the name of the database under which all the scehmas/tables will be created
	*currentSchema=aacc* - the name of the schema under whcih all the tables will be created
* **spring.datasource.username:** 
	Mention database user name
* **spring.datasource.password:** 
	Mention database password

## Redis Configuration ##

* **redis.host:** 
	Mention Redis hostname
* **redis.port:** 
	Mention Redis port
* **redis.serviceName:** 
	Mention service name
	

#Koopid configurations
	Configure respective urls under 'webchatConfig' for koopid provider table
**For CCMM connector **
* **webServiceUrl:**
	AACC SOAP web service which exposes the SOAP endpoint required to interact with the Agent. e.g. http://10.10.30.61/ccmmwebservices/
* **webServiceEndpoint:**
	Mention namespace of a SOAP web service with which interaction is to be established. e.g. http://webservices.ci.ccmm.applications.nortel.com/

**For EWC connector**
* **webSocketEndpoint:** 
	Mention websocket url of a server with which socket connection has to setup e.g. ws://192.168.210.48:8081/CustomerControllerWeb/chat

## Deploy Koopid AACC Gateway Connector ##
	* To run the gateway service with existing applications properties, execute following command - 
		java -jar Koopid-AACC-Gateway-0.0.1-SNAPSHOT.jar
	* To run the gateway service by overriding existing applications properties, execute following command - 
		java -jar Koopid-AACC-Gateway-0.0.1-SNAPSHOT.jar  --spring.config.location=file:///Users/home/config/override.properties
