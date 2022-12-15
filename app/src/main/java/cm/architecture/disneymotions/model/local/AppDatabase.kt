package cm.architecture.disneymotions.model.local

import androidx.room.Database
import androidx.room.RoomDatabase
import cm.architecture.disneymotions.model.data.Poster

@Database(entities = [Poster::class], version = 1, exportSchema = true)
abstract class AppDatabase : RoomDatabase() {

   abstract fun posterDao(): PosterDao
}