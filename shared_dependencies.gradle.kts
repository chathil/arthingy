val implementation by configurations
val kapt by configurations
val androidTestImplementation by configurations
val testImplementation by configurations

dependencies {
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Paging.runtime)

    implementation(com.example.arthingy.buildsrc.Libs.Kotlin.stdlib)
    implementation(com.example.arthingy.buildsrc.Libs.store)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.appcompat)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.coreKtx)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Room.legacySupport)

    implementation(com.example.arthingy.buildsrc.Libs.Coroutines.core)
    implementation(com.example.arthingy.buildsrc.Libs.Coroutines.android)
    implementation(com.example.arthingy.buildsrc.Libs.Coroutines.test)
    implementation(com.example.arthingy.buildsrc.Libs.Coroutines.playServices)

    kapt(com.example.arthingy.buildsrc.Libs.Hilt.hiltCompiler)
    implementation(com.example.arthingy.buildsrc.Libs.Hilt.hiltAndroid)

    testImplementation(com.example.arthingy.buildsrc.Libs.junit)
    androidTestImplementation(com.example.arthingy.buildsrc.Libs.AndroidX.Test.Ext.junit)
    androidTestImplementation(com.example.arthingy.buildsrc.Libs.AndroidX.Test.core)
    androidTestImplementation(com.example.arthingy.buildsrc.Libs.AndroidX.Test.rules)
    androidTestImplementation(com.example.arthingy.buildsrc.Libs.AndroidX.Test.espressoCore)


}