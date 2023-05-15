package com.example.practico3gm190689.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.practico3gm190689.db.HelperDB

class TipoAuto (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        val TABLE_NAME_TIPO_AUTOMOVIL = "tipo_automovil"
        val COL_ID_TIPO_AUTOMOVIL = "idtipoautomovil"
        val COL_DESCRIPCION = "descripcion"
        val CREATE_TABLE_TIPO_AUTOMOVIL = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_TIPO_AUTOMOVIL + "("
                        + COL_ID_TIPO_AUTOMOVIL +" integer primary key autoincrement, "
                        + COL_DESCRIPCION + " varchar(45) NOT NULL);"
                )
    }
    fun generarContentValues(idtipoauto: Int?, descripcion: String?
    ) : ContentValues? {
        val valores = ContentValues()
        valores.put(COL_ID_TIPO_AUTOMOVIL, idtipoauto)
        valores.put(COL_DESCRIPCION, descripcion)
        return  valores
    }

    fun ObtenerTipoAuto(): Cursor ? {
        val columns = arrayOf(COL_ID_TIPO_AUTOMOVIL, COL_DESCRIPCION)
        return db!!.query(TABLE_NAME_TIPO_AUTOMOVIL, columns, null, null, null, null, "$COL_ID_TIPO_AUTOMOVIL ASC")
    }

    fun AgregarTipoAuto(idtipoauto: Int?, descripcion: String?){
        db!!.insert(TABLE_NAME_TIPO_AUTOMOVIL, null, generarContentValues(idtipoauto, descripcion))
    }

    fun ModificarTipoAuto(idtipoauto: Int?, descripcion: String?){
        db!!.update(TABLE_NAME_TIPO_AUTOMOVIL, generarContentValues(idtipoauto, descripcion), "$COL_ID_TIPO_AUTOMOVIL=?", arrayOf(idtipoauto.toString()))
    }

    fun EliminarTipoAuto(idtipoauto: Int?){
        db!!.delete(TABLE_NAME_TIPO_AUTOMOVIL, "$COL_ID_TIPO_AUTOMOVIL=?", arrayOf(idtipoauto.toString()))
    }

    fun searchID(nombre: String): Int? {
        val columns = arrayOf(COL_ID_TIPO_AUTOMOVIL, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(TABLE_NAME_TIPO_AUTOMOVIL, columns, "${COL_DESCRIPCION}=?", arrayOf(nombre.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getInt(0)
    }

    fun searchDescripcion(id: Int): String? {
        val columns = arrayOf(COL_ID_TIPO_AUTOMOVIL, COL_DESCRIPCION)
        var cursor: Cursor? = db!!.query(TABLE_NAME_TIPO_AUTOMOVIL, columns, "${COL_ID_TIPO_AUTOMOVIL}=?", arrayOf(id.toString()), null, null, null )
        cursor!!.moveToFirst()
        return cursor!!.getString(1)
    }
}