package com.dbs.challenge.feature.article.data.repository

import com.dbs.challenge.core.utils.NetworkConnectivity
import com.dbs.challenge.feature.article.data.datasource.ArticleDataStore
import com.dbs.challenge.feature.article.data.datasource.ArticleLocalDataStore
import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.article.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapConcat
import kotlinx.coroutines.flow.map

class IArticleRepositoryImpl(
    private val articleDataStore: ArticleDataStore,
    private val articleLocalStore: ArticleLocalDataStore,
    private val networkConnectivity: NetworkConnectivity
) : IArticleRepository {

    override fun getArticles(): Flow<List<ArticleEntity>> {
        if (networkConnectivity.isConnected()) {
            val list = articleDataStore.getArticleData()
                .map { resplist ->
                    resplist.sortedByDescending { it.lastUpdate }.map { articleitem ->
                        ArticleEntity(
                            articleitem.id,
                            articleitem.title,
                            articleitem.shortDesc,
                            articleitem.avatar,
                            articleitem.lastUpdate
                        )
                    }
                }.flatMapConcat { articleLocalStore.insertArticleData(it) }

            return list
        } else
            return articleLocalStore.getArticleData()
    }
}
