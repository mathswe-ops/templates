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

## Troubleshooting

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
