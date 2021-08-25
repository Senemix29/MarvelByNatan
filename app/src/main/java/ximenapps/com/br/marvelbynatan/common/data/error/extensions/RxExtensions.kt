package ximenapps.com.br.marvelbynatan.common.data.error.extensions

import io.reactivex.rxjava3.core.Single
import ximenapps.com.br.marvelbynatan.common.data.error.exceptions.RequestError
import retrofit2.HttpException
import java.io.InterruptedIOException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

fun <T> Single<T>.handleRequestExceptions(): Single<T> {
    return onErrorResumeNext { Single.error(parseError(it)) }
}

private fun parseError(throwable: Throwable): Throwable {
    return when (throwable) {
        is HttpException -> RequestError.ApiException()
        is UnknownHostException,
        is TimeoutException,
        is InterruptedIOException,
        is SocketTimeoutException,
        is SocketException,
        is ConnectException -> RequestError.NetworkException()
        else -> throwable
    }
}



