package com.example.practico3gm190689

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.practico3gm190689.models.Colores

class AgregarColorActivity : AppCompatActivity() {
    private lateinit var descripcion: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var colorModelo: Colores? = null

    private var id: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_color)

        this.id = intent.getIntExtra("idcolor", 0)

        descripcion = findViewById(R.id.etDescripcionColor)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        if(id == 0){
            btnAgregar.text = "Crear"
        } else {
            btnAgregar.text = "Modificar"
            colorModelo = Colores(this)
            var descripcionColor: String = colorModelo!!.searchNombre(id).toString()
            descripcion.setText(descripcionColor)
        }

        btnAgregar.setOnClickListener {
            if(id == 0){
                this.CrearColor()
            } else {
                this.ModificarColor()
            }
        }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun CrearColor() {
        colorModelo = Colores(this)
        var descripcionColor = descripcion.text.toString()
        colorModelo!!.AgregarColor(null, descripcionColor)
        Toast.makeText(this, "Se creo  el color", Toast.LENGTH_LONG).show()
        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun ModificarColor(){
        colorModelo = Colores(this)
        var descripcionColor = descripcion.text.toString()
        colorModelo!!.ModificarColor(id, descripcionColor)
        Toast.makeText(this, "Se modifico  el color", Toast.LENGTH_LONG).show()
        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}