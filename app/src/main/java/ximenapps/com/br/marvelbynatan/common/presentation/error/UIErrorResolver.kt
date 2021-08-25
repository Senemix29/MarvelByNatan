package ximenapps.com.br.marvelbynatan.common.presentation.error

import ximenapps.com.br.marvelbynatan.R
import ximenapps.com.br.marvelbynatan.common.data.error.exceptions.RequestError
import ximenapps.com.br.marvelbynatan.common.presentation.error.UIError

class UIErrorResolver {
    fun resolve(throwable: Throwable): UIError {
        return when (throwable) {
            is RequestError.ApiException -> getApiError()
            is RequestError.NetworkException -> getNetworkError()
            else -> getGenericError()
        }
    }

    private fun getApiError(): UIError {
        return UIError(
            R.string.ui_api_error_title,
            R.string.ui_api_error_message,
            R.drawable.ic_api_problem
        )
    }

    private fun getNetworkError(): UIError {
        return UIError(
            R.string.ui_network_error_title,
            R.string.ui_network_error_message,
            R.drawable.ic_connection_problem
        )
    }

    private fun getGenericError(): UIError {
        return UIError(
            R.string.ui_unknown_error_title,
            R.string.ui_unknown_error_message,
            R.drawable.ic_generic_error
        )
    }
}