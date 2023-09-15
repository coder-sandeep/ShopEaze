package com.codersandeep.shopeaze.ui.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codersandeep.shopeaze.repository.RoomRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CartViewModel @Inject constructor(private val roomRepository: RoomRepository) : ViewModel(){
    fun getAllProducts() = roomRepository.getAllProducts()

    fun deleteProduct(productId: Int) = viewModelScope.launch { roomRepository.deleteProduct(productId)}
}