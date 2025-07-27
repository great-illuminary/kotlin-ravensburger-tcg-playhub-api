** Ravensburger's TCG Playhub API **

[
![Discord](https://img.shields.io/badge/Discord-Lorcana_Manager-blue)
](https://discord.gg/q9JRn8zjRS)

![badge](https://img.shields.io/badge/json-kotlin-green)
![badge](https://img.shields.io/badge/android-blue)
![badge](https://img.shields.io/badge/ios-white)
![badge](https://img.shields.io/badge/js-yellow)
![badge](https://img.shields.io/badge/jvm-red)
![badge](https://img.shields.io/badge/linux-blue)
![badge](https://img.shields.io/badge/windows-blueviolet)
![badge](https://img.shields.io/badge/mac-orange)

Kotlin Multiplatform library giving you access to the Ravensburger's tcg play hub api

# Documentation

Install the lib inside gradle using 

```gradle
implementation("eu.codlab:kotlin-ravensburger-tcg-playhub-api:$version")
```

# Usage

```
class MyBusinessLogic() {
    val loader = LoadRPHCall()

    suspend fun events() = loader.events()
}
```

# Join Us

Join the Discord server right now to get help, provide feedback & so much more.

[
![Discord](https://img.shields.io/badge/Discord-Lorcana_Manager-blue)
](https://discord.gg/q9JRn8zjRS)
