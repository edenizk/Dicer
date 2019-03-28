package com.example.deniz.dicer;

import android.annotation.TargetApi;
import android.graphics.drawable.Drawable;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.media.Image;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import junit.framework.Test;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {
    String strListOrAnim="1";
    int diceColorNum = 0;
    String txtAddP="0";
    private GestureDetectorCompat gestureObj;
    EditText txtdicenum;
    CheckBox addP;
    boolean info=false;
    LinearLayout layoutCredit,firstLayout;
    Button btnStart,btnplus,btnminus,btnsettings,btnrightcolor,btnleftcolor,btninfo,btnTwitter;
    int diceSymbolNum = 0;
    SourceImg mysource = new SourceImg();
    List<Button> imgDice = new ArrayList<Button>();

    @TargetApi(Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        read_file read_file = new read_file(MainActivity.this);

        gestureObj = new GestureDetectorCompat(this, new gestureClass());

        txtdicenum = (EditText) findViewById(R.id.txtdicenum);

        addP = (CheckBox) findViewById(R.id.addP);

        btnStart =(Button)findViewById(R.id.btnStart);
        btnplus = (Button) findViewById(R.id.btnplus);
        btnminus = (Button) findViewById(R.id.btnminus);
        btnsettings = (Button) findViewById(R.id.btnsettings);
        btnrightcolor = (Button) findViewById(R.id.btnrightcolor);
        btnleftcolor = (Button) findViewById(R.id.btnleftcolor);
        btninfo = (Button) findViewById(R.id.btninfo);
        btnTwitter =(Button) findViewById(R.id.btntwitter);

        imgDice.add((Button) findViewById(R.id.imgdicecolor));
        imgDice.add((Button) findViewById(R.id.imgDiceShape));
        imgDice.add((Button) findViewById(R.id.imgOpenLogo));


        layoutCredit = (LinearLayout)findViewById(R.id.layoutcredit2);

        firstLayout = (LinearLayout)findViewById(R.id.firstLayout);



        Animation openLogoAnim = AnimationUtils.loadAnimation(this, R.anim.openlogoanim);

        if(read_file.fun_firstTime()) {
            firstTime first_time = new firstTime(MainActivity.this);
            first_time.writing();
        }
        ////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////
                //listoranim
                strListOrAnim=getStrListOrAnim();


                //addp
                txtAddP=getTxtAddP();

        int a=Integer.parseInt(txtAddP);


                ///dicecolor

        diceColorNum=diceNumColor();
        Drawable drawableDiceColor = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);
        //symbol

        diceSymbolNum = read_file.diceNumSymbol();


        if((diceSymbolNum == 0))  {
            Drawable drawableDiceDots = ResourcesCompat.getDrawable(getResources(), mysource.diceDots[0], null);

            imgDice.get(0).setForeground(drawableDiceDots);
            imgDice.get(1).setForeground(drawableDiceDots);
            int ball = R.drawable.ball;
            imgDice.get(2).setForeground(ResourcesCompat.getDrawable(getResources(), ball, null));
        }
        else if((diceSymbolNum == 1)){
            for(Button myButton: imgDice) {
                myButton.setForeground(null);
                myButton.setText((1) + "");
            }
        }
        else if(diceSymbolNum == 2){
            for(Button myButton: imgDice) {
                myButton.setForeground(null);
                myButton.setText(mysource.romeNum(1));
            }


        }

        //Dice number

                txtdicenum.setText(diceNumRead());


        //end Reading
        ////////////////////////////////////////////////////////////////////////
        ///////////////////////////////////////////////////////////////////////

        imgDice.get(0).setBackground(drawableDiceColor);
        //imgDiceShape.setBackground(drawableDiceColor);

        imgDice.get(2).startAnimation(openLogoAnim);
        //openLogo.setBackground(drawableDiceColor);






        if(Integer.parseInt(txtdicenum.getText().toString())<15 && strListOrAnim.charAt(0)=='1'){
            addP.setVisibility(View.GONE);
        }
            if(a==1){
                addP.setChecked(true);
            }
            else  {
                addP.setChecked(false);
            }



        layoutCredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                layoutCredit.setVisibility(View.GONE);
                enableDisableButtons(true);


                info=false;
            }
        });

        btninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enableDisableButtons(false);
                layoutCredit.setVisibility(View.VISIBLE);
                info=true;
            }
        });

        btnplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int dicenum = Integer.parseInt(txtdicenum.getText().toString());

                    dicenum++;

                if(dicenum > 15 ){

                    addP.setVisibility(View.VISIBLE);
                    }

                txtdicenum.setText(dicenum + "");
            }
        });

        btnminus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int dicenum = Integer.parseInt(txtdicenum.getText().toString());
                if(dicenum>0) {
                    dicenum--;
                }

                if(dicenum < 15 && strListOrAnim.charAt(0)=='1'){

                    addP.setVisibility(View.GONE);
                }
                else{
                    addP.setVisibility(View.VISIBLE);
                }
                txtdicenum.setText(dicenum + "");
            }
        });


        btnrightcolor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (diceColorNum<mysource.diceColors.length)
                {
                    diceColorNum += 1;

                    if(diceColorNum==mysource.diceColors.length){
                        diceColorNum=0;
                    }
                    Drawable drawableDiceColor = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);

                    imgDice.get(0).setBackground(drawableDiceColor);
                    //openLogo.setBackground(drawableDiceColor);
                    imgDice.get(1).setBackground(drawableDiceColor);


                }



            }
        });
        btnleftcolor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(diceColorNum>(-1))
                {
                    diceColorNum -= 1;
                    if(diceColorNum==(-1)){
                        diceColorNum=(mysource.diceColors.length-1);
                    }
                    Drawable drawableDiceColor = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);

                    imgDice.get(0).setBackground(drawableDiceColor);
                    //openLogo.setBackground(drawableDiceColor);
                    imgDice.get(1).setBackground(drawableDiceColor);


                }
            }
        });
        btnsettings.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {


                if (addP.isChecked()) {
                    txtAddP = "1";
                }
                writing();

                Intent settings = new Intent(MainActivity.this, com.example.deniz.dicer.settings.class);

                startActivity(settings);

            }
        });





       btnStart.setOnClickListener(new View.OnClickListener()
       {
            @Override
            public void onClick(View view)
            {


                if(addP.isChecked()){
                    txtAddP = "1";
                }

                writing();
                Log.i("LOG:", "inside play");

                if(Integer.parseInt(txtdicenum.getText().toString()) > 15 || strListOrAnim.charAt(0)=='0'){

                    Intent dicelist = new Intent(MainActivity.this, com.example.deniz.dicer.dicelist.class);

                    startActivity(dicelist);
                }

                else{

                    test mytest = new test(3);

                    Intent play = new Intent(MainActivity.this, com.example.deniz.dicer.play.class);
                    play.putExtra("myint", (Serializable) mytest);
                    startActivity(play);

                }


            }
        });

       btnTwitter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Uri uri = Uri.parse("https://twitter.com/dkPixOj");
               Intent intent = new Intent(Intent.ACTION_VIEW, uri);
               startActivity(intent);
           }
       });


    }//////////////ON CREATe



    private void enableDisableButtons(boolean b) {
        if(b==false){
            //btnStart,btnplus,btnminus,btnsettings,btnrightcolor,btnleftcolor,btninfo
            btnStart.setClickable(false);
            btnplus.setClickable(false);
            btnminus.setClickable(false);
            btnsettings.setClickable(false);
            btnrightcolor.setClickable(false);
            btnleftcolor.setClickable(false);
            btninfo.setClickable(false);
            txtdicenum.setFocusable(false);

        }
        else{
            btnStart.setClickable(true);
            btnplus.setClickable(true);
            btnminus.setClickable(true);
            btnsettings.setClickable(true);
            btnrightcolor.setClickable(true);
            btnleftcolor.setClickable(true);
            btninfo.setClickable(true);
            txtdicenum.setFocusableInTouchMode(true);

        }


    }
