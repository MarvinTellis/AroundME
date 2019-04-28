package com.example.nearme;

import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class MainActivity extends AppCompatActivity {
    public static String firstN,secondN;
    public int flag;
    public EditText edT1;
    public EditText edT2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //System.out.println("MARVIN");
        super.onCreate(savedInstanceState);
        //System.out.println("NISH");
        setContentView(R.layout.activity_main);
        //System.out.println("DHRUV");

        final Button serviceB=(Button)findViewById(R.id.serviceB);
        flag=1;
        serviceB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (flag == 1) {
                    Toast.makeText(MainActivity.this, "ACTIVATED!", Toast.LENGTH_LONG).show();
                    startService(new Intent(getApplicationContext(), ShakeService.class));
                    flag = 0;
                } else {
                    Toast.makeText(MainActivity.this, "DEACTIVATED!", Toast.LENGTH_LONG).show();
                    stopService(new Intent(getApplicationContext(), ShakeService.class));
                    flag = 1;
                }

            }
        });
        Button doneB=(Button)findViewById(R.id.doneButton);
        if (doneB != null) {
            doneB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    edT1 = (EditText) findViewById(R.id.firstNumber);
                    edT2 = (EditText) findViewById(R.id.secondNumber);
                    if(edT1.getText()!=null)
                        firstN=edT1.getText().toString();
                    if(edT2.getText()!=null)
                        secondN=edT2.getText().toString();

                    FileOutputStream fileout;
                    try {

                        fileout = openFileOutput("emergencycontacts.txt", MODE_PRIVATE);

                        OutputStreamWriter outputWriter=new OutputStreamWriter(fileout);
                        outputWriter.append(firstN);
                        outputWriter.append("\n");
                        outputWriter.append(secondN);
                        outputWriter.close();

                        //display file saved message
                        Toast.makeText(getBaseContext(), "Emergency contacts numbers have been saved!!!",
                                Toast.LENGTH_SHORT).show();

                        FileInputStream fIn = openFileInput("emergencycontacts.txt");
                        BufferedReader myReader = new BufferedReader(
                                new InputStreamReader(fIn));
                        String No1 = myReader.readLine();
                        System.out.println("HERE -->"+No1);
                        String No2 = myReader.readLine();
                        System.out.println("HERE -->"+No2);
                        myReader.close();

                    } catch (Exception e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(),
                                Toast.LENGTH_SHORT).show();
                        e.printStackTrace();
                    }
                    Log.d(getPackageName(), "Done! button pressed.");
                }
            });
        }
    }
}
