package com.example.helicopter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helicopter.database.DBHelper
import com.example.helicopter.databinding.ActivityNewSpotBinding

class NewSpotActivity : AppCompatActivity() {
    private lateinit var binding: ActivityNewSpotBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewSpotBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(applicationContext)
        val i = intent
        binding.buttonSave.setOnClickListener {
            val client = binding.editClient.text.toString()
            val dateFlight = binding.editDateFlight.text.toString()
            val flight = binding.editFlight.text.toString()
            val travel = binding.editTravel.text.toString()
            val departure = binding.editDeparture.text.toString()
            val landing = binding.editLanding.text.toString()
            val steps = binding.editSteps.text.toString().toInt()
            if (client.isNotEmpty()&& dateFlight.isNotEmpty()&& flight.isNotEmpty() && travel.isNotEmpty() && departure.isNotEmpty() && landing.isNotEmpty()){
                val res = db.insertSpot(client,dateFlight,flight,travel,departure,landing,steps)
                if (res>0){
                    Toast.makeText(applicationContext,"Insert ok",Toast.LENGTH_SHORT).show()
                    setResult(1,i)
                    finish()
                }else{
                    Toast.makeText(applicationContext,"Insert Error",Toast.LENGTH_SHORT).show()
                }
            }


        }
        binding.buttonCancel.setOnClickListener {
            setResult(0,i)
            finish()

        }

    }
}