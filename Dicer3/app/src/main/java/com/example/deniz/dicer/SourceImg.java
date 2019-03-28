package com.example.deniz.dicer;

import android.content.Context;
import android.graphics.drawable.Drawable;

public class SourceImg   {

    public int[] diceColors = {R.drawable.fut_green_dice,
                                R.drawable.fut_blue_dice,
                                R.drawable.fut_dark_red_dice,
                                R.drawable.fut_orange_dice,
                                R.drawable.fut_red_dice,
                                R.drawable.fut_skin_dice,
                                R.drawable.fut_white_dice,
                                R.drawable.fut_yellow_dice,
                                R.drawable.fut_dark_dice,};


    public int[] diceDots = {R.drawable.dice11
            , R.drawable.dice22
            , R.drawable.dice33
            , R.drawable.dice44
            , R.drawable.dice55
            , R.drawable.dice66};



    public String romeNum(int i){
        String Num = "";

        while(i >= 1000 ) {
            Num += "M";
            i -= 1000;
        }
        while(i >= 900){
            Num += "CM";
            i -= 900;
        }
        while(i >= 500){
            Num += "D";
            i -= 500;
        }
        while(i >= 400){
            Num += "CD";
            i -= 400;
        }
        while (i >= 100){
            Num += "C";
            i -= 100;
        }
        while(i >= 90){
            Num += "XC";
            i -= 90;
        }
        while(i >= 50){
            Num += "L";
            i -= 50;
        }
        while(i >= 40){
            Num += "XL";
            i -= 40;
        }
        while(i >= 10){
            Num += "X";
            i -= 10;
        }
        while(i >= 9){
            Num += "IX";
            i -= 90;
        }
        while(i >= 5){
            Num += "V";
            i -= 5;
        }
        while(i >= 4){
            Num += "IV";
            i -= 4;
        }
        while (i >= 1){
            Num += "I";
            i -= 1;
        }


        return Num;
    }



}
