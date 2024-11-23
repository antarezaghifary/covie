plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.plugin.dagger)
    alias(libs.plugins.org.jetbrains.kotlin.kapt)
}

android {
    namespace = "com.example.coremovie"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.coremovie"
        multiDexEnabled = true
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"

    }

    packagingOptions {
        exclude("META-INF/gradle/incremental.annotation.processors")
        exclude("META-INF/*")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
        debug {
            isMinifyEnabled = false
            buildConfigField("String", "BASE_URL", "\"https://api.themoviedb.org/3/\"")
            buildConfigField("String", "API_KEY", "\"34d28168ca773abb8e7098976e940a85\"")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
    }
    packagingOptions {
        exclude("META-INF/NOTICE")  // will not include NOTICE file
        exclude ("META-INF/LICENSE") // will not include LICENSE file
        exclude ("META-INF/notice")
        exclude ("META-INF/notice.txt")
        exclude ("META-INF/license")
        exclude ("META-INF/license.txt")
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    implementation(libs.multidex)
    implementation(libs.splash)

    //viewmodel
    implementation(libs.dagger)
    kapt(libs.daggerCompiler)

    implementation(libs.viewmodelAct)
    implementation(libs.viewmodelFrg)
    implementation(libs.gson)
    implementation(libs.retrofit)
    implementation(libs.retrofitConverter)
    implementation(libs.loggingInterceptor)
    implementation(libs.lifecycle)
    implementation(libs.livedata)

}
