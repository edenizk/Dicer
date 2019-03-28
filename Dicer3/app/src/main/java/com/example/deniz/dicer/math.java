package com.example.deniz.dicer;

import android.widget.TextView;

public class math {
    private int valueOfDice[];
    private int choosenMath;


    public int[] getValueOfDice() {
        return valueOfDice;
    }

    public void setValueOfDice(int[] valueOfDice) {
        this.valueOfDice = valueOfDice;
    }

    public int getChoosenMath() {
        return choosenMath;
    }

    public void setChoosenMath(int choosenMath) {
        this.choosenMath = choosenMath;
    }


    public math (int valueOfDice[], int choosenMath){

        this.valueOfDice = valueOfDice;
        this.choosenMath = choosenMath;


    }

    private int getMath(int choosenMath , int valueOfDice[]){
        int temp = 0;
        int resultMath=0;

        switch (choosenMath){
            //notting
            case 5:
                break;
            case 0:
                for(int i=0;i<valueOfDice.length;i++)
                {
                    resultMath+=valueOfDice[i];
                }
                return resultMath;
                case 1:
                temp = 0;
                for(int i = 0; i<valueOfDice.length;i++){
                    if(valueOfDice[i]>temp){
                        temp=valueOfDice[i];
                    }
                }
                resultMath=temp;

                    return resultMath;
            case 2:
                temp = valueOfDice[0];
                for(int i = 1; i<valueOfDice.length;i++){
                    if(temp>valueOfDice[i]){
                        temp=valueOfDice[i];
                    }
                }
                resultMath=temp;
                return resultMath;
            case 3:
                for(int i=0;i<valueOfDice.length;i++) {
                }
                resultMath=middleMath(valueOfDice);
                return resultMath;
            case 4:
                resultMath=123;
                return resultMath;
        }
        return resultMath;

    }

    private int middleMath(int valueOfDice[])
    {
        int a=0;

        int n = valueOfDice.length;
        for (int i = 0; i < n-1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (valueOfDice[j] > valueOfDice[j + 1]) {
                    // swap temp and arr[i]
                    int temp = valueOfDice[j];
                    valueOfDice[j] = valueOfDice[j + 1];
                    valueOfDice[j + 1] = temp;
                }
            }
        }

        for(int i=0;i<n-1;i++){
            if(valueOfDice[i]!=valueOfDice[i+1]){
                a++;
            }

        }
        a++;
        int dummy[] = new int[a];

        dummy[0]=valueOfDice[0];

        int b=0;
        for(int i=0;i<n-1;i++){
            if(valueOfDice[i]==valueOfDice[i+1]){

            }else{
                dummy[b]=valueOfDice[i];
                b++;
            }
        }

        return dummy[a/2];
    }

    public String getMathText(){
        int resultMath=0;
        String txtMath="non Found";
        switch (choosenMath){
            //noting
            case 5:
                txtMath="empty";
                return txtMath;
            case 0:
                resultMath=getMath( choosenMath ,  valueOfDice);
                return txtMath.format("Sum of Dice: " + resultMath);
            case 1:
                resultMath=getMath( choosenMath , valueOfDice);
                return txtMath.format("Best Dice: " + resultMath);
            case 2:
                resultMath=getMath(choosenMath , valueOfDice);

                return txtMath.format("Worst Dice: " + resultMath);
            case 3:
                resultMath=getMath(choosenMath ,  valueOfDice);

                return txtMath.format("Middle Dice: " + resultMath);
            case 4:
                resultMath=getMath(choosenMath, valueOfDice);
                return txtMath.format("Dice Rolled: " + resultMath);
        }
    return txtMath;
    }


}
