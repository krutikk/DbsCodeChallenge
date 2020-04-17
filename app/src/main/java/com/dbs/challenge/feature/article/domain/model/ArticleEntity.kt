package com.dbs.challenge.feature.article.domain.model

import com.dbs.challenge.core.utils.getFormattedDate

data class ArticleEntity(
    val id: Long,
    val title: String?,
    val description: String?,
    val avatar: String?,
    private val date: Long?
) {
    fun getDate(): String {
        return date?.getFormattedDate() ?: ""
    }
}