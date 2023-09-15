package com.codersandeep.shopeaze.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.databinding.FragmentHomeBinding
import com.codersandeep.shopeaze.ui.adapters.HomeReAdapter
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.codersandeep.shopeaze.utils.ProductResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment(), HomeReAdapter.RecyclerViewEvent {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        homeViewModel.getAllProducts()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        homeViewModel.productsLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is ProductResponse.Error -> {Log.d(LOG_TAG,"error")}
                is ProductResponse.Success -> {
                    Log.d(LOG_TAG,"success")
                    binding.recyclerviewHome.adapter = HomeReAdapter(it.data!!,this)
                    binding.recyclerviewHome.layoutManager = GridLayoutManager(context,2)
                }
                is ProductResponse.Loading -> {Log.d(LOG_TAG,"loading...")}
            }
        }
    }

    override fun onItemClick(position: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(position)
        findNavController().navigate(action)
    }
}