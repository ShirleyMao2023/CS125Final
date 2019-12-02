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

public class partTwo extends AppCompatActivity {
    private int count = 0;
    final private String[] story2 = {"It has been long indeed since I have travelled these roads. I am quite pleased to have your company, ",
            "-------  1 hour later  -------",
            "Does it not appear a bit strange in this area of the woods? It feels as though someone may be watching us.",
            "Why, and if I look into those trees up ahead, it looks as though there are some men covered in blood!",
            "Oh my, it seems those ruffians are ready to attack us. Fear not, for I shall defend us!",
            "Which weapon should I use?"};
    private LinearLayout storyGroup;
    private TextView storyText2;
    private TextView dagger;
    private TextView sword;
    private TextView nothing;
    private LinearLayout choiceGroup2;
    final ForegroundColorSpan pink = new ForegroundColorSpan(Color.rgb(255,31,70));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_part_two);

        storyGroup = findViewById(R.id.StoryPt2);
        storyText2 = findViewById(R.id.StoryText2);
        String user = ((Variables) this.getApplication()).getUserName();

        Spannable wordToSpan = new SpannableString(story2[0] + user + ".");
        wordToSpan.setSpan(pink, story2[0].length(), story2[0].length() + user.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        storyText2.setText(wordToSpan);

        choiceGroup2 = findViewById(R.id.choiceGroup2);
        dagger = findViewById(R.id.Dagger);
        sword = findViewById(R.id.Sword);
        nothing = findViewById(R.id.NoWeapon);

        storyGroup.setOnClickListener(v -> {
            count++;
            if (count < story2.length) {
                storyText2.setText(story2[count]);
            } else if (count == story2.length) {
                System.out.println("Going into choices");
                storyGroup.setVisibility(View.GONE);
                choices();
            } else if (count > story2.length) {
                startActivity(new Intent(this, partThree.class));
            }
        });

        storyText2.setOnClickListener(v -> {
            count++;
            if (count < story2.length) {
                storyText2.setText(story2[count]);
            } else if (count == story2.length) {
                System.out.println("Going into choices");
                storyGroup.setVisibility(View.GONE);
                choices();
            } else if (count > story2.length) {
                startActivity(new Intent(this, partThree.class));
            }
        });
    }

    public void choices() {
        System.out.println("Inside");
        choiceGroup2.setVisibility(View.VISIBLE);

        nothing.setOnClickListener(v -> {
            choiceGroup2.setVisibility(View.GONE);
            storyText2.setText("That is a bit odd, no? A sword works quite a bit better against bandits than diplomacy does.");
            ((Variables) this.getApplication()).addScore(0);
        });

        dagger.setOnClickListener(v -> {
            choiceGroup2.setVisibility(View.GONE);
            storyText2.setText("That is a good choice, but it seems more the weapon of an assassin. I shall be honorable in my dealings and use my sword.");
            ((Variables) this.getApplication()).addScore(1);
        });

        sword.setOnClickListener(v -> {
            choiceGroup2.setVisibility(View.GONE);
            storyText2.setText("Aye, the sword makes the man, indeed! My trusty sword shall taste the blood of these cowards today.");
            ((Variables) this.getApplication()).addScore(2);
        });
        storyGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void onBackPressed() {
        finish();
        moveTaskToBack(true);
    }
}
