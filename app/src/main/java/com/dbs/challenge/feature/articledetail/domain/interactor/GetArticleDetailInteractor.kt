package com.dbs.challenge.feature.articledetail.domain.interactor

import com.dbs.challenge.core.interactor.Interactor
import com.dbs.challenge.feature.articledetail.domain.model.ArticleEntity
import com.dbs.challenge.feature.articledetail.domain.repository.IArticleDetailRepository
import kotlinx.coroutines.flow.Flow

class GetArticleDetailInteractor(
    private val iArticleRepository: IArticleDetailRepository
) : Interactor<String, Flow<ArticleEntity>> {

    override fun execute(id: String): Flow<ArticleEntity> {
        return iArticleRepository.getArticleDetail(id)
    }
}