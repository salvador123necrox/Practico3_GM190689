package com.example.practico3gm190689

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.practico3gm190689.models.Marcas

class AgregarMarcaActivity : AppCompatActivity() {

    private lateinit var nombre: EditText
    private lateinit var btnCrear: Button
    private lateinit var btnRegresar: Button

    private var marcasModelo: Marcas? = null

    private var id: Int = 0

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_marca)

        this.id = intent.getIntExtra("idmarca", 0)

        nombre = findViewById(R.id.etNombreMarca)
        btnCrear = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        if(id != 0){
            btnCrear.text = "Modificar"
            marcasModelo = Marcas(this)
            var nombreMarca: String = marcasModelo!!.searchNombre(id).toString()
            nombre.setText(nombreMarca)
        } else {
            btnCrear.text = "Crear"
        }

        btnCrear.setOnClickListener {
            if(id == 0){
                this.CrearMarca()
            } else {
                this.ModificarMarca()
            }
        }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun CrearMarca() {
        marcasModelo = Marcas(this)
        var nombreMarca = nombre.text.toString()
        marcasModelo!!.AgregarMarca(null, nombreMarca)
        Toast.makeText(this, "Se creo la marca", Toast.LENGTH_LONG).show()
        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun ModificarMarca()
    {
        marcasModelo = Marcas(this)
        var nombreMarca = nombre.text.toString()
        marcasModelo!!.ModificarMarca(id, nombreMarca)
        Toast.makeText(this, "Se modifico la marca", Toast.LENGTH_LONG).show()
        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}