package kg.tutorialapp.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ForeCast(

        var id: Long? = null,
        var lat: Double? = null,
        var lon: Double? = null,
        @PrimaryKey
        var timezone: String,
        var timezone_offset: Long? = 0,
        var current: CurrentForeCast? = null,
        var hourly: List<HourlyForecast>? = null,
        var daily: List<DailyForecast>? = null,
)

data class CurrentForeCast(
        @SerializedName("dt")
        var date: Long? = null,
        var sunrise: Long? = null,
        var sunset: Long? = null,
        var temp: Double? = null,
        var feels_like: Double? = null,
        var humidity: Int? = null,
        var weather: List<Weather>
)

data class Weather(
        var id: Long? = null,
        var main: String? = null,
        var description: String? = null,
        var icon: String? = null

)

data class HourlyForecast(
        @SerializedName("dt")
        var date: Long? = null,
        var temp: Double? = null,
        var weather: List<Weather>,
        @SerializedName("pop")
        var probability: Double? = null

)

data class DailyForecast(
        @SerializedName("dt")
        var date: Long? = null,
        var temp: Temperature? = null,
        var weather: List<Weather>,
        @SerializedName("pop")
        var probability: Double? = null

)

data class Temperature(
        var min: Double? = null,
        var max: Double? = null
)