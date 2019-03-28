package com.example.deniz.dicer;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class animation extends AppCompatActivity
{

    char[] txt = new char[]{'1','1','1','1','1','1','1','1'};
    char[] Controltxt = new char[]{'1','1','1','1','1','1','1','1'};

    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.animation);
        Log.i("LOG", "animation");

        final Button checkList[] = {
                (Button) findViewById(R.id.checkBounce)
                ,(Button) findViewById(R.id.checkBlink)
                ,(Button) findViewById(R.id.checkFadein)
                ,(Button) findViewById(R.id.checkLost)
                ,(Button) findViewById(R.id.checkMoves)
                ,(Button) findViewById(R.id.checkRotate)
                ,(Button) findViewById(R.id.checkZoomIn)
                ,(Button) findViewById(R.id.checkInf)
        };
        final Animation animlist[] = {AnimationUtils.loadAnimation(this, R.anim.set_bounce)
                ,AnimationUtils.loadAnimation(this, R.anim.set_blink)
                ,AnimationUtils.loadAnimation(this, R.anim.set_fadein)
                ,AnimationUtils.loadAnimation(this, R.anim.infrepeat)
                ,AnimationUtils.loadAnimation(this, R.anim.set_lost)
                ,AnimationUtils.loadAnimation(this, R.anim.set_moves)
                ,AnimationUtils.loadAnimation(this, R.anim.set_rotate)
                ,AnimationUtils.loadAnimation(this, R.anim.set_all)


        };
        final ImageView btndice[] = {(ImageView) findViewById(R.id.imgBounce)
                ,(ImageView) findViewById(R.id.imgBlink)
                ,(ImageView) findViewById(R.id.imgFadein)
                ,(ImageView) findViewById(R.id.imgTurn)
                ,(ImageView) findViewById(R.id.imgLost)
                ,(ImageView) findViewById(R.id.imgMoves)
                ,(ImageView) findViewById(R.id.imgRotate)
                ,(ImageView) findViewById(R.id.imgZoomIn)


        };

        final Drawable openclose[] = {getResources().getDrawable(R.drawable.fut_button_circle_close),getResources().getDrawable(R.drawable.fut_button_circle_open)};

        final Button btnback = (Button) findViewById(R.id.btnback);


        Log.i("LOG", "animation");
        for(int i=0; i<btndice.length;i++) {
            btndice[i].startAnimation(animlist[i]);
        }

        //////////////////////////
        //////////////////////////
        //Animation
       try
       {

            FileInputStream saveFile = openFileInput("save_animation");
            InputStreamReader inputReader = new InputStreamReader(saveFile);
            BufferedReader buffReader = new BufferedReader(inputReader);
            String dumppy;
            dumppy=buffReader.readLine();

           for(int i=0; i<dumppy.length();i++)
           {
                txt[i]=dumppy.charAt(i);
               Controltxt[i]=dumppy.charAt(i);
           }
           for (int i=0; i<checkList.length;i++)
           {
                if (txt[i] == '1') setTurnOn(checkList,openclose,i);

                else      setTurnOff(checkList,openclose,i);

           }

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();

        } catch (IOException e)
        {
            e.printStackTrace();

        }

        ////////////////////////////
                ///////////////////////
        //////////////////////////////////////
                ////////////////////////////
        checkList[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (txt[0]!='0')
                {
                    setTurnOff(checkList,openclose,0);

                } else
                {
                    setTurnOn(checkList,openclose,0);

                }
            }
        });
                checkList[1].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (txt[1]!='0')
                        {
                            setTurnOff(checkList,openclose,1);


                        } else
                        {
                            setTurnOn(checkList,openclose,1);


                        }
                    }
                });
                checkList[2].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (txt[2]!='0')
                        {
                            setTurnOff(checkList,openclose,2);

                            } else
                        {
                            setTurnOn(checkList,openclose,2);

                        }
                    }
                });
                checkList[3].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (txt[3]!='0')
                        {
                            setTurnOff(checkList,openclose,3);



                        } else
                        {

                            setTurnOn(checkList,openclose,3);


                        }
                    }
                });
                checkList[4].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (txt[4]!='0')
                        {
                            setTurnOff(checkList,openclose,4);

                        } else
                        {
                            setTurnOn(checkList,openclose,4);

                        }
                    }
                });
                checkList[5].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (txt[5]!='0')
                        {
                            setTurnOff(checkList,openclose,5);

                        } else
                        {
                            setTurnOn(checkList,openclose,5);

                        }
                    }
                });
                checkList[6].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (txt[6]!='0')
                        {
                            setTurnOff(checkList,openclose,6);

                        } else
                        {
                            setTurnOn(checkList,openclose,6);

                        }
                    }
                });
                checkList[7].setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View view)
                    {
                        if (txt[7]!='0')
                        {
                            setTurnOff(checkList,openclose,7);

                        } else
                        {
                            setTurnOn(checkList,openclose,7);

                        }
                    }
                });
                //////////////////////////////
                //////////////////////////////
                //////////////////////////////


        btnback.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                String dummy = new String(txt);

                try
                {
                    FileOutputStream outputSaveFile = openFileOutput("save_animation",MODE_PRIVATE);
                    outputSaveFile.write(dummy.getBytes());
                    outputSaveFile.close();
                    Toast.makeText(getApplicationContext(),"SAVED",Toast.LENGTH_LONG).show();

                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();

                } catch (IOException e)
                {
                    e.printStackTrace();

                }


                Intent settings = new Intent(animation.this, com.example.deniz.dicer.settings.class);

                startActivity(settings);




            }
        });



    }
    public void setTurnOff(Button checkList[], Drawable openclose[],int i )
    {
        checkList[i].setBackground(openclose[0]);
        checkList[i].setTextColor(Color.parseColor("#0D1321"));

        txt[i] = '0';

    }
    public void setTurnOn(Button checkList[], Drawable openclose[],int i )
    {
        checkList[i].setBackground(openclose[1]);
        checkList[i].setTextColor(Color.parseColor("#78d9ff"));


        txt[i] = '1';

    }



    @Override
    public void onBackPressed() {

        final Intent settings = new Intent(animation.this, com.example.deniz.dicer.settings.class);
        if (Arrays.equals(Controltxt,txt)) {
            startActivity(settings);

        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this,R.style.myDialogTheme);
            builder.setCancelable(false);
            builder.setTitle("SAVE");
            builder.setIcon(R.drawable.fut_button_circle_open);
            builder.setMessage("\n  Do you want to Save Changes?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    //if user pressed "yes", then he is allowed to exit from application


                    String dummy = new String(txt);

                    try {
                        FileOutputStream outputSaveFile = openFileOutput("save_animation", MODE_PRIVATE);
                        outputSaveFile.write(dummy.getBytes());
                        outputSaveFile.close();
                        Toast.makeText(getApplicationContext(), "SAVED", Toast.LENGTH_LONG).show();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();

                    } catch (IOException e) {
                        e.printStackTrace();

                    }


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
    }

}
