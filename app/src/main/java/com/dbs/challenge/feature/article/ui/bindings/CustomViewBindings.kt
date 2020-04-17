package com.dbs.challenge.feature.article.ui.bindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.dbs.challenge.R

object CustomViewBindings {
    @BindingAdapter("setAdapter")
    fun bindRecyclerViewAdapter(
        recyclerView: RecyclerView,
        adapter: RecyclerView.Adapter<*>?
    ) {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        recyclerView.adapter = adapter
    }

    @BindingAdapter("imageUrl")
    fun bindRecyclerViewAdapter(
        imageView: ImageView,
        imageUrl: String?
    ) {
        if (imageUrl != null) {
            // If we don't do this, you'll see the old image appear briefly
            // before it's replaced with the current image
            if (imageView.getTag(R.id.image_url) == null || imageView.getTag(R.id.image_url) != imageUrl) {
                imageView.setImageBitmap(null)
                imageView.setTag(R.id.image_url, imageUrl)
                Glide.with(imageView).load(imageUrl).transform(CircleCrop()).into(imageView)
            }
        } else {
            imageView.setTag(R.id.image_url, null)
            imageView.setImageBitmap(null)
        }
    }
}