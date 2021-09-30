package com.example.neocafeteae1prototype.application.bottom_navigation_items.branches


import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.neocafeteae1prototype.application.tools.BaseFragment
import com.example.neocafeteae1prototype.application.tools.adapters.MainRecyclerAdapter
import com.example.neocafeteae1prototype.application.tools.alert_dialog.CustomAlertDialog
import com.example.neocafeteae1prototype.application.tools.loadWithPicasso
import com.example.neocafeteae1prototype.databinding.FragmentDetailBranchBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels


class BranchesDetailFragment : BaseFragment<FragmentDetailBranchBinding>() {

    private val args: BranchesDetailFragmentArgs by navArgs()
    private val recyclerAdapter = MainRecyclerAdapter(null)
    private lateinit var intent: Intent

    override fun inflateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
    ): FragmentDetailBranchBinding {
        return FragmentDetailBranchBinding.inflate(inflater)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData(args.model)
        binding.include.notification.setOnClickListener {
            findNavController().navigate(BranchesDetailFragmentDirections.actionBranchesDetailFragmentToNotification2())
        }
        binding.include.backButton.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun setData(param1: AllModels.NeoCafePlace?) {
        with(binding) {
            if (param1 != null) {
                binding.branchImage.loadWithPicasso(param1.image)
            }

            branchInfo.text = param1?.branchInfo

            instagram.setOnClickListener {
                param1?.instagramLink?.let { it -> runLink(it) }

            }

            facebook.setOnClickListener {
                param1?.facebookLink?.let { it -> runLink(it) }
            }

            onTheMap.setOnClickListener {
                param1?.gis?.let { it1 -> run2Gis(it1) }
            }

            recyclerTime.apply {
                layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                adapter = recyclerAdapter
                recyclerAdapter.setList(param1!!.list)
            }

            call.setOnClickListener {
                intent = Intent(Intent.ACTION_DIAL).setData(Uri.parse("tel:${param1?.numberPhone}"))
                startActivity(intent)
            }

            addressOfBranch.text = param1?.street
        }

    }

    private fun run2Gis(link: String) {
        CustomAlertDialog({ runLink(link) }, "Перейти в 2ГИС?", "С помощью которого вы найдете быстрый маршрут к нам!").show(childFragmentManager, "TAG")
    }

    private fun runLink(uri: String) {
        intent = Intent(Intent.ACTION_VIEW, Uri.parse(uri))
        startActivity(intent)
    }


}