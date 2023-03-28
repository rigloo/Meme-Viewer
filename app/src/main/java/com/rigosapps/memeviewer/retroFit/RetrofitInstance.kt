package com.rigosapps.memeviewer.retroFit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {


    val memeApi: MemeApi by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://meme-api.com/").addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(MemeApi::class.java)


    }

    val newMemeApi: NewMemeApi by lazy {
        Retrofit
            .Builder()
            .baseUrl("https://www.reddit.com/").addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(NewMemeApi::class.java)


    }
}