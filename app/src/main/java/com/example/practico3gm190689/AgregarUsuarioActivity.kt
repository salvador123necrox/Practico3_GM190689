package com.example.practico3gm190689

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import com.example.practico3gm190689.models.Usuarios

class AgregarUsuarioActivity : AppCompatActivity() {

    private lateinit var etNombres: EditText
    private lateinit var etApellidos: EditText
    private lateinit var etEmail: EditText
    private lateinit var etUsuario: EditText
    private lateinit var etPassword: EditText
    private lateinit var spTipo: Spinner
    private lateinit var btnAgregar: Button
    private lateinit var btnRegresar: Button

    private var usuariosModelo: Usuarios? = null

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_agregar_usuario)

        etNombres = findViewById(R.id.etNombres)
        etApellidos = findViewById(R.id.etApellidos)
        etEmail = findViewById(R.id.etEmail)
        etUsuario = findViewById(R.id.etUsuario)
        etPassword = findViewById(R.id.etPassword)
        spTipo = findViewById(R.id.spTipoUsuario)
        btnAgregar = findViewById(R.id.btnAgregar)
        btnRegresar = findViewById(R.id.btnRegresar)
        setSpinnerTipoUsuario()
        btnAgregar.setOnClickListener { this.CrearUsuario() }
        btnRegresar.setOnClickListener {
            val activity = Intent(this, MainActivity::class.java)
            startActivity(activity)
            finish()
        }
    }

    fun CrearUsuario() {
        usuariosModelo = Usuarios(this)
        var nombres = etNombres.text.toString()
        var apellidos = etApellidos.text.toString()
        var email = etEmail.text.toString()
        var usuario = etUsuario.text.toString()
        var password = etPassword.text.toString()
        var tipoUsuario = spTipo.selectedItem.toString()
        usuariosModelo!!.AgregarUsuario(null, nombres, apellidos, email, usuario, password, tipoUsuario)
        Toast.makeText(this, "Se creo correctamente el usuario", Toast.LENGTH_LONG).show()
        val activity = Intent(this, MainActivity::class.java)
        startActivity(activity)
        finish()
    }

    fun setSpinnerTipoUsuario(){
        var tipoUsuario = ArrayList<String>()
        tipoUsuario.add("Administrador")
        tipoUsuario.add("Cliente")
        var adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, tipoUsuario)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spTipo.adapter = adapter
    }
}