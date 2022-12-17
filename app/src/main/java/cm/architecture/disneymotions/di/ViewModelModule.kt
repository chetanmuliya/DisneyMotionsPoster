package cm.architecture.disneymotions.di

import cm.architecture.disneymotions.view.main.MainViewModel
import org.koin.dsl.module

val viewmodelModule = module {

    single { MainViewModel(get()) }
}