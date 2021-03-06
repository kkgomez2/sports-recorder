package cs498.sportsrecorder;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StatInput extends AppCompatActivity {
    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_input);
        gameData = new GameData();
        SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm", Locale.US);
        gameData.setFilename("Game on " + sdf.format(new Date()));
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            setContentView(R.layout.activity_summary);
            //TextView textview = (TextView)findViewById(R.id.summary_text);
            //textview.setText(gameData.getSummary());
            TextView textview = (TextView)findViewById(R.id.summary_stats);
            textview.setText(gameData.getStatLine());
            textview = (TextView)findViewById(R.id.summary_timeline);
            textview.setText(gameData.getTimelineSummary());

        } else {
            setContentView(R.layout.activity_stat_input);
        }
    }

    @Override
    public void onBackPressed() {}

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
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.dialog_end_quarter, null);
        alert.setView(dialogView).
        setTitle("Enter current scores").
        setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                String score1 = ((EditText) dialogView.findViewById(R.id.score1)).getText().toString();
                String score2 = ((EditText) dialogView.findViewById(R.id.score2)).getText().toString();
                if (score1.trim().equals("")) {
                    score1 = "0";
                }
                if (score2.trim().equals("")) {
                    score2 = "0";
                }
                recordQuarter(score1, score2);
            }
        }).
        setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {}
        });
        alert.show();
    }

    private void recordQuarter(String score1, String score2) {
        gameData.endQuarter(score1, score2);
        Toast.makeText(this, "End of quarter recorded", Toast.LENGTH_SHORT).show();
        saveToFile();
        if(gameData.getQuarter() > 3){
            // Launch the Summary activity.
            Intent intent = new Intent(this, Summary.class);
            intent.putExtra(Summary.FILE_TO_LOAD, gameData.getFilename());
            startActivity(intent);
        }
    }

    private void saveToFile() {
        Log.d("Sports", "Saving to " + gameData.getFilename());

        try {
            FileOutputStream out = openFileOutput(gameData.getFilename(), Context.MODE_PRIVATE);
            String saveContent = gameData.getSaveContent();
            out.write(saveContent.getBytes());
            out.close();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void undo(View view){
        if (gameData.getTimeline().length > 0) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Undo last action?").
                setPositiveButton("OK", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                    recordUndo();
                }
            }).
            setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {}
            });
            alert.show();
        }
    }

    private void recordUndo() {
        gameData.undo();
        Toast.makeText(this, "Undid last action", Toast.LENGTH_SHORT).show();
        saveToFile();
    }

    // Share game summary by e-mail.
    public void share(View view) {}
}
