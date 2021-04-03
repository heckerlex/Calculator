package com.example.calculator;
import androidx.appcompat.app.AppCompatActivity;
import org.mariuszgromada.math.mxparser.*;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.EditText;
import java.lang.*;

public class MainActivity extends AppCompatActivity
{
    private EditText display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display=findViewById(R.id.text);
        display.setShowSoftInputOnFocus(false);
        display.setOnClickListener(v -> {
            if (getString(R.string.display).equals(display.getText().toString()))
            {
                display.setText("");
            }

        });
    }
     void updateText(String strToAdd){
        String oldStr=display.getText().toString();
        int cursorPos=display.getSelectionStart();
        String leftStr=oldStr.substring(0,cursorPos);
        String rightStr=oldStr.substring(cursorPos);
         if (getString(R.string.display).equals(display.getText().toString())) {

             display.setText(strToAdd);
             display.setSelection(cursorPos+1);
         }
        else{
             display.setText(String.format("%s%s%s",leftStr,strToAdd,rightStr));
             display.setSelection(cursorPos+1);
         }





    }
    public void zero(View view)
    {
        updateText("0");
    }
    public void one(View view)
    {
        updateText("1");
    }
    public void two(View view)
    {
        updateText("2");
    }
    public void three(View view)
    {
        updateText("3");
    }
    public void four(View view)
    {
        updateText("4");
    }
    public void five(View view)
    {
        updateText("5");
    }
    public void six(View view)
    {
        updateText("6");
    }
    public void seven(View view)
    {
        updateText("7");
    }
    public void eight(View view)
    {
        updateText("8");
    }
    public void nine(View view)
    {
        updateText("9");
    }
    public void subtract(View view)
    {
        updateText("-");
    }
    public void add(View view)
    {
        updateText("+");
    }
    public void multiply(View view)
    {
        String a=display.getText().toString();
        if(a.length()==0)
            return;
        updateText("×");
    }
    public void divide(View view)
    {
        String a=display.getText().toString();

        if(a.length()==0)
            return;

            updateText("÷");
    }
    public void equal(View view)
    {
        String userExp=display.getText().toString();
        userExp=userExp.replaceAll("÷","/");
        userExp=userExp.replaceAll("×","*");
        userExp=userExp.replaceAll("√","sqrt(");
        Expression exp=new Expression(userExp);
        String result=String.valueOf(exp.calculate());
        display.setText(result);
        display.setSelection(result.length());

    }
    public void exponent(View view)
    {
        String a=display.getText().toString();

        if(a.length()==0)
            return;
        updateText("^");
    }
    public void parenthesis(View view)
    {
        int cursorPos=display.getSelectionStart();
        int openPar=0;
        int closedPar=0;
        int textLen=display.getText().length();

        for(int i=0;i<cursorPos;i++)
        {
            if(display.getText().toString().substring(i,i+1).equals("(")){
                openPar +=1;
            }
            if(display.getText().toString().substring(i,i+1).equals(")")){
                closedPar +=1;
            }
        }
        if(openPar==closedPar||display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText("(");

        }
        else if(closedPar<openPar && !display.getText().toString().substring(textLen-1,textLen).equals("(")){
            updateText(")");

        }
        display.setSelection(cursorPos+1);
    }
    public void clear(View view)
    {
        display.setText("");
    }
    public void backspace(View view)
    {
        int cursorPos=display.getSelectionStart();
        int textLen=display.getText().length();
        if(cursorPos!=0&&textLen!=0){
            SpannableStringBuilder selection= (SpannableStringBuilder) display.getText();
            selection.replace(cursorPos-1,cursorPos,"");
            display.setText(selection);
            display.setSelection(cursorPos-1);

        }


    }
    public void plus_minus(View view)
    {
        String text = display.getText().toString();
        String newtext="";
        if(text.length()==0)
            return;
        for(int i=text.length()-1;i>=0;i--){

            if((i>0&&text.charAt(i)=='-' && text.charAt(i-1)=='*') || (i>0 && text.charAt(i)=='-' && text.charAt(i-1)=='/') || (i>0 && text.charAt(i)=='-' && text.charAt(i-1)=='^' )){
                newtext = text.substring(0,i)+text.substring(i+1,text.length());
                display.setText("");
                updateText(newtext);
                display.setSelection(newtext.length());
                return;
            }
            else if(text.charAt(i)=='*' || text.charAt(i)=='/' || text.charAt(i)=='^'){
                newtext = text.substring(0,i+1)+'-'+text.substring(i+1,text.length());
                display.setText("");
                updateText(newtext);
                display.setSelection(newtext.length());
                return;
            }

            else if(text.charAt(i)=='+'){
                newtext = text.substring(0,i)+"-"+text.substring(i+1,text.length());
                display.setText("");
                updateText(newtext);
                display.setSelection(newtext.length());
                return;
            }
            else if(text.charAt(i)=='-' && i>0) {
                newtext = text.substring(0, i) + "+" + text.substring(i + 1, text.length());
                display.setText("");
                updateText(newtext);
                display.setSelection(newtext.length());
                return;
            }

        }
        if(text.charAt(0)=='-'){
            newtext = text.substring( 1, text.length());

            display.setText("");
            updateText(newtext);
            display.setSelection(newtext.length());

            return;
        }
        if(Character.isDigit(text.charAt(0))) {
            newtext = "-"+text.substring( 0, text.length());
            display.setText("");
            updateText(newtext);
            display.setSelection(newtext.length());
            return;
        }

    }
    public void sqrt(View view)
    {
        String a=display.getText().toString();
        updateText("sqrt()");
        display.setSelection(a.length()+5);
    }
    public void dot(View view)
    {
        String a=display.getText().toString();
        int i,c=0,h=0;
        for(i=0;i<a.length();i++)
        {
            if(a.charAt(i)=='.')
            {
                c++;
            }

            if(a.charAt(i)=='+'||a.charAt(i)=='-'||a.charAt(i)=='÷'||a.charAt(i)=='×') {
                h++;
            }
        }
        if(c==0)
        updateText(".");
        if(c>0&&h>0&&a.charAt(i-1)!='.')
            updateText(".");


    }
}