package kg.tutorialapp.weather.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.tutorialapp.weather.HourlyForecast
import kg.tutorialapp.weather.R
import kg.tutorialapp.weather.format
import kg.tutorialapp.weather.models.Constants
import kotlin.math.roundToInt

class HourlyForeCastVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val tv_time = itemView.findViewById<TextView>(R.id.tv_time)
    private val iv_weather_icon = itemView.findViewById<ImageView>(R.id.iv_weather_icon)
    private val tv_precipitation = itemView.findViewById<TextView>(R.id.tv_precipitation)
    private val tv_temp = itemView.findViewById<TextView>(R.id.tv_temp)


    fun bind(item: HourlyForecast) {
        itemView.run {
            tv_time.text = item.date.format("HH:mm")
            item.probability?.let {
                tv_precipitation.text = "${(it * 100).roundToInt()}% "
            }
            tv_temp.text = item.temp?.roundToInt()?.toString()

            Glide.with(itemView.context)
                .load("${Constants.iconUri}${item.weather?.get(0)?.icon}${Constants.iconFormat}")
                .into(iv_weather_icon)
        }
    }

    companion object {

        fun create(parent: ViewGroup): HourlyForeCastVH {

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_hourly_forecast, parent, false)
            return HourlyForeCastVH(view)
        }

    }
}