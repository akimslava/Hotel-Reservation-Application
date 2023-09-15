plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "ru.akimslava.hotelreservation.feature.hotel"
    compileSdk = 34
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
    // Retrofit (for http exception)
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    // Coil
    implementation("io.coil-kt:coil-compose:2.4.0")
    // ViewModel
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    implementation(project(path = ":data:container"))
    implementation(project(path = ":data:service"))
    implementation(project(path = ":common:ui-kit"))
    implementation(project(path = ":common:theme"))
}