package com.codersandeep.shopeaze.ui.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.repository.ProductsRepository
import com.codersandeep.shopeaze.repository.RoomRepository
import com.codersandeep.shopeaze.utils.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(private val productsRepository: ProductsRepository, private val roomRepository: RoomRepository) : ViewModel() {
    val singleProductLiveData : LiveData<ProductResponse<ProductsItem>>
        get() = productsRepository.singleProductLiveData

    fun getSingleProduct(productId: Int){
        viewModelScope.launch {
            productsRepository.getSingleProduct(productId)
        }
    }
    fun getProductById(productId: Int) = roomRepository.getProductById(productId)

    fun saveProduct(productsItem: ProductsItem) = viewModelScope.launch { roomRepository.saveProduct(productsItem)}

}