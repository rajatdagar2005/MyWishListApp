package com.example.mywishlistapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlistapp.data.Wish
import kotlinx.coroutines.launch
import java.time.format.TextStyle

@Composable
fun AddEditDetailView(
    id:Long,
    viewModel: WishViewModel,
    navController: NavController
){


    // for database storing
    //This is typically used for displaying transient messages, such as in a Snackbar, in response to some user action or state change.
    val snackmessage = remember {  // this is message different than toast message
        mutableStateOf("")
    }
    val scope = rememberCoroutineScope()  //to run method such as storing data in database
    val scaffoldState = rememberScaffoldState()  // to use scaffold basically like pop up

    if (id!=0L){
        val wish = viewModel.getawishbyit(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState = wish.value.title
        viewModel.wishDescriptionState = wish.value.discription
    }else{
        viewModel.wishTitleState = ""
        viewModel.wishDescriptionState = ""
    }

    Scaffold(
        topBar = { AppBarView(title = if(id!=0L) {
            stringResource(id = R.string.update_wish)} else {
            stringResource(id = R.string.add_wish)}) {navController.navigateUp()}} ,
        scaffoldState = scaffoldState
        //{navController.navigateUp()}  to go back
    ) {
        Column(modifier = Modifier
            .padding(it)
            .wrapContentSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Title", value = viewModel.wishTitleState, onValueChanged = {
                viewModel.onwishtitlechanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            WishTextField(label = "Description", value = viewModel.wishDescriptionState, onValueChanged = {
                viewModel.onwishDescriptionchanged(it)
            })
            Spacer(modifier = Modifier.height(10.dp))
            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() && viewModel.wishDescriptionState.isNotEmpty()){
                    if(id != 0L){
                        // update wish
                        viewModel.updatewish(Wish(
                            id = id,
                            title = viewModel.wishTitleState.trim(),
                            discription = viewModel.wishDescriptionState.trim()
                        ))
                    }
                    else{
                        // add wish
                        viewModel.addwish(
                            Wish(
                            title = viewModel.wishTitleState.trim(),
                            discription = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackmessage.value = "wish has been created "
                    }
                }
                else{
                    // enter fields for wish to be created
                    snackmessage.value = "enter fields to create a wish "
                }
                scope.launch {
                    //scaffoldState.snackbarHostState.showSnackbar(snackmessage.value)
                    navController.navigateUp()
                }
            }, modifier = Modifier
                .size(
                    width = 150.dp,
                    height = 50.dp
                ) // Set fixed width and height for a rectangular shape
                .background(Color.Black), // Set background color to black
                colors = ButtonDefaults.buttonColors(Color.Black) // Ensure button uses black color
                ) {
                Text(text = if(id!=0L) {
                    stringResource(id = R.string.update_wish)} else {
                    stringResource(id = R.string.add_wish)},
                    style = androidx.compose.ui.text.TextStyle(fontSize = 18.sp)
                )
            }
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishTextField(label:String,value:String,onValueChanged:(String) -> Unit){
    OutlinedTextField(value = value, onValueChange = onValueChanged,
        label = {Text(text = label, color = Color.Black)},
        modifier = Modifier.fillMaxWidth(), keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = TextFieldDefaults.outlinedTextFieldColors(
            // using predefined colours
            focusedTextColor = Color.Black,
            // using our own colours
            unfocusedBorderColor = colorResource(id = R.color.black),
            focusedBorderColor = colorResource(id = R.color.black),
            disabledBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black)
        )
    )
}
@Preview(showBackground = true)
@Composable
fun WishTestFieldPrev(){
    WishTextField(label = "text", value = "text", onValueChanged = {})
}