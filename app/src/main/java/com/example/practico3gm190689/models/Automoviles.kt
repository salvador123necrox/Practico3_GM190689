package com.example.practico3gm190689.models

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import com.example.practico3gm190689.db.HelperDB

class Automoviles (context: Context?) {
    private var helper: HelperDB? = null
    private var db: SQLiteDatabase? = null

    init {
        helper = HelperDB(context)
        db = helper!!.writableDatabase
    }

    companion object {
        val TABLE_NAME_AUTOMOVILES = "automoviles"
        val COL_ID_AUTOMOVIL = "idautomovil"
        val COL_MODELO = "modelo"
        val COL_NUMERO_VIN = "numero_vin"
        val COL_NUMERO_CHASIS = "numero_chasis"
        val COL_NUMERO_MOTOR = "numero_motor"
        val COL_NUMERO_ASIENTOS = "numero_asientos"
        val COL_ANIO = "anio"
        val COL_CAPACIDAD_ASIENTOS = "capacidad_asientos"
        val COL_PRECIO = "precio"
        val COL_URI_IMG = "URI_IMG"
        val COL_DESCRIPCION = "descripcion"
        val COL_ID_MARCA = "idmarca"
        val COL_ID_TIPO_AUTOMOVIL = "idtipoautomovil"
        val COL_ID_COLOR = "idcolor"
        val CREATE_TABLE_AUTOMOVILES = (
                "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_AUTOMOVILES + "("
                        + COL_ID_AUTOMOVIL +" integer primary key autoincrement, "
                        + COL_MODELO + " varchar(45) NOT NULL,"
                        + COL_NUMERO_VIN + " varchar(45) NOT NULL,"
                        + COL_NUMERO_CHASIS + " varchar(45) NOT NULL,"
                        + COL_NUMERO_MOTOR + " varchar(45) NOT NULL,"
                        + COL_NUMERO_ASIENTOS + " integer NOT NULL,"
                        + COL_ANIO + " year NOT NULL,"
                        + COL_CAPACIDAD_ASIENTOS + " integer NOT NULL,"
                        + COL_PRECIO + " decimal(10,2) NOT NULL,"
                        + COL_URI_IMG + " varchar(45) NOT NULL,"
                        + COL_DESCRIPCION + " varchar(45) NOT NULL,"
                        + COL_ID_MARCA + " integer,"
                        + COL_ID_TIPO_AUTOMOVIL + " integer,"
                        + COL_ID_COLOR + " integer,"
                        + "FOREIGN KEY (idmarca) REFERENCES marcas(idmarca),"
                        + "FOREIGN KEY (idtipoautomovil) REFERENCES tipo_automovil(idtipoautomovil),"
                        + "FOREIGN KEY (idcolor) REFERENCES colores(idcolor));"
                )
    }

    // ContentValues
    fun generarContentValues(idauto: Int?, modelo: String?, numerovin: String?, numerochasis: String?, numeromotor: String?, numeroasientos: Int?, anio: Int?, capacidad: Int?, precio: Double?, uri: String?, descripcion: String?, idmarca: Int?, idtipoauto: Int?, idcolor: Int?
    ): ContentValues? {
        val valores = ContentValues()
        valores.put(COL_ID_AUTOMOVIL, idauto)
        valores.put(COL_MODELO, modelo)
        valores.put(COL_NUMERO_VIN, numerovin)
        valores.put(COL_NUMERO_CHASIS, numerochasis )
        valores.put(COL_NUMERO_MOTOR, numeromotor )
        valores.put(COL_NUMERO_ASIENTOS, numeroasientos)
        valores.put(COL_ANIO, anio)
        valores.put(COL_CAPACIDAD_ASIENTOS, capacidad )
        valores.put(COL_PRECIO, precio )
        valores.put(COL_URI_IMG, uri )
        valores.put(COL_DESCRIPCION, descripcion )
        valores.put(COL_ID_MARCA, idmarca )
        valores.put(COL_ID_TIPO_AUTOMOVIL, idtipoauto )
        valores.put(COL_ID_COLOR, idcolor )
        return valores
    }

    fun ObtenerAutos(): Cursor? {
        val columns = arrayOf(COL_ID_AUTOMOVIL, COL_ID_MARCA, COL_MODELO, COL_PRECIO)
        return db!!.query(TABLE_NAME_AUTOMOVILES, columns, null, null, null, null, "$COL_ID_AUTOMOVIL ASC")
    }

    fun AgregarAuto(idauto: Int?, modelo: String?, numerovin: String?, numerochasis: String?, numeromotor: String?, numeroasientos: Int?, anio: Int?, capacidad: Int?, precio: Double?, uri: String?, descripcion: String?, idmarca: Int?, idtipoauto: Int?, idcolor: Int?){
        db!!.insert(TABLE_NAME_AUTOMOVILES, null, generarContentValues(idauto, modelo, numerovin, numerochasis, numeromotor, numeroasientos, anio, capacidad, precio, uri, descripcion, idmarca, idtipoauto, idcolor))
    }

    fun EliminarAuto(idauto: Int?){
        db!!.delete(TABLE_NAME_AUTOMOVILES, "$COL_ID_AUTOMOVIL=?", arrayOf(idauto.toString()))
    }
}