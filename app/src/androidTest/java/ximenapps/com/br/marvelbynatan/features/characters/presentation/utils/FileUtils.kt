package ximenapps.com.br.marvelbynatan.features.characters.presentation.utils

fun readFile(path: String): String? {
    return Unit.javaClass.classLoader?.getResource(path)?.readText()
}
