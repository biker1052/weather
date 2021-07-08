package kg.tutorialapp.weather.storage

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kg.tutorialapp.weather.DailyForecast
import kg.tutorialapp.weather.HourlyForecast

class CollectionsConverter {
    @TypeConverter
    fun fromHourlyForeCastListToJson(list: List<HourlyForecast>?): String? =
        Gson().toJson(list)

    @TypeConverter
    fun fromJsonToHourlyForeCastList(json: String?): List<HourlyForecast>? =
        Gson().fromJson(json, object : TypeToken<List<DailyForecast>>() {}.type)

    @TypeConverter
    fun fromDailyForeCastListToJson(list: List<DailyForecast>?): String? =
        Gson().toJson(list)

    @TypeConverter
    fun fromJsonToDailyForeCastList(json: String?): List<DailyForecast>? =
        Gson().fromJson(json, object : TypeToken<List<DailyForecast>>() {}.type)
}