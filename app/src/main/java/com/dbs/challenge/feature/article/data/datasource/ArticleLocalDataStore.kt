package com.dbs.challenge.feature.article.data.datasource

import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleLocalDataStore(private val articleDao: ArticleDao) {
    fun getArticleData(): Flow<List<ArticleEntity>> = flow {
        emit(articleDao.getAllArticles())
    }

     fun insertArticleData(list: List<ArticleEntity>) = flow {
        emit(articleDao.insertArticleList(list))
    }
}