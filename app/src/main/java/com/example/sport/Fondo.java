package com.example.sport;

import android.app.Activity;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * Claudia and Lidia Yanes Garcia
 **/

public class Fondo extends Activity implements View.OnClickListener {
    private MiCountDownTimer countDownTimer, countDownTimer1, countDownTimer2;
    int[] dcolor = {R.drawable.uno, R.drawable.dos, R.drawable.tres, R.drawable.cuatro, R.drawable.cinco, R.drawable.seis, R.drawable.siete, R.drawable.ocho, R.drawable.nueve, R.drawable.diez };
    private UpdateTask updateTask;
    ImageView im;
    ImageButton empezar, pausa;
    boolean pausar=false;
    public int s= MainActivity.numero3;

    TextView textView, textView1, textView2, textView3;
    private long total=1000*(MainActivity.numero3 *(MainActivity.numero1 * MainActivity.numero2) + (MainActivity.numero3 -1) *MainActivity.numero4);
    private long tejercicio=1000*(MainActivity.numero1 * MainActivity.numero2);
    private long tdescanso=1000*(MainActivity.numero4);

    private SoundPool soundPool;
    private Set<Integer> soundLoaded;
    int[]sounds={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int go, stop, fin, finserie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fondo);
        im= (ImageView) findViewById(R.id.iblue);
        im.setImageResource(R.drawable.fondo);

        textView = (TextView) findViewById(R.id.text_view);
        textView.setText(" " + (total/1000) + " ");

        textView1 = (TextView) findViewById(R.id.text_view1);
        textView1.setText(" " + (tejercicio/1000) +" ");

        textView2 = (TextView) findViewById(R.id.text_view2);
        textView2.setText(" " + MainActivity.numero4 + " " );

        textView3 = (TextView) findViewById(R.id.text_view3);
        textView3.setText(" " + MainActivity.numero3 + " " );



        countDownTimer = new MiCountDownTimer(total, 1000, 0);
        countDownTimer1 = new MiCountDownTimer(tejercicio, 1000, 1);
        countDownTimer2 = new MiCountDownTimer(tdescanso, 1000, 2);

