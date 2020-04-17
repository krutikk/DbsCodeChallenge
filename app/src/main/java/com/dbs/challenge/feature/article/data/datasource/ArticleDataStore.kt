package com.dbs.challenge.feature.article.data.datasource

import com.dbs.challenge.core.api.IArticleApiClient
import com.dbs.challenge.feature.article.data.response.ArticleResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleDataStore(private val articleApi: IArticleApiClient) {

    fun getArticleData(id:String): Flow<ArticleResponse> = flow {
        emit(articleApi.getArticleDetail(id))
    }


}