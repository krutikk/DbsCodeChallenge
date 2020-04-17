package com.dbs.challenge.feature.article.domain.interactor

import com.dbs.challenge.core.interactor.Interactor
import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.article.domain.repository.IArticleRepository
import kotlinx.coroutines.flow.Flow

class GetArticlesInteractor(
    private val iArticleRepository: IArticleRepository
) : Interactor<Interactor.None, Flow<List<ArticleEntity>>> {

    override fun execute(params: Interactor.None): Flow<List<ArticleEntity>> {
        return iArticleRepository.getArticles()
    }
}