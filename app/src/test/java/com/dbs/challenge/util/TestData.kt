package com.dbs.challenge.util

import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity

object TestData {
    val dataEmpty = emptyList<ArticleEntity>()
    val dataList = listOf<ArticleEntity>(
        ArticleEntity(
            1,
            "article 1 title",
            "This is article 1 short description. She wholly fat who window extent either formal. Removing welcomed civility or hastened is.",
            "https://minotar.net/avatar/user2",
            1586404611
        ),
        ArticleEntity(
            2,
            "article 1 title",
            "This is article 1 short description. She wholly fat who window extent either formal. Removing welcomed civility or hastened is.",
            "https://minotar.net/avatar/user2",
            1586404611
        ),
        ArticleEntity(
            3,
            "article 1 title",
            "This is article 1 short description. She wholly fat who window extent either formal. Removing welcomed civility or hastened is.",
            "https://minotar.net/avatar/user2",
            1586404611
        )
    )

    val detailList = listOf<ArticleDetailEntity>(
        ArticleDetailEntity(
            1,
            "article 1 title",
            "https://minotar.net/avatar/user2"
            ),
        ArticleDetailEntity(
            2,
            "article 1 title",
            "https://minotar.net/avatar/user2"
        ),
        ArticleDetailEntity(
            3,
            "article 1 title",
            "https://minotar.net/avatar/user2"
        )
    )
}