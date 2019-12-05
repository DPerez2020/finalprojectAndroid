package com.example.final_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private EditText edt_nombre;
    private ImageView img_personaje;
    private TextView txv_bestScore;
    private MediaPlayer mp;

    String temp_score;
    Funtionality_Market funcionality= new Funtionality_Market();

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

        //toma el id de la imagen y la coloca
        funcionality.Main_imagenes_principio(this,img_personaje);
        //Conexion a la base de datos
        Conexion_DataBase conexionDataBase = Conexion_DataBase.getInstancia(this,"DB",1);
        SQLiteDatabase DB= conexionDataBase.getWritableDatabase();

        Cursor consulta= DB.rawQuery(
                "Select * from Puntaje where Score= (select max(Score) from Puntaje)",null);

        if(consulta.moveToFirst()){
            String temp_nombre=consulta.getString(0);
            temp_score=consulta.getString(1);
            txv_bestScore.setText("Record: "+ temp_score +" de "+ temp_nombre);
            conexionDataBase.close();
        }
        else
        {
            conexionDataBase.close();
        }

        //Reproduce la musica
        mp=MediaPlayer.create(this,R.raw.alphabet_song);
        mp.start();
        mp.setLooping(true);
    }

    //Vista del menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }
    //Funcionalidad del menu
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id==R.id.item_compartir){
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_SEND);
            intent.putExtra(Intent.EXTRA_TEXT,"Mira!, esta es mi calificacion mas alta ("+ temp_score+") en este juego educativo, puedes superarla?");
            intent.setType("text/plain");
            Intent chooser= Intent.createChooser(intent,"Selecciona donde quieres compartirlo");
            if(intent.resolveActivity(getPackageManager())!=null){
                startActivity(chooser);
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void jugar(View view){
        funcionality.play(this,mp,edt_nombre);
    }

    @Override
    public void onBackPressed() {
    }
}
