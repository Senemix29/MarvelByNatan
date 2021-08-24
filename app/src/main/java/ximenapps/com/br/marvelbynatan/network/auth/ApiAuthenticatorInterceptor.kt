package ximenapps.com.br.marvelbynatan.network.auth

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import org.koin.core.component.KoinComponent
import ximenapps.com.br.marvelbynatan.BuildConfig
import ximenapps.com.br.marvelbynatan.network.exception.AuthException

private const val ACCESS_TOKEN_HEADER = "x-access-token"
private const val AUTH_HEADER = "Authorization"
private const val AUTH_ENDPOINT = "challenge/api/login"
private const val API_KEY_JSON_FIELD = "api_key"

class ApiAuthenticatorInterceptor : Interceptor, KoinComponent {
    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()

        if (AuthMemoryCache.token.isEmpty()) {
            authorizeUser(request, chain)
        }

        val ongoingRequest = request
            .newBuilder()
            .addHeader(ACCESS_TOKEN_HEADER, AuthMemoryCache.token)
            .build()

        return chain.proceed(ongoingRequest)
    }

    private fun authorizeUser(request: Request, chain: Interceptor.Chain) {
        val authRequest = createAuthorizationRequest(request)
        val authRequestResponse = chain.proceed(authRequest)
        val apiKey = getApiKeyFromResponse(authRequestResponse)
        if (apiKey.isEmpty()) throw AuthException()

        saveCurrentToken(apiKey)
    }

    private fun createAuthorizationRequest(request: Request): Request {
        val credential = generateCredential()
        return request.newBuilder()
            .header(AUTH_HEADER, credential)
            .url(BuildConfig.BASE_URL + AUTH_ENDPOINT)
            .get()
            .build()
    }

    private fun getApiKeyFromResponse(authRequestResponse: Response): String {
        return try {
            val responseBody = authRequestResponse.body?.string()
//            JsonParser.parseString(responseBody).asJsonObject.get(API_KEY_JSON_FIELD).asString
            ""
        } catch (exception: Throwable) {
            ""
        }
    }

    private fun saveCurrentToken(token: String) {
        AuthMemoryCache.token = token
    }
}

private object AuthMemoryCache {
    var token = ""
}