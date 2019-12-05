package com.example.final_project;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.text.BoringLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Tiempo_limite extends AppCompatActivity {

    private int progreso=100;
    private int tiempo=10000;
    private int tiempo_view=10;
    //private boolean seacabo=false;
    Funtionality_Market funcionality= new Funtionality_Market();
    public void limite(final ProgressBar pgb_tiempo, final TextView txv_tiempo){
        final CountDownTimer timer = new CountDownTimer(tiempo,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                progreso=progreso-10;
                tiempo_view--;
                txv_tiempo.setText(tiempo_view+"");
                pgb_tiempo.setProgress(progreso);
            }
            @Override
            public void onFinish() {
                progreso=100;
                tiempo=10000;
                tiempo_view=10;
                txv_tiempo.setText("0");
            }
        }.start();
    }
}
