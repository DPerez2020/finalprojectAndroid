package com.example.final_project;

import android.content.Context;
import android.media.MediaPlayer;
import android.widget.EditText;
import android.widget.ImageView;

public class Funtionality_Market {
    private Main_imagenes_principio main_imagenes_principio;
    private Main_Play main_play;
    private Levels_DataBase dataBase;

    public Funtionality_Market(){
        main_imagenes_principio= new Main_imagenes_principio();
        main_play = new Main_Play();
        dataBase= new Levels_DataBase();
    }
    public void play(Context context, MediaPlayer mp, EditText edt_nombre){
        main_play.jugar(context,mp,edt_nombre);
    }
    public void Main_imagenes_principio(Context context, ImageView img_personaje){
        main_imagenes_principio.cargar_imagen(context,img_personaje);
    }
    public void setDataBase(Context context,int score,String nombre_jugador){
        dataBase.BaseDeDatos(context,score,nombre_jugador);
    }
}
