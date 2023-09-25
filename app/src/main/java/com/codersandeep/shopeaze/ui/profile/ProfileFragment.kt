package com.codersandeep.shopeaze.ui.profile

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.databinding.FragmentHomeBinding
import com.codersandeep.shopeaze.databinding.FragmentProfileBinding
import com.codersandeep.shopeaze.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    @Inject
    lateinit var tokenManager : TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        if (tokenManager.getToken().isNullOrBlank()) {
            binding.layoutLogged.visibility = View.INVISIBLE
            binding.layoutNotLogged.visibility = View.VISIBLE
        }else{
            binding.layoutLogged.visibility = View.VISIBLE
            binding.layoutNotLogged.visibility = View.INVISIBLE
        }

        binding.buttonSendToLogin.setOnClickListener(){
            val action = ProfileFragmentDirections.actionProfileFragmentToAuthActivity()
            findNavController().navigate(action)
        }
        binding.buttonLogout.setOnClickListener(){
            tokenManager.deleteToken()
            Toast.makeText(context, "Logged out", Toast.LENGTH_LONG).show()
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
