package com.example.cs125final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class partOne extends AppCompatActivity {
    private int count = 0;
    final private String[] story = {"Hail, fair traveler! May I be given the honor of knowing your name?",
        "A truly beautiful name. I am journeying to the prince’s castle, and it appears you are traveling to the city as well.",
        "I had not thought I would encounter another on this path tonight. These woods have grown dangerous as of late.",
        "Come, my comrade, let us travel together!",
        "-------  3 hours later  -------",
        "Oh dear, it seems I have lost my map, and we have reached a fork in the road.",
        "Which path shall we take?"};
    private TextView name;
    private Button button;
    private String user;
    private LinearLayout storyStuff;
    private TextView storyText;
    private TextView choiceA;
    private TextView choiceB;
    private TextView choiceC;
    private LinearLayout choiceGroup;
    final ForegroundColorSpan pink = new ForegroundColorSpan(Color.rgb(255,31,70));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_part_one);

        storyStuff = findViewById(R.id.storyStuff);
        storyText = findViewById(R.id.StoryText);
        name = findViewById(R.id.name);
        button = findViewById(R.id.button);
        storyText.setText(story[0]);
        choiceGroup = findViewById(R.id.choiceGroup);
        choiceA = findViewById(R.id.choice1);
        choiceB = findViewById(R.id.choice2);
        choiceC = findViewById(R.id.choice3);

        storyStuff.setOnClickListener(v -> {
            if (count == 0) {
                name.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                storyStuff.setVisibility(View.GONE);
                nameInput();
                count++;
            } else if (count < story.length) {
                storyText.setText(story[count]);
                count++;
            } else if (count == story.length) {
                storyStuff.setVisibility(View.GONE);
                choices();
                count++;
            } else if (count > story.length) {
                startActivity(new Intent(this, partTwo.class));
            }
            //perform your action here
        });

        storyText.setOnClickListener(v -> {
            if (count == 0) {
                name.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                storyStuff.setVisibility(View.GONE);
                nameInput();
                count++;
            } else if (count < story.length) {
                storyText.setText(story[count]);
                count++;
            } else if (count == story.length) {
                storyStuff.setVisibility(View.GONE);
                choices();
                count++;

            } else if (count > story.length) {
                startActivity(new Intent(this, partTwo.class));
            }
        });
    }

    public void nameInput() {
        name.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String nameInput = name.getText().toString().trim();
                button.setEnabled(!nameInput.isEmpty());
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    public void userName(View view) {
        user = name.getText().toString();
        ((Variables) this.getApplication()).setUserName(user);

        name.setVisibility(View.GONE);
        button.setVisibility(View.GONE);
        storyStuff.setVisibility(View.VISIBLE);

        Spannable wordToSpan = new SpannableString("Ah, " + user + "! " + story[count]);
        wordToSpan.setSpan(pink, 4, (4 + user.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        storyText.setText(wordToSpan);

        count++;
    }

    public void choices() {
        choiceGroup.setVisibility(View.VISIBLE);

        choiceA.setOnClickListener(v -> {
            choiceGroup.setVisibility(View.GONE);
            storyText.setText("Not very decisive, are you? Then we shall take the right path.");
            ((Variables) this.getApplication()).addScore(0);
        });

        choiceB.setOnClickListener(v -> {
            choiceGroup.setVisibility(View.GONE);
            storyText.setText("Hmm, that is an interesting choice, yet methinks we ought to take the right path instead.");
            ((Variables) this.getApplication()).addScore(1);
        });

        choiceC.setOnClickListener(v -> {
            choiceGroup.setVisibility(View.GONE);
            Spannable wordToSpan = new SpannableString("Well said! You have shown great wisdom, " + user + ". Onto the right path we go!");
            wordToSpan.setSpan(pink, 39, (40 + user.length()), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            storyText.setText(wordToSpan);
            ((Variables) this.getApplication()).addScore(2);
        });
        storyStuff.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        startActivity(intent);
        int pid = android.os.Process.myPid();
        android.os.Process.killProcess(pid);
//        finish();
//        moveTaskToBack(true);
    }
}
