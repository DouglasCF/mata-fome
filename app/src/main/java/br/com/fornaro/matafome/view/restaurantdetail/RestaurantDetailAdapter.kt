package br.com.fornaro.matafome.view.restaurantdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.matafome.databinding.ItemRestaurantDetailBinding
import br.com.fornaro.matafome.model.RestaurantDetail

class RestaurantDetailAdapter : RecyclerView.Adapter<RestaurantDetailAdapter.ViewHolder>() {

    private val list = mutableListOf<RestaurantDetail>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(ItemRestaurantDetailBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position])

    fun setData(data: List<RestaurantDetail>) {
        list.clear()
        list.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(private val binding: ItemRestaurantDetailBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurantDetail: RestaurantDetail) {
            binding.restaurantDetail = restaurantDetail
            binding.executePendingBindings()
        }
    }
}