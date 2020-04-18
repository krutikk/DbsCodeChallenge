package com.dbs.challenge.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dbs.challenge.feature.article.data.datasource.ArticleDao
import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.articledetail.data.datasource.ArticleDetailDao
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity

@Database(entities = [ArticleEntity::class, ArticleDetailEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
    abstract fun articleDetailDao(): ArticleDetailDao
}