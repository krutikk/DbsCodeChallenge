package com.dbs.challenge.feature.articledetail.data.response

import com.squareup.moshi.Json

data class ArticleDetailResponse(
    @field:Json(name = "id") val id: Long,
    @field:Json(name = "title") val title: String?,
    @field:Json(name = "last_update") val lastUpdate: Long?,
    @field:Json(name = "short_description") val shortDesc: String?,
    @field:Json(name = "avatar") val avatar: String?
)