package kg.tutorialapp.weather.network

import io.reactivex.Single
import kg.tutorialapp.weather.ForeCast
import retrofit2.http.GET
import retrofit2.http.Query


interface WeatherApi {

    @GET("onecall")
    fun fetchWeather(
            @Query("lat") lat: Double = 42.882004,
            @Query("lon") lon: Double = 74.582748,
            @Query("exclude") exclude: String = "munutely",
            @Query("appid") appid: String = "7245060fa14284c7fa115ab12dba8cb9",
            @Query("lang") lang: String = "ru",
            @Query("units") units: String = "metric"
    ): Single<ForeCast>
}