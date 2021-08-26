package ximenapps.com.br.marvelbynatan.application.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.dsl.KoinAppDeclaration
import ximenapps.com.br.marvelbynatan.common.CommonsDI
import ximenapps.com.br.marvelbynatan.features.characters.di.CharactersDI
import ximenapps.com.br.marvelbynatan.network.di.NetworkDI

class KoinAppDeclarationProvider {
    fun get(applicationContext: Context): KoinAppDeclaration = {
        androidLogger()
        androidContext(applicationContext)

        NetworkDI().load()
        CommonsDI().load()
        CharactersDI().load()
    }
}