package cm.architecture.disneymotions

import android.app.Application
import androidx.databinding.library.BuildConfig
import cm.architecture.disneymotions.di.networkModule
import cm.architecture.disneymotions.di.persistenceModule
import cm.architecture.disneymotions.di.repositoryModule
import cm.architecture.disneymotions.di.viewmodelModule
import cm.architecture.disneymotions.model.remote.GlobalResponseOperator
import com.skydoves.sandwich.SandwichInitializer
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class DisneyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

            startKoin{
                androidContext(this@DisneyApplication)
                modules(networkModule)
                modules(persistenceModule)
                modules(repositoryModule)
                modules(viewmodelModule)
            }

        SandwichInitializer.sandwichOperator = GlobalResponseOperator<Any>(this)

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}