package com.dbs.challenge.feature.articledetail.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "article_detail")
data class ArticleDetailEntity(
    @PrimaryKey val id: Long,
    @ColumnInfo(name = "desc") val text: String?,
    @ColumnInfo(name = "avatar")  var avatar: String? = ""
)