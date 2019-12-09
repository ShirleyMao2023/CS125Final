package com.example.cs125final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

public class partThree extends AppCompatActivity {
    private int count = 0;
    private String[] story3 = {"Hmph! How dare these craven fools attempt to accost those who wish them no harm?",
            "I would ask you, my worthy companion, to stay out of harm’s way and take cover for now.",
            "I do not doubt your courage, but I have had a fair amount of experience in such fights and would not want to trouble you.",
            "It seems the bandits have me surrounded! I must admit my helplessness in this situation.",
            "Will you help me fight against these foes?"};
    private LinearLayout storyGroup3;
    private TextView storyText3;
    private TextView yes;
    private TextView no1;
    private TextView no2;
    private LinearLayout choiceGroup3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_part_three);

        storyGroup3 = findViewById(R.id.storyGroup3);
        storyText3 = findViewById(R.id.storyText3);

        storyText3.setText(story3[0]);

        choiceGroup3 = findViewById(R.id.choiceGroup3);
        yes = findViewById(R.id.yes);
        no1 = findViewById(R.id.no1);
        no2 = findViewById(R.id.no2);

        storyGroup3.setOnClickListener(v -> {
            count++;
            if (count < story3.length) {
                storyText3.setText(story3[count]);
            } else if (count == story3.length) {
                System.out.println("Going into choices");
                storyGroup3.setVisibility(View.GONE);
                choices();
            }
        });

        storyText3.setOnClickListener(v -> {
            count++;
            if (count < story3.length) {
                storyText3.setText(story3[count]);
            } else if (count == story3.length) {
                System.out.println("Going into choices");
                storyGroup3.setVisibility(View.GONE);
                choices();
            }
        });
    }

    public void choices() {
        System.out.println("Inside");
        choiceGroup3.setVisibility(View.VISIBLE);

        no1.setOnClickListener(v -> {
            ((Variables) this.getApplication()).addScore(0);
            startActivity(new Intent(this, End.class));
        });

        no2.setOnClickListener(v -> {
            ((Variables) this.getApplication()).addScore(1);
            startActivity(new Intent(this, End.class));
        });

        yes.setOnClickListener(v -> {
            ((Variables) this.getApplication()).addScore(2);
            startActivity(new Intent(this, End.class));
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
    }
}
