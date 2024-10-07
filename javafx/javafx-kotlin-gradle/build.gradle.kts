plugins {
    kotlin("jvm") version "2.0.20"
    application
    id("org.javamodularity.moduleplugin") version "1.8.15"
}

group = "com.mathswe"
version = "1.0-SNAPSHOT"

application {
    mainModule = "com.mathswe.app"
    mainClass = "com.mathswe.app.MainKt"
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
