package com.definitely.notinstagram.view.details

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.definitely.notinstagram.R
import com.definitely.notinstagram.databinding.FragmentDetailsBinding
import com.squareup.picasso.Picasso

private const val TAG = "DetailsFragment"
class DetailsFragment : Fragment() {

    private lateinit var detailsBinding: FragmentDetailsBinding

    private lateinit var detailsViewFactory: DetailsViewModelFactory
    private lateinit var detailsViewModel: DetailsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        // Inflate the layout for this fragment
        detailsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_details, container, false)
        detailsViewFactory = DetailsViewModelFactory(DetailsFragmentArgs.fromBundle(requireArguments()).collectionId)
        detailsViewModel = ViewModelProvider(this, detailsViewFactory).get(DetailsViewModel::class.java)

        detailsViewModel.apply {
            fetchDetailsData()

            collectionId.observe(viewLifecycleOwner, {
                Log.d(TAG, "onCreateView: $it")
            })

            linkContents.observe(viewLifecycleOwner, {
                detailsBinding.apply {
                    textViewDetailsFragmentArtist.text = it[0].artistName
                    Picasso.get().load(it[0].artworkUrl100).into(imageViewDetailsFragmentCover)
                    textViewDetailsFragmentTrackName.text = it[0].collectionName
                    textViewDetailsFragmentPrice.text = it[0].collectionPrice.toString()
                    textViewDetailsFragmentCurrency.text = it[0].currency
                    textViewDetailsFragmentGenre.text = it[0].primaryGenreName

                }
            })


        }

        return detailsBinding.root
    }

}