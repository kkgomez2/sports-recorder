package cs498.sportsrecorder;

import android.graphics.Point;
import android.util.Log;

import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
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

    // Constructor. Creates a new GameData object for writing to.
    public GameData(){
        initializeEmpty();
    }

    // Constructor. Creates a GameData object to read from. Takes in the string of the content of
    // a save file.
    public GameData(String savedData){
        initializeEmpty();
        load(savedData);
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
        gameEvents[GameEvent.QUARTER_END.ordinal()] += 1;
        // TODO: Save quarter scores.
    }

    public int getQuarter(){
        return gameEvents[GameEvent.QUARTER_END.ordinal()];
    }

    public void undo(){
        // TODO.
    }

    // Returns a string that represents the internal data.
    public String getSaveContent(){
        String save = "";
        // First game the game events.
        for(int i = 0; i < gameEvents.length; i++){
            save += gameEvents[i] + " ";
        }

        // Save the quarter scores.
        for(int i = 0; i < MAX_QUARTERS; i++){
            save += quarterScores[i].x + " " + quarterScores[i].y + " ";
        }

        // Save the timeline.
        for(int i = 0; i < timeline.size(); i++){
            save += timeline.get(i).ordinal() + " ";
        }

        return save;
    }

    // Loads the data in from the given string.
    private void load(String loadData){
        String[] splitData = loadData.split(" ");

        // Check if valid.
        if (gameEvents.length + (2 * MAX_QUARTERS) > splitData.length){
            Log.d("Sports", "ERROR: Load data was not long enough. Skipping. Load data: " + loadData);
            return;
        }

        // Load the game events.
        for(int i = 0; i < gameEvents.length; i++){
            //save += gameEvents[i] + " ";
            gameEvents[i] = Integer.parseInt(splitData[i]);
        }

        // Load the quarter scores.
        for(int i = 0; i < MAX_QUARTERS; i++){
            //save += quarterScores[i].x + " " + quarterScores[i].y + " ";
            int index = gameEvents.length + 2*i;
            quarterScores[i].x = Integer.parseInt(splitData[index]);
            quarterScores[i].y = Integer.parseInt(splitData[index + 1]);
        }

        // Load the timeline.
        int remaining = splitData.length - gameEvents.length - (2 * MAX_QUARTERS);
        for(int i = 0; i < timeline.size(); i++){
            //save += timeline.get(i).ordinal() + " ";
            int index = gameEvents.length + (2 * MAX_QUARTERS) + i;
            int ordinal = Integer.parseInt(splitData[index]);
            timeline.set(i, GameEvent.values()[ordinal]);
        }
    }

    // Gets a human-readable summary of the data.
    public String getSummary(){
        String ret = "Summary.\n \nStat Line:\n";
        ret += getStatLine();
        ret += " \nTimeline:\n";
        String[] t = getTimeline();
        for(int i = 0; i < t.length; i++){
            ret += t[i] + "\n";
        }
        return ret;
    }

    // Returns the overall statistics as a stat line string.
    public String getStatLine(){
        int made = gameEvents[GameEvent.FT_MADE.ordinal()];
        String freeString = "Free Throws: " + made + " / "  +
                            (made + gameEvents[GameEvent.FT_MISS.ordinal()]);

        made = gameEvents[GameEvent.TWO_PT_MADE.ordinal()];
        String twoString = "Two Pointers: " + made + " / "  +
                           (made + gameEvents[GameEvent.TWO_PT_MISS.ordinal()]);

        made = gameEvents[GameEvent.THREE_PT_MADE.ordinal()];
        String threeString = "Three Pointers: " + made + " / "  +
                             (made + gameEvents[GameEvent.THREE_PT_MISS.ordinal()]);

        String assist = "Assists: " + gameEvents[GameEvent.ASSIST.ordinal()];
        String rebound = "Rebounds: " + gameEvents[GameEvent.REBOUND.ordinal()];
        String block = "Blocks: " + gameEvents[GameEvent.BLOCK.ordinal()];
        String steal = "Steals: " + gameEvents[GameEvent.STEAL.ordinal()];
        return freeString + "\n" + twoString + "\n" + threeString + "\n" + assist + "\n" + rebound +
                "\n" + block + "\n" + steal + "\n";
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
