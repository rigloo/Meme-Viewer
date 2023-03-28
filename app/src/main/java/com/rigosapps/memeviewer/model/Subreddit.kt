package com.rigosapps.memeviewer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subreddit(
    @PrimaryKey(autoGenerate = true) val key: Long,
    @ColumnInfo(name = "subreddit") val title: String,


) {


}
