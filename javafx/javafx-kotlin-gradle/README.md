# App (JavaFX + Kotlin + Gradle)

This template provides a project from scratch with support for:

- JavaFX
- Kotlin
- Gradle + KTS
- ArrowKt
- Kotlin Test with JUnit
- JPackage (Linux + Windows)

## Setup Template

- Add more JavaFX modules, if needed. Update [javafx.modules](build.gradle.kts)
  and [module-info.java](src/main/java/module-info.java).
- Replace the template code
  in [Main.kt](src/main/kotlin/com/mathswe/app/Main.kt).
- Rename the app and package name in
    - [settings.gradle.kts](settings.gradle.kts).
    - [module-info.java](src/main/java/module-info.java).
    - [package](src/main/kotlin).
    - [test package](src/test/kotlin).
    - [build.gradle.kts](build.gradle.kts).
- Replace `app_name` and `app_id`
  in [jpackage/linux/postinst](jpackage/linux/postinst).
- Replace this `README.md`.

## MathSweKt

This template has an in-house library [mathswekt](src/main/kotlin/mathswekt) to
use across any MathSwe Kotlin application.

## Ensuring new Changes Work

It is important to check essential commands don't break when changing anything
on the project or changing the OS or its state (libraries installed, etc.).

Run:

- `./gradlew clean build`
- `./gradlew wrapper`
- `./gradlew run`
- `./gradlew jlink`
- `./gradlew installDist`
- `jpackage`:
    - `./gradlew jpackage` (for non-Debian)
    - `./gradlew jpackage -PinstallerType=deb` (for Debian if the above didn't
      work)

Notice `jpackage` receives a `installerType` argument to make it work on Ubuntu,
since the `RPM` installer doesn't seem to work on some projects and Ubuntu. Try
`./gradlew jpackage` in your project, first. If it doesn't work in Ubuntu, pass
the argument.

To ensure the project is not broken, and *your system side effects**, like JDK
installed, OS tools (RPM, etc.), do not break the project.

## Application Plugin

Ensure to use the normal `mainClass` instead of the `Kt` suffixed for JavaFX
applications. For example, use `com.mathswe.app.Main` instead of
`com.mathswe.app.MainKt`.

The `Kt` version seems necessary for normal applications, but it is not for
JavaFX apps, and if you leave the `Kt` suffix, the `./gradle run --args=""`
won't pass the arguments to the JavaFX app.

## ArrowKt

The ArrowKt library should be used across any MathSwe Kotlin project to enhance
FP.

It presents a major issue since it doesn't fully support JPMS. One workaround
that might work is to add it with excluded offending modules:

```kotlin
val arrowVersion = "1.2.4"

implementation("io.arrow-kt:arrow-core:$arrowVersion") {
    // If you include all ArrowKt modules, the JPMS will fail when building:
    //
    // Error occurred during initialization of boot layer
    // java.lang.module.ResolutionException: Modules arrow.autocloseable
    // and arrow.annotations export package arrow to module arrow.continuations
    exclude(group = "io.arrow-kt", module = "arrow-autocloseable")
    exclude(group = "io.arrow-kt", module = "arrow-annotations")
}
implementation("io.arrow-kt:arrow-fx-coroutines:$arrowVersion")
```

Another way is to add it normally without exclusions and disable the Java
modules, which will impact `jlink`.

## Details

It uses the latest (LTS) versions at the date this template was created or
updated.

The project uses Java modules, but Gradle doesn't support this very well. That's
why it uses the `org.javamodularity.moduleplugin` to read Java modules when
building.

### Need to Test

#### JPackage Test Debt

I need to test the `jpackage` configuration in Windows. I copied it (and
refactored it) from a project (`tsdfx`) which installer worked on Windows, but
still have to test it in this template project.

In general, `jpackage` should be tested in multiple platforms, like Mac and
non-Debian distributions. There's the limitation I mentioned above when running
it on Ubuntu, since it can only generate the `deb` but not the `rpm`
installer (on my machine, at least).

All the detailed configurations in different platforms must be overridden with
proper values (icon, launcher, etc.).
[Override JPackage Resources](https://docs.oracle.com/en/java/javase/15/jpackage/override-jpackage-resources.html)

## Troubleshooting

### Printing Module Info

These values can provide insights to debug the module and class path.

```kotlin
println(System.getProperty("jdk.module.path"))
println("Classpath: ${System.getProperty("java.class.path")}")
println("Module: ${Main::class.java.module}")
```

### Fail to Load Resources

**Problem:** You can only load resources when their directory name has a hyphen,
despite the JAR and everything contains all the files. For example, it finds
`pr-cover/file.txt` but not `fonts/Poppins.ttf`.

---

I've had nonsense side effect bugs in other Gradle projects.

One cause of bugs is Java modules since tools don't support them very well.

The `org.javamodularity.moduleplugin` helps Gradle build modular
(`module-info.java`) applications, but it seems to cause more side effects.

One of the main cryptic issues is loading files from `resources`. The app loads
resources correctly if you run it from the IDE and a standalone file with
`fun main`. If you `./gradlew run` (i.e., the `application` +
`moduleplugin` plugins), it becomes cryptic.

It only reads `resources` if the directory has hyphens, like `pr-cover`, but it
doesn't find files if they have normal directory names, like `fonts`.

You can use this code to debug this nonsense, considering the Java modular app
with the `application` and `moduleplugin` plugins cause this.

```kotlin
fun main() {
    val mediumFontUrl =
        Resources::class.java.classLoader.getResource("fonts/Poppins/Poppins-Medium.ttf")
    val boldFontUrl =
        Resources::class.java.classLoader.getResource("fonts/Poppins/Poppins-Bold.ttf")
    val otherUrl = Resources::class.java.classLoader
        .getResource("other/mswe.png")

    val otherUrlWithHyphen = Resources::class.java.classLoader
        .getResource("other-hyphened/mswe.png")

    println("Working directory: " + System.getProperty("user.dir"))
    println("Classpath: ${System.getProperty("java.class.path")}")

    println()
    println("Medium font URL: $mediumFontUrl")
    println("Bold font URL: $boldFontUrl")
    println("Other URL: $otherUrl")
    println("Hyphen URL: $otherUrlWithHyphen")
    println()
}
```

```kotlin
class Resources

fun resPath(path: String): String = Resources::class
    .java
    .classLoader
    .getResource(path)
    ?.toURI()
    .toString()
```

Make sure the `Classpath` log returns the appropriate value instead of empty.

To disable modules and debug, you should comment the `moduleplugin` and
`application.mainModule` property. Rename the `src/main/java` dir to
`src/main/_java`.

Resource loading works correctly when disabling modules.

---

**Solution:** For modular apps, don't use the `classLoader` and relative paths.
Instead, read resources with their absolute path without the `classLoader`. The
module plugin removes the `java.class.path` system property.

For example:
`Resources::class.java.getResource("/fonts/Poppins/Poppins-Medium.ttf")`,
instead of
`Resources::class.java.classLoader.getResource("fonts/Poppins/Poppins-Medium.ttf")`
