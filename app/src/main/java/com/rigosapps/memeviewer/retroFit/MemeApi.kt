package com.rigosapps.memeviewer.retroFit

import androidx.lifecycle.LiveData
import com.rigosapps.memeviewer.model.Meme
import com.rigosapps.memeviewer.model.MemeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MemeApi {



    @GET("/gimme/{subreddit}/{count}")
    suspend fun getMemes(
        @Path("subreddit") subreddit: String,
        @Path("count") count: Int
    ): Response<MemeList>





}