# stackoverflow-users

## Overview

This Android app primarily makes a call to the the StackOverflow Users Api and then displays users `display_name`, `profile_image` and `badge_counts`. It has a search feature which will query names based on your search. Upon download it will save the relevant information into the Room Database. Also for fun added a pagination feature so you can keep downloading pages of users if you want to. 

## Dependencies

```
    def room_version = "2.2.6"
    def lifecycle_version = "2.2.0"
    implementation 'com.squareup.retrofit2:retrofit:2.8.0'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.1'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.3.7'
    implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.3.7'
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycle_version"
    implementation "androidx.room:room-runtime:$room_version"
    kapt "androidx.room:room-compiler:$room_version"
    implementation "androidx.room:room-ktx:$room_version"
    implementation 'io.coil-kt:coil:0.9.5'
```

We use Room for internal storage. We use retrofit and coroutines for to make our HTTP Call. We use COIL to load our images and perform cacheing. 