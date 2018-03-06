## Client usage of the multiple-file config server configuration

In order for the client application to pull from multiple config files on a github-backed Spring Config Server instance, it was perform a few actions:

First, include the boot configuration jar in the pom:

```xml
    <dependency>
      <groupId>com.example.config</groupId>
      <artifactId>auto-sample</artifactId>
      <version>1.0-SNAPSHOT</version>
    </dependency>
```

This can be installed to a local maven repo from this github repository.

Next, the bootstrap.properties file must be modified to include two new properties:

```
spring.cloud.config.enabled=false
spring.cloud.config.fileNames=app-common,missing-common
```

Setting spring.cloud.config.enabled to false will disable the default Spring config server property locator.

spring.cloud.config.fileNames is a comma-delimited list of files that the custom property locator will use, in order, to resolve properties.  This example lists two:  one that is expected to exist and one that does not.  

Note that if the spring.config.fileNames property is missing all properties will be resolved as they normally are with Spring Config Server.
