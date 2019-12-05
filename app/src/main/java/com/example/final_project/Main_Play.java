package com.example.final_project;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Main_Play extends AppCompatActivity {
    public void jugar(Context context, MediaPlayer mp, EditText edt_nombre){
        String nombre=edt_nombre.getText().toString();
        if(!nombre.equals("")){
            mp.stop();
            mp.release();
            Intent intent = new Intent(context, nivel1.class);
            intent.putExtra("jugador",nombre);
            context.startActivity(intent);
            //startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(context,"Primero debes escribir tu nombre",Toast.LENGTH_SHORT).show();
            //Abre el teclado para que el usuario introduzca su nombre
            edt_nombre.requestFocus();
            /*InputMethodManager imm= (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edt_nombre,InputMethodManager.SHOW_IMPLICIT);*/
        }
    }
}
