package com.example.helicopter.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.helicopter.R
import com.example.helicopter.database.DBHelper
import com.example.helicopter.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = DBHelper(this)


        binding.buttonSignup.setOnClickListener {

            val username = binding.editUsername.text.toString()
            val password = binding.editPassword.text.toString()
            val passwordC = binding.editConfirmPassword.text.toString()

            if(username.isNotEmpty() && password.isNotEmpty()&&passwordC.isNotEmpty()){
            if (password==passwordC) {
                val res = db.insertUser(username, password)
                if (res > 0) {
                    Toast.makeText(
                        applicationContext,
                        getString(R.string.cadastro_ok),
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                } else {
                   Toast.makeText(
                       applicationContext,
                       getString(R.string.cadastro_invalido),
                       Toast.LENGTH_SHORT
                   ).show()
                    binding.editUsername.setText("")
                    binding.editPassword.setText("")
                    binding.editConfirmPassword.setText("")
                }
            } else {
                Toast.makeText(
                    applicationContext,
                    getString(R.string.senhas_diferentes),
                    Toast.LENGTH_SHORT
                ).show()
            }
            }else{

                Toast.makeText(
                    applicationContext,
                    getString(R.string.insira_todos_os_campos),
                    Toast.LENGTH_SHORT
                ).show()
            }

            }


        }
    }

