package cpsc2150.extendedTicTacToe;
import java.util.Scanner;


public class GameBoard extends AbsGameBoard implements IGameBoard {
    /**
     * This class is designed to help run the game by creating the board and initializing it with default
     * values, check spaces to see if a player can place their marker their, place the marker, check for
     * a Winner a few different ways (Horizontal, Vertical, and Diagonal), see if they tied/there was no winner
     *
     * @invariants: the index of the elements in the 2D array must be [0...number of rows entered - 1][0...number of
     * columns entered - 1]
     *
     */
    private char[][] ticTacToeBoard;
    private int numberOfColumns;
    private int numberOfRows;
    private int tokensNeeded;

    /**
     * @pre NONE
     * @post ticTacToeBoard = new char[][] AND [all blanks cells == ' '] AND tokensNeeded = userTokensNeeded
     * AND numberOfRows = userRows AND numberOfColumns = userColumns
     *
     */
    public GameBoard(int userRows, int userColumns, int userTokensNeeded) {
        int i = 0;
        int j = 0;

        numberOfRows = userRows;
        numberOfColumns = userColumns;
        tokensNeeded = userTokensNeeded;

        ticTacToeBoard = new char[numberOfRows][numberOfColumns];

        for (i = 0; i < numberOfRows; i++) { //loops through the whole board, assigning each position with a default
            //character of ' '
            for (j = 0; j < numberOfColumns; j++) {
                ticTacToeBoard[i][j] = ' ';
            }
        }

    }

    public int getNumRows() {
        return numberOfRows;
    }

    public int getNumColumns() {
        return numberOfColumns;
    }

    public int getNumToWin() {
        return tokensNeeded;
    }

    public void placeMarker(BoardPosition marker, char player) {
        int userRow = 0;
        int userColumn = 0;

        userRow = marker.getRow();
        userColumn = marker.getColumn();

        ticTacToeBoard[userRow][userColumn] = player; //"places the marker" of the past in player at the past in position
    }

    public char whatsAtPos(BoardPosition pos) {
        int userRow = 0;
        int userColumn = 0;

        userRow = pos.getRow();
        userColumn = pos.getColumn();

        return ticTacToeBoard[userRow][userColumn]; //just returns whatever character is at the past in position on the board

    }
}