package com.dbs.challenge.feature.articledetail.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.dbs.challenge.R
import com.dbs.challenge.core.utils.observeWith
import com.dbs.challenge.databinding.FragmentArticleDetailBinding
import com.dbs.challenge.feature.article.ui.ArticleFragmentDirections
import com.dbs.challenge.feature.articledetail.presentation.viewmodel.ArticleDetailViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class ArticleDetailFragment : Fragment() {
    private val viewModel: ArticleDetailViewModel by viewModel()

    lateinit var binding: FragmentArticleDetailBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_article_detail, container, false
        )
        binding.viewModel = viewModel
        (binding.viewModel as ArticleDetailViewModel).getViewState("1")?.observeWith(this) {
            with(binding) {
                viewState = it
            }
        }

        setHasOptionsMenu(true)
        return binding.root
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }


}

