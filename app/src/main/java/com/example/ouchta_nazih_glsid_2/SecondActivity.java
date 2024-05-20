package com.example.ouchta_nazih_glsid_2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputEditText;

public class SecondActivity extends AppCompatActivity {
    private TextInputEditText fortuneEditText;
    private Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        fortuneEditText = findViewById(R.id.fortune);
        button2 = findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fortune = fortuneEditText.getText().toString().trim();

                if (!fortune.isEmpty()) {
                    // Pass the fortune to the next activity
                    Intent intent = new Intent(SecondActivity.this, MainGame.class);
                    intent.putExtra("fortune", fortune);
                    startActivity(intent);
                } else {
                    fortuneEditText.setError("Please enter your fortune");
                }
            }
        });
    }
}

