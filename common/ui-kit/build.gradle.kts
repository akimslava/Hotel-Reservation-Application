plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.akimslava.hotelreservation.common.uikit"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.4.2"
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    // For Pager
    implementation("androidx.compose.foundation:foundation-android:1.5.1")
    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")

    implementation(project(path = ":common:theme"))
    implementation(project(path = ":common:util"))
}