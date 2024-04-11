package com.example.applemarket

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    private lateinit var itemAdapter: ItemListAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initViews()
        addOnBackPressCallBack()
    }

    private fun addOnBackPressCallBack() { //33버전 이상 에서 backpress
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mainDiaLog()
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    private fun mainDiaLog() {
        AlertDialog.Builder(this)
            .setTitle("앱을 종료 합니다.")
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, key ->
                finish()
            })
            .setNegativeButton("No") { _, _ ->

            }
            .create()
            .show()
    }


    private fun initViews() {
        val dataList = ItemList().bind()

        itemAdapter = ItemListAdapter(dataList, onClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.BLUE_CARD, it)
            startActivity(intent)
        })
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = itemAdapter
    }
}