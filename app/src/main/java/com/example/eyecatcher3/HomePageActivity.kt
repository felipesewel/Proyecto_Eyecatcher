package com.example.eyecatcher3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class HomePageActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        sharedPreferences = getSharedPreferences("SettingsPreferences", Context.MODE_PRIVATE)
        val alarmSwitch: Switch = findViewById(R.id.alarmSwitch)
        val settingsButton: Button = findViewById(R.id.settingsButton)

        // Recuperar el estado guardado del Switch de alarma
        val isAlarmEnabled = sharedPreferences.getBoolean("alarmState", false)
        alarmSwitch.isChecked = isAlarmEnabled

        // Configurar el Switch de estado de alarma
        alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Guardar el estado del Switch en SharedPreferences
            sharedPreferences.edit().putBoolean("alarmState", isChecked).apply()

            if (isChecked) {
                Toast.makeText(this, "Alarma activada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Alarma desactivada", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar el botón de configuración
        settingsButton.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            startActivity(intent)
        }
    }
}



