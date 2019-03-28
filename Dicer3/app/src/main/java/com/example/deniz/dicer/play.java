package com.example.deniz.dicer;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class play extends AppCompatActivity
{

    int btndicenum = 2;

    int finalBtndicenum;
    private GestureDetectorCompat gestureObj;
    char[] strMath = new char[]{'0','0','0','0','0'};
    int choosenMath = 4;
    int diceColorNum = 0, diceSymbolNum = 0;
    int resultMath = 0;
    final boolean[] blnAnimation = new boolean[8];
    String txtdiceanimation,txtSetMath="Something went wrong";
    SourceImg mysource = new SourceImg();

//TextView txtMath;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.play);

        gestureObj = new GestureDetectorCompat(this, new play.gestureClass());

        test atest = (test) getIntent().getSerializableExtra("myint");
        Log.i("LOG", String.valueOf(atest.getAsd()));

        Button btnsettings = (Button) findViewById(R.id.btnsettings);
        Button btnBack = (Button) findViewById(R.id.btnback);

        final TextView txtMath = (TextView) findViewById(R.id.txtMath);

        final Button btndice[] = {(Button) findViewById(R.id.btndice)
                , findViewById(R.id.btndice2)
                , findViewById(R.id.btndice3)
                , findViewById(R.id.btndice4)
                , findViewById(R.id.btndice5)
                , findViewById(R.id.btndice6)
                , findViewById(R.id.btndice7)
                , findViewById(R.id.btndice8)
                , findViewById(R.id.btndice9)
                , (Button) findViewById(R.id.btndice10)
                , (Button) findViewById(R.id.btndice11)
                , (Button) findViewById(R.id.btndice12)
                , (Button) findViewById(R.id.btndice13)
                , (Button) findViewById(R.id.btndice14)
                , (Button) findViewById(R.id.btndice15)
        };

        final Animation animlist[] = {AnimationUtils.loadAnimation(this, R.anim.bounce)
                , AnimationUtils.loadAnimation(this, R.anim.blink)
                , AnimationUtils.loadAnimation(this, R.anim.fadein)
                , AnimationUtils.loadAnimation(this, R.anim.lost)
                , AnimationUtils.loadAnimation(this, R.anim.moves)
                , AnimationUtils.loadAnimation(this, R.anim.rotate)
                , AnimationUtils.loadAnimation(this, R.anim.all)
        };



        final Animation infrepeat[] = {AnimationUtils.loadAnimation(this, R.anim.infrepeat), AnimationUtils.loadAnimation(this, R.anim.infrepeat2)};

        ////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////
        //Reading

        //dice symbol
        diceSymbolNum = diceNumSymbol();

        //color

        diceColorNum = diceNumColor();
        Drawable drawableDiceColors = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);

        //animation
        txtdiceanimation = getAnimation();

        for (int i = 0; i < blnAnimation.length; i++) {
            if (txtdiceanimation.charAt(i) == '1') blnAnimation[i] = true;
            else blnAnimation[i] = false;

        }

        //Dice number
        String stringDicenum = diceNumandSideRead("DiceNum");
        //Dice side
        final int side = Integer.parseInt(diceNumandSideRead("SideNum"));
        ///READ/////
        final int btndicenum = Integer.parseInt(stringDicenum);
        final int[] valueOfDice = new int[btndicenum];

        //MAth
        String dummy = "00000";
        dummy = getMath(dummy);
        Log.i("Math", "dumy : " + dummy);

        for (int i = 0; i < dummy.length(); i++) {
            if (dummy.charAt(i) == '1') {
                choosenMath = i;
            }

        }
        Log.i("Math", "MAth : " + choosenMath);


        if (stringDicenum == null) {
        } else {


            int i;
            for (i = 0; i < btndicenum; i++) {
                btndice[i].setBackground(drawableDiceColors);
                SetTextSize(side, btndice[i]);
                btndice[i].setVisibility(View.VISIBLE);
            }
            while (i < 15) {
                btndice[i].setVisibility(View.GONE);
                i++;
            }

        }


        for (int i = 0; i < blnAnimation.length; i++) {
        }


        for (int i = 0; i < btndicenum; ++i) {

            randomnum(btndice[i],  i, valueOfDice, side);
        }

        getMath2(valueOfDice, txtMath);

        if (blnAnimation[7] == true) {
            btndice[0].startAnimation(infrepeat[0]);

            btndice[1].startAnimation(infrepeat[1]);
        }


        finalBtndicenum = btndicenum;

        for (int i = 0; i < btndicenum; ++i) {
            final Button btn = btndice[i];
            btn.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    for (int i = 0; i < valueOfDice.length; i++) {
                        valueOfDice[i] = 0;
                    }
                    for (int i = 0; i < finalBtndicenum; i++) {
                        animationCheck(blnAnimation, btndice[i], animlist);
                        randomnum(btndice[i], i, valueOfDice, side);

                    }
                    getMath2(valueOfDice, txtMath);

                }
            });
        }


        btnsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent settings = new Intent(play.this, com.example.deniz.dicer.settings.class);

                startActivity(settings);

            }
        });



    }




    /////////////////////////////////////////////END OF ONCREATE




    @RequiresApi(api = Build.VERSION_CODES.M)
    private void randomnum(Button btndice,int i,int valueOfDice[], int side)
    {
        SecureRandom securerandomnum = new SecureRandom();

        int randomnum = 0 + securerandomnum.nextInt(side);
        //Drawable drawableDiceDots = ResourcesCompat.getDrawable(getResources(), mysource.diceValue[0], null);
       // Log.i("LOG","random "+randomnum);
        if((side <= 6) && (diceSymbolNum == 0))  {
           Drawable drawableDiceDots = ResourcesCompat.getDrawable(getResources(), mysource.diceDots[randomnum], null);
            btndice.setForeground(drawableDiceDots);

        }
        else if((diceSymbolNum == 1) || (side > 6) && (diceSymbolNum != 2)){
            btndice.setForeground(null);
            btndice.setText((randomnum+1) + "");
        }
        else if(diceSymbolNum == 2){
            btndice.setForeground(null);
            Log.i("LOG", mysource.romeNum(randomnum) + " Rome " + randomnum);
            btndice.setText(mysource.romeNum(randomnum+1));

        }


        valueOfDice[i] = randomnum+1;

    }
    public void SetTextSize(int side, Button btndice){
        if((diceSymbolNum == 1) || (side > 6) && (diceSymbolNum != 2)){
            if(side<100){
                btndice.setTextSize(70);

            }
            else if (side < 1000){
                btndice.setTextSize(50);

            }
            else if (side < 10000){
                btndice.setTextSize(40);

            }
            else if (side < 100000){
                btndice.setTextSize(30);

            }
            else if (side < 1000000){
                btndice.setTextSize(25);
            }
        }
        else if(diceSymbolNum == 2){
                btndice.setTextSize(25);
        }

    }




    private void getMath2(int valueOfDice[],TextView txtMath) {
        math mymath=new math(valueOfDice,choosenMath);

        txtMath.setText(mymath.getMathText());
    }


    private void animationCheck(boolean blnAnimation[], Button btndice, Animation animlist[])
    {

        SecureRandom securerandomnum = new SecureRandom();
        int randomnum = 0 + securerandomnum.nextInt((6 + 1));

        switch (randomnum)
        {

            case 0:
                if (blnAnimation[0] == true)
                {
                    btndice.startAnimation(animlist[0]);
                    break;
                } else {
                }
            case 1:
                if (blnAnimation[1] == true)
                {
                    btndice.startAnimation(animlist[1]);
                    break;
                } else {
                }

            case 2:
                if (blnAnimation[2] == true)
                {
                    btndice.startAnimation(animlist[2]);
                    break;
                } else {
                }

            case 3:
                if (blnAnimation[3] == true)
                {
                    btndice.startAnimation(animlist[3]);
                    break;
                } else {
                }

            case 4:
                if (blnAnimation[4] == true)
                {
                    btndice.startAnimation(animlist[4]);
                    break;
                } else {
                }

            case 5:
                if (blnAnimation[5] == true)
                {
                    btndice.startAnimation(animlist[5]);
                    break;
                } else {
                }

            case 6:
                if (blnAnimation[6] == true)
                {
                    btndice.startAnimation(animlist[6]);
                    break;
                } else {
                    break;
                }


        }


    }
    ///////////////////////////////////
    ////////////back//////////////////
    @Override
    public void onBackPressed()
    {
        Intent MainActivity = new Intent(play.this, com.example.deniz.dicer.MainActivity.class);

        startActivity(MainActivity);

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
            if(motionEvent.getX() > motionEvent1.getX()){

                Intent dicelist = new Intent(play.this, com.example.deniz.dicer.dicelist.class);

                startActivity(dicelist);
            }
            else if(motionEvent.getX() < motionEvent1.getX()){
                Intent main = new Intent(play.this, com.example.deniz.dicer.MainActivity.class);

                startActivity(main);
            }


            return true;
        }
    }
    /////////////////////////////////
    ///////////gesture//////////////
    /////////////////////////////////////////////
