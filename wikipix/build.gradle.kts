import org.jetbrains.kotlin.gradle.plugin.mpp.Framework
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import com.example.arthingy.buildsrc.Libs
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    kotlin("native.cocoapods")
    id("com.android.library")
    id("kotlinx-serialization")
    id("com.squareup.sqldelight")
}

version = "1.0"

kotlin {
    android()

    ios()

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }

    sourceSets["commonMain"].dependencies {
        implementation(Libs.SqlDelight.runtime)
        implementation(Libs.SqlDelight.coroutinesExtensions)
        implementation(Libs.Ktor.commonCore)
        implementation(Libs.Ktor.commonJson)
        implementation(Libs.Ktor.commonLogging)
        implementation(Libs.Coroutines.coreNative) {
            version {
                strictly(Libs.Coroutines.nativeVersion)
            }
        }
        implementation(Libs.Ktor.commonSerialization)
        implementation(Libs.stately)
        api(Libs.kermit)
    }

    sourceSets["commonTest"].dependencies {
        implementation(kotlin("test-common"))
        implementation(kotlin("test-annotations-common"))
    }
    sourceSets["androidMain"].dependencies {
        implementation(kotlin("stdlib", Libs.Kotlin.version))
        implementation(Libs.SqlDelight.driverAndroid)
        implementation(Libs.Coroutines.androidNative)
        implementation(Libs.Ktor.androidCore)
    }
    sourceSets["androidTest"].dependencies {
        implementation(kotlin(Libs.KotlinTest.common))
        implementation(kotlin(Libs.KotlinTest.annotations))
        implementation(kotlin(Libs.KotlinTest.jvm))
        implementation(kotlin(Libs.KotlinTest.junit))
        implementation(Libs.junit)
    }

    sourceSets["iosMain"].dependencies {
        implementation(Libs.SqlDelight.driverIos)
        implementation(Libs.Ktor.ios)
        implementation(Libs.Coroutines.coreNative) {
            version {
                strictly(Libs.Coroutines.nativeVersion)
            }
        }
    }

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
    }

    targets.withType<KotlinNativeTarget> {
        binaries.withType<Framework> {
            isStatic = true
            export(Libs.kermit)
            transitiveExport = true
        }
    }
}

android {
    compileSdkVersion(30)
    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    defaultConfig {
        minSdkVersion(21)
        targetSdkVersion(30)
    }
}

sqldelight {
    database("WikipixCache") {
        packageName = "com.example.wikipix.data.source.local"
        sourceFolders = listOf("sqldelight")
    }
}