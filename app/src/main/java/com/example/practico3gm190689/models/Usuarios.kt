package com.example.practico3gm190689.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.practico3gm190689.db.HelperDB

class Usuarios (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        val TABLE_NAME_USUARIOS = "usuarios"
        val COL_ID_USUARIO = "idusuario"
        val COL_NOMBRES = "nombre"
        val COL_APELLIDOS = "apellidos"
        val COL_EMAIL = "email"
        val COL_USER = "user"
        val COL_PASSWORD = "password"
        val COL_TIPO = "tipo"
        val CREATE_TABLE_USUARIOS = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_USUARIOS + "("
                        + COL_ID_USUARIO +" integer primary key autoincrement, "
                        + COL_NOMBRES + " varchar(45) NOT NULL,"
                        + COL_APELLIDOS + " varchar(45) NOT NULL,"
                        + COL_EMAIL + " varchar(45) NOT NULL,"
                        + COL_USER + " varchar(45) unique NOT NULL,"
                        + COL_PASSWORD + " varchar(45) NOT NULL,"
                        + COL_TIPO + " varchar(45) NOT NULL);"
                )
    }

    fun generarContentValues(idusuario: Int?, nombres: String?, apellidos: String?, email: String?, user: String?, password: String?, tipo: String?
    ): ContentValues {
        val valores = ContentValues()
        valores.put(COL_ID_USUARIO, idusuario)
        valores.put(COL_NOMBRES, nombres)
        valores.put(COL_APELLIDOS, apellidos)
        valores.put(COL_EMAIL, email)
        valores.put(COL_USER, user)
        valores.put(COL_PASSWORD, password)
        valores.put(COL_TIPO, tipo)
        return valores
    }

    fun ObtenerUsuarios(): Cursor? {
        val columns = arrayOf(COL_ID_USUARIO, COL_NOMBRES, COL_APELLIDOS, COL_EMAIL, COL_TIPO)
        return db!!.query(
            TABLE_NAME_USUARIOS,
            columns,
            null, null, null, null, "$COL_APELLIDOS ASC"
        )
    }

    fun AgregarUsuario(idusuario: Int?, nombres: String?, apellidos: String?, email: String?, user: String?, password: String?, tipo: String?) {
        db!!.insert(
            TABLE_NAME_USUARIOS,
            null,
            generarContentValues(idusuario, nombres, apellidos, email, user, password, tipo)
        )
    }


}