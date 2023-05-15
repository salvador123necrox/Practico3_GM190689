package com.example.practico3gm190689

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.practico3gm190689.models.TipoAuto

class AgregarTipoAutoActivity : AppCompatActivity() {

    private lateinit var etDescripcionTipoAuto: EditText
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var TipoAuto: TipoAuto? = null

    private var idTipoAuto: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_tipo_auto)

        this.idTipoAuto = intent.getIntExtra("idtipoauto", 0)

        etDescripcionTipoAuto = findViewById(R.id.etDescripcionTipoAuto)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)

        if(idTipoAuto == 0) {
            btnAgregar.text = "Crear"
        } else {
            btnAgregar.text = "Modificar"
            TipoAuto = TipoAuto(this)
            var descripcionTipoAuto: String = TipoAuto!!.searchDescripcion(idTipoAuto).toString()
            etDescripcionTipoAuto.setText(descripcionTipoAuto)
        }

        btnAgregar.setOnClickListener {
            if(idTipoAuto == 0) {
                this.AgregarTipoAuto()
            } else {
                this.EditarTipoAuto()
            }
        }

        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun AgregarTipoAuto() {
        TipoAuto = TipoAuto(this)
        var DescripcionTipoAuto = etDescripcionTipoAuto.text.toString()

        TipoAuto!!.AgregarTipoAuto(null, DescripcionTipoAuto)

        Toast.makeText(this, "Se creo correctamente el tipo de automovil", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun EditarTipoAuto(){
        TipoAuto = TipoAuto(this)
        var descripcionTipoAuto = etDescripcionTipoAuto.text.toString()

        TipoAuto!!.ModificarTipoAuto(idTipoAuto, descripcionTipoAuto)

        Toast.makeText(this, "Se modifico correctamente el tipo de auto", Toast.LENGTH_LONG).show()

        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }
}