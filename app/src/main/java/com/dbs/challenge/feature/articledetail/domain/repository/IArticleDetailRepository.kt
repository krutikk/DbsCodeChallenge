package com.dbs.challenge.feature.articledetail.domain.repository

import com.dbs.challenge.feature.articledetail.domain.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface IArticleDetailRepository {
    fun getArticleDetail(id: String): Flow<ArticleEntity>
}