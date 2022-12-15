package cm.architecture.disneymotions.repository

import cm.architecture.disneymotions.model.local.PosterDao

class DetailRepository constructor(
    private val posterDao: PosterDao
) : Repository{

    fun getPosterById(){

    }
}