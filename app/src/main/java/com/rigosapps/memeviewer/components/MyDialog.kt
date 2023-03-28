package com.rigosapps.memeviewer.components

import android.R

import android.graphics.Color
import android.graphics.fonts.FontFamily
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.MagnifierStyle.Companion.Default
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Cancel
import androidx.compose.material.icons.filled.Money
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Transparent
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.rigosapps.memeviewer.ui.theme.Gray
import com.rigosapps.memeviewer.ui.theme.LightPurple
import com.rigosapps.memeviewer.ui.theme.Purple200
import com.rigosapps.memeviewer.ui.theme.secondaryColor

@Composable
fun MyDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            shape = RoundedCornerShape(16.dp), color = LightPurple
        ) {
            Box(
                contentAlignment = Alignment.Center
            ) {
                Column(modifier = Modifier.padding(20.dp)) {

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "Enter exact name of subreddit", style = TextStyle(
                                fontSize = 24.sp,

                                fontWeight = FontWeight.Bold
                            ), textAlign = TextAlign.Center
                        )
                        Icon(imageVector = Icons.Filled.Cancel,
                            contentDescription = "",
                            tint = Gray,
                            modifier = Modifier
                                .width(30.dp)
                                .height(30.dp)
                                .clickable { setShowDialog(false) })
                    }

                    Spacer(modifier = Modifier.height(20.dp))

                    Box(
                        contentAlignment = Alignment.Center
                        ,
                        modifier = Modifier
                            .background(color = LightPurple)
                            .fillMaxWidth()
                            .border(
                                BorderStroke(
                                    width = 2.dp,
                                    color = colorResource(id = if (txtFieldError.value.isEmpty()) R.color.holo_green_light else R.color.holo_red_dark)
                                ), shape = RoundedCornerShape(50)
                            )
                    ) {
                        TextField(
                            shape = RoundedCornerShape(50.dp),
                            colors = TextFieldDefaults.textFieldColors(
                                backgroundColor = White,
                                focusedIndicatorColor = secondaryColor,
                                unfocusedIndicatorColor = LightPurple,
                                textColor = Black
                            ),

                            placeholder = {
                                Text(
                                    text = "ex. \"wholesomememes\"",
                                    textAlign = TextAlign.Center
                                )
                            },
                            value = txtField.value,
                            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Ascii),
                            onValueChange = {
                                txtField.value = it.take(20)
                            },
                        )
                    }


                    Spacer(modifier = Modifier.height(20.dp))

                    Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                        Button(
                            onClick = {
                                if (txtField.value.isEmpty()) {
                                    txtFieldError.value = "Field can not be empty"
                                    return@Button
                                }
                                setValue(txtField.value)
                                setShowDialog(false)
                            },
                            shape = RoundedCornerShape(50.dp),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(50.dp)
                        ) {
                            Text(text = "Done")
                        }
                    }
                }
            }
        }
    }
}