//////////////////////reading//////////////
    ////////////Reading/////////////////
    public int diceNumSymbol(){
        int Num = 0;
        Log.i("LOG", Num +"your num");

        ///dicecolor
        try{
            FileInputStream saveSymbol = openFileInput("Symbol");
            InputStreamReader inputSymbol = new InputStreamReader(saveSymbol);
            BufferedReader buffSymbol = new BufferedReader(inputSymbol);
            Num= Integer.parseInt(buffSymbol.readLine());
            Log.i("LOG", Num +"your num");
            return Num;

        } catch (FileNotFoundException e)
        {
            Log.i("LOG", Num +"file num");

            e.printStackTrace();
        } catch (IOException e)
        {
            Log.i("LOG", Num +"file io num");

            e.printStackTrace();
        }
        // Log.i("LOG", diceColorNum +"dicecolor");

        return Num;
    }
    /////////////////////////////////////////
    //////////////////////////////////////
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
    /////////////////////////////////////////

    public String getAnimation()
    {
        try
        {
            //Dice animation
            FileInputStream saveFile = openFileInput("save_animation");
            InputStreamReader inputReader = new InputStreamReader(saveFile);
            BufferedReader buffReader = new BufferedReader(inputReader);

            txtdiceanimation = buffReader.readLine();

            return txtdiceanimation;

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return txtdiceanimation;


    }
    /////////////////////////////////////////
    ////////////////////////////////////////////////////////

    public String diceNumandSideRead(String Filename)
    {
        //Dice number
        String txtdicenum = null;
        Log.i("LOG","diceside "+ txtdicenum);

        try
        {
            FileInputStream DicenumFile = openFileInput(Filename);
            InputStreamReader inputDiceNum = new InputStreamReader(DicenumFile);
            BufferedReader buffDiceNum = new BufferedReader(inputDiceNum);

            txtdicenum=buffDiceNum.readLine();
            Log.i("LOG","diceside "+ txtdicenum);

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
       /* if (txtdicenum == null){
            txtdicenum = "6";
        }*/
        Log.i("LOG","diceside "+ txtdicenum);

        return txtdicenum;


    }
    //////////////////////////////////////////////////////
    /////////////////////////////////////////////

    public String getMath(String dummy)
    {


        try
        {
            FileInputStream Math = openFileInput("Math");
            InputStreamReader inputMath = new InputStreamReader(Math);
            BufferedReader buffMath = new BufferedReader(inputMath);
            dummy=buffMath.readLine();
            return dummy;

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return dummy;


    }
    ///////////////////////////////////////////////////
    ///////////////////////////////////////////////////
    //////////////////reading///////////////

}

