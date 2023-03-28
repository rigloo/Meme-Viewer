package com.rigosapps.memeviewer.retroFit

import com.rigosapps.memeviewer.model.NewMemeList
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NewMemeApi {


//GET 50 top memes of the week
    @GET("/r/{subreddit}/top/.json?t=week")
    suspend fun getTopMemes(
        @Path("subreddit") subreddit: String,
        @Query("limit") count: Int
    ): Response<NewMemeList>

    //https://www.reddit.com/r/surrealmemes/random.json



//    https://www.reddit.com/r/surrealmemes/hot/.json
    //Get Hot Memes

    @GET("/r/{subreddit}/hot/.json")
    suspend fun getHotMemes(
        @Path("subreddit") subreddit: String,
        @Query("limit") count: Int
    ): Response<NewMemeList>












}