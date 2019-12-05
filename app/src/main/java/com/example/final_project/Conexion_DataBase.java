package com.example.final_project;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class Conexion_DataBase extends SQLiteOpenHelper {
    private static Conexion_DataBase instancia=null;
    private String crear="create table Puntaje(Nombre text, Score int)";
    public static Conexion_DataBase getInstancia(Context ctx, String name, int version){
        if(instancia==null){
            instancia= new Conexion_DataBase(ctx,name,null,version);
        }
        return instancia;
    }
    /*private Conexion_DataBase(Context ctx){
        super(ctx,"Puntajes",null,1);

    }*/
    public Conexion_DataBase(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(crear);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Puntaje");
        db.execSQL(crear);
    }
}
