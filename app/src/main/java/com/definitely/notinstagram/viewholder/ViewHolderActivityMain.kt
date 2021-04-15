package com.definitely.notinstagram.viewholder

import android.view.View
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.definitely.notinstagram.R
import com.definitely.notinstagram.databinding.ViewHolderActivityMainBinding
import com.definitely.notinstagram.service.CustomClickListener

class ViewHolderActivityMain(itemView: ViewHolderActivityMainBinding, var clickListener: CustomClickListener): RecyclerView.ViewHolder(itemView.root), View.OnClickListener {

    val viewHolderImageView = itemView.imageViewActivityMainViewHolder

    init {
        viewHolderImageView.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        clickListener.onClick(adapterPosition)
    }
}