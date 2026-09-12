plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.ehailing"
    compileSdk = 34

    defaultConfig {
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        val mapsApiKey: String = project.findProperty("MAPS_API_KEY") as String? ?: ""
        manifestPlaceholders["MAPS_API_KEY"] = mapsApiKey
    }

    flavorDimensions += "app"
    productFlavors {
        create("rider") {
            dimension = "app"
            applicationId = "com.example.ehailing.rider"
            resValue("string", "app_name", "eHailing")
        }
        create("driver") {
            dimension = "app"
            applicationId = "com.example.ehailing.driver"
            resValue("string", "app_name", "eHailing Driver")
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions { jvmTarget = "17" }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions { kotlinCompilerExtensionVersion = "1.5.14" }
    packaging { resources { excludes += "/META-INF/{AL2.0,LGPL2.1}" } }
}

dependencies {
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.8.4")
    implementation("androidx.activity:activity-compose:1.9.1")
    implementation(platform("androidx.compose:compose-bom:2024.09.00"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")
    implementation("androidx.navigation:navigation-compose:2.8.0")
    implementation("com.google.maps.android:maps-compose:6.1.0")
    implementation("com.google.android.gms:play-services-maps:18.2.0")
    implementation("com.google.android.gms:play-services-location:21.3.0")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}