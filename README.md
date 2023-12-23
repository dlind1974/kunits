# kunits
Library of measurement units in Kotlin.
The library contains classes to handle measurements of Distance, Area and Speed with corresponding units of measurement.
Serialization and deserialization of measurements is supported using [jackson](https://github.com/FasterXML/jackson), see json [unit tests](./libkunits/src/test/kotlin/com/github/dlind1974/kunits/json) in this repo for more details on how to serialize/deserialize.

For now only support of a limited number of si-units.

## Examples of usage

Creating a measurement from a number
```kotlin
import com.github.dlind1974.kunits.*

val distance = 12.5.kilometers
```

Conversion between units
```kotlin
import com.github.dlind1974.kunits.*

val distance = 12.5.kilometers
val distanceInMeters = distance.meters
```

Calculation
```kotlin
import com.github.dlind1974.kunits.*

val d1 = 12.5.kilometers
val d2 = 2.meters
val d3 = d1 + d2
println(d3) // => 12.502km
```

# Serialization

## Using [kotlinx.serialization](https://github.com/Kotlin/kotlinx.serialization)

See [kotlinx json unit tests](./libkunits/src/test/kotlin/com/github/dlind1974/kunits/json/kotlinx) for more details on usage.

### Serializing
```kotlin
import com.github.dlind1974.kunits.*
import com.github.dlind1974.kunits.json.kotlinx.*

val speed = 12.7.meterPerSecond
val speedJson = speed.serializeToString()
val expectedSpeeJson = "{\"amount\":12.7,\"unit\":\"m/s\"}"
assertEquals(expectedSpeeJson, speedMpsJson)
```

### Deserializing
```kotlin
import com.github.dlind1974.kunits.*
import com.github.dlind1974.kunits.json.kotlinx.*

val json = "{\"amount\":12.7,\"unit\":\"m/s\"}"
val speed: Speed =  json.deserializeToSpeed()
assertEquals(speed, 12.7.meterPerSecond)
```

## Using [Jackson](https://github.com/FasterXML/jackson)

See [jackson json unit tests](./libkunits/src/test/kotlin/com/github/dlind1974/kunits/json/jackson) on how to setup the object mapper and more details on usage.

### Serializing
```kotlin
import com.github.dlind1974.kunits.*
import com.github.dlind1974.kunits.json.jackson.*

val speed = 12.7.meterPerSecond
val speedJson: String = objectMapper.writeValueAsString(speed)
val expectedSpeedJson = "{\"amount\":12.7,\"unit\":\"m/s\"}"
assertEquals(speedJson, expectedSpeedJson)
```

### Deserializing
```kotlin
import com.github.dlind1974.kunits.*
import com.github.dlind1974.kunits.json.jackson.*

val speedJson = "{\"amount\":12.7,\"unit\":\"m/s\"}"
val speed: Speed = objectMapper.readValue(speedJson, Speed::class.java)
assertEquals(speed, 12.7.meterPerSecond)
```

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
