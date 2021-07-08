package kg.tutorialapp.weather.storage

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kg.tutorialapp.weather.ForeCast


@Database(
        entities = [ForeCast::class],
        version = 1,
        exportSchema = false
)
@TypeConverters(ModelsConverter::class, CollectionsConverter::class)
abstract class ForeCastDatabase : RoomDatabase() {
    abstract fun foreCastDao(): ForeCastDao

    companion object {
        const val DB_NAME = "forecastDB"

        private var DB: ForeCastDatabase? = null

        fun getInstance(context: Context): ForeCastDatabase {
            if (DB == null) {
                DB = Room.databaseBuilder(
                        context,
                        ForeCastDatabase::class.java,
                        DB_NAME
                )
                        .fallbackToDestructiveMigration()
                        .build()
            }
            return DB!!
        }
    }
}