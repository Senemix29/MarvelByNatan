package ximenapps.com.br.marvelbynatan.network.infrastructure

import ximenapps.com.br.marvelbynatan.network.auth.ApiAuthenticatorInterceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ximenapps.com.br.marvelbynatan.BuildConfig
import java.util.concurrent.TimeUnit

class NetworkInfraStructure {
    private fun getDefaultOkHttpClient(): OkHttpClient {
        val timeout = 60L
        val builder = OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(ApiAuthenticatorInterceptor())
            .addInterceptor(HttpLoggingInterceptor().also {
                if (BuildConfig.DEBUG) it.level = HttpLoggingInterceptor.Level.BODY
            })

        return builder.build()
    }

    fun getRetrofit(
        baseUrl: String = BuildConfig.BASE_URL,
        okHttpClient: OkHttpClient = getDefaultOkHttpClient()
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseUrl)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
