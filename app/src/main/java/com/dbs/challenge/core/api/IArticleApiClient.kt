package com.dbs.challenge.core.api

import com.dbs.challenge.feature.article.data.response.ArticleResponse
import com.dbs.challenge.feature.articledetail.data.response.ArticleDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface IArticleApiClient {
    @GET("article")
    suspend fun getArticles(): List<ArticleResponse>

    @GET("article/{id}")
    suspend fun getArticleDetail(@Path("id") id: String): ArticleDetailResponse

}