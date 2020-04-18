package com.dbs.challenge.feature.articledetail.data.repository

import com.dbs.challenge.feature.articledetail.data.datasource.ArticleDetailDataStore
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity
import com.dbs.challenge.feature.articledetail.domain.repository.IArticleDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IArticleDetailRepositoryImpl(
    private val articleDataStore: ArticleDetailDataStore
) : IArticleDetailRepository {

    override fun getArticleDetail(id: String): Flow<ArticleDetailEntity> =
        articleDataStore.getArticleData(id)
            .map { articleitem ->
                ArticleDetailEntity(
                    articleitem.id,
                    articleitem.text
                )
            }

}
