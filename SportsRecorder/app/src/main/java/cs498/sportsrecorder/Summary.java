package cs498.sportsrecorder;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Summary extends AppCompatActivity {
    public final static String FILE_TO_LOAD = "cs498.sportsrecorder.FILE_TO_LOAD";

    private GameData gameData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        // Get the file to load.
        Intent intent = getIntent();
        String filename = intent.getStringExtra(FILE_TO_LOAD);

        // Feed it to the GameData object.
        String content = "";
        try {
            File file = new File(this.getFilesDir(), filename);
            BufferedReader reader = new BufferedReader(new FileReader(file));
            // All saved on one line.
            content = reader.readLine();
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        gameData = new GameData(content);

        // Put the data in the textview.
        TextView textview = (TextView)findViewById(R.id.summary_stats);
        textview.setText(gameData.getStatLine());
        textview = (TextView)findViewById(R.id.summary_timeline);
        textview.setText(gameData.getTimelineSummary());

    }

    // Share game summary by e-mail.
    public void share(View view) {
        gameData.share();
    }
}
