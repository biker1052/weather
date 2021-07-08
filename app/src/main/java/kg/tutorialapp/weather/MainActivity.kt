package kg.tutorialapp.weather

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kg.tutorialapp.weather.storage.ForeCastDatabase


class MainActivity : AppCompatActivity() {
    private val db by lazy {
        ForeCastDatabase.getInstance(applicationContext)
    }
    private lateinit var tv_forecast_list: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        variables()
        makeRxCall()

        db.foreCastDao().getAll().observe(this, Observer {
            tv_forecast_list.text = it?.toString()
        }

        )
    }

    private fun variables() {
        tv_forecast_list = findViewById(R.id.tv_forecast_list)
    }


    @SuppressLint("CheckResult")
    private fun makeRxCall() {
        WeatherClient.weatherAPI.fetchWeather()
                .subscribeOn(Schedulers.io())
                .map {
                    db.foreCastDao().deleteAll()
                    db.foreCastDao().insert(it)
                    it
                }
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                },
                        {
                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                        })

    }

//    @SuppressLint("CheckResult")
//    private fun getFromDb() {
//        db.foreCastDao()
//                .getAll()
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        {
//                            tv_forecast_list.text = it?.toString()
//                            Toast.makeText(this, "getFromDb", Toast.LENGTH_LONG).show()
//                        },
//                        {
//                            Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                            Log.i(TAG,it.message.toString())
//                        })
//
//    }


    companion object {
        const val TAG = "RX"
    }
}