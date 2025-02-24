package com.example.mywishlistapp

import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.SwipeToDismiss
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.DummyData
import com.example.mywishlistapp.data.Wish

//its our scaffold basically a bar on top of app which allow us to go back
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun HomeView(navController: NavController,viewModel: WishViewModel){
    val context = LocalContext.current    // tells us where we are right now
    // this Scaffold which contains drawer info  its in built
    Scaffold (
        // this top bar is a composable from Scaffold so handle it like a composable
        topBar = {AppBarView(title = "WishList") {
            Toast.makeText(context, "back button clicked", Toast.LENGTH_LONG).show()}
        },
        // this floating action button is a composable from Scaffold so handle it like a composable
        floatingActionButton = {
            FloatingActionButton(modifier = Modifier.padding(all = 20.dp), contentColor = Color.Cyan,
                backgroundColor = Color.Black,
                onClick = { Toast.makeText(context,"add button clicked",Toast.LENGTH_LONG).show() 
                navController.navigate(Screen.AddScreen.route + "/0L")}) {
                Icon(imageVector = Icons.Default.Add, contentDescription = null)
            }
        }
    ){
        val wishlist = viewModel.getallwishes.collectAsState(initial = emptyList()) // listof()
        LazyColumn(modifier = Modifier
            .fillMaxSize()
            .padding(it),){
            // for lazy columns items are used
            items(wishlist.value, key = {wish-> wish.id}){  //wishlist.value
                // adding swipe to delete functionality in app
                wish ->
                    val dismisstate = rememberDismissState(
                        confirmStateChange = {
                            if(it == DismissValue.DismissedToEnd || it==DismissValue.DismissedToStart){
                                viewModel.deletewish(wish)
                            }
                            true
                        }
                    )
                    SwipeToDismiss(state = dismisstate,
                        background = {
                            val color by animateColorAsState(
                                if(dismisstate.dismissDirection == DismissDirection.EndToStart)
                                {Color.Black} else {Color.Transparent}, label = ""
                            )
                            val alignment = Alignment.CenterEnd
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = alignment
                            ){
                                Icon(imageVector = Icons.Default.Delete,contentDescription = null, tint = Color.White)
                            }
                        },
                        directions = setOf(DismissDirection.EndToStart),
                        dismissThresholds = { FractionalThreshold(1f) },
                        dismissContent = { wishList(wish = wish){
                           val id = wish.id
                           navController.navigate(Screen.AddScreen.route + "/$id")
                        }
                    }
                )
            }
        }
    }
}
@Composable
// Wish is a data class
// start is left , end is right 
fun wishList(wish: Wish, onClick: () -> Unit){
    // looks nicely than making column and adding text field and can be like button i.e clickable
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(top = 8.dp, start = 8.dp, end = 8.dp)
        .clickable { onClick() }, elevation = 10.dp, backgroundColor = Color.White) 
    {
        Column (modifier = Modifier.padding(16.dp)){
            Text(text = wish.title, fontWeight = FontWeight.ExtraBold)
            Text(text = wish.discription)
        }
    }
}