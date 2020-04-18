package com.dbs.challenge.feature.articleedit.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.dbs.challenge.R
import com.dbs.challenge.databinding.FragmentArticleEditBinding
import com.dbs.challenge.feature.articleedit.model.ArticleDetail


class ArticleEditFragment : Fragment() {

    lateinit var binding: FragmentArticleEditBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_article_edit, container, false
        )
        binding.article =
            ArticleDetail(
                arguments?.getString("desc") ?: "", arguments?.getString("title") ?: "",
                arguments?.getString("id") ?: ""
            )
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuItemCancel -> {
                view?.findNavController()?.navigateUp()
                true
            }
            else -> false
        }
    }


    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_menu_edit, menu)
    }


}

