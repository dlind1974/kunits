# kunits
Measurement units in Kotlin

# Useful gradle commands
Run gradlew projects to get a list of available projects. This will lists the projects for, e.g. _libkunits_
```
./gradlew projects
```

List of all tasks for a target in project
```
./gradlew libkunits:tasks
```

To get a graph of dependencies
```
./gradlew libkunits:dependencies
```

# Publishing a project as module
https://docs.gradle.org/current/userguide/publishing_setup.html

## Publishing library to local maven

In the root of this repository, run the following command

```bash
./gradlew libkunits:publishToMavenLocal
```

This will typically publish the library to the following locations
```
~/.m2/repository/com/github/dlind1974/libkunits/
```

The version of the published library will be decided by the current git version, see gitVersion in [build.gradle.kts](./libkunits/build.gradle.kts), for more details see https://plugins.gradle.org/plugin/com.palantir.git-version.

To get the version name you can run the following command
```
git describe --tags --always --first-parent
```

## Consuming library from local maven

In build.gradle.kts of the application to consume the library add the following where _\<version\>_ is set to the library version to consume e.g. _`1.0.0`_ 
```kotlin
repositories {
    mavenLocal()
}

dependencies {
    implementation ("com.github.dlind1974:libkunits:<version>")
}
```

# Useful links
* A basic understanding of kotlin libraries<br>
https://docs.gradle.org/current/samples/sample_building_kotlin_libraries.html

* Gradle version compatibility<br>
https://docs.gradle.org/current/userguide/compatibility.html<br>
_Note that gradle 7.5 or newer is required for open-jdk 18.
A specific gradle version is easy to install using [sdkman](https://sdkman.io/)._
