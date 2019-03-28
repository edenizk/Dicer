package com.example.deniz.dicer;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class dice extends AppCompatActivity {

    String strListOrAnim = "1";
    char[] strMath = new char[]{'0','0','0','0','0'};
    EditText txtdicenum, txtsidenum;
    CheckBox addP;
    String txtAddP="0";
    int diceColorNum = 0, diceSymbolNum = 0;
    Boolean side_changer = true;
    String txt = null;
    TextView txtListorAnim;
    Button btnplus, btnminus, btnrightcolor, btnleftcolor, btnsideminus, btnsideplus, btnrightsymbol, btnleftsymbol;
    SourceImg mysource = new SourceImg();
    List<Button> imgDice = new ArrayList<Button>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dice);

        MainActivity reading = new MainActivity();


        txtdicenum = (EditText) findViewById(R.id.txtdicenum);
        txtsidenum = (EditText) findViewById(R.id.txtsidenum);
        addP = (CheckBox) findViewById(R.id.addP);

        btnplus = (Button) findViewById(R.id.btnplus);
        btnminus = (Button) findViewById(R.id.btnminus);
        btnrightcolor = (Button) findViewById(R.id.btnrightcolor);
        btnleftcolor = (Button) findViewById(R.id.btnleftcolor);
        btnrightsymbol = (Button) findViewById(R.id.btnrightsymbol);
        btnleftsymbol = (Button) findViewById(R.id.btnleftsymbol);
        btnsideminus = (Button) findViewById(R.id.btnsideminus);
        btnsideplus = (Button) findViewById(R.id.btnsideplus);

        imgDice.add((Button) findViewById(R.id.imgdicecolor));
        imgDice.add((Button) findViewById(R.id.imgDiceShape));
        imgDice.add((Button) findViewById(R.id.imgDiceSymbolColor));
        imgDice.add((Button) findViewById(R.id.imgDiceSymbol));

        final Button checkList[] =
                {
                        (Button) findViewById(R.id.checkSum)
                        ,(Button) findViewById(R.id.checkBest)
                        ,(Button) findViewById(R.id.checkWorst)
                        ,(Button)findViewById(R.id.checkMiddle)
                        ,(Button) findViewById(R.id.checkCount)

                };

        txtListorAnim = (TextView) findViewById(R.id.txtListorAnim);

        final Drawable openclose[] = {getResources().getDrawable(R.drawable.fut_button_circle_close),getResources().getDrawable(R.drawable.fut_button_circle_open)};

        Button btnback = (Button) findViewById(R.id.btnback);

        registerForContextMenu(txtListorAnim);
        //////////////////////REading//////////////////
        //////////////////////////////////////////////
            ///dicecolor

            diceColorNum=diceNumColor();

        Drawable drawableDiceColor = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);

        change_bc_dice(drawableDiceColor);


        //dice symbol
        diceSymbolNum = diceNumSymbol();
        symbol(diceSymbolNum);
        //addp
        txtAddP=getTxtAddP();
        int a=Integer.parseInt(txtAddP);
        if(a==1) {
            addP.setChecked(true);
        }
        else{
            addP.setChecked(false);
        }


        //Dice number

        txtdicenum.setText(diceNumAndSideRead("DiceNum"));
        //Side number
        txtsidenum.setText(diceNumAndSideRead("SideNum"));
        ///math
            String dummy = "00000";
            dummy=getMath(dummy);
            for(int i=0; i<dummy.length();i++)
            {
                strMath[i]=dummy.charAt(i);

            }

            //listoranim
            strListOrAnim=getStrListOrAnim();

        //////////////////////REading//////////////////
        //////////////////////////////////////////////
        if(strListOrAnim.charAt(0)=='0'){
            txtListorAnim.setText("List");
        }else
        {
            txtListorAnim.setText("Animation");
        }

        Log.i("LOG","txtaddp "+ txtAddP);





        for(int i=0;i<checkList.length;i++)
            if(strMath[i]=='1'){
                Log.i("LOG","sda "+ strMath[i]);

                setTurnOn(checkList,openclose,i);

            }else {
                Log.i("LOG","sad "+ strMath[i]);

                setTurnOff(checkList, openclose, i);
            }


        txtListorAnim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openContextMenu(txtListorAnim);

            }
        });

