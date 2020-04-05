package com.xXHMMRXx.galeria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ImageView imagen;
    TextView letrero;
    Button btn;
    int sr,srTemp =1;

    int n, nTemp;
    Vibrator vibrar;
    Random rand = new Random();

    MediaPlayer mp, mpTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imagen = (ImageView) findViewById(R.id.imageView);
        letrero = (TextView) findViewById(R.id.texto);
        btn = (Button) findViewById(R.id.button);
        mostrarRand();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarRand();
            }
        });
    }

    //mostrar valores de aleatorio
    public void mostrarRand()
    {
        stopPlying();
        vibrar = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        random();
        while (srTemp == sr || n == nTemp || mpTemp == mp)
        {
            random();
        }
        vibrarM();
        AudioMP();
        imagen.setImageResource(randArray[sr].getImagen());
        letrero.setText(randArray[sr].getAleatorio());
        srTemp = sr;
        nTemp = n;
        mpTemp = mp;
    }

    public void random()
    {
        sr = ThreadLocalRandom.current().nextInt(0,6);
        n = rand.nextInt(1000);
        mp = MediaPlayer.create(this,randArray[sr].getSonido());
    }

    public void AudioMP()
    {
        mp.start();
    }

    private void stopPlying(){
        if(mp != null){
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    public void vibrarM()
    {
        long[] pattern ={n, n, n, n};
        vibrar.vibrate(pattern, -1);
    }

    aleatorio i1 = new aleatorio(R.drawable.uno, "img 1",R.raw.sound1);
    aleatorio i2 = new aleatorio(R.drawable.dos, "img 2",R.raw.sound2);
    aleatorio i3 = new aleatorio(R.drawable.tres, "img 3",R.raw.sound3);
    aleatorio i4 = new aleatorio(R.drawable.cuatro, "img 4",R.raw.sound4);
    aleatorio i5 = new aleatorio(R.drawable.cinco, "img 5",R.raw.sound5);
    aleatorio i6 = new aleatorio(R.drawable.seis, "img 6",R.raw.sound6);
    aleatorio i7 = new aleatorio(R.drawable.siete, "img 7",R.raw.sound7);

    //llenando array
    aleatorio[] randArray = new aleatorio[]{
            i1, i2, i3, i4, i5, i6, i7
    };

}
