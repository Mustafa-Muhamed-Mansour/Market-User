package com.market_user.opinion

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.market_user.adapter.OpinionAdapter
import com.market_user.common.Validation
import com.market_user.databinding.FragmentOpinionBinding
import com.market_user.model.OpinionModel


class OpinionFragment : BottomSheetDialogFragment()
{

    private lateinit var binding: FragmentOpinionBinding
    private lateinit var viewModel: OpinionViewModel
    private lateinit var bottomSheetDialog: BottomSheetDialog
    private lateinit var dataOfSeller: OpinionFragmentArgs
    private lateinit var validation: Validation



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View
    {
        binding = FragmentOpinionBinding.inflate(inflater, container, false)
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
        bottomSheetDialog = BottomSheetDialog(requireContext())
        dataOfSeller = OpinionFragmentArgs.fromBundle(requireArguments())
        validation = Validation()
    }

    private fun clickViews()
    {

        binding
            .imgBtnOpinion
            .setOnClickListener {

                val opinion = binding.editOpinion.text.toString()

                if (validation.checkOpinion(requireContext(), opinion).none())
                {
                    binding.editOpinion.requestFocus()
                    return@setOnClickListener
                }

                else
                {
                    opinionUser(dataOfSeller.dataSeller.id, opinion)
                    binding.editOpinion.text.clear()
                }
            }
    }

    private fun initViewModel()
    {
        viewModel = ViewModelProvider(requireActivity())[OpinionViewModel::class.java]
        retrieveOpinionOfUser(dataOfSeller.dataSeller.id)
    }

    private fun opinionUser(id: String, opinion: String)
    {
        viewModel
            .opinionUser(id, opinion)
            .observe(viewLifecycleOwner)
            {

                if (it)
                {
                    Toast.makeText(requireContext(), "done your comment is uploaded", Toast.LENGTH_SHORT).show()
                    bottomSheetDialog.dismiss()
                }

                else
                {
                    Toast.makeText(requireContext(), "error", Toast.LENGTH_SHORT).show()
                }

            }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun retrieveOpinionOfUser(id: String)
    {
        viewModel
            .retrieveOpinion(id)
            .observe(viewLifecycleOwner)
            {

                binding.loadingOpinion.visibility = View.GONE

                val opinionAdapter = OpinionAdapter(it)
                binding.rVOpinion.adapter = opinionAdapter
                binding.rVOpinion.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                binding.rVOpinion.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))
                opinionAdapter.notifyDataSetChanged()
            }
    }

}