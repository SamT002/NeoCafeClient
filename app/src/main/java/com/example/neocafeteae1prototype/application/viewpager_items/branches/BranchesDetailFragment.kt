package com.example.neocafeteae1prototype.application.viewpager_items.branches

/*import com.example.neocafeteae1prototype.application.extensions.plus*/
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.neocafeteae1prototype.application.viewpager_items.branches.viewModel.BranchesViewModel
import com.example.neocafeteae1prototype.databinding.FragmentBranchBinding
import com.example.neocafeteae1prototype.domain.sealedClasses.AllModels
import com.squareup.picasso.Picasso


class BranchesDetailFragment : Fragment() {

    private var param1: AllModels.NeoCafePlace? = null
    private val viewModel by lazy { BranchesViewModel() }

    private var _binding:FragmentBranchBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?,
    ): View? {
        Log.i("Tag", "onCreateView")
        _binding = FragmentBranchBinding.inflate(inflater, container, false)

        var a = viewModel.getData()
        setData(a)


        return binding.root
    }

    private fun setData(param1: AllModels.NeoCafePlace?) {
        Picasso.with(requireContext())
            .load(param1?.image)
            .into(binding.branchImage)

//        binding.addressOfBranch + 14F
    }

}