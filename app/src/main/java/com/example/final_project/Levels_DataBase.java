package com.example.final_project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class Levels_DataBase {
    public void BaseDeDatos(Context context, int score, String nombre_jugador){
        Conexion_DataBase conexionDataBase = Conexion_DataBase.getInstancia(context,"DB",1);
        SQLiteDatabase DB= conexionDataBase.getWritableDatabase();
        Cursor consulta= DB.rawQuery(
                "Select * from Puntaje where Score= (select max(Score) from Puntaje)",null);
        if(consulta.moveToFirst()){
            String tem_nombre=consulta.getString(0);
            String tem_score=consulta.getString(1);

            int best_score=Integer.parseInt(tem_score);
            if(score>best_score){
                ContentValues modificacion= new ContentValues();
                modificacion.put("Nombre",nombre_jugador);
                modificacion.put("Score",score);

                DB.update("Puntaje",modificacion,"Score="+best_score,null);
            }
            DB.close();
        }else{
            ContentValues insertar= new ContentValues();
            insertar.put("Nombre",nombre_jugador);
            insertar.put("Score",score);
            DB.insert("Puntaje",null,insertar);
            DB.close();
        }
    }
}
