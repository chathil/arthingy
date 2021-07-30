// Top-level build file where you can add configuration options common to all sub-projects/modules.
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask

buildscript {
    repositories {
        google()
    }
    dependencies {
        classpath(com.example.arthingy.buildsrc.Libs.androidGradlePlugin)
        classpath(com.example.arthingy.buildsrc.Libs.Kotlin.gradlePlugin)
        classpath(com.example.arthingy.buildsrc.Libs.Hilt.hiltGradlePlugin)
        classpath(com.example.arthingy.buildsrc.Libs.Kotlin.serializationPlugin)

        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle.kts files
    }
}


plugins {
    id("com.diffplug.spotless").version("5.12.4")
    id("com.github.ben-manes.versions").version("0.39.0")
}


subprojects {
    repositories {
        google()
        mavenCentral()
    }
//    apply(plugin = "java")
//    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply {
        plugin("com.diffplug.spotless")
//        plugin(org.jetbrains.kotlin.gradle.plugin.KotlinPlatformJvmPlugin::class)
    }
    spotless {
        kotlin {
            target("**/*.kt")
            targetExclude("$buildDir/**/*.kt")
            targetExclude("bin/**/*.kt")
            ktlint(com.example.arthingy.buildsrc.Versions.ktlint)
//            licenseHeaderFile rootProject.file('spotless/copyright.kt') /* add copyright header */
        }
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            // Treat all Kotlin warnings as errors
//            allWarningsAsErrors = true

            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.RequiresOptIn"

            // Enable experimental coroutines APIs, including Flow
            freeCompilerArgs =
                freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.ExperimentalCoroutinesApi"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlinx.coroutines.FlowPreview"
            freeCompilerArgs = freeCompilerArgs + "-Xopt-in=kotlin.Experimental"
            // Set JVM target to 1.8
            jvmTarget = "1.8"
        }
    }
}

fun isNonStable(version: String): Boolean {
    val stableKeyword = listOf("RELEASE", "FINAL", "GA").any { version.toUpperCase().contains(it) }
    val regex = "^[0-9,.v-]+(-r)?$".toRegex()
    val isStable = stableKeyword || regex.matches(version)
    return isStable.not()
}

tasks.withType<DependencyUpdatesTask> {
    rejectVersionIf {
        isNonStable(candidate.version) && !isNonStable(currentVersion)
    }
}
