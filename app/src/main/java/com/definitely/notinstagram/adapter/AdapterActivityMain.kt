package com.definitely.notinstagram.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.definitely.notinstagram.service.CustomClickListener
import com.definitely.notinstagram.R
import com.definitely.notinstagram.databinding.ViewHolderActivityMainBinding
import com.definitely.notinstagram.model.HomeModelResult
import com.definitely.notinstagram.viewholder.ViewHolderActivityMain
import com.squareup.picasso.Picasso

class AdapterActivityMain(var context: Context, var imageModelList: List<HomeModelResult>, var clickListener: CustomClickListener): RecyclerView.Adapter<ViewHolderActivityMain>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderActivityMain {

        val viewHolderBinding = DataBindingUtil.inflate<ViewHolderActivityMainBinding>(LayoutInflater.from(context), R.layout.view_holder_activity_main, parent, false)
        return ViewHolderActivityMain(viewHolderBinding, clickListener)
    }

    override fun onBindViewHolder(holder: ViewHolderActivityMain, position: Int) {
        Picasso.get().load(imageModelList[position].artworkUrl100).into(holder.viewHolderImageView)
    }

    override fun getItemCount(): Int {
        return imageModelList.size
    }
}