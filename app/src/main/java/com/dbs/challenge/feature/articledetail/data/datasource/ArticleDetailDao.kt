package com.dbs.challenge.feature.articledetail.data.datasource

import androidx.room.*
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity

@Dao
abstract class ArticleDetailDao {
    @Query("SELECT * FROM article_detail")
    abstract suspend fun getArticleDetail(): ArticleDetailEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun insertArticle(article: ArticleDetailEntity)

    @Transaction
    open suspend fun insertArticleDetail(articleDetailEntity: ArticleDetailEntity): ArticleDetailEntity {
        insertArticle(articleDetailEntity)
        return articleDetailEntity
    }

}