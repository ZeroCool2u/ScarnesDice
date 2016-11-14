package com.example.scarnesdice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.RunnableFuture;


//TODO: Finish timer system.
//TODO: Complete play logic. Currently, after losing first turn, you end up playing as the computer.

public class MainActivity extends AppCompatActivity {
    private enum Turn{
        USER, COMPUTER
    }

    private static final String scoreBoardFormat = "Your Score: %d Computer's Score: %d\nYour Turn Score: %d Computer Turn Score: %d";
    static int usersFullScore = 0;
    static int usersTurnScore = 0;
    static int computersFullScore = 0;
    static int computersTurnScore = 0;
    static Turn turn = Turn.USER;
    static Random rand = new Random();
/*    private Handler timerHandler = new Handler();
    private Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            computerTurn();
            if  (turn == Turn.COMPUTER && computersTurnScore < 20){
                timerHandler.postDelayed(this, 750);
            }else if( turn == Turn.COMPUTER){
                holdButton();
            }
        }
    };*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void clickHandler(View buttonView){
        String buttonID = getResources().getResourceEntryName(buttonView.getId());
        switch (buttonID) {
            case "button_roll":
                rollButton();
                break;
            case "button_hold":
                holdButton();
                break;
            case "button_reset":
                resetButton();
                break;
        }
    }

    public int rollDie(){
        return rand.nextInt(6) + 1;
    }

    protected void rollButton(){
        int newDiceFace = rollDie();
        ImageView diceImage = (ImageView) findViewById(R.id.dice_image);
        TextView scoreBoard = (TextView) findViewById(R.id.score_message);

        if (newDiceFace == 1) {
            diceImage.setImageResource(R.drawable.dice1);
            if(turn == Turn.USER){
                usersTurnScore = 0;
                turn = Turn.COMPUTER;
                computerTurn();
            }else{
                computersTurnScore = 0;
                turn = Turn.USER;
            }
            String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
            scoreBoard.setText(newScoreBoardText);
        }
        else if (newDiceFace == 2) {
            diceImage.setImageResource(R.drawable.dice2);
            if(turn == Turn.USER){
                usersTurnScore += 2;
            }else{
                computersTurnScore += 2;
            }
            String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
            scoreBoard.setText(newScoreBoardText);
        }
        else if (newDiceFace == 3) {
            diceImage.setImageResource(R.drawable.dice3);
            if(turn == Turn.USER){
                usersTurnScore += 3;
            }else{
                computersTurnScore += 3;
            }
            String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
            scoreBoard.setText(newScoreBoardText);
        }
        else if (newDiceFace == 4) {
            diceImage.setImageResource(R.drawable.dice4);
            if(turn == Turn.USER){
                usersTurnScore += 4;
            }else{
                computersTurnScore += 4;
            }
            String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
            scoreBoard.setText(newScoreBoardText);
        }
        else if (newDiceFace == 5) {
            diceImage.setImageResource(R.drawable.dice5);
            if(turn == Turn.USER){
                usersTurnScore += 5;
            }else{
                computersTurnScore += 5;
            }
            String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
            scoreBoard.setText(newScoreBoardText);
        }
        else {
            diceImage.setImageResource(R.drawable.dice6);
            if(turn == Turn.USER){
                usersTurnScore += 6;
            }else{
                computersTurnScore += 6;
            }
            String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
            scoreBoard.setText(newScoreBoardText);
        }
    }

    protected void holdButton(){
        if(turn == Turn.USER){
            usersFullScore += usersTurnScore;
            usersTurnScore = 0;
            turn = Turn.COMPUTER;
            computerTurn();
            turn = Turn.USER;
        }else{
            computersFullScore += computersTurnScore;
            computersTurnScore = 0;
            turn = Turn.USER;
        }
        TextView scoreBoard = (TextView) findViewById(R.id.score_message);
        String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
        scoreBoard.setText(newScoreBoardText);
    }

    protected void resetButton(){
        usersFullScore = 0;
        usersTurnScore = 0;
        computersFullScore = 0;
        computersTurnScore = 0;
        TextView scoreBoard = (TextView) findViewById(R.id.score_message);
        ImageView diceImage = (ImageView) findViewById(R.id.dice_image);
        diceImage.setImageResource(R.drawable.dice1);
        String newScoreBoardText = String.format(Locale.US,scoreBoardFormat,usersFullScore,computersFullScore,usersTurnScore,computersTurnScore);
        scoreBoard.setText(newScoreBoardText);
    }

    protected void computerTurn(){
        Button rollBtn = (Button) findViewById(R.id.button_roll);
        Button holdBtn = (Button) findViewById(R.id.button_hold);
        rollBtn.setEnabled(false);
        holdBtn.setEnabled(false);
        rollButton();
        rollBtn.setEnabled(true);
        holdBtn.setEnabled(true);
    }
}
