package com.example.deniz.dicer;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.SecureRandom;

public class dicelist extends AppCompatActivity {

   // int num1=0,num2=0,num3=0,num4=0,num5=0,num6=0;
    int num[]={0,0,0,0,0,0},checksum=0;
    SecureRandom secureRandomNum = new SecureRandom();
    private GestureDetectorCompat gestureObj;

    int choosenMath = 4;
    String txtAddP="0";

    int diceColorNum = 0;
    int btndicenum = 2;

    SourceImg mysource = new SourceImg();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dicelist);
        Log.i("LOG", "rol");

        gestureObj = new GestureDetectorCompat(this, new gestureClass2());
        final TextView txtDice[] = {(TextView) findViewById(R.id.txtdice1),
                (TextView) findViewById(R.id.txtdice2),
                (TextView) findViewById(R.id.txtdice3),
                (TextView) findViewById(R.id.txtdice4),
                (TextView) findViewById(R.id.txtdice5),
                (TextView) findViewById(R.id.txtdice6)};
        final TextView txtMath = (TextView) findViewById(R.id.txtMath2);
        final ImageView imgDice[] = {(ImageView) findViewById(R.id.imgdice1),
                (ImageView) findViewById(R.id.imgdice2),
                (ImageView) findViewById(R.id.imgdice3),
                (ImageView) findViewById(R.id.imgdice4),
                (ImageView) findViewById(R.id.imgdice5),
                (ImageView) findViewById(R.id.imgdice6)};


        Button Roll = (Button) findViewById(R.id.Roll);
        Button btnRefresh = (Button) findViewById(R.id.btnRefresh);



        ////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////
        //Reading

        ////////////////////////////////////////////////////////////////////
        ////////////////////////////////////////////////////////////////////
        //Reading

//addp
        txtAddP=getTxtAddP();

        checksum=Integer.parseInt(txtAddP);

        //color

        diceColorNum=diceNumColor();
        Drawable drawableDiceColor = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);

        for (int i = 0; i < imgDice.length; i++)
        {
            imgDice[i].setBackground(drawableDiceColor);
        }

        //Dice number
        String stringDicenum = diceNumRead();
        ///READ/////
        final int btndicenum = Integer.parseInt(stringDicenum);

        final int[] valueOfDice = new int[btndicenum];


        //MAth
        String dummy = "00000";
        dummy=getMath(dummy);
        Log.i("Math", "MAth");

        for(int i=0; i<dummy.length();i++)
        {
            if(dummy.charAt(i)=='1'){
                choosenMath=i;
            }

        }
        getMath2(valueOfDice,txtMath);



        Roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                    if (checksum==0) {
                        for (int i = 0; i < 6; i++) {
                            num[i]=0;
                            txtDice[i].setText("0");
                        }
                    }


                    for (int i = 0; i < btndicenum; i++) {


                        int randomNum = 1 + secureRandomNum.nextInt(6);
                        valueOfDice[i] = randomNum;

                        switch (randomNum) {

                            case 1:
                                ++num[0];
                                break;
                            case 2:
                                ++num[1];
                                break;
                            case 3:
                                ++num[2];
                                break;
                            case 4:
                                ++num[3];
                                break;
                            case 5:
                                ++num[4];
                                break;
                            case 6:
                                ++num[5];
                                break;

                        }
                    }
                        for (int j = 0; j < 6; j++) {

                            txtDice[j].setText(num[j] + "");

                        }
                getMath2(valueOfDice,txtMath);


            }





        });

        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int i = 0; i < 6; i++) {
                    num[i]=0;
                    txtDice[i].setText("0");

                }


            }
        });


    }
    /////////////////////////////////
    ///////////gesture//////////////
    public boolean onTouchEvent(MotionEvent event){
        this.gestureObj.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    ////////////////////////////////////////
    class gestureClass2 extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
             if(motionEvent.getX() < motionEvent1.getX()){


                 if(btndicenum>15) {
                     Intent main = new Intent(dicelist.this, com.example.deniz.dicer.MainActivity.class);

                     startActivity(main);
                 }
                 else{
                     Intent play = new Intent(dicelist.this, com.example.deniz.dicer.play.class);

                     startActivity(play);
                 }
            }



            return true;
        }
    }
    /////////////////////////////////
    ///////////gesture//////////////

    private void getMath2(int valueOfDice[],TextView txtMath) {
        math mymath=new math(valueOfDice,choosenMath);

        txtMath.setText(mymath.getMathText());
    }
    ////////////////////////////////
    ////////////////reading//////
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
    /////////////////////////////////////
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

        return txtdicenum;


    }
    //////////////////////////////////////////////////////
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
    /////////////////////////////////////////////
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
    /////////////////////////////////////////
    ///////////////////////////reading
}
