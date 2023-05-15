package com.example.practico3gm190689.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.practico3gm190689.models.*

class HelperDB(context: Context?) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {

    companion object {
        private const val DB_NAME = "practico3.sqlite"
        private const val DB_VERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(Marcas.CREATE_TABLE_MARCAS)
        db.execSQL(Colores.CREATE_TABLE_COLORES)
        db.execSQL(TipoAuto.CREATE_TABLE_TIPO_AUTOMOVIL)
        db.execSQL(Usuarios.CREATE_TABLE_USUARIOS)
        db.execSQL(Automoviles.CREATE_TABLE_AUTOMOVILES)
        db.execSQL(Favoritos.CREATE_TABLE_FAVORITOS)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) { }
}