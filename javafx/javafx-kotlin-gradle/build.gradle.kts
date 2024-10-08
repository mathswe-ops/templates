plugins {
    kotlin("jvm") version "2.0.20"
    application
    id("org.javamodularity.moduleplugin") version "1.8.15"
    id("org.openjfx.javafxplugin") version "0.1.0"
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

javafx {
    version = "21"
    modules = listOf("javafx.controls")
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
