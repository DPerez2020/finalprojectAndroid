package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private EditText edt_nombre;
    private ImageView img_personaje;
    private TextView txv_bestScore;
    private MediaPlayer mp;

    //Genera un numero aleatorio para colocar iconos dinamicamente
    int num_aleatorio = (int) (Math.random()*10);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edt_nombre=findViewById(R.id.edt_nombre);
        img_personaje=findViewById(R.id.imageView_personaje);
        txv_bestScore=findViewById(R.id.textView_bestScore);

        //Me permite colocar los iconos en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        //toma el id de la imagen
        int id;
        if(num_aleatorio==0 || num_aleatorio==10){
            id=getResources().getIdentifier("mango", "drawable",getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==1 || num_aleatorio==9){
            id=getResources().getIdentifier("fresa", "drawable",getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==2 || num_aleatorio==8){
            id=getResources().getIdentifier("manzana", "drawable",getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==3 || num_aleatorio==7){
            id=getResources().getIdentifier("sandia", "drawable",getPackageName());
            img_personaje.setImageResource(id);
        }else if(num_aleatorio==4 || num_aleatorio==5 || num_aleatorio==6){
            id=getResources().getIdentifier("uva", "drawable",getPackageName());
            img_personaje.setImageResource(id);
        }

        //Conexion a la base de datos
        DataBase dataBase=DataBase.getInstancia(this,"DB",1);
        SQLiteDatabase DB= dataBase.getWritableDatabase();

        Cursor consulta= DB.rawQuery(
                "Select * from Puntaje where Score= (select max(Score) from Puntaje)",null);

        if(consulta.moveToFirst()){
            String temp_nombre=consulta.getString(0);
            String temp_score=consulta.getString(1);
            txv_bestScore.setText("Record: "+ temp_score +" de "+ temp_nombre);
            dataBase.close();
        }
        else
        {
            dataBase.close();
        }

        //Reproduce la musica
        mp=MediaPlayer.create(this,R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);
    }

    public void jugar(View view){
        String nombre=edt_nombre.getText().toString();
        if(!nombre.equals("")){
            mp.stop();
            mp.release();

            Intent intent = new Intent(this,Main2Activity_nivel7.class);
            intent.putExtra("jugador",nombre);
            startActivity(intent);
            finish();
        }
        else {
            Toast.makeText(this,"Primero debes escribir tu nombre",Toast.LENGTH_SHORT).show();
            //Abre el teclado para que el usuario introduzca su nombre
            edt_nombre.requestFocus();
            InputMethodManager imm= (InputMethodManager)getSystemService(this.INPUT_METHOD_SERVICE);
            imm.showSoftInput(edt_nombre,InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public void onBackPressed() {
    }
}
