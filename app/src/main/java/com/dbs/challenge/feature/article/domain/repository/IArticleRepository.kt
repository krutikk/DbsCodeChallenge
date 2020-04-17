package com.dbs.challenge.feature.article.domain.repository

import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import kotlinx.coroutines.flow.Flow

interface IArticleRepository {
    fun getArticles(): Flow<List<ArticleEntity>>
}