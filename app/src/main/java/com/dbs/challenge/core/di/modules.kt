package com.dbs.challenge.core.di


import com.dbs.challenge.BuildConfig
import com.dbs.challenge.core.api.IArticleApiClient
import com.dbs.challenge.feature.article.data.datasource.ArticleDataStore
import com.dbs.challenge.feature.article.data.repository.IArticleRepositoryImpl
import com.dbs.challenge.feature.article.domain.interactor.GetArticlesInteractor
import com.dbs.challenge.feature.article.domain.repository.IArticleRepository
import com.dbs.challenge.feature.article.presentation.viewmodel.ArticlesViewModel
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
    single {
        qualifier("GetArticlesInteractor")
        GetArticlesInteractor(
            get()
        )

    }
    single<IArticleRepository> {
        IArticleRepositoryImpl(get())

    }
    single {
        qualifier("ArticleDataStore")
        ArticleDataStore(get())
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