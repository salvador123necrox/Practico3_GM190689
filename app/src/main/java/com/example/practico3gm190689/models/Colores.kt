package com.example.practico3gm190689.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.practico3gm190689.db.HelperDB

class Colores (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        val TABLE_NAME_COLORES = "colores"
        val COL_ID_COLORE = "idcolore"
        val COL_DESCRIPCION = "descripcion"
        val CREATE_TABLE_COLORES = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_COLORES + "("
                        + COL_ID_COLORE +" integer primary key autoincrement, "
                        + COL_DESCRIPCION + " varchar(45) NOT NULL);"
                )
    }
    fun generarContentValues(idcolor: Int?, descripcion: String?
    ) : ContentValues? {
        val valores = ContentValues()
        valores.put(COL_ID_COLORE, idcolor)
        valores.put(COL_DESCRIPCION, descripcion)
        return  valores
    }

    fun ObtenerColores(): Cursor? {
        val columns = arrayOf(COL_ID_COLORE, COL_DESCRIPCION)
        return db!!.query(TABLE_NAME_COLORES, columns, null, null, null, null, "${COL_DESCRIPCION} ASC")
    }

    fun AgregarColor(id: Int?, descripcion: String){
        db!!.insert(TABLE_NAME_COLORES, null, generarContentValues(id, descripcion))
    }

    fun ModificarColor(id: Int?, descripcion: String){
        db!!.update(TABLE_NAME_COLORES, generarContentValues(id, descripcion), "$COL_ID_COLORE=?", arrayOf(id.toString()))
    }

    fun EliminarColor(id: Int?){
        db!!.delete(TABLE_NAME_COLORES, "$COL_ID_COLORE=?", arrayOf(id.toString()))
    }

    fun searchID(nombre: String): Int? {
        val columns = arrayOf(COL_ID_COLORE, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(TABLE_NAME_COLORES, columns, "${COL_DESCRIPCION}=?", arrayOf(nombre.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getInt(0)
    }

    fun searchNombre(id: Int): String? {
        val columns = arrayOf(COL_ID_COLORE, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(TABLE_NAME_COLORES, columns, "${COL_ID_COLORE}=?", arrayOf(id.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getString(1)
    }

}