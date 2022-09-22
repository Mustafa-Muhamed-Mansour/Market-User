package com.market_user.register

import android.app.Activity
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.Navigation
import com.market_user.R
import com.market_user.common.Validation
import com.market_user.databinding.FragmentRegisterBinding

class RegisterFragment : Fragment()
{

    private lateinit var binding: FragmentRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    private lateinit var image: String
    private lateinit var resultUri: Uri
    private lateinit var someLauncher: ActivityResultLauncher<Intent>
    private lateinit var validation: Validation

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentRegisterBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)


        initViews()
        clickViews()
        initViweModel()

    }

    private fun initViews()
    {
        someLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult())
        {

            val intent = it.data!!

            if (it.resultCode == Activity.RESULT_OK && intent.data != null)
            {
                resultUri = intent.data!!
                image = resultUri.toString()
                binding.imgUser.setImageURI(resultUri)
            }

            else
            {
                Toast.makeText(requireContext(), "Please choose your image", Toast.LENGTH_SHORT).show()
            }
        }
        validation = Validation()
    }

    private fun clickViews()
    {
        binding
            .imgUser
            .setOnClickListener {

                openGallery()

            }

        binding
            .btnLogin
            .setOnClickListener {

                Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment)

            }

        binding
            .btnRegister
            .setOnClickListener {

                val email = binding.editEmail.text.toString()
                val password = binding.editPassword.text.toString()
                val name = binding.editName.text.toString()
                val phone = binding.editPhone.text.toString()

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

                if (validation.checkName(requireContext(), name).none())
                {
                    binding.editName.requestFocus()
                    return@setOnClickListener
                }

                if (validation.checkPhone(requireContext(), phone).none())
                {
                    binding.editPhone.requestFocus()
                    return@setOnClickListener
                }

                else
                {
                    retrieveViewModel(email, password, name, phone)
                }
            }
    }

    private fun initViweModel()
    {
        viewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    private fun retrieveViewModel(email: String, password: String, name: String, phone: String)
    {
        viewModel
            .registerUser(image, email, password, name, phone)
            .observe(viewLifecycleOwner)
            {

                if (it)
                {
                    Navigation.findNavController(requireView()).navigate(R.id.action_registerFragment_to_loginFragment)
                }

                else
                {
                    Toast.makeText(requireContext(), "try later again", Toast.LENGTH_SHORT).show()
                }

            }
    }



    private fun openGallery()
    {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        someLauncher.launch(intent)
    }

}