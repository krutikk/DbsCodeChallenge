package com.dbs.challenge.feature.article.data.datasource

import androidx.room.*
import com.dbs.challenge.feature.article.domain.model.ArticleEntity

@Dao
abstract class ArticleDao {
    @Query("SELECT * FROM Article order by date desc")
    abstract suspend fun getAllArticles(): List<ArticleEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArticle(article: ArticleEntity)

    @Transaction
    open suspend fun insertArticleList(list: List<ArticleEntity>) : List<ArticleEntity>  {
        insertArticles(list)
        return list
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArticles(article: List<ArticleEntity>)
}