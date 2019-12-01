package com.example.cs125final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class End extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_end);

        TextView endText = findViewById(R.id.endingText);
        LinearLayout endPic = findViewById(R.id.endingPic);
        String name = ((Variables) this.getApplication()).getUserName();

        int score = ((Variables) this.getApplication()).getScore();

        if (score == 6) {
            endText.setText("Those bandits have been thoroughly defeated. You have proven to be a loyal and valiant subject. " +
                    "For rescuing me, your reward will be to marry me, Prince Meow!");
            endPic.setBackgroundResource(R.drawable.prince_meow);

        } else if (score > 2) {
            endText.setText("“We have barely escaped from those bandits. " +
                    "You are a good subject of mine, " + name
                    + ", though not a great one. Take this pendant as a token of appreciation from me, Prince Meow. Farewell!");
            endPic.setBackgroundResource(R.drawable.cat);

        } else {
            endText.setText("It is as I, Prince Meow, suspected - you are a traitor! For your betrayal against your kingdom, your punishment is death!");
            endPic.setBackgroundResource(R.drawable.sword);

        }
    }

    public void closeApp(View view) {
        finish();
        moveTaskToBack(true);
    }
    public void restartApp(View view) {

        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);
    }
}
