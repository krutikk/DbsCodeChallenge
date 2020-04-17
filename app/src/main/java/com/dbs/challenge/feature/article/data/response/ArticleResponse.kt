package com.dbs.challenge.feature.article.data.response

import com.squareup.moshi.Json

data class ArticleResponse(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "last_update") val lastUpdate: Long?,
    @field:Json(name = "short_description") val shortDesc: String?,
    @field:Json(name = "avatar") val avatar: String?
)