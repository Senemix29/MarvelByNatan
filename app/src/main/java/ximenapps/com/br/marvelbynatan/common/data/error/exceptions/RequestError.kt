package ximenapps.com.br.marvelbynatan.common.data.error.exceptions

sealed class RequestError : Throwable() {
    class ApiException : RequestError()
    class NetworkException : RequestError()
}