////////////////////////////////////////////////////
        //////////////////////////////////////////////
        for (int i = 0; i < checkList.length; ++i)
        {
            final Button btn = checkList[i];
            final int counter = i;
            btn.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    if (strMath[counter]!='0')
                    {
                        setTurnOff(checkList,openclose,counter);



                    } else
                    {
                        for(int i=0;i<checkList.length;i++)
                            if(i==counter){
                                setTurnOn(checkList,openclose,i);

                            }else {
                                setTurnOff(checkList, openclose, i);
                            }
                    }

                }
            });
        }

        ////////////////////////////////////////////////
        ////////////////////////////////////////////////

        btnplus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int dicenum = Integer.parseInt(txtdicenum.getText().toString());

                dicenum++;



                txtdicenum.setText(dicenum + "");
            }
        });
        Log.i("LOG","minus button");

        btnminus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int dicenum = Integer.parseInt(txtdicenum.getText().toString());
                if(dicenum>0) {
                    dicenum--;
                }
                else{

                    Toast.makeText(getApplicationContext(),"Can't be 0 or smaller than 0",Toast.LENGTH_LONG).show();

                }

                txtdicenum.setText(dicenum + "");
            }
        });

        btnsideplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sidenum = Integer.parseInt(txtsidenum.getText().toString());
                if(diceSymbolNum !=2) {
                    sidenum++;

                }
                else{
                    if(sidenum<4999) {
                        sidenum++;

                    }
                    else{
                        Toast.makeText(getApplicationContext(), "In Rome Numbers Side Can't be 5000 or more than 5000", Toast.LENGTH_LONG).show();

                    }
                }
                if((side_changer == false)&&(sidenum<=6)||(diceSymbolNum == 0)){
                    side_changer = true;
                }
                if(((sidenum > 6)&&(diceSymbolNum==0)&&(side_changer == true))){
                    symbol(1);
                    diceSymbolNum = 1;
                    side_changer = false;
                    Toast.makeText(getApplicationContext(), "Your Dice Symbol Has Changed", Toast.LENGTH_LONG).show();
                }




                txtsidenum.setText(sidenum + "");
            }
        });

        btnsideminus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int sidenum = Integer.parseInt(txtsidenum.getText().toString());

                if(sidenum > 0){

                    sidenum--;
                    Log.i("LOG","side num-- " + sidenum);
                }
                else{

                    Toast.makeText(getApplicationContext(),"Can't be 0 or smaller than 0",Toast.LENGTH_LONG).show();

                }
                txtsidenum.setText(sidenum + "");

                if((side_changer == false)&&(sidenum<=6)){

                    side_changer = true;
                }
                if((diceSymbolNum == 0)&&(sidenum<7)){
                    symbol(0);

                }

            }
        });

        Log.i("LOG","right button");

        btnrightcolor.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (diceColorNum<(mysource.diceColors.length-1))
                {

                        diceColorNum += 1;


                    if(diceColorNum==mysource.diceColors.length){
                        diceColorNum=0;
                    }
                    Drawable drawableDiceColor = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);

                    change_bc_dice(drawableDiceColor);


                }



            }
        });
        Log.i("LOG","left button");


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
                    Log.i("LOG", "mysourcelenght" + mysource.diceColors.length);
                    Drawable drawableDiceColor = ResourcesCompat.getDrawable(getResources(),mysource.diceColors[diceColorNum],null);

                    change_bc_dice(drawableDiceColor);
                }
            }
        });

        btnrightsymbol.setOnClickListener(new View.OnClickListener()
        {

            public void onClick(View view)
            {
                if (diceSymbolNum<3)
                {
                    diceSymbolNum += 1;

                    if(diceSymbolNum==3){
                        diceSymbolNum=0;
                    }
                    symbol(diceSymbolNum);



                }



            }
        });
        Log.i("LOG","left button");


        btnleftsymbol.setOnClickListener(new View.OnClickListener()
        {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view)
            {
                if(diceSymbolNum>(-1))
                {
                    diceSymbolNum -= 1;
                    if(diceSymbolNum==(-1)){
                        diceSymbolNum=2;
                    }
                    symbol(diceSymbolNum);


                }
            }
        });
    Log.i("LOG","back button");

        btnback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if(addP.isChecked()){
                    txtAddP = "1";
                }
                writing();

                Intent settings = new Intent(dice.this, com.example.deniz.dicer.settings.class);

                startActivity(settings);


            }
        });
        Log.i("LOG","finished button");

    }
    ///////end of  on create

    public void change_bc_dice(Drawable drawableDiceColor){
        for(int i=0; i < imgDice.size(); i++){
            imgDice.get(i).setBackground(drawableDiceColor);
        }
    }
    @TargetApi(Build.VERSION_CODES.M)
    public void symbol(int i){
        switch (i){
            case 0:
                int sidenum = Integer.parseInt(txtsidenum.getText().toString());
                diceSymbolNum = 0;
                Log.i("LOG","side num " + sidenum);
                if(sidenum<=6) {
                    for(Button myButton: imgDice){
                        myButton.setText("");
                        Drawable drawableDiceDots = ResourcesCompat.getDrawable(getResources(), mysource.diceDots[0], null);
                        myButton.setForeground(drawableDiceDots);
                    }

                }
                else {
                    for(Button myButton: imgDice){
                        myButton.setForeground(null);
                        myButton.setText("UNABLE");
                    }

                }

                break;

            case 1:
                for(Button myButton: imgDice) {
                    myButton.setForeground(null);
                    myButton.setText("1");
                }
                diceSymbolNum = 1;
                break;
            case 2:
                for(Button myButton: imgDice) {
                    myButton.setForeground(null);
                    myButton.setText("I");
                }
                diceSymbolNum = 2;
                break;


        }

    }

    public void setTurnOff(Button checkList[], Drawable openclose[], int i )
    {

        checkList[i].setBackground(openclose[0]);
        checkList[i].setTextColor(Color.parseColor("#0D1321"));

        strMath[i] = '0';

    }
    public void setTurnOn(Button checkList[], Drawable openclose[],int i )
    {
        checkList[i].setBackground(openclose[1]);
        checkList[i].setTextColor(Color.parseColor("#78d9ff"));


        strMath[i] = '1';

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Choose your option");
        getMenuInflater().inflate(R.menu.listoranim, menu);
    }
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menuList:
                txtListorAnim.setText("List");
                strListOrAnim="0";
                Toast.makeText(this, "List Option Selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.menuAnim:
                txtListorAnim.setText("Animation");
                strListOrAnim="1";
                Toast.makeText(this, "Animation Option Selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }


    }

    @Override
    public void onBackPressed() {
        final Intent settings = new Intent(dice.this, com.example.deniz.dicer.settings.class);

        AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.myDialogTheme).setTitle("SAVE").setIcon(R.drawable.fut_button_circle_open);
        builder.setCancelable(false);
        builder.setMessage("\n  Do you want to Save Changes?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user pressed "yes", then he is allowed to exit from application


                if (addP.isChecked()) {
                    txtAddP = "1";
                }
                writing();



                startActivity(settings);

            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //if user select "No", just cancel this dialog and continue with app
                startActivity(settings);
            }
        });
        AlertDialog alert = builder.create();
        alert.show();




    }

    public void writing(){
        String dummy = new String(strMath);
        Log.i("LOG","dummy "+ dummy);

        try
        {
            FileOutputStream savecolor = openFileOutput("save_color",MODE_PRIVATE);
            savecolor.write(Integer.toString(diceColorNum).getBytes());
            savecolor.close();

            FileOutputStream outputDicenum = openFileOutput("DiceNum",MODE_PRIVATE);
            outputDicenum.write(txtdicenum.getText().toString().getBytes());
            outputDicenum.close();
            if((Integer.parseInt(txtsidenum.getText().toString()) > 5000)&&(diceSymbolNum == 2)) {
                FileOutputStream outputsidenum = openFileOutput("SideNum", MODE_PRIVATE);
                outputsidenum.write("4999".getBytes());
                outputsidenum.close();
            }
            else{
                FileOutputStream outputsidenum = openFileOutput("SideNum", MODE_PRIVATE);
                outputsidenum.write(txtsidenum.getText().toString().getBytes());
                outputsidenum.close();
            }
            if((diceSymbolNum == 0) && (Integer.parseInt(txtsidenum.getText().toString()) > 6)){
                FileOutputStream outputsidesymbol = openFileOutput("Symbol",MODE_PRIVATE);
                outputsidesymbol.write("1".getBytes());
                outputsidesymbol.close();
            }
            else {
                FileOutputStream outputsidesymbol = openFileOutput("Symbol", MODE_PRIVATE);
                outputsidesymbol.write(Integer.toString(diceSymbolNum).getBytes());
                outputsidesymbol.close();
            }
            FileOutputStream outputSaveFile = openFileOutput("ListorAnim",MODE_PRIVATE);
            outputSaveFile.write(strListOrAnim.getBytes());
            outputSaveFile.close();

            FileOutputStream outputMath = openFileOutput("Math",MODE_PRIVATE);
            outputMath.write(dummy.getBytes());
            outputMath.close();
            Log.i("LOG","ded "+ txtAddP);

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
    ///////////////////////////////////////


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
    ////////////////////////////////////////////////////////

    public String diceNumAndSideRead(String Filename)
    {
        //Dice number
        String txtdicenum = null;

        try
        {
            FileInputStream DicenumFile = openFileInput(Filename);
            InputStreamReader inputDiceNum = new InputStreamReader(DicenumFile);
            BufferedReader buffDiceNum = new BufferedReader(inputDiceNum);

            txtdicenum=buffDiceNum.readLine();

            Log.i("LOG","dicenum 21 "+ txtdicenum);
            return txtdicenum;

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        return txtdicenum;


    }


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
    //////////////////reading///////////////

}
