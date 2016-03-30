package cs498.sportsrecorder;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

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
        data.addMadeFreeThrow();
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
    }

    public void undo(View view){
        data.undo();
    }
}
