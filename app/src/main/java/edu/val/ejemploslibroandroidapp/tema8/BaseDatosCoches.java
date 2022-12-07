package edu.val.ejemploslibroandroidapp.tema8;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

import edu.val.ejemploslibroandroidapp.tema1y2.MainActivity;

public class BaseDatosCoches extends SQLiteOpenHelper {

    private static final String SQL_CREAR_TABLA_PERSONAS = "CREATE TABLE PERSONA ( id INTEGER PRIMARY KEY, nombre TEXT)";
    private static final String SQL_CREAR_TABLA_COCHES = "CREATE TABLE COCHE (id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, idpersona INTEGER, FOREIGN KEY (idpersona) REFERENCES PERSONA (id))";


    public BaseDatosCoches(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //incluir las instrucciones de creación - DDL
        db.execSQL(SQL_CREAR_TABLA_PERSONAS);
        db.execSQL(SQL_CREAR_TABLA_COCHES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //recuperación, creación de nuevas tablas y volcado de datos
    }

    private void cerrarBaseDatos (SQLiteDatabase database)
    {
        database.close();
    }


    public void insertarPersona (Persona persona)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String instruccion_insertar_persona = "INSERT INTO PERSONA (id, nombre) VALUES ("+persona.getId()+",'"+persona.getNombre()+"')";
        database.execSQL(instruccion_insertar_persona);
        cerrarBaseDatos (database);

    }

    public void insertarCoche (Coche coche)
    {
        SQLiteDatabase database = this.getWritableDatabase();
        String instruccion_insertar_coche = "INSERT INTO COCHE (modelo, idpersona) VALUES ('"+coche.getModelo()+"',"+coche.getPersona().getId()+")";
        database.execSQL(instruccion_insertar_coche);
        cerrarBaseDatos (database);
    }

    public List<Coche> obtenerCochesPersona (Persona persona)
    {
        List<Coche> cocheList = null;
        SQLiteDatabase sqLiteDatabase = null;
        String instruccion_consulta = "SELECT modelo FROM COCHE WHERE idpersona = " + persona.getId();
        Cursor cursor = null;
        String modelo_aux = null;
        Coche coche_aux = null;

            sqLiteDatabase = this.getReadableDatabase();
            cursor = sqLiteDatabase.rawQuery(instruccion_consulta, null);
            if (cursor!=null && cursor.getCount()>0)
            {
                Log.d(MainActivity.ETIQUETA_LOG, "La consulta ha recuperado datos");
                cursor.moveToFirst();
                cocheList = new ArrayList<Coche>(cursor.getCount());

                do {
                    modelo_aux = cursor.getString(0);
                    coche_aux = new Coche(modelo_aux);
                    cocheList.add(coche_aux);


                }while (cursor.moveToNext());

                cursor.close();
            }

            this.cerrarBaseDatos(sqLiteDatabase);
        return cocheList;
    }


}
