import com.example.arthingy.buildsrc.Libs

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlinx-serialization")
    id("kotlin-parcelize")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
}

apply {
    from("../shared_dependencies.gradle.kts")
}


android {
    compileSdk = 30
    buildToolsVersion = "30.0.3"

    defaultConfig {
        minSdk = 21
        targetSdk = 30
        

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(com.example.arthingy.buildsrc.Libs.kotlinxSerialization)
    implementation(com.example.arthingy.buildsrc.Libs.kotlinxCollections)

    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Room.runtime)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Room.ktx)
    kapt(com.example.arthingy.buildsrc.Libs.AndroidX.Room.compiler)

    implementation(com.example.arthingy.buildsrc.Libs.Retrofit.retrofit)
    implementation(com.example.arthingy.buildsrc.Libs.Retrofit.coroutinesAdapter)
    implementation(com.example.arthingy.buildsrc.Libs.Retrofit.kotlinxSerializationConverter)
}





