import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget
import com.example.arthingy.buildsrc.Libs

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

    sourceSets {
        all {
            languageSettings.apply {
                useExperimentalAnnotation("kotlin.RequiresOptIn")
                useExperimentalAnnotation("kotlinx.coroutines.ExperimentalCoroutinesApi")
            }
        }
    }

    val iosTarget: (String, KotlinNativeTarget.() -> Unit) -> KotlinNativeTarget = when {
        System.getenv("SDK_NAME")?.startsWith("iphoneos") == true -> ::iosArm64
        else -> ::iosX64
    }

    iosTarget("ios") {}

    cocoapods {
        summary = "Some description for the Shared Module"
        homepage = "Link to the Shared Module homepage"
        ios.deploymentTarget = "14.1"
        frameworkName = "wikipix"
        // set path to your ios project podfile, e.g. podfile = project.file("../iosApp/Podfile")
    }
    
    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libs.SqlDelight.runtime)
                implementation(Libs.SqlDelight.coroutinesExtensions)
                implementation(Libs.Ktor.commonCore)
                implementation(Libs.Ktor.commonJson)
                implementation(Libs.Ktor.commonLogging)
                implementation(Libs.Coroutines.core) {
                    version {
                        strictly(Libs.Coroutines.version)
                    }
                }
                implementation(Libs.Koin.koinCore)
                implementation(Libs.Ktor.commonSerialization)
                api(Libs.kermit)
            }
        }
        val commonTest by getting {
            dependencies {
                implementation(kotlin("test-common"))
                implementation(kotlin("test-annotations-common"))
                implementation(Libs.Koin.koinTest)
            }
        }
        val androidMain by getting {
            dependencies {
                implementation(kotlin("stdlib", Libs.Kotlin.version))
                implementation(Libs.SqlDelight.driverAndroid)
                implementation(Libs.Coroutines.android)
                implementation(Libs.Ktor.androidCore)
            }
        }
        val androidAndroidTestRelease by getting
        val androidTest by getting {
            dependsOn(androidAndroidTestRelease)
            dependencies {
                implementation(kotlin(Libs.KotlinTest.common))
                implementation(kotlin(Libs.KotlinTest.annotations))
                implementation(kotlin(Libs.KotlinTest.jvm))
                implementation(kotlin(Libs.KotlinTest.junit))
                implementation(Libs.junit)
            }
        }
        val iosMain by getting {
            dependencies {
                implementation(Libs.SqlDelight.driverIos)
                implementation(Libs.Ktor.ios)
            }
        }
        val iosTest by getting
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