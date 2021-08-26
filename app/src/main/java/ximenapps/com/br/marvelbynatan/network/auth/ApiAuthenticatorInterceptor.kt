package ximenapps.com.br.marvelbynatan.network.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent
import ximenapps.com.br.marvelbynatan.BuildConfig
import ximenapps.com.br.marvelbynatan.BuildConfig.API_PRIVATE_KEY
import ximenapps.com.br.marvelbynatan.BuildConfig.API_PUBLIC_KEY
import ximenapps.com.br.marvelbynatan.network.exception.AuthException

private const val TS_QUERY_PARAM_NAME = "ts"
private const val API_KEY_QUERY_PARAM_NAME = "apikey"
private const val HASH_QUERY_PARAM_NAME = "hash"

class ApiAuthenticatorInterceptor : Interceptor, KoinComponent {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val hashKey = generateHash(timestamp, API_PRIVATE_KEY, API_PUBLIC_KEY)
        val newUrl = request.url
            .newBuilder()
            .addQueryParameter(TS_QUERY_PARAM_NAME, timestamp)
            .addQueryParameter(API_KEY_QUERY_PARAM_NAME, API_PUBLIC_KEY)
            .addQueryParameter(HASH_QUERY_PARAM_NAME, hashKey)
            .build()

        val ongoingRequest = request
            .newBuilder()
            .url(newUrl)
            .build()

        return chain.proceed(ongoingRequest)
    }
}