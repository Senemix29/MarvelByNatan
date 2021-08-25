package ximenapps.com.br.marvelbynatan

import android.app.Application
import io.reactivex.rxjava3.internal.functions.Functions
import io.reactivex.rxjava3.plugins.RxJavaPlugins
import org.koin.core.context.startKoin
import ximenapps.com.br.marvelbynatan.application.di.KoinAppDeclarationProvider

class MarvelApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        setupRx()
        initKoin()
    }

    private fun initKoin() {
        startKoin(appDeclaration = KoinAppDeclarationProvider().get(this))
    }

    private fun setupRx() {
        RxJavaPlugins.setErrorHandler(Functions.emptyConsumer());
    }
}