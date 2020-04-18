package com.dbs.challenge.feature.articledetail.domain.model

data class ArticleDetailEntity(
    val id: Long,
    val text: String?,
    var avatar: String? = ""
)