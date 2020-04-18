package com.dbs.challenge.feature.articledetail.domain.interactor

import com.dbs.challenge.core.interactor.Interactor
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity
import com.dbs.challenge.feature.articledetail.domain.repository.IArticleDetailRepository
import kotlinx.coroutines.flow.Flow

class GetArticleDetailInteractor(
    private val iArticleRepository: IArticleDetailRepository
) : Interactor<String, Flow<ArticleDetailEntity>> {

    override fun execute(id: String): Flow<ArticleDetailEntity> {
        return iArticleRepository.getArticleDetail(id)
    }
}