package com.example.mywishlistapp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mywishlistapp.data.Wish
import com.example.mywishlistapp.data.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch

class WishViewModel(
    private val wishRepository:WishRepository = graph.wishRepository
):ViewModel() {
    var wishTitleState by mutableStateOf("")
    var wishDescriptionState by mutableStateOf("")

    fun onwishtitlechanged(newString: String){
        wishTitleState = newString
    }
    fun onwishDescriptionchanged(newString: String){
        wishDescriptionState = newString
    }
// flow of extra added
    lateinit var getallwishes: Flow<List<Wish>>   // lateinit is promise that you make to compiler that variable is initialised before calling any operation


    init{ // like constructor  calls when function call
        viewModelScope.launch {
            getallwishes = wishRepository.getWishes()
        }
    }


    fun addwish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO for input to add output to load
            wishRepository.AddAWish(wish) //wish = Wish()
        }
    }
    fun updatewish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO for input to add output to load
            wishRepository.updatewish(wish) //wish = Wish()
        }
    }
    fun getawishbyit(id:Long):Flow<Wish>{
        return wishRepository.getawishbyid(id)
    }

    fun deletewish(wish: Wish){
        viewModelScope.launch(Dispatchers.IO) { //Dispatchers.IO for input to add output to load
            wishRepository.deletewish(wish)
        }
    }
}