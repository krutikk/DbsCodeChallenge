package com.dbs.challenge.articledetail.usecase

import com.dbs.challenge.feature.articledetail.domain.interactor.GetArticleDetailInteractor
import com.dbs.challenge.feature.articledetail.domain.model.ArticleDetailEntity
import com.dbs.challenge.feature.articledetail.domain.repository.IArticleDetailRepository
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
class GetArticleDetailInteractorTest {

    @MockK
    lateinit var iArticleDetailRepository: IArticleDetailRepository
    lateinit var getRArticleDetailInteractor: GetArticleDetailInteractor

    @Before
    fun setUp() {
        MockKAnnotations.init(this)
        this.getRArticleDetailInteractor = GetArticleDetailInteractor(iArticleDetailRepository)
    }

    @Test
    fun `retrieve article success`() = runBlocking {
        val launchesChannel = ConflatedBroadcastChannel<ArticleDetailEntity>()
        launchesChannel.offer(TestData.detailList[0])
        coEvery { iArticleDetailRepository.getArticleDetail("1") } returns launchesChannel.asFlow()

        val articleDetailEntity = getRArticleDetailInteractor.execute("1").first()

        assertEquals(1L, articleDetailEntity.id)
    }


    @Test(expected = UnsupportedOperationException::class)
    fun `retrieve article error`() = runBlocking {
        val launchesChannel = ConflatedBroadcastChannel<ArticleDetailEntity>()
        launchesChannel.offer(TestData.detailList[0])
        coEvery { iArticleDetailRepository.getArticleDetail("1") } throws UnsupportedOperationException()

        val articleDetailEntity = getRArticleDetailInteractor.execute("1").first()
    }
}