package kg.tutorialapp.weather.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.weather.ForeCast
import kg.tutorialapp.weather.R
import kg.tutorialapp.weather.network.WeatherClient
import kg.tutorialapp.weather.format
import kg.tutorialapp.weather.models.Constants
import kg.tutorialapp.weather.storage.ForeCastDatabase
import kg.tutorialapp.weather.ui.rv.DailyForeCastAdapter
import kg.tutorialapp.weather.ui.rv.HourlyForeCastAdapter
import kotlin.math.roundToInt


class MainActivity : AppCompatActivity() {
    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }

    private lateinit var tv_temperature: TextView
    private lateinit var tv_date: TextView
    private lateinit var tv_temp_max: TextView
    private lateinit var tv_temp_min: TextView
    private lateinit var tv_feels_like: TextView
    private lateinit var tv_weather: TextView
    private lateinit var tv_sunrise: TextView
    private lateinit var tv_sunset: TextView
    private lateinit var tv_humidity: TextView
    private lateinit var tv_refresh: TextView

    private lateinit var iv_weather_icon: ImageView

    private lateinit var progress: ProgressBar

    private lateinit var dailyForecastAdapter: DailyForeCastAdapter
    private lateinit var hourlyForecastAdapter: HourlyForeCastAdapter

    private lateinit var rv_daily_forecast: RecyclerView
    private lateinit var rv_hourly_forecast: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        variables()
        setupViews()
        setupRecycleViews()
        getWeatherFromApi()
        subscribeToLiveData()
    }

    private fun variables() {
        tv_temperature = findViewById(R.id.tv_temperature)
        tv_date = findViewById(R.id.tv_date)
        tv_temp_max = findViewById(R.id.tv_temp_max)
        tv_temp_min = findViewById(R.id.tv_temp_min)
        tv_feels_like = findViewById(R.id.tv_feels_like)
        tv_weather = findViewById(R.id.tv_weather)
        tv_sunrise = findViewById(R.id.tv_sunrise)
        tv_sunset = findViewById(R.id.tv_sunset)
        tv_humidity = findViewById(R.id.tv_humidity)
        tv_refresh = findViewById(R.id.tv_refresh)

        iv_weather_icon = findViewById(R.id.iv_weather_icon)

        progress = findViewById(R.id.progress)

        rv_daily_forecast = findViewById(R.id.rv_daily_forecast)
        rv_hourly_forecast = findViewById(R.id.rv_hourly_forecast)

    }

    private fun setupViews() {
        tv_refresh.setOnClickListener {
            showLoading()
            getWeatherFromApi()
        }

    }

    private fun setupRecycleViews() {
        dailyForecastAdapter = DailyForeCastAdapter()
        rv_daily_forecast.adapter = dailyForecastAdapter
        hourlyForecastAdapter = HourlyForeCastAdapter()
        rv_hourly_forecast.adapter = hourlyForecastAdapter

    }

    private fun showLoading() {
        progress.visibility = View.VISIBLE

    }

    private fun hideLoading() {
        progress.visibility = View.GONE
    }

    @SuppressLint("CheckResult")
    private fun getWeatherFromApi() {

        WeatherClient.weatherAPI.fetchWeather()
            .subscribeOn(Schedulers.io())
            .map {
                db.foreCastDao().insert(it)
                it
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                hideLoading()
            },
                {
                    hideLoading()
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                })

    }

    private fun subscribeToLiveData() {
        db.foreCastDao().getAll().observe(this, Observer {
            it?.let {
                setValuesToViews(it)
                loadWeatherIcon(it)
                setItemsToRecyclerView(it)

            }
        }

        )
    }

    private fun setValuesToViews(it: ForeCast) {
        tv_temperature.text = it.current?.temp?.roundToInt().toString()
        tv_date.text = it.current?.date?.format()
        tv_temp_max.text = it.daily?.get(0)?.temp?.max?.roundToInt().toString()
        tv_temp_min.text = it.daily?.get(0)?.temp?.min?.roundToInt().toString()
        tv_feels_like.text = it.current?.feels_like?.roundToInt().toString()
        tv_weather.text = it.current?.weather?.get(0)?.description
        tv_sunrise.text = it.current?.sunrise?.format("HH:mm")
        tv_sunset.text = it.current?.sunset?.format("HH:mm")
        tv_humidity.text = "${it.current?.humidity?.toString()} %"
    }

    private fun loadWeatherIcon(it: ForeCast) {
        it.current?.weather?.get(0)?.icon?.let { icon ->
            Glide.with(this)
                .load("${Constants.iconUri}${icon}${Constants.iconFormat}")
                .into(iv_weather_icon)
        }
    }

    private fun setItemsToRecyclerView(it: ForeCast) {
        it.daily?.let { dailyList ->
            dailyForecastAdapter.setItems(dailyList)
        }
        it.hourly?.let { hourlyList ->
            hourlyForecastAdapter.setItems(hourlyList)
        }
    }
}

