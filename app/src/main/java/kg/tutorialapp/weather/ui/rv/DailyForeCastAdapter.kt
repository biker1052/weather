package kg.tutorialapp.weather.ui.rv

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kg.tutorialapp.weather.DailyForecast

class DailyForeCastAdapter : RecyclerView.Adapter<DailyForeCastVH>() {
    private val items = arrayListOf<DailyForecast>()
    fun setItems(newItems:List<DailyForecast>){
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DailyForeCastVH {
        return DailyForeCastVH.create(parent)
    }

    override fun onBindViewHolder(holder: DailyForeCastVH, position: Int) {
         holder.bind(items[position])
    }

    override fun getItemCount() = items.count()
}