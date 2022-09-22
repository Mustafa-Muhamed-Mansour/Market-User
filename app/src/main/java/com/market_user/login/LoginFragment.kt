package com.market_user.login

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.market_user.R
import com.market_user.common.Validation
import com.market_user.databinding.FragmentLoginBinding

class LoginFragment : Fragment()
{

    private lateinit var binding: FragmentLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var validation: Validation


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        initView()
        clickViews()
        initViewModel()

    }

    private fun initView()
    {
        validation = Validation()
    }

    private fun clickViews()
    {
        binding
            .btnRegister
            .setOnClickListener {

                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_registerFragment)

            }

        binding
            .txtForgetPassword
            .setOnClickListener {

                Navigation.findNavController(it).navigate(R.id.action_loginFragment_to_forgetPasswordFragment)

            }

        binding
            .btnLogin
            .setOnClickListener {

                val email = binding.editEmail.text.toString()
                val password = binding.editPassword.text.toString()

                if (validation.checkEmail(requireContext(), email).none())
                {
                    binding.editEmail.requestFocus()
                    return@setOnClickListener
                }

                if (validation.checkPassword(requireContext(), password).none())
                {
                    binding.editPassword.requestFocus()
                    return@setOnClickListener
                }

                else
                {
                    retrieveViewModel(email, password)
                }

            }
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(this)[LoginViewModel::class.java]
    }

    private fun retrieveViewModel(email: String, password: String)
    {
        viewModel
            .loginUser(email, password)
            .observe(viewLifecycleOwner)
            {

                if (it)
                {
                    Navigation.findNavController(requireView()).navigate(R.id.action_loginFragment_to_homeFragment)
                }

                else
                {
                    Toast.makeText(requireContext(), "try later again", Toast.LENGTH_SHORT).show()
                }
            }
    }

}