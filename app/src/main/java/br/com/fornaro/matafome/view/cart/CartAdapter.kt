package br.com.fornaro.matafome.view.cart

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.matafome.database.entities.CartItem
import br.com.fornaro.matafome.databinding.ItemCartItemBinding
import br.com.fornaro.matafome.databinding.ItemCartRestaurantBinding
import br.com.fornaro.matafome.model.Restaurant

class CartAdapter(private val minusListener: (CartItem) -> Unit, private val plusListener: (CartItem) -> Unit) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val RESTAURANT = 0
        private const val ITEM = 1
    }

    private val list = mutableListOf<Any>()

    override fun getItemViewType(position: Int) =
            if (list[position] is Restaurant) RESTAURANT else ITEM

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            when (viewType) {
                RESTAURANT -> RestaurantViewHolder(ItemCartRestaurantBinding.inflate(LayoutInflater.from(parent.context), parent, false))
                else -> ItemViewHolder(ItemCartItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
            }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
            when (getItemViewType(position)) {
                RESTAURANT -> (holder as RestaurantViewHolder).bind(list[position] as Restaurant)
                else -> (holder as ItemViewHolder).bind(list[position] as CartItem, minusListener, plusListener)
            }

    fun setData(data: List<CartItem>) {
        list.clear()

        data.forEach { cartItem ->
            val restaurant = list.find { it is Restaurant && it.name == cartItem.restaurantName }
            if (restaurant == null) {
                list.add(Restaurant("", cartItem.restaurantName, "", "", cartItem.deliveryFee, cartItem.restaurantImage, 0.0))
            }
            list.add(cartItem)
        }
        notifyDataSetChanged()
    }

    class RestaurantViewHolder(private val binding: ItemCartRestaurantBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(restaurant: Restaurant) {
            binding.restaurant = restaurant
            binding.executePendingBindings()
        }
    }

    class ItemViewHolder(private val binding: ItemCartItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(cartItem: CartItem, minusListener: (CartItem) -> Unit, plusListener: (CartItem) -> Unit) {
            binding.cartItem = cartItem
            binding.executePendingBindings()

            binding.minusButton.setOnClickListener { minusListener(cartItem) }
            binding.plusButton.setOnClickListener { plusListener(cartItem) }
        }
    }
}