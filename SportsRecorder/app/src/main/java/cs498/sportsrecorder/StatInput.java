package cs498.sportsrecorder;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StatInput extends AppCompatActivity {
    GameData data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat_input);
        data = new GameData();

    }

    public void addMadeFreeThrow(View view){
        data.addMadeFreeThrow();
    }

    public void addMissedFreeThrow(View view){
        data.addMissedFreeThrow();
    }

    public void addMadeTwoPoint(View view){
        data.addMadeTwoPoint();
    }

    public void addMissedTwoPoint(View view){
        data.addMissedTwoPoint();
    }

    public void addMadeThreePoint(View view){
        data.addMadeThreePoint();
    }

    public void addMissedThreePoint(View view){
        data.addMissedThreePoint();
    }

    public void addRebound(View view){
        data.addRebound();
    }

    public void addAssist(View view) {
        data.addAssist();
    }

    public void addSteal(View view){
        data.addSteal();
    }

    public void addBlock(View view){
        data.addBlock();
    }

    public void endQuarter(View view){
        data.endQuarter();
        if(data.getQuarter() > 4){
            // Game is over, save the game.
            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy hh:mm", Locale.US);
            String filename = "Game on " + sdf.format(new Date());
            Log.d("Sports", "Saving to " + filename);

            try {
                FileOutputStream out = openFileOutput(filename, Context.MODE_PRIVATE);
                String saveContent = data.getSaveContent();
                out.write(saveContent.getBytes());
                out.close();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void undo(View view){
        data.undo();
    }
}
