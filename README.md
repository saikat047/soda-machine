# soda-machine
## Build
The project is built with Maven 3.9 and Java 21.
To build the command, run:
```
mvn clean install
```

## RUN
The project's build artifact is a standard jar built by Maven that can be found under the target folder
with naming pattern <em>soda-machine-{version}.jar</em>.
To run the artifact, run the following command from the root of the project directory:
```
java -cp target/soda-machine-1.0-SNAPSHOT.jar com.company.Main 
```
