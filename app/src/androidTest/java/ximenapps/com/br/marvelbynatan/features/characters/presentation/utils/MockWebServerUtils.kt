package ximenapps.com.br.marvelbynatan.features.characters.presentation.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest

const val NOT_FOUND_HTTP_CODE = 404
const val SUCCESS_HTTP_CODE = 200
private val enqueuedResponsesRegister = mutableMapOf<String, MockResponse>()

fun MockWebServer.mockRequest(
    path: String,
    response_file: String,
    statusCode: Int = SUCCESS_HTTP_CODE
) {
    readFile(response_file)?.let { jsonResponse ->
        val response = MockResponse()
            .setBody(jsonResponse)
            .setResponseCode(statusCode)

        enqueuedResponsesRegister[path] = response
    }
}

fun MockWebServer.initDispatcher() {
    val mockRequestDispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val response = enqueuedResponsesRegister[takeRequest().path]
            return response ?: MockResponse().setResponseCode(NOT_FOUND_HTTP_CODE)
        }
    }
    dispatcher = mockRequestDispatcher
}

