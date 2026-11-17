# City Building Game

By Zachary Cone, Philip Adefila, and Braydon Newnham

## Launch with Maven

Requirements:

- JDK 17 or newer
- Maven 3.9 or newer

From the project directory, run:

```shell
mvn clean javafx:run
```

The JavaFX Maven plugin downloads the platform-specific JavaFX dependencies
automatically and starts `domainpackage.MainGame`. To launch without cleaning
previous build output, use:

```shell
mvn javafx:run
```

To compile and package the project without launching it:

```shell
mvn clean package
```
