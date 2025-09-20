import com.android.build.api.dsl.ViewBinding
plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    //navigation
    alias(libs.plugins.navigation.safeargs )
    //parcelize
    id("org.jetbrains.kotlin.plugin.parcelize")
    //ksp
    id("com.google.devtools.ksp")

}

android {
    namespace = "com.example.pager"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.pager"
        minSdk = 28
        targetSdk = 36
        versionCode = 1
        versionName = "1.0"
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures{
        viewBinding = true
    }
}

dependencies {
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    //view pager indicator
    implementation("com.tbuonomo:dotsindicator:5.1.0")
    //navigation
    implementation("androidx.navigation:navigation-fragment-ktx:2.9.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.9.3")
    //glide
    implementation("com.github.bumptech.glide:glide:5.0.4")
    //lottie
    implementation(libs.lottie)
    //room
    val room_version = "2.8.0"

    implementation("androidx.room:room-runtime:$room_version")
    ksp("androidx.room:room-compiler:$room_version")
}