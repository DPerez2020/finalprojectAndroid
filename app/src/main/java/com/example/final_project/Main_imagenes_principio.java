package com.example.final_project;

import android.content.Context;
import android.provider.ContactsContract;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class Main_imagenes_principio extends AppCompatActivity {
    public void cargar_imagen(Context context, ImageView img_personaje){
        int num_aleatorio = (int) (Math.random()*10);
        int id;
        if(num_aleatorio==0 || num_aleatorio==10){
            id=context.getResources().getIdentifier("mango", "drawable",context.getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==1 || num_aleatorio==9){
            id=context.getResources().getIdentifier("fresa", "drawable",context.getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==2 || num_aleatorio==8){
            id=context.getResources().getIdentifier("manzana", "drawable",context.getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==3 || num_aleatorio==7){
            id=context.getResources().getIdentifier("sandia", "drawable",context.getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==4 || num_aleatorio==5 || num_aleatorio==6){
            id=context.getResources().getIdentifier("uva", "drawable",context.getPackageName());
            img_personaje.setImageResource(id);
        }
    }
}
