package com.example.cs125final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button begin = findViewById(R.id.begin);



    }
    public void closeApplication(View view) {
        finish();
        moveTaskToBack(true);
    }
    public void beginApplication(View view) {
        startActivity(new Intent(this, partOne.class));
    }
}
