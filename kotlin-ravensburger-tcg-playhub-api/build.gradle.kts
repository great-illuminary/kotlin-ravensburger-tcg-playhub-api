plugins {
    alias(additionals.plugins.android.library)
    alias(additionals.plugins.kotlin.multiplatform)
    alias(additionals.plugins.kotlin.serialization)
    id("jvmCompat")
    id("publication")
}

kotlin {
    androidTarget {
        publishLibraryVariants("release")
    }

    jvm()

    js(IR) {
        browser()
    }

    iosX64()
    iosArm64()
    iosSimulatorArm64()

    mingwX64()

    macosX64()
    macosArm64()
    watchosX64()
    watchosArm32()
    watchosArm64()

    linuxX64()
    linuxArm64()

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(additionals.kotlinx.coroutines)
                api(additionals.kotlinx.serialization.json)
                api(additionals.kotlinx.datetime)
                implementation(additionals.multiplatform.http.client)
            }
        }

        val commonTest by getting {
            dependencies {
                api(additionals.multiplatform.file.access)
                api(kotlin("test"))
                api(additionals.kotlinx.coroutines.test)
            }
        }
    }
}

android {
    namespace = "eu.codlab.ravensburger.playhub"
}