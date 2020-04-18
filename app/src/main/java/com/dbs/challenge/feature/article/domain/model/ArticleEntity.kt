package com.dbs.challenge.feature.article.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dbs.challenge.core.utils.getFormattedDate

@Entity(tableName = "article")
data class ArticleEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "title") val title: String?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "avatar") val avatar: String?,
    @ColumnInfo(name = "date") val date: Long?
) {
    fun getFormattedDate(): String {
        return date?.getFormattedDate() ?: ""
    }
}