package com.codersandeep.shopeaze.ui.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.databinding.FragmentDetailsBinding
import com.codersandeep.shopeaze.models.ProductsItem
import com.codersandeep.shopeaze.utils.Constants.LOG_TAG
import com.codersandeep.shopeaze.utils.NetworkResponse
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!
    private val args : DetailsFragmentArgs by navArgs()
    private val detailsViewModel by viewModels<DetailsViewModel>()
    private lateinit var currentProduct : ProductsItem

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentDetailsBinding.inflate(inflater,container,false)

        //Log.d(LOG_TAG,"frag fetching..."+args.productId)
        detailsViewModel.getSingleProduct(args.productId+1)
        detailsViewModel.getProductById(args.productId+1).observe(viewLifecycleOwner){
            if(it != null) {
                binding.gotoCartButton.visibility = View.VISIBLE
            }
        }


        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.gotoCartButton.setOnClickListener(){
            val action = DetailsFragmentDirections.actionDetailsFragmentToCartFragment()
            findNavController().navigate(action)
        }
        detailsViewModel.singleProductLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Error -> {Log.d(LOG_TAG,"error")}
                is NetworkResponse.Success -> {

                    currentProduct = it.data!!
                   binding.loadingProgressBar.visibility = View.INVISIBLE
                    binding.productDetailsWrapper.visibility = View.VISIBLE
                    Picasso.get()
                        .load(it.data.image)
                        .error(R.drawable.ic_brokenimage)
                        .into(binding.productImage)
                    binding.productTitle.text = currentProduct.title
                    binding.productPrice.text = "$ "+currentProduct.price
                    binding.productDesciption.text = currentProduct.description
                }
                is NetworkResponse.Loading -> {
                    binding.loadingProgressBar.visibility = View.VISIBLE
                    binding.productDetailsWrapper.visibility = View.INVISIBLE
                }
            }

            binding.addToCartButton.setOnClickListener{
                detailsViewModel.saveProduct(currentProduct)
                Toast.makeText(context,"Item added to cart",Toast.LENGTH_LONG).show()
            }

            view.findViewById<TextView>(R.id.details_text).text = it.data.toString()
        }
    }
}