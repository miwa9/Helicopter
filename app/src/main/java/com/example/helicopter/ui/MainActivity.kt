package com.example.helicopter.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.helicopter.Model.SpotModel
import com.example.helicopter.database.DBHelper
import com.example.helicopter.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var spotList: ArrayList<SpotModel>
    private lateinit var adapter: ArrayAdapter<SpotModel>
    private lateinit var result: ActivityResultLauncher<Intent>
    private lateinit var dbHelper:DBHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        dbHelper = DBHelper(this)
        val sharedPreferences = application.getSharedPreferences("login",Context.MODE_PRIVATE)
        loadList()
        binding.buttonLogout.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("username","")
            editor.apply()
            finish()
        }



        binding.listViewSpot.setOnItemClickListener { _, _, position, _ ->

           /* Toast.makeText(applicationContext,spotList[position].client, Toast.LENGTH_SHORT).show()*/

            val intent = Intent(applicationContext, SpotDetailActivity::class.java)
            intent.putExtra("id",spotList[position].id)
           // startActivity(intent)
            result.launch(intent)
        }

        binding.buttonAdd.setOnClickListener {
            result.launch(Intent(applicationContext, NewSpotActivity::class.java))
        }
            result = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
                if(it.data!=null && it.resultCode ==1){
                    loadList()

                }else if(it.data!=null && it.resultCode ==0){
                    Toast.makeText(applicationContext,"Operation Canceled", Toast.LENGTH_SHORT).show()
                }
            }
        }

    private fun loadList() {
        spotList = dbHelper.getAllSpot()
        adapter =
            ArrayAdapter(
                applicationContext,
                android.R.layout.simple_list_item_1,
                spotList
            )
        binding.listViewSpot.adapter = adapter

    }

}
