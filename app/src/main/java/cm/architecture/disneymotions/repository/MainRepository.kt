package cm.architecture.disneymotions.repository

import cm.architecture.disneymotions.model.local.PosterDao
import cm.architecture.disneymotions.model.remote.DisneyService
import timber.log.Timber

class MainRepository constructor(
    private val disneyService: DisneyService,
    private val posterDao: PosterDao
): Repository {

    init {
        Timber.d("Injection MainRepository")
    }

}