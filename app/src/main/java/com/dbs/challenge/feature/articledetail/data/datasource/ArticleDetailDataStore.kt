package com.dbs.challenge.feature.articledetail.data.datasource

import com.dbs.challenge.core.api.IArticleApiClient
import com.dbs.challenge.feature.articledetail.data.response.ArticleDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ArticleDetailDataStore(private val articleApi: IArticleApiClient) {

    fun getArticleData(id: String): Flow<ArticleDetailResponse> = flow {
        emit(articleApi.getArticleDetail(id))
    }


}