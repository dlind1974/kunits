/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 * For more details take a look at the 'Building Java & JVM projects' chapter in the Gradle
 * User Manual available at https://docs.gradle.org/7.6/userguide/building_java_projects.html
 * This project uses @Incubating APIs which are subject to change.
 */
plugins {
    // Apply the org.jetbrains.kotlin.jvm Plugin to add support for Kotlin.
    id("org.jetbrains.kotlin.jvm") version "1.7.10"

    // Get library version from git tag
    id("com.palantir.git-version") version "0.15.0"

    kotlin("plugin.serialization") version "1.9.21"

    // Apply the java-library plugin for API and implementation separation.
    `java-library`
    `maven-publish`
}

group = "com.github.dlind1974"

val gitVersion: groovy.lang.Closure<String> by extra
version = gitVersion()

repositories {
    // Use Maven Central for resolving dependencies.
    mavenCentral()
}

dependencies {
    api("com.fasterxml.jackson.module:jackson-module-kotlin:2.14.0")
    api("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
    testImplementation ("com.google.truth:truth:1.1.3")
}

testing {
    suites {
        // Configure the built-in test suite
        val test by getting(JvmTestSuite::class) {
            // Use Kotlin Test test framework
            useKotlinTest("1.7.10")

            dependencies {
                // Use newer version of JUnit Engine for Kotlin Test
                implementation("org.junit.jupiter:junit-jupiter-engine:5.9.1")
            }
        }
    }
}

publishing {
    publications {
        create<MavenPublication>("kunitsLibrary") {
            from(components["java"])
        }
    }
    /*
    repositories {
        maven {
            name = "myRepo"
            url = uri(layout.buildDirectory.dir("repo"))
        }
    }
    */
}
