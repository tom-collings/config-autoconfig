## BootConfiguration class for multiple properties files in a single github repo

This is a simple jar that creates a `@Configuration` class that defines a custom PropertySourceLocator for pulling files from a github repo when using Spring Configuration Server.

There are three classes in this repo:  The first is simply the `@Configuration` class.  The second is an extension of the Spring ConfigClientProperties class that defines the new field `fileNames`.  This field is used by the third class, which loops over the file names to pull extra properties.

The third class is a lift of the actual Spring implementation with slight modifications to handle the multiple sources.

To get this configuration to be loaded at boot time, we have to use the META-INF/spring.factories file and declare this configuration class.
