package com.codersandeep.shopeaze.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.repository.ProductsRepository
import com.codersandeep.shopeaze.utils.ProductResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor (private val productsRepository: ProductsRepository) : ViewModel() {

    val productsLiveData : LiveData<ProductResponse<List<ProductsItem>>>
    get() = productsRepository.productsLiveData

    fun getAllProducts() {
        viewModelScope.launch {
            productsRepository.getAllProducts()
        }
    }
}