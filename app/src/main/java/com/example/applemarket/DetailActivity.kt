package com.example.applemarket

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.applemarket.databinding.ActivityDetailBinding
import com.google.android.material.snackbar.Snackbar
import java.text.DecimalFormat

class DetailActivity: AppCompatActivity() {
    private val binding by lazy { ActivityDetailBinding.inflate( layoutInflater ) }
    private lateinit var data: ItemEntity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        bindViews()
        initViews()
    }

    private fun initViews() {
        var stat = intent?.getBooleanExtra(STATUS, false)!!

        if(stat) {
            binding.likeButton.setBackgroundResource(R.drawable.awesom_v2)
        } else {
            binding.likeButton.setBackgroundResource(R.drawable.baseline_auto_awesome_24)
        }

        val position = data.key

        binding.backButton.setOnClickListener {
            setResult(Activity.RESULT_OK, Intent().apply {
                putExtra(MainActivity.LIKE_STATUS, stat)
                putExtra(MainActivity.POSITION, position)
            })
            finish()
        }

        binding.likeButton.setOnClickListener {
            stat = if(!stat) {
                binding.likeButton.setBackgroundResource(R.drawable.awesom_v2)
                Snackbar.make(binding.totLayout, "좋아요", Snackbar.LENGTH_SHORT).show()
                true
            } else {
                binding.likeButton.setBackgroundResource(R.drawable.baseline_auto_awesome_24)
                Snackbar.make(binding.totLayout, "좋아요 취소", Snackbar.LENGTH_SHORT).show()
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
        binding.priceTextView.text = data.price
        binding.titleImageView.setImageResource(data.image)
        binding.itemTextView.text = data.name
        binding.nameTextView.text = data.seller
    }

    companion object {
        const val BLUE_CARD = "bluecard"
        const val STATUS = "status"
    }
}