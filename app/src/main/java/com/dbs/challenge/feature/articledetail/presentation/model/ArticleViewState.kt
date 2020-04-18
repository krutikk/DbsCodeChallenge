package com.dbs.challenge.feature.articledetail.presentation.model

import com.dbs.challenge.core.state.BaseViewState
import com.dbs.challenge.core.state.Status
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity

class ArticleViewState(
    val status: Status,
    val error: String? = null,
    val data: ArticleDetailEntity? = null
) : BaseViewState(status, error)