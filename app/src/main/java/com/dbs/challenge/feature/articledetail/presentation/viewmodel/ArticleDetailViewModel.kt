package com.dbs.challenge.feature.articledetail.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dbs.challenge.core.platform.BaseViewModel
import com.dbs.challenge.core.state.Status
import com.dbs.challenge.core.utils.io
import com.dbs.challenge.core.utils.ui
import com.dbs.challenge.feature.articledetail.domain.interactor.GetArticleDetailInteractor
import com.dbs.challenge.feature.articledetail.presentation.model.ArticleViewState
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


class ArticleDetailViewModel(
    private val getArticlesInteractor: GetArticleDetailInteractor
) : BaseViewModel() {

    private var result: MutableLiveData<ArticleViewState>? = null


    fun getViewState(id: String): LiveData<ArticleViewState> {
        if (result == null) {
            result = MutableLiveData<ArticleViewState>()
            getArticleDeatil(id)
        }
        return result as MutableLiveData<ArticleViewState>
    }

    private fun getArticleDeatil(id: String) {
        result?.value = ArticleViewState(Status.LOADING)
        viewModelScope.launch {
            try {
                io {
                    getArticlesInteractor.execute(id)
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