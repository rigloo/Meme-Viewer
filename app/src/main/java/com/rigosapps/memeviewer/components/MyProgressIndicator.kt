package com.rigosapps.memeviewer.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun MyProgressIndicator(modifier: Modifier = Modifier) {

    Box(modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

        CircularProgressIndicator()

    }


}


