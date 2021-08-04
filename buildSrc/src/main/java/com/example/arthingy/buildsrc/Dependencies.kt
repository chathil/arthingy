package com.example.arthingy.buildsrc

object Versions {
    const val ktlint = "0.41.0"
}

object Libs {
    /**
     * The latest AGP is causing this error
     * "abstract method "void androidx.lifecycle.DefaultLifecycleObserver.onCreate(androidx.lifecycle.LifecycleOwner)"
     */
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"
    const val ktLint = "com.pinterest:ktlint:0.41.0"
    const val junit = "junit:junit:4.13.2"
    const val material = "com.google.android.material:material:1.4.0"
    const val kotlinxSerialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.2"
    const val kotlinxCollections = "org.jetbrains.kotlinx:kotlinx-collections-immutable-jvm:0.3.4"
    const val hiltNavigationCompose = "androidx.hilt:hilt-navigation-compose:1.0.0-alpha03"
    const val store = "com.dropbox.mobile.store:store4:4.0.2-KT15"
    const val fluentIcons = "com.microsoft.design:fluent-system-icons:1.1.136@aar"
    // Used in KMM module
    const val kermit = "co.touchlab:kermit:0.1.9"

    // Used in KMM module
    object SqlDelight {
        private const val version = "1.5.1"
        const val gradlePlugin = "com.squareup.sqldelight:gradle-plugin:$version"
        const val runtime = "com.squareup.sqldelight:runtime:$version"
        const val coroutinesExtensions = "com.squareup.sqldelight:coroutines-extensions:$version"
        const val runtimeJdk = "com.squareup.sqldelight:runtime-jvm:$version"
        const val driverIos = "com.squareup.sqldelight:native-driver:$version"
        const val driverAndroid = "com.squareup.sqldelight:android-driver:$version"
    }

    // Used in KMM module
    object Ktor {
        private const val version = "1.5.2"
        const val commonCore = "io.ktor:ktor-client-core:$version"
        const val commonJson = "io.ktor:ktor-client-json:$version"
        const val commonLogging = "io.ktor:ktor-client-logging:$version"
        const val androidCore = "io.ktor:ktor-client-okhttp:$version"
        const val ios = "io.ktor:ktor-client-ios:$version"
        const val commonSerialization = "io.ktor:ktor-client-serialization:$version"
        const val serialization = "io.ktor:ktor-serialization:$version"
    }

    // Used in KMM module
    object Koin {
        private const val version = "3.1.2"
        const val koinCore = "io.insert-koin:koin-core:$version"
        const val koinTest = "io.insert-koin:koin-test:$version"
    }

    object Hilt {
        private const val version = "2.38.1"
        const val hiltCompiler = "com.google.dagger:hilt-android-compiler:$version"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$version"
    }

    object Kotlin {
        const val version = "1.5.10"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
        const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$version"
    }

    object KotlinTest {
        const val common = "test-common"
        const val annotations = "test-annotations-common"
        const val jvm = "test"
        const val junit = "test-junit"
    }

    object Retrofit {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val coroutinesAdapter =
            "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"
        const val kotlinxSerializationConverter =
            "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
    }

    object Coroutines {
        const val version = "1.5.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:$version"
    }

    object Accompanist {
        private const val version = "0.15.0"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
        const val pager = "com.google.accompanist:accompanist-pager:$version"
        const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"
    }

    object AndroidX {
        const val appcompat = "androidx.appcompat:appcompat:1.3.1"
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha05"
        const val dataStore = "androidx.datastore:datastore-preferences:1.0.0-rc02"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:1.3.6"
        const val palette = "androidx.palette:palette:1.0.0"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.0"
        }

        object Paging {
            private const val version = "3.0.1"
            const val runtime = "androidx.paging:paging-runtime:$version"
            const val compose = "androidx.paging:paging-compose:1.0.0-alpha12"
        }

        object Compose {
            const val snapshot = ""
            const val version = "1.0.0"

            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val material = "androidx.compose.material:material:$version"
            const val materialIconsExtended =
                "androidx.compose.material:material-icons-extended:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val test = "androidx.compose.ui:ui-test:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val compiler = "androidx.compose.compiler:compiler:$version"
        }

        object Room {
            private const val version = "2.3.0"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val legacySupport = "androidx.legacy:legacy-support-v4:1.0.0"
            const val compiler = "androidx.room:room-compiler:$version"
        }

        object Test {
            private const val version = "1.4.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.3"
                const val junit = "androidx.test.ext:junit-ktx:$version"

            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.4.0"
        }

        object Lifecycle {
            private const val version = "2.3.1"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$version"
            const val runtimeKtx = "androidx.lifecycle:lifecycle-runtime-ktx:2.4.0-alpha02"
            const val viewModelCompose =
                "androidx.lifecycle:lifecycle-viewmodel-compose:1.0.0-alpha07"
        }

    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.3.1"
    }

}

