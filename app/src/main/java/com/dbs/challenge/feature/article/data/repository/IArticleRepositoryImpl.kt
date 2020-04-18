package com.dbs.challenge.feature.article.data.repository

import com.dbs.challenge.feature.article.data.datasource.ArticleDataStore
import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.article.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class IArticleRepositoryImpl(
    private val articleDataStore: ArticleDataStore
) : IArticleRepository {

    override fun getArticles(): Flow<List<ArticleEntity>> =
        articleDataStore.getArticleData()
            .map {
                resplist ->
                resplist.sortedByDescending { it.lastUpdate }.map { articleitem ->
                    ArticleEntity(
                        articleitem.id,
                        articleitem.title,
                        articleitem.shortDesc,
                        articleitem.avatar,
                        articleitem.lastUpdate
                    )
                }
            }

}
