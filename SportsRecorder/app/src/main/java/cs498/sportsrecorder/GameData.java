package cs498.sportsrecorder;

import java.util.ArrayList;

public class GameData {
    private enum GameEvent {
        FT_MADE,
        FT_MISS,
        TWO_PT_MADE,
        TWO_PT_MISS,
        THREE_PT_MADE,
        THREE_PT_MISS,
        REBOUND,
        BLOCK,
        STEAL,
        ASSIST,
        QUARTER_END
    }

    private ArrayList<GameEvent> timeline;
    private int[] gameEvents;

    public GameData(){
        timeline = new ArrayList<GameEvent>();
        gameEvents = new int[GameEvent.values().length];
        for(int i = 0; i < gameEvents.length; i++){
            gameEvents[i] = 0;
        }
    }

    public void addMadeFreeThrow(){
        gameEvents[GameEvent.FT_MADE.ordinal()] += 1;
        timeline.add(GameEvent.FT_MADE);
    }

    public void addMissedFreeThrow(){
        gameEvents[GameEvent.FT_MISS.ordinal()] += 1;
        timeline.add(GameEvent.FT_MISS);
    }

    public void addMadeTwoPoint(){
        gameEvents[GameEvent.TWO_PT_MADE.ordinal()] += 1;
        timeline.add(GameEvent.TWO_PT_MADE);
    }

    public void addMissedTwoPoint(){
        gameEvents[GameEvent.TWO_PT_MISS.ordinal()] += 1;
        timeline.add(GameEvent.TWO_PT_MISS);
    }

    public void addMadeThreePoint(){
        gameEvents[GameEvent.THREE_PT_MADE.ordinal()] += 1;
        timeline.add(GameEvent.THREE_PT_MADE);
    }

    public void addMissedThreePoint(){
        gameEvents[GameEvent.THREE_PT_MISS.ordinal()] += 1;
        timeline.add(GameEvent.THREE_PT_MISS);
    }

    public void addRebound(){
        gameEvents[GameEvent.REBOUND.ordinal()] += 1;
        timeline.add(GameEvent.REBOUND);
    }

    public void addAssist() {
        gameEvents[GameEvent.ASSIST.ordinal()] += 1;
        timeline.add(GameEvent.ASSIST);
    }

    public void addSteal(){
        gameEvents[GameEvent.STEAL.ordinal()] += 1;
        timeline.add(GameEvent.STEAL);
    }

    public void addBlock(){
        gameEvents[GameEvent.BLOCK.ordinal()] += 1;
        timeline.add(GameEvent.BLOCK);
    }

    public void endQuarter(){
        // TODO.
    }

    public void undo(){
        // TODO.
    }

    // Save the data to a file. Returns the filename.
    public String save(){
        return "FILE"; //TODO
    }

}
