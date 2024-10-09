plugins {
    kotlin("jvm") version "2.0.20"
    application
    id("org.javamodularity.moduleplugin") version "1.8.15"
    id("org.openjfx.javafxplugin") version "0.1.0"
    id("org.beryx.jlink") version "3.0.1"
}

group = "com.mathswe"
version = "1.0-SNAPSHOT"

application {
    mainModule = "com.mathswe.app"
    mainClass = "com.mathswe.app.Main"
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

jlink {
    imageZip = project
        .file(
            "${layout.buildDirectory}/distributions/app-${javafx.platform.classifier}.zip"
        )
    options.addAll(
        listOf(
            "--strip-debug",
            "--no-header-files",
            "--no-man-pages",
        )
    )

    launcher {
        name = "javafx-kotlin-gradle-template"
    }

    jpackage {
        // It requires the package `rpm` in Linux and Wix 3 on Windows
        // https://docs.oracle.com/en/java/javase/14/jpackage/packaging-overview.html

        val currentOs = org.gradle.internal.os.OperatingSystem.current()

        if (currentOs.isLinux) {
            val installerTypeProperty = project
                .findProperty("installerType") as String?

            // Set the installer type (DEB) explicitly from the
            // jpackage argument to avoid building RMP (building RMP on
            // Ubuntu might fail sometimes)
            if (installerTypeProperty != null) {
                installerType = installerTypeProperty
            }

            // For Debian. Overrides resources (untested on RedHat)
            installerOptions.addAll(
                listOf(
                    "--resource-dir",
                    "jpackage/linux",
                    "--verbose",
                )
            )
        }
        else if (currentOs.isWindows) {
            imageOptions.addAll(listOf("--win-console"))

            installerOptions.addAll(
                listOf(
                    "--resource-dir",
                    "jpackage/windows",
                    "--verbose",
                    "--win-per-user-install",
                    "--win-dir-chooser",
                    "--win-menu",
                )
            )
        }
    }
}
