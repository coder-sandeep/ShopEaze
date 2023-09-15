package com.codersandeep.shopeaze.ui.cart

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.databinding.FragmentCartBinding
import com.codersandeep.shopeaze.databinding.FragmentHomeBinding
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.ui.adapters.CartReAdapter
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CartFragment : Fragment(), CartReAdapter.RecyclerViewEvent {

    private var _binding: FragmentCartBinding? = null
    private val binding get() = _binding!!
    private val result : List<ProductsItem>? = null

    private val cartViewModel by viewModels<CartViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCartBinding.inflate(inflater,container,false)
        cartViewModel.getAllProducts().observe(viewLifecycleOwner){
            Log.d(LOG_TAG,it.toString())
            if(it.isEmpty()){
                binding.cartReWrapper.visibility = View.INVISIBLE
                binding.emptyCartWrapper.visibility = View.VISIBLE
            }
           else{
               Log.d(LOG_TAG,it.toString())
               binding.cartReWrapper.visibility = View.VISIBLE
               binding.emptyCartWrapper.visibility = View.INVISIBLE

               binding.recyclerViewCart.adapter = CartReAdapter(it,this)
               binding.recyclerViewCart.layoutManager = LinearLayoutManager(context)
           }
        }
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onItemClick(productId: Int) {
        cartViewModel.deleteProduct(productId)
    }
}