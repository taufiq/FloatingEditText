package com.codigo.floatingtextlibraryproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.codigo.floatingedittext.FloatingEditText;

public class MainActivity extends AppCompatActivity {
    FloatingEditText text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
