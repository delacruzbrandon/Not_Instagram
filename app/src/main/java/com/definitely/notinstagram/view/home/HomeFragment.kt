package com.definitely.notinstagram.view.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.RecyclerView
import com.definitely.notinstagram.R
import com.definitely.notinstagram.adapter.AdapterActivityMain
import com.definitely.notinstagram.databinding.FragmentHomeBinding
import com.definitely.notinstagram.service.CustomClickListener

private const val TAG = "HomeFragment"
class HomeFragment : Fragment() {

    private lateinit var homeBinding: FragmentHomeBinding
    private lateinit var recyclerView: RecyclerView

    private lateinit var homeViewModel: HomeViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        homeBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        recyclerView = homeBinding.recyclerViewFragmentHomeContent

        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

        homeViewModel.apply {
            fetchHomeData()

            imageModel.observe(viewLifecycleOwner, {
                Log.d(TAG, "onCreateView: $it")
                val adapterActivityMain = AdapterActivityMain(requireContext(), it,
                        object : CustomClickListener {
                            override fun onClick(position: Int) {
                                onItemClick(it[position].collectionId)
                            }
                        })
                recyclerView.adapter = adapterActivityMain
                adapterActivityMain.notifyDataSetChanged()

            })

        }

        return homeBinding.root
    }

    fun onItemClick(collectionId: Int) {
        val action = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(collectionId)
        NavHostFragment.findNavController(this).navigate(action)
    }
}