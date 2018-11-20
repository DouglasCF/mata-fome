package br.com.fornaro.matafome.view.restaurants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.matafome.databinding.ItemRestaurantBinding
import br.com.fornaro.matafome.model.Restaurant

class RestaurantsAdapter(private val listener: (Restaurant) -> Unit) :
    RecyclerView.Adapter<RestaurantsAdapter.ViewHolder>() {

    private val list = mutableListOf<Restaurant>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        ViewHolder(ItemRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position], listener)
    fun setData(data: List<Restaurant>) {
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant, listener: (Restaurant) -> Unit) {
            binding.restaurant = restaurant
            binding.executePendingBindings()

            itemView.setOnClickListener { listener(restaurant) }
        }
    }
}