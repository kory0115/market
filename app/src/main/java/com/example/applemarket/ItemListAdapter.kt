package com.example.applemarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ViewholderMainBinding
import kotlinx.coroutines.runBlocking
import java.text.DecimalFormat

class ItemListAdapter(
    private val dataList: List<ItemEntity>,
    val onClick: (ItemEntity) -> Unit,
    val onLongClick: (ItemEntity) -> Boolean
): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ViewholderMainBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itemEntity: ItemEntity) {

            binding.chatTextView.text = itemEntity.chat.toString()
            binding.likeTextView.text = itemEntity.like.toString()
            binding.mainImageView.setImageResource(itemEntity.image)
            binding.priceTextView.text = itemEntity.price
            binding.titleTextView.setText(itemEntity.description)
            binding.subTextView.text = itemEntity.address

            binding.root.setOnClickListener {
                onClick(itemEntity)
            }

            binding.root.setOnLongClickListener {
                onLongClick(itemEntity)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ViewholderMainBinding.inflate( LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() = dataList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataList[position])
    }
}