plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    id("kotlin-parcelize")
    id("androidx.navigation.safeargs.kotlin")

}

android {
    namespace = "com.shubham.assignment"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.shubham.assignment"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }





    buildTypes {
        release {
            isMinifyEnabled = true
            multiDexEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug{
            isMinifyEnabled = false
            multiDexEnabled = false
            isShrinkResources = false

        }
    }

    viewBinding{
        enable = true
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

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    // requiredDependencies

    // Glide
    implementation(libs.glide)
    // livedata and viewModel
    implementation(libs.androidx.viewModel)
    implementation(libs.androidx.navigationUI)
    implementation(libs.androidx.navigation)
    implementation(libs.androidx.liveData)
    implementation(libs.androidx.viewModelSavedState)
    implementation(libs.kapt.viewModelAnnotationProcessor)

    implementation(libs.sdp)
    implementation(libs.ssp)

    implementation(libs.retrofit)
    implementation(libs.retrofit.gsonFactory)
    implementation(libs.jetbrains.coroutines)
    implementation(libs.androidx.swipeToRefresh)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}