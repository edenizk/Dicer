package com.example.deniz.dicer;

import android.app.assist.AssistStructure;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
/**
 * Created by deniz on 23/04/2018.
 */

public class settings extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings);



        final Button btnback = (Button) findViewById(R.id.btnback);
        Button btnAnimation = (Button)findViewById(R.id.btnAnimation);
        Button btnDice = (Button) findViewById(R.id.btnDice);
        Button btnTheme = (Button) findViewById(R.id.btnTheme);

        btnDice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent diceMenu = new Intent(settings.this, com.example.deniz.dicer.dice.class);

                startActivity(diceMenu);
            }
        });


        btnAnimation.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {



                Intent animation = new Intent(settings.this, com.example.deniz.dicer.animation.class);

                startActivity(animation);
            }
        });
        btnTheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent theme = new Intent(settings.this, com.example.deniz.dicer.theme.class);
                Log.i("LOG:","start");

                startActivity(theme);
            }
        });


        btnback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {




                Intent play = new Intent(settings.this, com.example.deniz.dicer.MainActivity.class);

                startActivity(play);


            }
        });


    }
    ///////////////////////////////////
    ////////////back//////////////////
    @Override
    public void onBackPressed()
    {
        Intent settings = new Intent(settings.this, com.example.deniz.dicer.MainActivity.class);

        startActivity(settings);

    }

    ///////////////////////////////////
    ////////////back//////////////////



}
