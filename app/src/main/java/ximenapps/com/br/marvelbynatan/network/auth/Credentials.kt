package ximenapps.com.br.marvelbynatan.network.auth

import java.security.MessageDigest
import java.util.concurrent.TimeUnit

private const val MD5 = "MD5"

val timestamp = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis()).toString()
fun generateHash(vararg texts: String): String {
    val concatenatedText = texts.joinToString(separator = "")
    return md5(concatenatedText)
}

private fun md5(input: String): String {
    val bytes = MessageDigest
        .getInstance(MD5)
        .digest(input.toByteArray())
    return bytes.toHex()
}

private fun ByteArray.toHex(): String {
    return joinToString("") { "%02x".format(it) }
}