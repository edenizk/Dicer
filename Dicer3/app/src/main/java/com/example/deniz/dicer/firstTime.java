package com.example.deniz.dicer;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import static android.content.Context.MODE_PRIVATE;

public class firstTime extends ContextWrapper {


    public firstTime(Context base) {
        super(base);
    }

    public void writing(){
        //char[] strMath = new char[]{'0','0','0','0','0'};

        String dummy = new String("00000");
       // Log.i("LOG","dummy "+ dummy);

        try
        {
            FileOutputStream savecolor = openFileOutput("save_color",MODE_PRIVATE);
            savecolor.write("0".getBytes());
            savecolor.close();

            FileOutputStream outputDicenum = openFileOutput("DiceNum",MODE_PRIVATE);
            outputDicenum.write("2".getBytes());
            outputDicenum.close();

            FileOutputStream outputsidenum = openFileOutput("SideNum",MODE_PRIVATE);
            outputsidenum.write("6".getBytes());
            outputsidenum.close();

            FileOutputStream outputsidesymbol = openFileOutput("Symbol",MODE_PRIVATE);
            outputsidesymbol.write("0".getBytes());
            outputsidesymbol.close();

            FileOutputStream outputSaveFile = openFileOutput("ListorAnim",MODE_PRIVATE);
            outputSaveFile.write("1".getBytes());
            outputSaveFile.close();

            FileOutputStream outputMath = openFileOutput("Math",MODE_PRIVATE);
            outputMath.write("00000".getBytes());
            outputMath.close();
            //Log.i("LOG","ded "+ txtAddP);

            FileOutputStream outputAddP = openFileOutput("AddP",MODE_PRIVATE);
            outputAddP.write("0".getBytes());
            outputAddP.close();

            FileOutputStream firstTime = openFileOutput("firstTime",MODE_PRIVATE);
            firstTime.write("0".getBytes());
            firstTime.close();

            FileOutputStream outputanim = openFileOutput("save_animation", MODE_PRIVATE);
            outputanim.write("11111111".getBytes());
            outputanim.close();

            Toast.makeText(getApplicationContext(),"First Time Saved",Toast.LENGTH_LONG).show();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();

        } catch (IOException e)
        {
            e.printStackTrace();

        }


    }
}
