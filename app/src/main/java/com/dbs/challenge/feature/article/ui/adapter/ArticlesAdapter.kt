package com.dbs.challenge.feature.article.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.dbs.challenge.databinding.ItemArticleBinding
import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.article.ui.adapter.ArticlesAdapter.ArticlesViewHolder


class ArticlesAdapter(private val callBack: (ArticleEntity, View) -> Unit) :
    ListAdapter<ArticleEntity, ArticlesViewHolder>(ArticleDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticlesViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val itemBinding: ItemArticleBinding =
            ItemArticleBinding.inflate(layoutInflater, parent, false)
        itemBinding.item.setOnClickListener {
            val articleEntity: ArticleEntity? = itemBinding.article
            articleEntity?.let { it1 -> callBack.invoke(it1, it) }
        }
        return ArticlesViewHolder(itemBinding)
    }


    class ArticlesViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(article: ArticleEntity?) {
            binding.setVariable(BR.article, article)
            binding.executePendingBindings()
        }

    }

    private class ArticleDiffCallback : DiffUtil.ItemCallback<ArticleEntity>() {

        override fun areItemsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: ArticleEntity, newItem: ArticleEntity): Boolean {
            return oldItem == newItem
        }

    }

    override fun onBindViewHolder(holder: ArticlesViewHolder, position: Int) {
        val articleEntity: ArticleEntity? = getItem(position)
        holder.bind(articleEntity)
    }
}

@BindingAdapter("articleImage")
fun loadImage(view: ImageView, imageUrl: String?) {
    Glide.with(view.context)
        .load(imageUrl).apply(RequestOptions().circleCrop())
        .into(view)
}
