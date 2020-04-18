package com.dbs.challenge.feature.articledetail.data.repository

import com.dbs.challenge.core.utils.NetworkConnectivity
import com.dbs.challenge.feature.articledetail.data.datasource.ArticleDetailDataStore
import com.dbs.challenge.feature.articledetail.data.datasource.ArticleDetailLocalDataStore
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity
import com.dbs.challenge.feature.articledetail.domain.repository.IArticleDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.map

class IArticleDetailRepositoryImpl(
    private val articleDataStore: ArticleDetailDataStore,
    private val articleLocalDataStore: ArticleDetailLocalDataStore,
    private val networkConnectivity: NetworkConnectivity
) : IArticleDetailRepository {

    override fun getArticleDetail(id: String): Flow<ArticleDetailEntity> {
        if (networkConnectivity.isConnected()) {
            val data = articleDataStore.getArticleData(id)
                .map { articleitem ->
                    ArticleDetailEntity(
                        articleitem.id,
                        articleitem.text
                    )
                }.flatMapMerge { articleLocalDataStore.insertArticleData(it) }
            return data
        } else {
            return articleLocalDataStore.getArticleData()
        }
    }
}