package com.example.applemarket

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.databinding.ActivityDetailBinding

class DetailActivity: AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate( layoutInflater ) }
    private lateinit var data: ItemEntity
    private var plugin: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindViews()
        initViews()
    }

    private fun initViews() {

        binding.backButton.setOnClickListener {
            finish()
        }

        binding.likeButton.setOnClickListener {
            plugin = if(!plugin) {
                binding.likeButton.setBackgroundResource(R.drawable.awesom_v2)
                true
            } else {
                binding.likeButton.setBackgroundResource(R.drawable.baseline_auto_awesome_24)
                false
            }
        }
    }

    private fun bindViews() {
        data = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(BLUE_CARD, ItemEntity::class.java)!!
        } else {
            intent?.getParcelableExtra<ItemEntity>(BLUE_CARD) as ItemEntity
        }

        binding.addressTextView.text = data.address
        binding.desTextView.setText(data.description)
        binding.priceTextView.text = data.price //1000단위로 끊는 기능 추가
        binding.titleImageView.setImageResource(data.image)
        binding.itemTextView.text = data.name
        binding.nameTextView.text = data.seller
    }

    companion object {
        const val BLUE_CARD = "bluecard"
    }
}