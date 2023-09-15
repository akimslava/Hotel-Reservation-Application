plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("org.jetbrains.kotlin.plugin.serialization") version "1.8.10"
}

android {
    namespace = "ru.akimslava.hotelreservation.data.container"
    compileSdk = 34
    kotlinOptions {
        jvmTarget = "1.8"
    }
}
dependencies {
    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Kotlin Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
    // Kotlin Serialization Converter
    implementation("com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:1.0.0")
    // For toMediaType()
    implementation("com.squareup.okhttp3:okhttp:4.11.0")

    implementation(project(path = ":data:service"))
}