        soundLoaded = new HashSet<Integer>();
        if (updateTask != null && updateTask.getStatus() == AsyncTask.Status.FINISHED) {
            updateTask = null;
        }
        else if (updateTask == null ) {
            updateTask = new UpdateTask();
            updateTask.execute();

        }
        empezar=(ImageButton)findViewById(R.id.imageButton);
        //empezar.setOnClickListener(this);
        pausa=(ImageButton)findViewById(R.id.imageButton2);
        //pausa.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.imageButton){
            if(pausar==true) {
                countDownTimer = new MiCountDownTimer(total, 1000, 0);
                countDownTimer.start();

                countDownTimer1 = new MiCountDownTimer(tejercicio, 1000,1);
                countDownTimer1.start();

                countDownTimer2 = new MiCountDownTimer(tdescanso, 1000,2);
                countDownTimer2.start();

            }}else if(view.getId() == R.id.imageButton2){

            countDownTimer.cancel();
            countDownTimer1.cancel();
            countDownTimer2.cancel();


            pausar=true;
            super.onPause();
        }
    }
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onResume() {
        super.onResume();

        AudioAttributes.Builder attrBuilder = new AudioAttributes.Builder();
        attrBuilder.setUsage(AudioAttributes.USAGE_GAME);

        SoundPool.Builder spBuilder = new SoundPool.Builder();
        spBuilder.setAudioAttributes(attrBuilder.build());
        spBuilder.setMaxStreams(2);
        soundPool = spBuilder.build();



        soundPool.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            @Override
            public void onLoadComplete(SoundPool soundPool, int sampleId, int status) {
                if(status==0){
                    soundLoaded.add(sampleId);
                    Log.i("SOUND", "Sound loaded"+ sampleId);
                }else {
                    Log.i("SOUND", "Error cannot load sound status"+ status);

                }
            }
        });
        int num1 = soundPool.load(this, R.raw.num1, 1);
        int num2 = soundPool.load(this, R.raw.num2, 1);
        int num3 = soundPool.load(this, R.raw.num3, 1);
        int num4 = soundPool.load(this, R.raw.num4, 1);
        int num5 = soundPool.load(this, R.raw.num5, 1);
        int num6 = soundPool.load(this, R.raw.num6, 1);
        int num7 = soundPool.load(this, R.raw.num7, 1);
        int num8 = soundPool.load(this, R.raw.num8, 1);
        int num9 = soundPool.load(this, R.raw.num9, 1);
        int num10 = soundPool.load(this, R.raw.num10, 1);
        go=soundPool.load(this, R.raw.go, 1);
        stop=soundPool.load(this, R.raw.stop, 1);
        fin=soundPool.load(this, R.raw.aplauso, 1);
        finserie=soundPool.load(this, R.raw.finserie, 1);
        sounds = new int[]{num1,num2, num3, num4, num5, num6, num7, num8, num9, num10};
    }

    class UpdateTask extends AsyncTask<Void, Integer, Void> {
        int i = 0;
        Random rnd = new Random();


        @Override
        protected Void doInBackground(Void... voids) {
            playSound(go);
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (int set=0; set<MainActivity.numero3; set++){
                if(isCancelled()) break;
                if(set!=0) {
                    publishProgress(0,0,5,9);
                    if(MainActivity.numero4>3){
                        try {
                            Thread.sleep((MainActivity.numero4-3) * 1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }}
                }
                playSound(go);
                if(MainActivity.numero4==0){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if(MainActivity.numero4>=3){
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }}else{
                    try {
                        Thread.sleep(MainActivity.numero4 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int j = 0; j < MainActivity.numero1; j++) {
                    if(isCancelled()) break;

                    i = rnd.nextInt(MainActivity.numero0);

                    publishProgress(i,1,set,j);
                    try {
                        Thread.sleep(MainActivity.numero2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }}
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if(values[2]==0 && values[3]==0) {
                countDownTimer.start();
            }
            if(values[3]==0){
                countDownTimer1.start();
                textView1.setBackgroundColor(Color.GREEN);
                textView2.setBackgroundColor(Color.BLACK);
            }
            if(values[1]==0) {
                im.setImageResource(R.drawable.fondo);
                textView1.setBackgroundColor(Color.BLACK);
                textView2.setBackgroundColor(Color.GREEN);
                textView1.setText(" "+ MainActivity.numero1 * MainActivity.numero2 +" ");
                if(MainActivity.numero4>2){
                    playSound(stop);
                }
                playSound(finserie);
                countDownTimer2.start();
            }else{
                im.setImageResource(dcolor[values[0]]);
                playSound(sounds[values[0]]);
            }
        }


    }

    private void playSound(int soundId){
        if(soundLoaded.contains(soundId)){
            soundPool.play(soundId, 500.0f, 500.0f, 0,0,0f);
        }
    }


    @Override
    public void onBackPressed() {
        updateTask.cancel(true);
        Fondo.this.finish();
        super.onBackPressed();

    }

    public class MiCountDownTimer extends CountDownTimer {
        public int n;

        public MiCountDownTimer(long starTime, long interval, int numero) {
            super(starTime, interval);
            // TODO Auto-generated constructor stub
            n=numero;
        }

        @Override
        public void onTick(long l) {
            if(n==0) {
                textView.setText(" "+ (int) (l / 1000)+ " ");
                total = l;
            }else if(n==1){
                textView1.setText( " "+ (int) (l / 1000)+ " ");
                tejercicio = l;
            }else{
                textView2.setText(" "+ (int) (l / 1000)+ " ");
                tdescanso = l;
            }

        }

        @Override
        public void onFinish() {
            Log.i("aquiiiii", "numero "+ n);
            if(n==0){
                if(s==1) {
                    im.setImageResource(R.drawable.done);
                    playSound(fin);
                    textView.setText(" 0 ");
                    textView1.setText(" 0 ");
                    textView2.setText(" 0 ");
                }

            }else if(n==1) {
                s= s - 1;
                textView3.setText(" "+ s+ " " );
                textView1.setBackgroundColor(Color.BLACK);

            } else if (n==2){
                if(s == 1 ){
                    textView2.setText(" 0 ");

                }else {
                    textView2.setText(" " + MainActivity.numero4 + " ");
                }
            }
        }

    }

}
