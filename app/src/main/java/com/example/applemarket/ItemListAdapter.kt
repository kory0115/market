package com.example.applemarket

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.applemarket.databinding.ViewholderMainBinding

class ItemListAdapter(
    private val dataList: List<ItemEntity>,
    val onClick: (ItemEntity) -> Unit
): RecyclerView.Adapter<ItemListAdapter.ViewHolder>() {


    inner class ViewHolder(private val binding: ViewholderMainBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(itemEntity: ItemEntity) {

            binding.chatTextView.text = itemEntity.chat.toString()
            binding.likeTextView.text = itemEntity.like.toString()
            binding.mainImageView.setImageResource(itemEntity.image)
            binding.priceTextView.text = itemEntity.price
            binding.titleTextView.setText(itemEntity.description)
            binding.subTextView.text = itemEntity.address
            //price 표현시 1000단위로 끊는 기능 추가

            binding.root.setOnClickListener {
                onClick(itemEntity)
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