package com.example.deniz.dicer;


import android.annotation.SuppressLint;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.webkit.WebView;
import android.widget.CheckBox;
import android.widget.ImageView;
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
public class theme extends AppCompatActivity{
    //setTheme(ATheme);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.ATheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.theme);

//        ImageView mytest = (ImageView) findViewById(R.id.imgtest);
    //  SourceImg mysource = new SourceImg();
     // Drawable drawable = ResourcesCompat.getDrawable(getResources(),mysource.mSourceImg[3],null);
       // mytest.setBackground(drawable);





    }
}
