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

## Launch with BlueJ

Open the project directory (the directory containing `package.bluej`) in
BlueJ, compile the classes, and run `domainpackage.MainGame` using its `main`
method. BlueJ must use a JDK with JavaFX available on the module path. The
application accepts both Maven's resource layout and BlueJ's project-directory
classpath layout.
