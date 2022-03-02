package cpsc2150.extendedTicTacToe;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class GameBoardMem extends AbsGameBoard implements IGameBoard {
    /**
     * This class is designed to help run the game by creating the board and initializing it with default
     * values, check spaces to see if a player can place their marker their, place the marker, check for
     * a Winner a few different ways (Horizontal, Vertical, and Diagonal), see if they tied/there was no winner
     *
     * @invariants: [there can't be repeat key values] AND [there can't be any repeat BoardPositions within the List
     * for any particular key]
     *
     */
    private Map<Character, List<BoardPosition>> ticMapToeBoard = new HashMap<Character, List<BoardPosition>>();
    private int numberOfColumns;
    private int numberOfRows;
    private int tokensNeeded;

    /**
     * @pre NONE
     * @post numberOfRows = userRows AND numberOfColumns AND tokensNeeded = userTokensNeeded
     */
    public GameBoardMem(int userRows, int userColumns, int userTokensNeeded) {
        numberOfRows = userRows;
        numberOfColumns = userColumns;
        tokensNeeded = userTokensNeeded;
    }

    public int getNumColumns() {
        return numberOfColumns;
    }

    public int getNumRows() {
        return numberOfRows;
    }

    public int getNumToWin() {
        return tokensNeeded;
    }

    public void placeMarker(BoardPosition marker, char player) {
        if(!ticMapToeBoard.containsKey(player)) { //checks first to see if the player exists within the map and if not
            //adds them to the map
            ticMapToeBoard.put(player, new ArrayList<>());
        }
        if(!isPlayerAtPos(marker, player)) { //if the "marker" doesn't already exist at the key in the map, places the
            //marker in the List of BoardPositions for the key
            ticMapToeBoard.get(player).add(marker);
        }

    }

    @Override
    public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(!ticMapToeBoard.containsKey(player)) { //first checks to make sure that a valid key has been passed in
            return false;
        }
        for(BoardPosition temporaryPos : ticMapToeBoard.get(player)) { //loops through the map/board at the specified key
            if(temporaryPos.equals(pos)) { //returns true if the passed in player has a marker at the past in position
                return true;
            }
        }
        return false; //returns false if the player didn't place a marker at the past in position
    }

    public char whatsAtPos(BoardPosition pos) {
        for(HashMap.Entry<Character, List<BoardPosition>> temporaryMap : ticMapToeBoard.entrySet()) {
            if(isPlayerAtPos(pos, temporaryMap.getKey())) { //checks to see if the current key contains the past in position
                return temporaryMap.getKey(); //returns key that holds the past in position
            }
        }
        return ' '; //returns a blank if nothing was at the past in position
    }

}
