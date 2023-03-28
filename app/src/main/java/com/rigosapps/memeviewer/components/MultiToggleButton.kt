package com.rigosapps.memeviewer.components

import android.graphics.Color
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material.Colors
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.Unspecified
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.rigosapps.memeviewer.ui.theme.DarkPurple
import com.rigosapps.memeviewer.ui.theme.LightPurple

@Composable
fun MultiToggleButton(
    currentSelection: String,
    toggleStates: List<String>,
    onToggleChange: (String) -> Unit
) {
    val selectedTint = DarkPurple
    val unselectedTint = LightPurple

    Row(
        modifier = Modifier
            .height(IntrinsicSize.Min)
            .border(BorderStroke(1.dp, androidx.compose.ui.graphics.Color.Black))
    ) {
        toggleStates.forEachIndexed { index, toggleState ->
            val isSelected = currentSelection.lowercase() == toggleState.lowercase()
            val backgroundTint = if (isSelected) selectedTint else unselectedTint
            val textColor = if (isSelected)  androidx.compose.ui.graphics.Color.White else   androidx.compose.ui.graphics.Color.Unspecified

            if (index != 0) {
                Divider(
                    color = androidx.compose.ui.graphics.Color.DarkGray,
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                )
            }

            Row(
                modifier = Modifier
                    .background(backgroundTint)
                    .padding(vertical = 6.dp, horizontal = 8.dp)
                    .toggleable(
                        value = isSelected,
                        enabled = true,
                        onValueChange = { selected ->
                            if (selected) {
                                onToggleChange(toggleState)
                            }


                        })
            ) {
                Text(toggleState.uppercase(), color = textColor, modifier = Modifier.padding(4.dp))
            }

        }
    }
}