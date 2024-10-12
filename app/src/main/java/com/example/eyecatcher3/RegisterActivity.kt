package com.example.eyecatcher3

import android.content.Intent
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        sharedPreferences = getSharedPreferences("UserPreferences", Context.MODE_PRIVATE)

        val registerEmailEditText: EditText = findViewById(R.id.registerEmailEditText)
        val registerPasswordEditText: EditText = findViewById(R.id.registerPasswordEditText)
        val registerButton: Button = findViewById(R.id.registerButton)
        val loginRedirectTextView: TextView = findViewById(R.id.loginRedirectTextView)

        registerButton.setOnClickListener {
            val email = registerEmailEditText.text.toString()
            val password = registerPasswordEditText.text.toString()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                // Guardar las credenciales del usuario en SharedPreferences
                val editor = sharedPreferences.edit()
                editor.putString("userEmail", email)
                editor.putString("userPassword", password)
                editor.apply()

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar la redirección al inicio de sesión
        loginRedirectTextView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}

