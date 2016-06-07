package com.example.wuxingyao.loadwebdata;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = (TextView) findViewById(R.id.tv);
        tv.setText("Loading...");
        new AsyncTask<Void, Void, String>() {
            protected String doInBackground(Void... params) {
                try {
                    InputStream in = new URL("http://163.com").openStream();

                    BufferedReader reader = new BufferedReader(new InputStreamReader(in, "UTF-8"));

                    String line = null;
                    StringBuffer content = new StringBuffer();
                    while ((line = reader.readLine()) != null) {
                        content.append(line);
                    }
                    reader.close();
                    return content.toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return null;
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                if(s != null) {
                    tv.setText(s);
                }
            }
        }.execute();

    }
}
