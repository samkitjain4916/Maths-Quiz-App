package com.example.mathsquiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    CountDownTimer timer;
    TextView textViewTimer,textViewScore,textViewQue,textViewTimerHeading,textViewScoreHeading;
    GridLayout gridLayout;
    Button reset,button;

    boolean is_running;
    int score;
    int attempted;
    String ansTag;

    @SuppressLint("SetTextI18n")
    public void startGame()
    {
        makeVisible();
        score=0;
        attempted=0;
        is_running=true;

        textViewScore.setText("0/0");
        textViewTimer.setText("15s");
        timer = new CountDownTimer(15100,1000)
        {
            @Override
            public void onTick(long l)
            {
                textViewTimer.setText(((int)l/1000)+"s");
            }

            @Override
            public void onFinish()
            {
                is_running=false;
                reset.setVisibility(View.VISIBLE);
                reset.setEnabled(true);
            }
        }.start();

        popQue();
    }

    @SuppressLint({"SetTextI18n", "LongLogTag"})
    public void popQue()
    {
        attempted++;
        Random random = new Random();
        int a = random.nextInt(100);
        int b = random.nextInt(100);
        int correctOption = random.nextInt(4);
        int ans = a + b;
        textViewQue.setText(a + "+" + b);

        for (int i = 0; i < gridLayout.getChildCount(); i++)
        {
            button = (Button) gridLayout.getChildAt(i);

            if(i!=correctOption)
            {
                int falseOption = random.nextInt(200);
                button.setText(Integer.toString(falseOption));
            }
            else
            {
                button.setText(Integer.toString(ans));
                ansTag = button.getTag().toString();
            }
        }

    }

    @SuppressLint("SetTextI18n")
    public void optionSelected(View view)
    {
        if(is_running)
        {
            button = (Button)view;
            if(button.getTag().toString().equals(ansTag))
            {
                //correct option
                score++;
            }
            textViewScore.setText(score+"/"+attempted);

            popQue();
        }
    }

    public void playAgain(View view)
    {
        reset.setVisibility(View.INVISIBLE);
        reset.setEnabled(false);
        timer.cancel();
        startGame();
    }

    public void start(View view)
    {
        button = (Button)view;
        button.setVisibility(View.GONE);
        startGame();
    }

    public void makeVisible()
    {
        textViewTimerHeading.setVisibility(View.VISIBLE);
        textViewScoreHeading.setVisibility(View.VISIBLE);
        textViewScore.setVisibility(View.VISIBLE);
        textViewTimer.setVisibility(View.VISIBLE);
        textViewQue.setVisibility(View.VISIBLE);
        gridLayout.setVisibility(View.VISIBLE);
    }

    public void makeInvisible()
    {
        textViewTimerHeading.setVisibility(View.INVISIBLE);
        textViewScoreHeading.setVisibility(View.INVISIBLE);
        textViewScore.setVisibility(View.INVISIBLE);
        textViewTimer.setVisibility(View.INVISIBLE);
        textViewQue.setVisibility(View.INVISIBLE);
        gridLayout.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTimerHeading = findViewById(R.id.textViewTimerHeading);
        textViewScoreHeading = findViewById(R.id.textViewScoreHeading);
        textViewScore = findViewById(R.id.textViewScore);
        textViewQue   = findViewById(R.id.textViewQue);
        textViewTimer = findViewById(R.id.textViewTimer);
        reset         = findViewById(R.id.reset);
        gridLayout    = findViewById(R.id.gridLayout);

        makeInvisible();
        reset.setVisibility(View.INVISIBLE);
        reset.setEnabled(false);
    }
}