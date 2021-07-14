package kg.tutorialapp.weather.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import kg.tutorialapp.weather.ForeCast


@Database(
        entities = [ForeCast::class],
        version = 2,
        exportSchema = false
)
@TypeConverters(ModelsConverter::class, CollectionsConverter::class)
abstract class ForeCastDatabase : RoomDatabase() {
    abstract fun foreCastDao(): ForeCastDao

    companion object {
        const val DB_NAME = "forecastDB"
    }
}