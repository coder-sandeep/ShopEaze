package com.codersandeep.shopeaze.ui.auth

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.codersandeep.shopeaze.R
import com.codersandeep.shopeaze.databinding.FragmentLoginBinding
import com.codersandeep.shopeaze.models.SigninRequest
import com.codersandeep.shopeaze.utils.Helper
import com.codersandeep.shopeaze.utils.NetworkResponse
import com.codersandeep.shopeaze.utils.TokenManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private val authViewModel by viewModels<AuthViewModel>()
    @Inject
    lateinit var tokenManager: TokenManager
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        Log.d("LOGG", "Hi!")
        binding.btnLogin.setOnClickListener() {
            Helper.hideKeyboard(it)
            val userName = binding.txtUsername.text.toString()
            val pass = binding.txtPassword.text.toString()
            if (userName.isEmpty() || pass.isEmpty())
                binding.txtError.text = "Please fill username and password"
            else
                authViewModel.userLogin(SigninRequest(pass, userName))
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        authViewModel.userResponseLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is NetworkResponse.Success -> {
                    tokenManager.saveToken(it.data!!.token)
                    findNavController().navigate(R.id.action_loginFragment_to_mainActivity)
                    Toast.makeText(context,"Login successful", Toast.LENGTH_LONG).show()
                    Log.d("LOGG", it.data.toString())
                }

                is NetworkResponse.Error -> {
                    binding.btnLogin.visibility = View.VISIBLE
                    binding.loadingPanel.visibility = View.INVISIBLE
                    binding.txtError.text = it.message.toString()
                    Log.d("LOGG", "Lag lage")
                }

                is NetworkResponse.Loading -> {
                    binding.btnLogin.visibility = View.INVISIBLE
                    binding.loadingPanel.visibility = View.VISIBLE
                    Log.d("LOGG", "Loading...")
                }
            }
        }
    }
}