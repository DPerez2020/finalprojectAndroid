package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class nivel4 extends AppCompatActivity {

    private TextView txv_score,txv_nombre;
    private ImageView img_num1,img_num2,img_vidas,img_signo;
    private EditText edt_respuesta;
    private MediaPlayer mp,mp_great,mp_bad;

    Funtionality_Market funcionality = new Funtionality_Market();
    int score,num_aleatorio_uno,num_aleatorio_dos,resultado,vidas=3;
    String nombre_jugador,string_score,string_vidas;
    String numero[]={"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel4);
        Toast.makeText(this,"Nivel 4- Sumas y Restas",Toast.LENGTH_SHORT).show();
        txv_score=findViewById(R.id.textView_score);
        txv_nombre=findViewById(R.id.textView_nombre);
        img_num1=findViewById(R.id.imageView_Num1);
        img_num2=findViewById(R.id.imageView_Num2);
        img_vidas=findViewById(R.id.imageView_vidas);
        img_signo=findViewById(R.id.imageView_signo);
        edt_respuesta=findViewById(R.id.editText_resultado);

        nombre_jugador=getIntent().getStringExtra("jugador");
        txv_nombre.setText("Jugador: "+nombre_jugador);

        string_score=getIntent().getStringExtra("score");
        score=Integer.parseInt(string_score);
        txv_score.setText("Score: "+score);

        string_vidas=getIntent().getStringExtra("vidas");
        vidas=Integer.parseInt(string_vidas);
        if(vidas==3){
            img_vidas.setImageResource(R.drawable.tresvidas);
        }
        else if(vidas==2){
            img_vidas.setImageResource(R.drawable.dosvidas);
        } else if(vidas==1){
            img_vidas.setImageResource(R.drawable.unavida);
        }

        //Me permite colocar los iconos en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mp=MediaPlayer.create(this,R.raw.goats);
        mp.start();
        mp.setLooping(true);

        mp_great=MediaPlayer.create(this,R.raw.wonderful);
        mp_bad=MediaPlayer.create(this,R.raw.bad);
        num_aleatorio();
    }

    public void comparar(View view){
        String respuesta= edt_respuesta.getText().toString();
        if(!respuesta.equals("")){
            int respuesta_jugador=Integer.parseInt(respuesta);
            if(respuesta_jugador==resultado){
                mp_great.start();

                score++;
                txv_score.setText("Score: "+score);

                edt_respuesta.setText("");

                funcionality.setDataBase(this,score,nombre_jugador);

            }else
            {
                mp_bad.start();
                vidas--;
                funcionality.setDataBase(this,score,nombre_jugador);

                switch (vidas){
                    case 3:
                        img_vidas.setImageResource(R.drawable.tresvidas);
                        break;
                    case 2:
                        Toast.makeText(this,"Te quedan dos manzanas",Toast.LENGTH_SHORT).show();
                        img_vidas.setImageResource(R.drawable.dosvidas);
                        break;
                    case 1:
                        Toast.makeText(this,"Te queda una manzana",Toast.LENGTH_SHORT).show();
                        img_vidas.setImageResource(R.drawable.unavida);
                        break;
                    case 0:
                        Toast.makeText(this,"Has perdido todas tus manzanas",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(this,MainActivity.class);
                        startActivity(intent);
                        finish();
                        mp.stop();
                        mp.release();
                        break;
                }
                edt_respuesta.setText("");
            }
            num_aleatorio();
        }else{
            Toast.makeText(this,"Escribe tu respuesta",Toast.LENGTH_SHORT).show();
        }
    }

    public void num_aleatorio(){
        if(score<=19){
            num_aleatorio_uno= (int)(Math.random()*10);
            num_aleatorio_dos= (int)(Math.random()*10);

            if(num_aleatorio_uno>=0 && num_aleatorio_uno<=4){
                resultado=num_aleatorio_uno+num_aleatorio_dos;
                img_signo.setImageResource(R.drawable.adicion);
            }
            else
            {
                if(num_aleatorio_uno>=num_aleatorio_dos){
                    resultado=num_aleatorio_uno-num_aleatorio_dos;
                    img_signo.setImageResource(R.drawable.resta);
                }
                else
                {
                    num_aleatorio();
                }
            }
            if(resultado>=0){
                for (int i=0;i<numero.length;i++){
                    int id=getResources().getIdentifier(numero[i],"drawable",getPackageName());
                    if(num_aleatorio_uno==i){
                        img_num1.setImageResource(id);
                    }else if(num_aleatorio_dos==i){
                        img_num2.setImageResource(id);
                    }
                }
            }
            else{
                num_aleatorio();
            }
        }
        else{
            Intent intent = new Intent(this, nivel5.class);
            string_score=String.valueOf(score);
            string_vidas=String.valueOf(vidas);

            intent.putExtra("score",string_score);
            intent.putExtra("jugador",nombre_jugador);
            intent.putExtra("vidas",string_vidas);

            startActivity(intent);
            finish();
            mp.stop();
            mp.release();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
    }
}