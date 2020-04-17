package com.dbs.challenge.feature.article.presentation.model

import com.dbs.challenge.core.state.BaseViewState
import com.dbs.challenge.core.state.Status
import com.dbs.challenge.feature.article.domain.model.ArticleEntity

class ArticleViewState(
    val status: Status,
    val error: String? = null,
    val data: List<ArticleEntity>? = null
) : BaseViewState(status, error)