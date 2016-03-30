package cs498.sportsrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Called when the user clicks on the new game button. Launches the StatInput activity.
    public void newGame(View view){
        Log.d("Sports", "Launching new game.");
        Intent intent = new Intent(this, StatInput.class);
        startActivity(intent);
    }

    // Called when the user clicks on the load game button. Launches the LoadGame activity.
    public void loadGame(View view){
        Log.d("Sports", "Launching load game.");
        Intent intent = new Intent(this, LoadGameActivity.class);
        startActivity(intent);
    }
}
