package com.dbs.challenge.feature.article.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dbs.challenge.R
import com.dbs.challenge.core.state.Status
import com.dbs.challenge.core.utils.observeWith
import com.dbs.challenge.core.utils.showDialog
import com.dbs.challenge.databinding.FragmentArticlesBinding
import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.article.presentation.viewmodel.ArticlesViewModel
import com.dbs.challenge.feature.article.ui.adapter.ArticlesAdapter
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleFragment : Fragment() {
    private val viewModel: ArticlesViewModel by viewModel()

    lateinit var binding: FragmentArticlesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_articles, container, false
        )
        binding.viewModel = viewModel
        (binding.viewModel as ArticlesViewModel).viewState?.observeWith(this) {
            with(binding) {
                viewState = it
                when (it.status) {
                    Status.SUCCESS -> it.data?.let { articles -> initArticleAdapter(articles) }
                    Status.ERROR -> showDialog()
                    else -> print("Empty or loading")
                }

            }
        }
        val adapter = ArticlesAdapter { entity, view ->
            navigateToDetail(view, entity)
        }
        binding.recyclerArticles.adapter = adapter
        return binding.root
    }

    private fun navigateToDetail(view: View, entity: ArticleEntity) {
        val direction = ArticleFragmentDirections.actionArticleFragmentToArticledetailFragment(
            entity.title ?: ""
        )
        direction.id = entity.id.toString()
        Navigation.findNavController(view).navigate(direction)
    }

    private fun initArticleAdapter(list: List<ArticleEntity>) {
        (binding.recyclerArticles.adapter as ArticlesAdapter).submitList(list)

    }

}