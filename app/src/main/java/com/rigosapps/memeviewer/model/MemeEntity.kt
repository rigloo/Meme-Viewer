package com.rigosapps.memeviewer.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class MemeEntity(
    @PrimaryKey(autoGenerate = true) val key: Long,
    @ColumnInfo(name = "name") val title: String,
    @ColumnInfo(name = "url") val url: String

) {


}
