package com.example.sport;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PowerManager;
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
    private CountUpTimer tiempomas;
    int[] dcolor = {R.drawable.uno, R.drawable.dos, R.drawable.tres, R.drawable.cuatro, R.drawable.cinco, R.drawable.seis, R.drawable.siete, R.drawable.ocho, R.drawable.nueve, R.drawable.diez };
    private UpdateTask updateTask;
    ImageView im;
    ImageButton empezar, pausa;
    public int s= MainActivity.numero3;
    int boton=0;
    int cual;
    int start_antes_error=0;
    int secondbreak, tipo;


    TextView textView, textView1, textView2, textView3;
    private long total=1000*(MainActivity.numero3 *(MainActivity.numero1 * MainActivity.numero2) + (MainActivity.numero3 -1) *MainActivity.numero4);
    private long tejercicio=1000*(MainActivity.numero1 * MainActivity.numero2);
    private long tdescanso=1000*(MainActivity.numero4);

    private SoundPool soundPool;
    private Set<Integer> soundLoaded;
    int[]sounds={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
    int go, stop, fin, finserie;
    int tiempoanadir =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);
        im= (ImageView) findViewById(R.id.iblue);
        im.setImageResource(R.drawable.fondo);
        im.setKeepScreenOn(true);

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
        tiempomas = new CountUpTimer(30000) {
            @Override
            public void onTick(int second) {
                tiempoanadir = second;
            }
        };

        soundLoaded = new HashSet<Integer>();
        if (updateTask != null && updateTask.getStatus() == AsyncTask.Status.FINISHED) {
            updateTask = null;
        }
        else if (updateTask == null ) {
            updateTask = new UpdateTask();
            updateTask.execute();

        }
        boton=0;
        empezar=(ImageButton)findViewById(R.id.imageButton);
        empezar.setOnClickListener(this);
        pausa=(ImageButton)findViewById(R.id.imageButton2);
        pausa.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.imageButton){

            if(start_antes_error ==222) {
                Log.i("Loooooogdespues", " value  "+ secondbreak);
                Log.i("sss", "ss" + total);
                countDownTimer = new MiCountDownTimer(total, 1000, 0);
                countDownTimer.start();
                if (cual == 10000) {
                    Log.i("sss", "ss" + tdescanso);
                    countDownTimer2 = new MiCountDownTimer(tdescanso, 1000, 2);
                    countDownTimer2.start();
                } else {
                    Log.i("sss", "ss" + tejercicio);
                    countDownTimer1 = new MiCountDownTimer(tejercicio, 1000, 1);
                    countDownTimer1.start();
                }
                start_antes_error=0;
                boton=1;
            }
        }else if(view.getId() == R.id.imageButton2){

            start_antes_error = 222;
            Log.i("Loooooogantes", " value  "+ secondbreak);
           countDownTimer2.cancel();
           countDownTimer1.cancel();
           countDownTimer.cancel();
            String ejercicio= textView1.getText().toString().trim();
           Log.i("Tiempo ejercicio", "este   "+ejercicio);
           Log.i("Tiempo ejercicio", " largo   "+ MainActivity.numero1 * MainActivity.numero2);
           String general = String.valueOf(MainActivity.numero1 * MainActivity.numero2).trim();
            if(!ejercicio.equals(general)){
                Log.i("Tiempo ejercicio", " entro");
                tiempomas.start();
            }
            boton=2;

        }
    }
    private boolean isPause() {
        if (boton == 2) {
            return true;
        } else {
            return false;
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
            while(isPause()==true){ }
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int set=0; set<MainActivity.numero3; set++){
                if(isCancelled()) break;
                while(isPause()==true){}
                if(set!=0) {
                    publishProgress(0,0,5,9);
                    if(MainActivity.numero4>2){
                        while(isPause()==true) {}
                            try {
                                Log.i("ERRRRRRORNO", "ERRRRRRoR");
                                Thread.sleep((MainActivity.numero4 - 2) * 1000);
                            } catch (InterruptedException e) {
                                Log.i("ERRRRRROR", "ERRRRRRoR");
                                e.printStackTrace();
                            }

                    }
                }
                while(isPause()==true){}
                addtime();
                playSound(go);
                if(MainActivity.numero4==0){
                    while(isPause()==true){ }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else if(MainActivity.numero4>=2){
                    while(isPause()==true){ }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else{
                    while(isPause()==true){ }

                    try {
                        Thread.sleep(MainActivity.numero4 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

                for (int j = 0; j < MainActivity.numero1; j++) {
                    if(isCancelled()) break;
                    while(isPause()==true){ }

                    i = rnd.nextInt(MainActivity.numero0);

                    publishProgress(i,1,set,j);
                    try {
                        Thread.sleep(MainActivity.numero2 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    tiempomas.cancel();
                    addtime2 ();

                }}
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            while(isPause()==true){ }

            if(values[2]==0 && values[3]==0) {
            countDownTimer.start();
            }
            if(values[3]==0){
                tejercicio = 1000*(MainActivity.numero1 * MainActivity.numero2);
                countDownTimer1 = new MiCountDownTimer(tejercicio, 1000,1);
                countDownTimer1.start();
                cual=0;
                textView1.setBackgroundColor(Color.GREEN);
                textView2.setBackgroundColor(Color.BLACK);
            }
            String ejercicio= textView1.getText().toString().trim();
            if(values[1]==0) {
                tdescanso = 1000*(MainActivity.numero4);
                countDownTimer2 = new MiCountDownTimer(tdescanso, 1000,2);
                countDownTimer2.start();
                cual=10000;
                im.setImageResource(R.drawable.fondo);
                textView1.setBackgroundColor(Color.BLACK);
                textView2.setBackgroundColor(Color.GREEN);
                textView1.setText(" "+ MainActivity.numero1 * MainActivity.numero2 +" ");
                while(isPause()==true){ }
                if(MainActivity.numero4>2){
                    playSound(stop);
                }
                playSound(finserie);

            }else{
                while(isPause()==true){ }
                im.setImageResource(dcolor[values[0]]);
                playSound(sounds[values[0]]);
            }
        }


    }

    private void playSound(int soundId){
      /*  if(boton == 1) {
            addtime(secondbreak);
            boton=3;
        }*/
            if (soundLoaded.contains(soundId)) {
                soundPool.play(soundId, 500.0f, 500.0f, 0, 0, 0f);
            }


    }


    @Override
    public void onBackPressed() {
        updateTask.cancel(true);
        boton=0;
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


        public void onTick(long l) {
           if(n==0) {
                textView.setText(" "+ (int) (l / 1000)+ " ");
                total = l;
            }
            else if(n==1){
                textView1.setText( " "+ (int) (l / 1000)+ " ");
                tejercicio = l;
            }else{
                textView2.setText(" "+ (int) (l/ 1000)+ " ");
                tdescanso = l;
            }

        }

        @Override
        public void onFinish() {
            if(n==1) {
                s= s - 1;
                textView3.setText(" "+ s+ " " );
                textView1.setBackgroundColor(Color.BLACK);

            }
            if((n==0) && (s==1)){
                while(isPause()==true){ }
                im.setImageResource(R.drawable.done);
                    playSound(fin);
                    textView.setText(" 0 ");
                    textView1.setText(" 0 ");
                    textView2.setText(" 0 ");


            }
            if (n==2){
                if(s == 1 ){
                    textView2.setText(" 0 ");
                }else {
                    textView2.setText(" " + MainActivity.numero4 + " ");
                }
            }
        }

    }
    public abstract class CountUpTimer extends CountDownTimer {
        private static final long INTERVAL_MS = 1000;
        private final long duration;

        protected CountUpTimer(long durationMs) {
            super(durationMs, INTERVAL_MS);
            this.duration = durationMs;
        }

        public abstract void onTick(int second);

        @Override
        public void onTick(long msUntilFinished) {
            int second = (int) ((duration - msUntilFinished) / 1000);
            onTick(second);
        }

        @Override
        public void onFinish() {
            onTick(duration / 1000);
        }
    }

    public void addtime2 (){
        while(isPause()==true){ }
        Log.i("Aaaaaaaaaaa", "tiempo  " + tiempoanadir);

        try {
                Thread.sleep(tiempoanadir * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            tiempoanadir = 0;
        }

    public void addtime() {
        /*int entero = MainActivity.numero4;
        String enteroString = Integer.toString(entero);

        int entero1 = (MainActivity.numero1 * MainActivity.numero2);
        String enteroString1 = Integer.toString(entero1);

        String descanso= textView2.getText().toString().trim();
        String ejercicio= textView1.getText().toString().trim();
        Log.i("Eeeeeeeeeeeeeeeeeeeeeee", " descanso  " + descanso);
        Log.i("Eeeeeeeeeeeeeeeeeeeeeee", " enteroString  " + enteroString);
        Log.i("Eeeeeeeeeeeeeeeeeeeeeee", " ejercicio  " + ejercicio);
        Log.i("Eeeeeeeeeeeeeeeeeeeeeee", " enteroString1  " + enteroString1);

        if ( !(descanso.equals(enteroString)) && ejercicio.equals(enteroString1)) {*/
        while(isPause()==true){ }
            Log.i("Eeeeeeeeeeeeeeeeeeeeeee", " s  " + tdescanso);
            if(tdescanso>2500) {
                try {
                    Thread.sleep(tdescanso - 2500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

    }
