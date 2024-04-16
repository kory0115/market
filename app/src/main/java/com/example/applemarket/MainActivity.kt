package com.example.applemarket

import android.annotation.SuppressLint
import android.app.Activity
import android.content.DialogInterface
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.view.View
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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

    @SuppressLint("NotifyDataSetChanged")
    private val resultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            result: ActivityResult ->
        if(result.resultCode != Activity.RESULT_OK) {
            return@registerForActivityResult
        }

        if(result.resultCode == Activity.RESULT_OK) {
            val stat = result.data?.getBooleanExtra(LIKE_STATUS, false)
            val pos = result.data?.getIntExtra(POSITION, 0)!!
            if(stat == true) {
                dataList[pos - 1].status = true
                dataList[pos - 1].like += 1
            } else {
                dataList[pos - 1].status = false
                dataList[pos - 1].like -= 1
            }
            itemAdapter.notifyDataSetChanged()
        } else {
            Toast.makeText(this, "잘못된 접근 입니다.", Toast.LENGTH_SHORT).show()
        }
    }

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
        bindViews()
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
        val position = itemEntity.key
        dataList.remove(itemEntity)
        for(i in position - 1 until dataList.size) {
            dataList[i].key = i + 1
        }
        binding.recyclerView.adapter?.notifyDataSetChanged()
    }

    private fun initViews() {
        dataList = ItemList().bind() as ArrayList<ItemEntity>

        itemAdapter = ItemListAdapter(onClick = {
            val intent = Intent(this, DetailActivity::class.java)

            intent.putExtra(DetailActivity.BLUE_CARD, it)
            intent.putExtra(DetailActivity.STATUS, it.status)
            resultLauncher.launch(intent)
        }, onLongClick = {
            deleteDialog(it)
        })
        itemAdapter.submitList(dataList)
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = itemAdapter
    }

    private fun bindViews() {
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

        binding.actionButton.setOnClickListener {
            binding.recyclerView.scrollToPosition(0)
        }

        binding.recyclerView.setOnScrollChangeListener { view, x, y, ox, oy ->
            if(y > oy) {
                val fadeIn = AlphaAnimation(0f, 1f).apply { duration = 300 }
                binding.actionButton.startAnimation(fadeIn)
                binding.actionButton.visibility = View.VISIBLE
            }

            if(y <= oy) {
                val fadeOut = AlphaAnimation(1f, 0f).apply { duration = 300 }
                binding.actionButton.startAnimation(fadeOut)
                binding.actionButton.visibility = View.GONE
            }
        }
    }

    companion object {
        const val LIKE_STATUS = "like_status"
        const val POSITION = "position"
    }
}