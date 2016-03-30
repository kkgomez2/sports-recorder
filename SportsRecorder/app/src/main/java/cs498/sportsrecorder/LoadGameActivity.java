package cs498.sportsrecorder;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;

public class LoadGameActivity extends AppCompatActivity {
    private ListView game_list;
    private ArrayAdapter<String> game_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_game);
        game_list = (ListView)findViewById(R.id.load_games_list);

        // Fill the game list with games. TODO: Remove.
        String[] games = {"First Game", "Second Game", "Third Game"};
        game_adapter = new ArrayAdapter<String>(this, R.layout.activity_load_game_listview, games);
        game_list.setAdapter(game_adapter);

    }

}
