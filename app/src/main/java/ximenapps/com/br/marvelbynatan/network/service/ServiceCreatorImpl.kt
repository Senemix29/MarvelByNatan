package ximenapps.com.br.marvelbynatan.network.service

import retrofit2.Retrofit

class ServiceCreatorImpl(
    private val retrofit: Retrofit
) : ServiceCreator {
    override fun <T> create(serviceClazz: Class<T>): T {
        return retrofit.create(serviceClazz)
    }
}