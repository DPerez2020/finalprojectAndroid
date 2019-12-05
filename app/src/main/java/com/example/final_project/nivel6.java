package com.example.final_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class nivel6 extends AppCompatActivity {

    private TextView txv_score,txv_nombre;
    private ImageView img_num1,img_num2,img_vidas;
    private EditText edt_respuesta;
    private MediaPlayer mp,mp_great,mp_bad;
    private Funtionality_Market funcionality;
    int score,num_aleatorio_uno,num_aleatorio_dos,resultado,vidas=3;
    String nombre_jugador,string_score,string_vidas;
    String numero[]={"cero","uno","dos","tres","cuatro","cinco","seis","siete","ocho","nueve"};
    //Levels_Numero_aleatorio numero_aleatorio = new Levels_Numero_aleatorio();

    private ProgressBar pgb_tiempo;
    private TextView txv_tiempo;
    private Tiempo_limite tiempo_limite;
    private ImageView img_signo;

    private Boolean isrunning=false;
    private int progreso=100;
    private int tiempo=10000;
    private int tiempo_view=10;
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nivel6);
        Toast.makeText(this,"Nivel 1- Sumas, restas y multiplicacion",Toast.LENGTH_SHORT).show();
        txv_score=findViewById(R.id.textView_score);
        txv_nombre=findViewById(R.id.textView_nombre);
        img_num1=findViewById(R.id.imageView_Num1);
        img_num2=findViewById(R.id.imageView_Num2);
        img_vidas=findViewById(R.id.imageView_vidas);
        edt_respuesta=findViewById(R.id.editText_resultado);
        funcionality= new Funtionality_Market();
        nombre_jugador=getIntent().getStringExtra("jugador");
        txv_nombre.setText("Jugador: "+nombre_jugador);
        img_signo=findViewById(R.id.imageView_signo);
        mp_great=MediaPlayer.create(this,R.raw.wonderful);
        mp_bad=MediaPlayer.create(this,R.raw.bad);
        pgb_tiempo=findViewById(R.id.pgb_tiempo);
        txv_tiempo=findViewById(R.id.txv_cuentagresiva);
        tiempo_limite= new Tiempo_limite();
        vidas= Integer.parseInt(getIntent().getStringExtra("vidas")) ;
        vidas();
        string_score=getIntent().getStringExtra("score");
        score=Integer.parseInt(string_score);
        txv_score.setText("Score: "+score);
        num_aleatorio();
        //Me permite colocar los iconos en el action bar
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.mipmap.ic_launcher);

        mp=MediaPlayer.create(this,R.raw.goats);
        mp.start();
        mp.setLooping(true);

        mp_great=MediaPlayer.create(this,R.raw.wonderful);
        mp_bad=MediaPlayer.create(this,R.raw.bad);
        //numero_aleatorio.num_aleatorio(this,img_num2,img_num1,mp,1);
        //num_aleatorio();
    }
    public void comparar(View view){
        if(isrunning){
            timer.cancel();
            isrunning=false;
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
                            Intent intent = new Intent(this, MainActivity.class);
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
        else
        {
            isrunning=false;
            num_aleatorio();
        }

    }

    public void num_aleatorio(){
        if(score<=20){
            num_aleatorio_uno= (int)(Math.random()*10);
            num_aleatorio_dos= (int)(Math.random()*10);

            if(num_aleatorio_uno>=0 && num_aleatorio_uno<=4){
                resultado=num_aleatorio_uno+num_aleatorio_dos;
                img_signo.setImageResource(R.drawable.adicion);
            }
            else if(num_aleatorio_uno>=5 && num_aleatorio_dos<=7)
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
            else{
                resultado=num_aleatorio_dos*num_aleatorio_uno;
                img_signo.setImageResource(R.drawable.multiplicacion);
            }
            for (int i=0;i<numero.length;i++){
                int id=getResources().getIdentifier(numero[i],"drawable",getPackageName());
                if(num_aleatorio_uno==i){
                    img_num1.setImageResource(id);
                }else if(num_aleatorio_dos==i){
                    img_num2.setImageResource(id);
                }
            }
            if(isrunning){
                timer.cancel();
                isrunning=false;
                limite_tiempo();

            }
            else
            {
                limite_tiempo();
            }
        }
        else{
            Intent intent = new Intent(this, MainActivity.class);
            string_score=String.valueOf(score);
            string_vidas=String.valueOf(vidas);

            Toast.makeText(this,"Eres un crack",Toast.LENGTH_SHORT).show();

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


    public void limite_tiempo() {
        if (!isrunning) {
            isrunning=true;
            progreso = 100;
            tiempo = 10000;
            tiempo_view = 10;
            timer = new CountDownTimer(tiempo, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    progreso = progreso - 10;
                    tiempo_view--;
                    txv_tiempo.setText(tiempo_view + "");
                    pgb_tiempo.setProgress(progreso);
                }

                @Override
                public void onFinish() {
                    isrunning=false;
                    progreso = 100;
                    tiempo = 10000;
                    tiempo_view = 10;
                    txv_tiempo.setText("0");
                    mp_bad.start();
                    vidas--;
                    funcionality.setDataBase(getApplicationContext(), score, nombre_jugador);
                    switch (vidas) {
                        case 3:
                            img_vidas.setImageResource(R.drawable.tresvidas);
                            break;
                        case 2:
                            Toast.makeText(getApplicationContext(), "Te quedan dos manzanas", Toast.LENGTH_SHORT).show();
                            img_vidas.setImageResource(R.drawable.dosvidas);
                            break;
                        case 1:
                            Toast.makeText(getApplicationContext(), "Te queda una manzana", Toast.LENGTH_SHORT).show();
                            img_vidas.setImageResource(R.drawable.unavida);
                            break;
                        case 0:
                            Toast.makeText(getApplicationContext(), "Has perdido todas tus manzanas", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                            finish();
                            mp.stop();
                            mp.release();
                            break;
                    }
                    edt_respuesta.setText("");
                    num_aleatorio();
                }
            }.start();
        }
        else
        {  isrunning=false;
            timer.cancel();
            limite_tiempo();
        }
    }
    void vidas(){
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
    }
}