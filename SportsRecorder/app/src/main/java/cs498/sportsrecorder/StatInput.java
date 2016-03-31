package cs498.sportsrecorder;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StatInput extends AppCompatActivity {
    private GameData gameData;
    private String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_input);
        gameData = new GameData();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm", Locale.US);
        filename = "Game on " + sdf.format(new Date());
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_summary);
            TextView textview = (TextView)findViewById(R.id.summary_text);
            textview.setText(gameData.getSummary());

        } else {
            setContentView(R.layout.activity_stat_input);
        }
    }

    public void addMadeFreeThrow(View view){
        gameData.addMadeFreeThrow();
        Toast.makeText(this, "Made free throw recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addMissedFreeThrow(View view){
        gameData.addMissedFreeThrow();
        Toast.makeText(this, "Missed free throw recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addMadeTwoPoint(View view){
        gameData.addMadeTwoPoint();
        Toast.makeText(this, "Made field goal recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addMissedTwoPoint(View view){
        gameData.addMissedTwoPoint();
        Toast.makeText(this, "Missed field goal recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addMadeThreePoint(View view){
        gameData.addMadeThreePoint();
        Toast.makeText(this, "Made three pointer recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addMissedThreePoint(View view){
        gameData.addMissedThreePoint();
        Toast.makeText(this, "Missed three pointer recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addRebound(View view){
        gameData.addRebound();
        Toast.makeText(this, "Rebound recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addAssist(View view) {
        gameData.addAssist();
        Toast.makeText(this, "Assist recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addSteal(View view){
        gameData.addSteal();
        Toast.makeText(this, "Steal recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void addBlock(View view){
        gameData.addBlock();
        Toast.makeText(this, "Block recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    public void endQuarter(View view){
        gameData.endQuarter();
        Toast.makeText(this, "End of quarter recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
        if(gameData.getQuarter() > 3){
            // Game is over (Past 4th quarter, zero based), save the game.
            /*SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm", Locale.US);
            String filename = "Game on " + sdf.format(new Date());
            Log.d("Sports", "Saving to " + filename);

            try {
                FileOutputStream out = openFileOutput(filename, Context.MODE_PRIVATE);
                String saveContent = gameData.getSaveContent();
                out.write(saveContent.getBytes());
                out.close();
            } catch (Exception e){
                e.printStackTrace();
            }*/

            // Launch the Summary activity.
            Intent intent = new Intent(this, Summary.class);
            intent.putExtra(Summary.FILE_TO_LOAD, filename);
            startActivity(intent);
        }
    }

    private void saveToFile() {
        Log.d("Sports", "Saving to " + filename);

        try {
            FileOutputStream out = openFileOutput(filename, Context.MODE_PRIVATE);
            String saveContent = gameData.getSaveContent();
            out.write(saveContent.getBytes());
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void undo(View view){
        gameData.undo();
        Toast.makeText(this, "Undid last action", Toast.LENGTH_SHORT).show();
        saveToFile();
    }
}
