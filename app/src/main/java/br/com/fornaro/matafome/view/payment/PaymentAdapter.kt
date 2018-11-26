package br.com.fornaro.matafome.view.payment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import br.com.fornaro.matafome.databinding.ItemPaymentBinding

class PaymentAdapter(private val list: Array<String>, private val listener: (String) -> Unit) :
        RecyclerView.Adapter<PaymentAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            ViewHolder(ItemPaymentBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(list[position], listener)

    class ViewHolder(private val binding: ItemPaymentBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(payment: String, listener: (String) -> Unit) {
            binding.payment = payment
            binding.executePendingBindings()

            itemView.setOnClickListener { listener(payment) }
        }
    }
}