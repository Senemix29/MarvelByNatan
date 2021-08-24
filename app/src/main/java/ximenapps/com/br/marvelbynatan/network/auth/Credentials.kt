package ximenapps.com.br.marvelbynatan.network.auth

import android.util.Base64.NO_WRAP
import android.util.Base64.encodeToString

private const val PREFIX = "Basic "
fun generateCredential(username: String = "hello", password: String = "world") =
    PREFIX + encodeToString(
        "$username:$password".toByteArray(),
        NO_WRAP
    )
