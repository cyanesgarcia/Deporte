package com.example.sport;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends Activity implements View.OnClickListener {
    EditText et0, et1, et2, et3, et4;
    public static int  numero0, numero1, numero2, numero3, numero4;
    public TextView mtextView, mtextView1, mtextView2, mtextView3;
    private UpdateTask updateTask1;
    public int ans=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et0= (EditText)findViewById(R.id.editText0);
        et1= (EditText)findViewById(R.id.editText1);
        et2= (EditText)findViewById(R.id.editText2);
        et3= (EditText)findViewById(R.id.editText3);
        et4= (EditText)findViewById(R.id.editText4);

        mtextView = (TextView) findViewById(R.id.mtext_view);
        mtextView1 = (TextView) findViewById(R.id.mtext_view1);

        et1.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                        updateTask1.execute();

                }
            }
        });

        et2.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }
        });
        et3.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }
        });

        et4.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (updateTask1 != null && updateTask1.getStatus() == AsyncTask.Status.FINISHED) {
                    updateTask1 = null;
                }
                else if (updateTask1 == null ) {
                    updateTask1 = new UpdateTask();
                    updateTask1.execute();

                }
            }
        });


        Button button = (Button) findViewById(R.id.bfstart);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (isEmpty(et0) || isEmpty(et1) || isEmpty(et2) || isEmpty(et3) || isEmpty(et4)) {
            Toast.makeText(this, "Te faltan datos", Toast.LENGTH_SHORT).show();
            return;
        }
        numero0 = Integer.parseInt(et0.getText().toString().trim());
        if(numero0==0 || numero0 > 10){
            Toast.makeText(this, "El numero de postas tiene que estar entre 1 y 10", Toast.LENGTH_SHORT).show();
            return;
        }
        if(numero3==1){
            numero4=0;
        }

        Intent intent = new Intent(this, Fondo.class);
        startActivity(intent);
    }

    private boolean isEmpty(EditText etText) {
        if(etText.getText().toString().trim().length() == 0) {
            return true;
        }else{
            return false;
        }
    }

    class UpdateTask extends AsyncTask<Void, Integer, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
                    publishProgress(0);
            return null;
                }

        @Override
        protected void onProgressUpdate(Integer... values) {
            if (!(isEmpty(et1)) && !(isEmpty(et2))) {
                numero1 = Integer.parseInt(et1.getText().toString().trim());
                numero2= Integer.parseInt(et2.getText().toString().trim());
                mtextView1.setText(" "+ numero1 * numero2 +" ");
            } else{
                mtextView1.setText(" 0 ");

            }
            if(!(isEmpty(et1)) && !(isEmpty(et2)) && !(isEmpty(et3)) && !(isEmpty(et4))){
                //  private long total=MainActivity.numero3 *(MainActivity.numero1 * MainActivity.numero2) + (MainActivity.numero3 -1) *MainActivity.numero4);
                numero1 = Integer.parseInt(et1.getText().toString().trim());
                numero2= Integer.parseInt(et2.getText().toString().trim());
                numero3 = Integer.parseInt(et3.getText().toString().trim());
                numero4= Integer.parseInt(et4.getText().toString().trim());
                mtextView.setText(" "+ (MainActivity.numero3 *(MainActivity.numero1 * MainActivity.numero2) + (MainActivity.numero3 -1) *MainActivity.numero4) +" " );
            }else{
                mtextView.setText(" 0 ");

            }

            }
        }
    @Override
    public void onBackPressed() {
        updateTask1.cancel(true);
        super.onBackPressed();

    }
}



