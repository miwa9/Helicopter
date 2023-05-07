package com.example.helicopter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helicopter.Model.SpotModel
import com.example.helicopter.database.DBHelper
import com.example.helicopter.databinding.ActivitySpotDetailBinding


class SpotDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySpotDetailBinding
    private lateinit var db: DBHelper
    private var spotModel= SpotModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySpotDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val i = intent
        val id = i.extras?.getInt("id")
        db = DBHelper(applicationContext)

        if (id != null) {
            spotModel = db.getSpot(id)
            binding.editClient.setText(spotModel.client)
            binding.editDateFlight.setText(spotModel.dateFlight)
            binding.editFlight.setText(spotModel.flight)
            binding.editTravel.setText(spotModel.travel)
            binding.editDeparture.setText(spotModel.departure)
            binding.editLanding.setText(spotModel.landing)
            binding.editSteps.setText(spotModel.steps.toString())
        }



        binding.buttonCancel.setOnClickListener {

               binding.editClient.setText(spotModel.client)
                binding.editDateFlight.setText(spotModel.dateFlight)
                binding.editFlight.setText(spotModel.flight)
               binding.editTravel.setText(spotModel.travel)
               binding.editDeparture.setText(spotModel.departure)
                binding.editLanding.setText(spotModel.landing)
               binding.editSteps.setText(spotModel.steps.toString())

        }
            binding.buttonSave.setOnClickListener {
                val res = db.updateSpot(
                   id = spotModel.id,
                   client = binding.editClient.text.toString(),
                   dateFlight = binding.editDateFlight.text.toString(),
                   flight = binding.editFlight.text.toString(),
                   travel = binding.editTravel.text.toString(),
                   departure = binding.editDeparture.text.toString(),
                   landing = binding.editLanding.text.toString(),
                   steps = binding.editSteps.text.toString().toInt()
                )
                if (res > 0) {
                    Toast.makeText(applicationContext, "Update ok", Toast.LENGTH_SHORT).show()
                    setResult(1,i)
                    finish()
                } else {
                    Toast.makeText(applicationContext, "Update error", Toast.LENGTH_SHORT).show()

                    setResult(0,i)
                    finish()
                }
           }
            binding.buttonDelete.setOnClickListener {
               val res = db.deleteSpot(spotModel.id)
               if (res > 0) {
                   Toast.makeText(applicationContext, "Delete ok", Toast.LENGTH_SHORT).show()
                   setResult(1,i)
                   finish()
                } else {
                   Toast.makeText(applicationContext, "Delete error", Toast.LENGTH_SHORT).show()
                   setResult(0,i)
                    finish()
                }
            }
        }
    }