////////////Reading/////////////////

    public int diceNumColor(){
        int diceColorNum = 0;
        Log.i("LOG", diceColorNum +"dicecolor");

        ///dicecolor
        try{
            FileInputStream savecolor2 = openFileInput("save_color");
            InputStreamReader inputColorReader = new InputStreamReader(savecolor2);
            BufferedReader buffColorReader = new BufferedReader(inputColorReader);
            diceColorNum= Integer.parseInt(buffColorReader.readLine());
            Log.i("LOG", diceColorNum +"dicecolor");
            return diceColorNum;

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        Log.i("LOG", diceColorNum +"dicecolor");

        return diceColorNum;
    }

    ////////////////////////////////////////////////////////

    public String diceNumRead()
    {
        //Dice number
        String txtdicenum = null;
        Log.i("LOG","dicenum "+ txtdicenum);

        try
        {
            FileInputStream DicenumFile = openFileInput("DiceNum");
            InputStreamReader inputDiceNum = new InputStreamReader(DicenumFile);
            BufferedReader buffDiceNum = new BufferedReader(inputDiceNum);

            txtdicenum=buffDiceNum.readLine();
            Log.i("LOG","dicenum "+ txtdicenum);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        if(txtdicenum==null){
                    return txtdicenum = "2";
        }else return txtdicenum;


    }
    ///////////////////////////////////////////////////


        public String getTxtAddP()
        {
            try
            {
                //addp
                FileInputStream inputAddP = openFileInput("AddP");
                InputStreamReader inputAddPReader = new InputStreamReader(inputAddP);
                BufferedReader buffAddPReader = new BufferedReader(inputAddPReader);

                txtAddP= buffAddPReader.readLine();
                return txtAddP;

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return txtAddP;


        }
    //////////////////////////////////////////////
        public String getStrListOrAnim()
        {
            try
            {
                //list or anim
                FileInputStream listanimsave = openFileInput("ListorAnim");
                InputStreamReader inputlistanime = new InputStreamReader(listanimsave);
                BufferedReader inputlistanim = new BufferedReader(inputlistanime);
                strListOrAnim=inputlistanim.readLine();
                return strListOrAnim;

            } catch (FileNotFoundException e)
            {
                e.printStackTrace();
            } catch (IOException e)
            {
                e.printStackTrace();
            }

            return strListOrAnim;


        }
        /////////////////////////////////////////////


    //////////////////reading///////////////
    ///////////////////////////////////
    ////////////back//////////////////
    @Override
    public void onBackPressed()
    {

        if(info==true){
            layoutCredit.setVisibility(View.GONE);
            enableDisableButtons(true);


            info=false;
        }
        else {

            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.myDialogTheme).setTitle("EXIT").setIcon(R.drawable.fut_button_circle_open);


            builder.setCancelable(false);
            builder.setMessage("\n  Do you want to Exit?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application



                    if (addP.isChecked()) {
                        txtAddP = "1";
                    }

                    writing();

                    finish();

                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user select "No", just cancel this dialog and continue with app
                    dialog.cancel();
                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
    }

    ///////////////////////////////////
    ////////////back//////////////////

    /////////////////////////////////
    ///////////gesture//////////////
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObj.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    ////////////////////////////////////////
    class gestureClass extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
            if (motionEvent.getX() > motionEvent1.getX()) {

                if (addP.isChecked()) {
                    txtAddP = "1";
                }

                writing();

                if (Integer.parseInt(txtdicenum.getText().toString()) > 15) {

                    Intent dicelist = new Intent(MainActivity.this, com.example.deniz.dicer.dicelist.class);

                    startActivity(dicelist);
                } else {

                    Intent play = new Intent(MainActivity.this, com.example.deniz.dicer.play.class);

                    startActivity(play);

                }

            }
                return true;
            }

    }
    /////////////////////////////////
    ///////////gesture//////////////
    public void writing(){

        try
        {

            FileOutputStream savecolor = openFileOutput("save_color",MODE_PRIVATE);
            savecolor.write(Integer.toString(diceColorNum).getBytes());
            savecolor.close();


            FileOutputStream outputDicenum = openFileOutput("DiceNum",MODE_PRIVATE);
            outputDicenum.write(txtdicenum.getText().toString().getBytes());
            outputDicenum.close();

            FileOutputStream outputAddP = openFileOutput("AddP",MODE_PRIVATE);
            outputAddP.write(txtAddP.getBytes());
            outputAddP.close();

            Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }


    }



}


