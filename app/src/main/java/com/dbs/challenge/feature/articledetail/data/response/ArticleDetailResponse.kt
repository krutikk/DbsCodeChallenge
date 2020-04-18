package com.dbs.challenge.feature.articledetail.data.response

import com.squareup.moshi.Json

data class ArticleDetailResponse(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "text") val text: String?
)