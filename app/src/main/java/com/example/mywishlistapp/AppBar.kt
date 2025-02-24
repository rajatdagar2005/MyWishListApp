package com.example.mywishlistapp

import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp

@Composable
fun AppBarView(
    title: String,
    onBackNavClicked: () -> Unit = {}  // make our own Lamda function
){
    val navigationIcon: (@Composable () -> Unit)? =
        if(!title.contains("WishList")){
            {
                // adding functionality so that back button will be visible when we are not
                // no home screen
                IconButton(onClick = { onBackNavClicked() }) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = null,
                        tint = Color.White) // auto mirrored for urdu
                }
            }
        }
    else{
        null
    }

    TopAppBar(  // inbuilt like column
        title = {
        Text(text = title,
            color = colorResource(id = R.color.teal_200) ,
            modifier = Modifier
                .padding(start = 4.dp)
                .heightIn(max = 24.dp))
    },
        elevation = 3.dp,
        backgroundColor = colorResource(id = R.color.app_bar_color),
        navigationIcon = navigationIcon
    )  // R.color.white means res -> values -> colours -> white
}