plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.app.incroyable.quickgrid"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.incroyable.quickgrid"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {

    implementation(project(":mylibrary"))

    implementation(libs.core)
    implementation(libs.glide)
    implementation(libs.dexter)
    implementation(libs.library)
    implementation(libs.material)
    implementation(libs.utils.v4)
    implementation(libs.compressor)
    implementation(libs.androidx.core)
    implementation(libs.android.gpuimage)
    implementation(libs.androidx.collection)
    implementation(libs.universal.image.loader)
    implementation(libs.avloadingindicatorview)
    implementation(libs.androidx.percentlayout)

    implementation(libs.carbon) {
        exclude(group = "com.android.support")
    }
    implementation(libs.easydialog) {
        exclude(group = "com.android.support")
    }
    implementation(libs.android.image.cropper) {
        exclude(group = "com.android.support")
    }
}