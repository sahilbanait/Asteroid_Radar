# Asteroid_Radar
MVVM + Retrofit + Couroutine + Room + RecyclerView + Picasso + SafeArgs + Glide

This app extract the asteroids using NASA Api


# 1.
![Screenshot 2021-10-19 201537](https://user-images.githubusercontent.com/77091616/137907394-62a6557a-0cbd-4d23-abbe-813f31c8ce9b.jpg)

# 2.
![Screenshot 2021-12](https://user-images.githubusercontent.com/77091616/137909864-d4bae9a6-c8b9-425d-8558-fea43dccd4fd.jpg)


# Dependencies

    implementation fileTree(dir: 'libs', include: ['*.jar'])![Uploading Screenshot 2021-10-19 201537.jpg…]()

    implementation 'org.jetbrains.kotlin:kotlin-stdlib:1.5.31'
    implementation 'androidx.appcompat:appcompat:1.3.1'
    implementation 'androidx.core:core-ktx:1.6.0'
    implementation 'androidx.constraintlayout:constraintlayout:2.1.1'

    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.3.1"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.3.1"
    implementation 'androidx.fragment:fragment-ktx:1.3.6'

    implementation "android.arch.navigation:navigation-fragment-ktx:1.0.0"
    implementation "android.arch.navigation:navigation-ui-ktx:1.0.0"

    implementation "com.squareup.moshi:moshi:1.11.0"
    implementation "com.squareup.moshi:moshi-kotlin:1.11.0"

    implementation "com.squareup.retrofit2:retrofit:2.9.0"
    implementation "com.squareup.retrofit2:converter-moshi:2.9.0"
    implementation 'com.squareup.retrofit2:converter-scalars:2.9.0'

    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-core:1.5.0"
    implementation "org.jetbrains.kotlinx:kotlinx-coroutines-android:1.5.0"
    implementation "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2"

    implementation "androidx.recyclerview:recyclerview:1.2.1"

    implementation 'com.squareup.picasso:picasso:2.71828'

    implementation "androidx.room:room-runtime:2.3.0"
    kapt "androidx.room:room-compiler:2.3.0"
    implementation "androidx.room:room-ktx:2.3.0"

    implementation 'com.github.bumptech.glide:glide:4.12.0'
    annotationProcessor 'com.github.bumptech.glide:compiler:4.12.0'

    implementation "android.arch.work:work-runtime-ktx:1.0.1"

    testImplementation 'junit:junit:4.13.2'
    androidTestImplementation 'androidx.test.ext:junit:1.1.3'
    androidTestImplementation 'androidx.test.espresso:espresso-core:3.4.0'
