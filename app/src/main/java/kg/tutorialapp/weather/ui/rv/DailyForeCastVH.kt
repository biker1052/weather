package kg.tutorialapp.weather.ui.rv

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kg.tutorialapp.weather.DailyForecast
import kg.tutorialapp.weather.R
import kg.tutorialapp.weather.format
import kg.tutorialapp.weather.models.Constants
import kotlin.math.roundToInt

class DailyForeCastVH(itemView: View):RecyclerView.ViewHolder(itemView) {
    private val tv_weekday=itemView.findViewById<TextView>(R.id.tv_weekday)
    private val tv_precipitation=itemView.findViewById<TextView>(R.id.tv_precipitation)
    private val tv_temp_max=itemView.findViewById<TextView>(R.id.tv_temp_max)
    private val tv_min_temp=itemView.findViewById<TextView>(R.id.tv_min_temp)
    private val iv_weather_icon=itemView.findViewById<ImageView>(R.id.iv_weather_icon)

    fun bind(item:DailyForecast){
        itemView.run {
            tv_weekday.text = item.date.format("dd/MM")
            item.probability?.let {
                tv_precipitation.text = "${(it*100).roundToInt()}% "
            }
            tv_temp_max.text=item.temp?.max?.roundToInt()?.toString()
            tv_min_temp.text=item.temp?.min?.roundToInt()?.toString()

            Glide.with(itemView.context)
                .load("${Constants.iconUri}${item.weather?.get(0)?.icon}${Constants.iconFormat}")
                .into(iv_weather_icon)

        }
    }
    companion object {

        fun create(parent: ViewGroup): DailyForeCastVH{

            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_daily_forecast,parent,false)
            return DailyForeCastVH(view)
        }

    }
}