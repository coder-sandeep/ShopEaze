package com.codersandeep.shopeaze.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.codersandeep.shopeaze.databinding.FragmentHomeBinding
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.codersandeep.shopeaze.utils.NetworkResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

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
                is NetworkResponse.Error -> {Log.d(LOG_TAG,"error")}
                is NetworkResponse.Success -> {Log.d(LOG_TAG,"success")}
                is NetworkResponse.Loading -> {Log.d(LOG_TAG,"loading...")}
            }
        }
    }
}