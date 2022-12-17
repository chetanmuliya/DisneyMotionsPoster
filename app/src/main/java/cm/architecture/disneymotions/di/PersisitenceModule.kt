package cm.architecture.disneymotions.di

import androidx.room.Room
import cm.architecture.disneymotions.model.local.AppDatabase
import cm.velotio.disneymotions.R
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val persistenceModule = module {
    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            androidApplication().getString(R.string.database)
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    single { get<AppDatabase>().posterDao() }
}