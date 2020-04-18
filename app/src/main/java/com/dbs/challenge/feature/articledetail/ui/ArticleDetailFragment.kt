package com.dbs.challenge.feature.articledetail.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dbs.challenge.R
import com.dbs.challenge.core.utils.observeWith
import com.dbs.challenge.databinding.FragmentArticleDetailBinding
import com.dbs.challenge.feature.articledetail.presentation.viewmodel.ArticleDetailViewModel
import kotlinx.android.synthetic.main.item_article.*
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
        arguments?.getString("id")?.let { id ->
            (binding.viewModel as ArticleDetailViewModel).getViewState("1")
                ?.observeWith(this) {
                    with(binding) {
                        viewState = it
                    }
                }
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemEdit -> {
                navigateToDetail()
                true
            }
            else -> false
        }
    }

    private fun navigateToDetail() {
        val direction =
            ArticleDetailFragmentDirections.actionArticleDetailFragmentToArticleEditFragment()
        direction.title = arguments?.getString("title").toString()
        direction.desc = txtDesc.text as String
        view?.findNavController()?.navigate(direction)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu, menu)
    }



}

