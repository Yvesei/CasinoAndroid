package com.example.ouchta_nazih_glsid_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainGame extends AppCompatActivity{

    private TextView fortuneTextView;
    private TextView fortuneTextView2;
    private TextView dialog;
    private Button deBtn;
    private Button endBtn;
    private ImageView imageView;
    private TextView result;
    private int[] images = {
            R.drawable.dice1,
            R.drawable.dice2,
            R.drawable.dice3,
            R.drawable.dice4,
            R.drawable.dice5,
            R.drawable.dice6
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_game);

        fortuneTextView = findViewById(R.id.fortuneTextView);
        fortuneTextView2 = findViewById(R.id.fortuneTextView2);
        dialog = findViewById(R.id.dialog);
        deBtn = findViewById(R.id.deBtn);
        endBtn = findViewById(R.id.endBtn);
        result = findViewById(R.id.textView2);

        imageView = findViewById(R.id.imageView);
        // Get the fortune from the Intent
        String fortune = getIntent().getStringExtra("fortune");


        // Display the fortune
        if (fortune != null) {
            fortuneTextView.setText(fortune);
        }
        int randomFortune = getRandomNumber(10, 100);
        fortuneTextView2.setText(String.valueOf(randomFortune));
        deBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playGame();
            }
        });
        endBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Finish the current activity and redirect to login page
                Intent intent = new Intent(MainGame.this, MainActivity.class);
                startActivity(intent);
                finish(); // Optionally, call finish() if you want to remove this activity from the back stack
            }
        });
    }
    private int getRandomNumber(int min, int max) {
        Random random = new Random();
        return random.nextInt((max - min) + 1) + min;
    }


    private void playGame() {
        int randomRoll = getRandomNumber(1, 6);

        // Display the appropriate image
        imageView.setImageResource(images[randomRoll - 1]);

        int playerFortune = Integer.parseInt(fortuneTextView.getText().toString());
        int casinoFortune = Integer.parseInt(fortuneTextView2.getText().toString());

        if (randomRoll == 2 || randomRoll == 3) {
            playerFortune++;
            casinoFortune--;
            result.setText("Player wins!");
        } else {
            playerFortune--;
            casinoFortune++;
            result.setText("Casino wins!");
        }

        // Update fortunes
        fortuneTextView.setText(String.valueOf(playerFortune));
        fortuneTextView2.setText(String.valueOf(casinoFortune));

        // Check if game should end
        if (playerFortune == 0 || casinoFortune == 0) {
            endGame(playerFortune, casinoFortune);
        }
    }
    private void endGame(int playerFortune, int casinoFortune) {
        // Display game over message
        String message = playerFortune == 0 ? "Player's fortune is 0. Casino wins!" : "Casino's fortune is 0. Player wins!";
        dialog.setText(message);
        deBtn.setEnabled(false);
    }

}



