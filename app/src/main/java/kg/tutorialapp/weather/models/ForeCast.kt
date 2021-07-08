package kg.tutorialapp.weather

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
data class ForeCast(
        @PrimaryKey(autoGenerate = true)
        var id: Long? = null,
        var lat: Double? = null,
        var lon: Double? = null,
        var timezone: String? = null,
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

//"daily": [
//{
//    "dt": 1624431600,
//    "sunrise": 1624404208,
//    "sunset": 1624459449,
//    "moonrise": 1624454640,
//    "moonset": 1624398480,
//    "moon_phase": 0.44,
//    "temp": {
//    "day": 24.74,
//    "min": 12.39,
//    "max": 28.74,
//    "night": 17.78,
//    "eve": 27.38,
//    "morn": 16.12
//},
//    "feels_like": {
//    "day": 23.92,
//    "night": 16.58,
//    "eve": 26.33,
//    "morn": 14.94
//},
//    "pressure": 1015,
//    "humidity": 25,
//    "dew_point": 3.41,
//    "wind_speed": 4.32,
//    "wind_deg": 324,
//    "wind_gust": 4.76,
//    "weather": [
//    {
//        "id": 802,
//        "main": "Clouds",
//        "description": "переменная облачность",
//        "icon": "03d"
//    }
//    ],
//    "clouds": 35,
//    "pop": 0.29,
//    "uvi": 8.6
//},
//{
//    "dt": 1624518000,
//    "sunrise": 1624490625,
//    "sunset": 1624545856,
//    "moonrise": 1624545540,
//    "moonset": 1624487640,
//    "moon_phase": 0.48,
//    "temp": {
//    "day": 31.3,
//    "min": 15.96,
//    "max": 32.21,
//    "night": 18.78,
//    "eve": 26.88,
//    "morn": 20.09
//},
//    "feels_like": {
//    "day": 29.24,
//    "night": 18.28,
//    "eve": 26.42,
//    "morn": 19.09
//},
//    "pressure": 1007,
//    "humidity": 17,
//    "dew_point": 1.74,
//    "wind_speed": 5.09,
//    "wind_deg": 165,
//    "wind_gust": 8.33,
//    "weather": [
//    {
//        "id": 500,
//        "main": "Rain",
//        "description": "небольшой дождь",
//        "icon": "10d"
//    }
//    ],
//    "clouds": 2,
//    "pop": 0.7,
//    "rain": 1.06,
//    "uvi": 8.78
//},
//{
//    "dt": 1624604400,
//    "sunrise": 1624577043,
//    "sunset": 1624632261,
//    "moonrise": 1624635900,
//    "moonset": 1624577460,
//    "moon_phase": 0.5,
//    "temp": {
//    "day": 25.32,
//    "min": 14.94,
//    "max": 25.75,
//    "night": 16.69,
//    "eve": 22.76,
//    "morn": 16.57
//},
//    "feels_like": {
//    "day": 25.11,
//    "night": 16.19,
//    "eve": 22.45,
//    "morn": 16.35
//},
//    "pressure": 1010,
//    "humidity": 46,
//    "dew_point": 10.94,
//    "wind_speed": 4.02,
//    "wind_deg": 305,
//    "wind_gust": 4.94,
//    "weather": [
//    {
//        "id": 500,
//        "main": "Rain",
//        "description": "небольшой дождь",
//        "icon": "10d"
//    }
//    ],
//    "clouds": 36,
//    "pop": 0.8,
//    "rain": 5.23,
//    "uvi": 7.92
//},
//{
//    "dt": 1624690800,
//    "sunrise": 1624663464,
//    "sunset": 1624718665,
//    "moonrise": 1624725480,
//    "moonset": 1624667820,
//    "moon_phase": 0.56,
//    "temp": {
//    "day": 25.53,
//    "min": 14.55,
//    "max": 28.28,
//    "night": 20.11,
//    "eve": 27.4,
//    "morn": 14.55
//},
//    "feels_like": {
//    "day": 24.87,
//    "night": 18.99,
//    "eve": 26.4,
//    "morn": 13.89
//},
//    "pressure": 1010,
//    "humidity": 28,
//    "dew_point": 3.87,
//    "wind_speed": 4.95,
//    "wind_deg": 318,
//    "wind_gust": 5.44,
//    "weather": [
//    {
//        "id": 801,
//        "main": "Clouds",
//        "description": "небольшая облачность",
//        "icon": "02d"
//    }
//    ],
//    "clouds": 13,
//    "pop": 0.42,
//    "uvi": 8.27
//},
//{
//    "dt": 1624777200,
//    "sunrise": 1624749886,
//    "sunset": 1624805065,
//    "moonrise": 1624814400,
//    "moonset": 1624758600,
//    "moon_phase": 0.59,
//    "temp": {
//    "day": 28.4,
//    "min": 15.09,
//    "max": 31.8,
//    "night": 23.53,
//    "eve": 31.8,
//    "morn": 15.19
//},
//    "feels_like": {
//    "day": 27.08,
//    "night": 22.57,
//    "eve": 29.67,
//    "morn": 13.97
//},
//    "pressure": 1010,
//    "humidity": 22,
//    "dew_point": 2.71,
//    "wind_speed": 3.63,
//    "wind_deg": 323,
//    "wind_gust": 3.92,
//    "weather": [
//    {
//        "id": 800,
//        "main": "Clear",
//        "description": "ясно",
//        "icon": "01d"
//    }
//    ],
//    "clouds": 0,
//    "pop": 0.24,
//    "uvi": 8.88
//},
//{
//    "dt": 1624863600,
//    "sunrise": 1624836311,
//    "sunset": 1624891464,
//    "moonrise": 1624902780,
//    "moonset": 1624849380,
//    "moon_phase": 0.63,
//    "temp": {
//    "day": 33.3,
//    "min": 19.53,
//    "max": 36.13,
//    "night": 25.46,
//    "eve": 34.56,
//    "morn": 19.7
//},
//    "feels_like": {
//    "day": 30.95,
//    "night": 24.82,
//    "eve": 32.14,
//    "morn": 18.61
//},
//    "pressure": 1007,
//    "humidity": 15,
//    "dew_point": 1.48,
//    "wind_speed": 4.61,
//    "wind_deg": 328,
//    "wind_gust": 4.25,
//    "weather": [
//    {
//        "id": 500,
//        "main": "Rain",
//        "description": "небольшой дождь",
//        "icon": "10d"
//    }
//    ],
//    "clouds": 0,
//    "pop": 0.32,
//    "rain": 0.16,
//    "uvi": 9
//},
//{
//    "dt": 1624950000,
//    "sunrise": 1624922737,
//    "sunset": 1624977861,
//    "moonrise": 0,
//    "moonset": 1624940040,
//    "moon_phase": 0.66,
//    "temp": {
//    "day": 32.87,
//    "min": 22.31,
//    "max": 34.23,
//    "night": 24.85,
//    "eve": 25.83,
//    "morn": 23.7
//},
//    "feels_like": {
//    "day": 30.96,
//    "night": 24.38,
//    "eve": 25.57,
//    "morn": 22.88
//},
//    "pressure": 1006,
//    "humidity": 22,
//    "dew_point": 5.46,
//    "wind_speed": 8.37,
//    "wind_deg": 277,
//    "wind_gust": 11.96,
//    "weather": [
//    {
//        "id": 500,
//        "main": "Rain",
//        "description": "небольшой дождь",
//        "icon": "10d"
//    }
//    ],
//    "clouds": 98,
//    "pop": 0.51,
//    "rain": 0.91,
//    "uvi": 9
//},
//{
//    "dt": 1625036400,
//    "sunrise": 1625009165,
//    "sunset": 1625064255,
//    "moonrise": 1624990800,
//    "moonset": 1625030580,
//    "moon_phase": 0.7,
//    "temp": {
//    "day": 34.89,
//    "min": 22.08,
//    "max": 36.08,
//    "night": 22.08,
//    "eve": 27.04,
//    "morn": 22.96
//},
//    "feels_like": {
//    "day": 32.71,
//    "night": 21.83,
//    "eve": 26.79,
//    "morn": 22.09
//},
//    "pressure": 1003,
//    "humidity": 18,
//    "dew_point": 5.78,
//    "wind_speed": 9.27,
//    "wind_deg": 198,
//    "wind_gust": 12.02,
//    "weather": [
//    {
//        "id": 500,
//        "main": "Rain",
//        "description": "небольшой дождь",
//        "icon": "10d"
//    }
//    ],
//    "clouds": 5,
//    "pop": 0.83,
//    "rain": 2.11,
//    "uvi": 9
//}
//]
//}