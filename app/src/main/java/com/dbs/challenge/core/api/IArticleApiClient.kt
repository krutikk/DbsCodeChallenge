package com.dbs.challenge.core.api

import com.dbs.challenge.feature.data.response.ArticleResponse
import retrofit2.http.GET

interface IArticleApiClient {
    @GET("article")
    suspend fun getArticles(): List<ArticleResponse>


}