package ximenapps.com.br.marvelbynatan.network.service

interface ServiceCreator {
    fun <T> create(serviceClazz: Class<T>): T
}