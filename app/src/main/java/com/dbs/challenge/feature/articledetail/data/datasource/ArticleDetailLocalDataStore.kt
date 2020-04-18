package com.dbs.challenge.feature.articledetail.data.datasource

import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleDetailLocalDataStore(private val articleDetailDao: ArticleDetailDao) {
    fun getArticleData(): Flow<ArticleDetailEntity> = flow {
        emit(articleDetailDao.getArticleDetail())
    }


     fun insertArticleData(articleDetailEntity: ArticleDetailEntity) = flow {
        emit(articleDetailDao.insertArticleDetail(articleDetailEntity))
    }

}