package com.rigosapps.memeviewer.model

data class Data(
    val after: String,
    val children: List<Children>,
    val dist: Int,
    val geo_filter: String,
    val modhash: String
)