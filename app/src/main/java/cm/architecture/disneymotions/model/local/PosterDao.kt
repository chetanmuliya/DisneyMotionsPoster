package cm.architecture.disneymotions.model.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import cm.architecture.disneymotions.model.data.Poster

@Dao
interface PosterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosterList(posters: List<Poster>)

    @Query("SELECT * FROM Poster WHERE id = :id_")
    fun getPoster(id_: Long): Poster

    @Query("SELECT * FROM Poster")
    fun getPosterList(): List<Poster>
}