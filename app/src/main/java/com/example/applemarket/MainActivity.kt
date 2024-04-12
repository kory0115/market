package com.example.applemarket

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.applemarket.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy { ActivityMainBinding.inflate( layoutInflater ) }
    private lateinit var itemAdapter: ItemListAdapter
    private var dataList = arrayListOf<ItemEntity>()
    private lateinit var notice: Notification

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
        notice = Notification(this)
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.UPSIDE_DOWN_CAKE) {
            addOnBackPressCallBack()
        } else {
            onBackPressed()
        }
    }

    private fun addOnBackPressCallBack() { //33버전 이상 에서 backpress
        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                mainDiaLog()
            }
        }

        this.onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onBackPressed() { //33버전 미만
        super.onBackPressed()
        mainDiaLog()
    }

    private fun mainDiaLog() {
        AlertDialog.Builder(this)
            .setTitle("앱을 종료 합니다.")
            .setMessage("앱을 종료하고 배경화면으로 넘어갑니다.")
            .setIcon(R.drawable.baseline_chat_24)
            .setPositiveButton("Yes", DialogInterface.OnClickListener { dialogInterface, key ->
                finish()
            })
            .setNegativeButton("No") { _, _ ->

            }
            .create()
            .show()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun deleteDialog(list: ItemEntity) : Boolean {
        AlertDialog.Builder(this)
            .setTitle("경고!")
            .setMessage("항목을 삭제 하시겠습니까?")
            .setIcon(R.drawable.baseline_crisis_alert_24)
            .setPositiveButton("Yes") { _, _ ->
                deleteButton(list)
            }
            .setNegativeButton("No") { _, _ ->

            }
            .create()
            .show()
        return true
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteButton(itemEntity: ItemEntity) {
        dataList.remove(itemEntity)
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun initViews() {
        dataList = ItemList().bind() as ArrayList<ItemEntity>

        binding.bellButton.setOnClickListener {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                if (!NotificationManagerCompat.from(this).areNotificationsEnabled()) {
                    val intent = Intent(Settings.ACTION_APP_NOTIFICATION_SETTINGS).apply {
                        putExtra(Settings.EXTRA_APP_PACKAGE, packageName)
                    }
                    startActivity(intent)
                }
            }
            notice.deliverNotification()
        }

        itemAdapter = ItemListAdapter(onClick = {
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.BLUE_CARD, it)
            startActivity(intent)
        }, onLongClick = {
            deleteDialog(it)
        })
        itemAdapter.submitList(dataList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = itemAdapter
    }
}