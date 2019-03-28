package com.example.deniz.dicer;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class read_file extends ContextWrapper {


    public read_file(Context base) {
        super(base);
    }

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
    ////////////////////////////////////////
    ////////////////////////////////////////
    public boolean fun_firstTime(){
        boolean firstStart = true;
        try{
            FileInputStream loadfirst = openFileInput("firstTime");
            InputStreamReader inputfirst = new InputStreamReader(loadfirst);
            BufferedReader buffinput = new BufferedReader(inputfirst);
            firstStart= Boolean.valueOf(buffinput.readLine());

            return firstStart;

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return firstStart;
    }
    //////////////////////////////////////////////
    //////////////////////////////////////////////




}
