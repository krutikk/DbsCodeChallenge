package com.dbs.challenge.core.di


import androidx.room.Room
import com.dbs.challenge.BuildConfig
import com.dbs.challenge.core.api.IArticleApiClient
import com.dbs.challenge.core.database.ArticleDatabase
import com.dbs.challenge.core.utils.NetworkConnectivity
import com.dbs.challenge.feature.article.data.datasource.ArticleDataStore
import com.dbs.challenge.feature.article.data.datasource.ArticleLocalDataStore
import com.dbs.challenge.feature.article.data.repository.IArticleRepositoryImpl
import com.dbs.challenge.feature.article.domain.interactor.GetArticlesInteractor
import com.dbs.challenge.feature.article.domain.repository.IArticleRepository
import com.dbs.challenge.feature.article.presentation.viewmodel.ArticlesViewModel
import com.dbs.challenge.feature.articledetail.data.datasource.ArticleDetailDataStore
import com.dbs.challenge.feature.articledetail.data.repository.IArticleDetailRepositoryImpl
import com.dbs.challenge.feature.articledetail.domain.interactor.GetArticleDetailInteractor
import com.dbs.challenge.feature.articledetail.domain.repository.IArticleDetailRepository
import com.dbs.challenge.feature.articledetail.presentation.viewmodel.ArticleDetailViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.qualifier
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

private val appModule = module {

    viewModel {
        ArticlesViewModel(get())
    }
    viewModel {
        ArticleDetailViewModel(get())
    }
    single {
        qualifier("GetArticlesInteractor")
        GetArticlesInteractor(
            get()
        )

    }
    single { get<ArticleDatabase>().articleDao() }

    single {
        qualifier("GetArticleDetailInteractor")
        GetArticleDetailInteractor(
            get()
        )

    }
    single { Room.databaseBuilder(get(), ArticleDatabase::class.java, "article-database").build() }

    single<IArticleRepository> {
        IArticleRepositoryImpl(get(), get(), get())
    }
    single<IArticleDetailRepository> {
        IArticleDetailRepositoryImpl(get())

    }
    single {
        qualifier("ArticleDataStore")
        ArticleDataStore(get())
    }

    single {
        qualifier("ArticleDataStore")
        ArticleDetailDataStore(get())
    }
    single {
        qualifier("ArticleLocalDataStore")
        ArticleLocalDataStore(get())
    }

    single {
        NetworkConnectivity(get())
    }
}
val networkModule = module {
    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideArticleApi(get()) }
    single { provideRetrofit(get()) }
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder().baseUrl(BuildConfig.API_URL).client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create()).build()
}

fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(httpLoggingInterceptor).build()
}

fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
    return httpLoggingInterceptor
}

fun provideArticleApi(retrofit: Retrofit): IArticleApiClient =
    retrofit.create(IArticleApiClient::class.java)


val applicationModules = listOf(appModule, networkModule)