package com.dbs.challenge.feature.article.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dbs.challenge.core.interactor.Interactor
import com.dbs.challenge.core.platform.BaseViewModel
import com.dbs.challenge.core.state.Status
import com.dbs.challenge.core.utils.io
import com.dbs.challenge.core.utils.ui
import com.dbs.challenge.feature.article.domain.interactor.GetArticlesInteractor
import com.dbs.challenge.feature.article.presentation.model.ArticleViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ArticlesViewModel(
    private val getArticlesInteractor: GetArticlesInteractor
) : BaseViewModel() {

    private var result: MutableLiveData<ArticleViewState>? = null
    val viewState: LiveData<ArticleViewState>
        get() {
            if (result == null) {
                result = MutableLiveData<ArticleViewState>()
                getCurrentArticle()
            }
            return result as MutableLiveData<ArticleViewState>
        }

    private fun getCurrentArticle() {
        result?.value = ArticleViewState(Status.LOADING)
        viewModelScope.launch {
            try {
                io {
                    getArticlesInteractor.execute(Interactor.None)
                        .collect {
                            ui {
                                result?.value =
                                    ArticleViewState(Status.SUCCESS, null, it)
                            }
                        }
                }
            } catch (e: Exception) {
                ui { result?.value = ArticleViewState(Status.ERROR, e.message, null) }
            }
        }
    }

}