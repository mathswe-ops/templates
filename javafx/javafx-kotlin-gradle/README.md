# App (JavaFX + Kotlin + Gradle)

This template provides a project from scratch with support for:

- JavaFX
- Kotlin
- Gradle + KTS
- ArrowKt
- Kotlin Test with JUnit
- JPackage (Linux + Windows)

## Setup Template

- Rename the app and package name in 
  - [settings.gradle.kts](settings.gradle.kts).
  - [module-info.java](src/main/java/module-info.java).
  - [package](src/main/kotlin).
  - [test package](src/test/kotlin).
  - [build.gradle.kts](build.gradle.kts).

- Replace this `README.md`.

## Details

It uses the latest (LTS) versions at the date this template was created or
updated.

The project uses Java modules, but Gradle doesn't support this very well. That's
why it uses the `org.javamodularity.moduleplugin` to read Java modules when
building.
