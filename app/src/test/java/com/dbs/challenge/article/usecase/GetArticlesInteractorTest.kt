package com.dbs.challenge.article.usecase

import com.dbs.challenge.core.interactor.Interactor
import com.dbs.challenge.feature.article.domain.interactor.GetArticlesInteractor
import com.dbs.challenge.feature.article.domain.model.ArticleEntity
import com.dbs.challenge.feature.article.domain.repository.IArticleRepository
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity
import com.dbs.challenge.util.TestData
import io.mockk.MockKAnnotations
import io.mockk.coEvery
import io.mockk.impl.annotations.MockK
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.channels.ConflatedBroadcastChannel
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class GetArticlesInteractorTest {

    @MockK
    lateinit var iArticleDetailRepository: IArticleRepository
    lateinit var getArticleInteractor: GetArticlesInteractor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.getArticleInteractor = GetArticlesInteractor(iArticleDetailRepository)
    }

    @Test
    fun `retrieve articles success`() = runBlocking {
        val launchesChannel = ConflatedBroadcastChannel<List<ArticleEntity>>()
        launchesChannel.offer(TestData.dataList)
        coEvery { iArticleDetailRepository.getArticles() } returns launchesChannel.asFlow()

        val listOfdata = getArticleInteractor.execute(Interactor.None)

        assertEquals(3, listOfdata.first().size)
    }


    @Test(expected = UnsupportedOperationException::class)
    fun `retrieve article error`() = runBlocking {
        val launchesChannel = ConflatedBroadcastChannel<ArticleDetailEntity>()
        launchesChannel.offer(TestData.detailList[0])
        coEvery { iArticleDetailRepository.getArticles() } throws UnsupportedOperationException()

        val articleDetailEntity = getArticleInteractor.execute(Interactor.None).first()
    }
}