package com.example.cs125final;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

public class MainActivity extends AppCompatActivity {
    private String fact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        ((Variables) this.getApplication()).resetScore();

        setContentView(R.layout.activity_main);

        Intent service = new Intent(this, MusicService.class);
        ((Variables) this.getApplication()).setMusicService(service);

        startService(service);

        TextView catFact = findViewById(R.id.catFact);
        catFact.bringToFront();

        RequestQueue requestQueue;
        requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://catfact.ninja/facts?limit=1", null, response -> {
            try {
                fact = setFact(response);
                catFact.setText(fact);
                Log.d("myapp", "The response is " + response.getString("data"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
                }, error -> Log.d("myapp", "Something went wrong"));

        requestQueue.add(jsonObjectRequest);
    }

    public void closeApplication(View view) {
        stopService(((Variables) this.getApplication()).getMusicService());
        finish();
        moveTaskToBack(true);

    }

    public void beginApplication(View view) {

        startActivity(new Intent(this, partOne.class));
    }

    /**
     * Populates the games lists UI with data retrieved from the server.
     * @param result parsed JSON from the server
     */
    private String setFact(final JSONObject result) {
        try {
            JSONArray gameArray = result.getJSONArray("data");
            JSONObject object = gameArray.getJSONObject(0);
            fact = object.getString("fact");
            return fact;
        } catch (JSONException e) {
            Log.e("MYAPP", "unexpected JSON exception", e);
        }
        return null;
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
