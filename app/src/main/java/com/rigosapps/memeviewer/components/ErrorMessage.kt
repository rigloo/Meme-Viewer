package com.rigosapps.memeviewer.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ErrorMessage(modifier: Modifier = Modifier) {

    Box(
        modifier
            .fillMaxSize()
            .padding(10.dp), contentAlignment = Alignment.Center
    ) {

        Column() {

            Text(
                text = "This is embarrassing...",
                textAlign = TextAlign.Center,
                fontSize = 30.sp,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )
            Text(
                text = "For some reason could not load the content . Either your internet connection is unstable or the subreddit name is invalid.",
                textAlign = TextAlign.Center,
                fontSize = 20.sp,
                color = Color.White
            )

        }


    }


}