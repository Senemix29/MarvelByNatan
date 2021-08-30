package ximenapps.com.br.marvelbynatan.features.characters.presentation.utils

import android.net.Uri
import okhttp3.mockwebserver.*
import java.lang.Exception
import java.util.*

const val NOT_FOUND_HTTP_CODE = 404
const val SUCCESS_HTTP_CODE = 200
private val mockResponsesQueue: Queue<Pair<String, MockResponse>> = LinkedList()

fun mockRequest(
    path: String,
    response_file: String,
    statusCode: Int = SUCCESS_HTTP_CODE
) {
    readFile(response_file)?.let { jsonResponse ->
        val response = MockResponse()
            .setBody(jsonResponse)
            .setResponseCode(statusCode)

        mockResponsesQueue.add(Pair(path, response))
    }
}

fun mockRequestWIthNetworkError(path: String) {
    val response = MockResponse().setSocketPolicy(SocketPolicy.NO_RESPONSE)
    mockResponsesQueue.add(Pair(path, response))
}

fun MockWebServer.initDispatcher() {
    val mockRequestDispatcher = object : Dispatcher() {
        override fun dispatch(request: RecordedRequest): MockResponse {
            val requestPath = takeRequest().getPathWithoutQueryParams()
            val queuedResponse = mockResponsesQueue.removeOrNull()

            return getQueuedResponseIfMatchesRequest(queuedResponse, requestPath)
        }
    }
    dispatcher = mockRequestDispatcher
}

private fun getQueuedResponseIfMatchesRequest(
    responsePair: Pair<String, MockResponse>?,
    requestPath: String
) = if (responsePair?.first == requestPath) {
    responsePair.second
} else {
    MockResponse().setResponseCode(NOT_FOUND_HTTP_CODE)
}

private fun Queue<Pair<String, MockResponse>>.removeOrNull(): Pair<String, MockResponse>? {
    return try {
        this.remove()
    } catch (e: Exception) {
        null
    }
}

private fun RecordedRequest.getPathWithoutQueryParams(): String {
    val recordedPath = path
    return Uri.parse(recordedPath).path.orEmpty()
}

