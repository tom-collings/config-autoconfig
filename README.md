## Multiple properties files when using Spring Configuration server

There are use cases where it may be useful to pull configuration from multiple files when using Spring Configuration Server:  For example, if there are multiple applications using the same database configuration properties, putting those values in a common properties file might be preferred.

If the Spring Configuration Server is set up with a wildcard searchPaths value and other applications are using the same github repo that would *not* want these common properties, then we have to find a way to "opt-in" to the additional properties loading.

Normally the Spring Config Server will read from <spring.application.name>-<profile>.properties and then follow with application-<profile>.properties.  This utility will allow users to add other file names on the github back end to be loaded after these.

This example comes in three parts.  To run, first start the config server by going to the ./configServer directory and typing

```
mvn spring-boot:run
```

Next, build and install the bootstrap configuration component.  This component will look for a comma-delimited list of file name prefixes in the property `spring.cloud.config.fileNames` and read from those files on the github backend, if they exist.

To build and install this component, from the ./auto-sample directory type

```
mvn clean install
```

Lastly, run the sample client by navigating to the ./configClient directory and typing

```
mvn spring-boot:run
```

Verify that properties are being pulled from the https://github.com/tom-collings/simple-config repo by executing some curl commands:

localhost:8080/hello should return "This hello message" (from hello.properties)
localhost:8080/common should return "common message" (from app-common.properties)
localhost:8080/verycommon should return "very common message" (from app-common.properties)

To verify that profiles are being recognized, start the program with a dev profile:

```
mvn clean spring-boot:run -Dspring.profiles.active=dev
```

The same curls will return different values if there is a -dev.properties file on github:

localhost:8080/hello will now return "This dev hello message" (from hello-dev.properties)
localhost:8080/common will now return "common dev message" (from app-common-dev.properties)
localhost:8080/verycommon will still return "very common message" as this property only exists in app-common.properties
