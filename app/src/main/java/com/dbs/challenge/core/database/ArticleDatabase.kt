package com.dbs.challenge.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.dbs.challenge.feature.article.data.datasource.ArticleDao
import com.dbs.challenge.feature.article.domain.model.ArticleEntity

@Database(entities = [ArticleEntity::class], version = 1)
abstract class ArticleDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao
}