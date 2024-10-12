package com.example.eyecatcher3

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar

class SettingsActivity : AppCompatActivity() {

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        // Configurar la Toolbar como la ActionBar de la actividad
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        // Habilitar la flecha de "Atrás" en la Toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = ""

        // Inicializar SharedPreferences
        sharedPreferences = getSharedPreferences("SettingsPreferences", Context.MODE_PRIVATE)

        val distanceEditText: EditText = findViewById(R.id.distanceEditText)
        val confirmDistanceButton: Button = findViewById(R.id.confirmDistanceButton)
        val notificationSwitch: Switch = findViewById(R.id.notificationSwitch)
        val logoutButton: Button = findViewById(R.id.logoutButton)

        // Recuperar la distancia guardada previamente
        val savedDistance = sharedPreferences.getInt("activationDistance", -1)
        if (savedDistance != -1) {
            distanceEditText.setText(savedDistance.toString())
        }

        // Configurar el botón de confirmar distancia
        confirmDistanceButton.setOnClickListener {
            val distanceText = distanceEditText.text.toString()
            val distance = distanceText.toIntOrNull()
            if (distance != null && distance in 0..300) {
                // Guardar la distancia en SharedPreferences
                sharedPreferences.edit().putInt("activationDistance", distance).apply()
                Toast.makeText(this, "Distancia de activación guardada: $distance cm", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Por favor, ingresa un valor entre 0 y 300 cm", Toast.LENGTH_SHORT).show()
            }
        }

        // Recuperar el estado guardado del Switch de notificaciones
        val isNotificationsEnabled = sharedPreferences.getBoolean("notificationsState", false)
        notificationSwitch.isChecked = isNotificationsEnabled

        // Configurar el Switch de notificaciones
        notificationSwitch.setOnCheckedChangeListener { _, isChecked ->
            // Guardar el estado del Switch en SharedPreferences
            sharedPreferences.edit().putBoolean("notificationsState", isChecked).apply()

            if (isChecked) {
                Toast.makeText(this, "Notificaciones activadas", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Notificaciones desactivadas", Toast.LENGTH_SHORT).show()
            }
        }

        // Configurar el botón de cerrar sesión
        logoutButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }
    }

    // Manejar el evento de la flecha de "Atrás"
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                // Redirigir al usuario a la página de inicio (HomePageActivity)
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}







