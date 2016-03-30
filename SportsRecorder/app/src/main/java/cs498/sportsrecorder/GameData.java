package cs498.sportsrecorder;

import android.graphics.Point;

import java.util.ArrayList;
import java.util.PriorityQueue;

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

    // Stores the events that occured.
    private ArrayList<GameEvent> timeline;
    private int[] gameEvents;

    private static int MAX_QUARTERS = 10;
    private Point[] quarterScores;
    private int currentQuarter;

    public GameData(){
        initializeEmpty();
    }

    public GameData(String savedGameFilename){
        initializeEmpty();

        // TODO: Read in file.
    }

    public void initializeEmpty(){
        timeline = new ArrayList<GameEvent>();
        gameEvents = new int[GameEvent.values().length];
        for(int i = 0; i < gameEvents.length; i++){
            gameEvents[i] = 0;
        }
        quarterScores = new Point[MAX_QUARTERS];
        for(int i = 0; i < MAX_QUARTERS; i++){
            Point zero = new Point(0,0);
            quarterScores[i] = zero;
        }
        currentQuarter = 0;
    }

    public void addMadeFreeThrow(){
        gameEvents[GameEvent.FT_MADE.ordinal()] += 1;
        timeline.add(GameEvent.FT_MADE);
    }

    public int getMadeFreeThrows(){
        return gameEvents[GameEvent.FT_MADE.ordinal()];
    }

    public void addMissedFreeThrow(){
        gameEvents[GameEvent.FT_MISS.ordinal()] += 1;
        timeline.add(GameEvent.FT_MISS);
    }

    public int getMissedFreeThrows(){
        return gameEvents[GameEvent.FT_MISS.ordinal()];
    }

    public void addMadeTwoPoint(){
        gameEvents[GameEvent.TWO_PT_MADE.ordinal()] += 1;
        timeline.add(GameEvent.TWO_PT_MADE);
    }

    public int getMadeTwoPoints(){
        return gameEvents[GameEvent.TWO_PT_MADE.ordinal()];
    }

    public void addMissedTwoPoint(){
        gameEvents[GameEvent.TWO_PT_MISS.ordinal()] += 1;
        timeline.add(GameEvent.TWO_PT_MISS);
    }

    public int getMissedTwoPoints(){
        return gameEvents[GameEvent.TWO_PT_MISS.ordinal()];
    }

    public void addMadeThreePoint(){
        gameEvents[GameEvent.THREE_PT_MADE.ordinal()] += 1;
        timeline.add(GameEvent.THREE_PT_MADE);
    }

    public int getMadeThreePoints(){
        return gameEvents[GameEvent.THREE_PT_MADE.ordinal()];
    }

    public void addMissedThreePoint(){
        gameEvents[GameEvent.THREE_PT_MISS.ordinal()] += 1;
        timeline.add(GameEvent.THREE_PT_MISS);
    }

    public int getMissedThreePoints(){
        return gameEvents[GameEvent.THREE_PT_MISS.ordinal()];
    }

    public void addRebound(){
        gameEvents[GameEvent.REBOUND.ordinal()] += 1;
        timeline.add(GameEvent.REBOUND);
    }

    public int getRebounds(){
        return gameEvents[GameEvent.REBOUND.ordinal()];
    }

    public void addAssist() {
        gameEvents[GameEvent.ASSIST.ordinal()] += 1;
        timeline.add(GameEvent.ASSIST);
    }

    public int getAssists(){
        return gameEvents[GameEvent.ASSIST.ordinal()];
    }

    public void addSteal(){
        gameEvents[GameEvent.STEAL.ordinal()] += 1;
        timeline.add(GameEvent.STEAL);
    }

    public int getSteals(){
        return gameEvents[GameEvent.STEAL.ordinal()];
    }

    public void addBlock(){
        gameEvents[GameEvent.BLOCK.ordinal()] += 1;
        timeline.add(GameEvent.BLOCK);
    }

    public int getBlocks(){
        return gameEvents[GameEvent.BLOCK.ordinal()];
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

    // Loads the data in 'filename' and fills the internal data structures.
    private void load(String filename){

    }

    // Returns the timeline as an array of strings.
    public String[] getTimeline(){
        String[] ret = new String[timeline.size()];
        for(int i = 0; i < timeline.size(); i++){
            ret[i] = getEventName(timeline.get(i));
        }
        return ret;
    }

    private String getEventName(GameEvent e){
        switch (e){
            case FT_MADE:
                return "Free Throw Made.";
            case FT_MISS:
                return "Free Throw Missed.";
            case TWO_PT_MADE:
                return "Two Pointer Made.";
            case TWO_PT_MISS:
                return "Two Pointer Missed.";
            case THREE_PT_MADE:
                return "Three Pointer Made";
            case THREE_PT_MISS:
                return "Three Pointer Missed.";
            case REBOUND:
                return "Rebound.";
            case BLOCK:
                return "Block.";
            case STEAL:
                return "Steal.";
            case ASSIST:
                return "Assist.";
            case QUARTER_END:
                return "Quarter Ended.";
            default:
                return "ERROR: Unknown event.";
        }
    }

}
