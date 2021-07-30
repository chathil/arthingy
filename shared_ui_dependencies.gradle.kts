val implementation by configurations
val kapt by configurations
val androidTestImplementation by configurations

dependencies {
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Paging.compose)

    implementation(com.example.arthingy.buildsrc.Libs.Kotlin.stdlib)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Room.legacySupport)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.coreKtx)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.appcompat)

    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.material)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.tooling)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.layout)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.materialIconsExtended)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.uiUtil)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.runtime)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.foundation)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.compiler)

    implementation(com.example.arthingy.buildsrc.Libs.Accompanist.insets)
    implementation(com.example.arthingy.buildsrc.Libs.Accompanist.glide)
    implementation(com.example.arthingy.buildsrc.Libs.Accompanist.pager)
    implementation(com.example.arthingy.buildsrc.Libs.Accompanist.pagerIndicators)

    androidTestImplementation(com.example.arthingy.buildsrc.Libs.AndroidX.Compose.uiTest)

    kapt(com.example.arthingy.buildsrc.Libs.Hilt.hiltCompiler)
    implementation(com.example.arthingy.buildsrc.Libs.Hilt.hiltAndroid)

    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.dataStore)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.navigation)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Activity.activityCompose)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Lifecycle.viewModelCompose)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.Lifecycle.runtimeKtx)
    implementation(com.example.arthingy.buildsrc.Libs.AndroidX.fragmentKtx)

    implementation(com.example.arthingy.buildsrc.Libs.material)

}