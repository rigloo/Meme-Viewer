package com.rigosapps.memeviewer.helpers

import com.rigosapps.memeviewer.model.Meme
import com.rigosapps.memeviewer.model.MemeEntity
import com.rigosapps.memeviewer.model.NewMeme
import com.rigosapps.memeviewer.model.NewMemeList


fun MemeToMemeEntity(meme: Meme): MemeEntity {

    return MemeEntity(0, meme.title, meme.url)


}

fun MemeEntityToMeme(meme: MemeEntity): Meme {

    return Meme(
        title = meme.title,
        url = meme.url,
        author = "",
        nsfw = false,
        postLink = "",
        preview = listOf<String>(),
        spoiler = false,
        subreddit = "",
        ups = 0

    )


}

fun NewMemeToMemeEntity(meme: NewMeme): MemeEntity {

    return MemeEntity(0, meme.title, meme.url )


}
