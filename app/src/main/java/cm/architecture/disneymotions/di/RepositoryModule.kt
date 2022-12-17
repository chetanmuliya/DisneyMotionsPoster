package cm.architecture.disneymotions.di

import cm.architecture.disneymotions.repository.DetailRepository
import cm.architecture.disneymotions.repository.MainRepository
import org.koin.dsl.module

val repositoryModule = module {

    single { MainRepository(get(),get()) }

    single { DetailRepository(get()) }
}