package cs498.sportsrecorder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class LoadGameActivity extends AppCompatActivity {
    private ListView game_list;
    private ArrayAdapter<String> game_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);
        game_list = (ListView)findViewById(R.id.load_games_list);

        // See what games exist, and get their names.
        File dir = this.getFilesDir();
        File[] files = dir.listFiles();
        ArrayList<String> games = new ArrayList<String>();
        for (int i = 0; i < files.length; i++){
            String name = files[i].getName();
            if(name.contains("Game on")) {
                // One of our save files.
                games.add(name);
            }
        }
        Log.d("Sports", games.size() + " saved games found.");

        // Fill the game list with games.
        game_adapter = new ArrayAdapter<String>(this, R.layout.activity_load_game_listview, games);
        game_list.setAdapter(game_adapter);
    }

}
