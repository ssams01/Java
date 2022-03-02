package cpsc2150.extendedTicTacToe;
public abstract class AbsGameBoard implements IGameBoard{

    /**
     *@pre NONE
     *
     * @post [the board has been converted to a string through the use of the number of columns and number of rows
     * entered, along with the use of the '|' character in between each] AND [The game board is unchanged]
     *
     *
     * @return [the board converted to a string]
     *
     * */
    @Override
    public String toString() {
        int i = 0;
        int j = 0;
        int k = 0;
        int nine = 9; //for the next three variables: just used to help lower the number of magic numbers
        int eight = 8;
        int zero = 0;
        int numberOfColumns = getNumColumns();
        int numberOfRows = getNumRows();
        String board = "  "; //what will hold the string representation of our board

        board += (numberOfColumns > nine) ? " " : "";
        for (k = 0; k <= numberOfColumns - 1; k++) {
            if ((k >= zero) && (k <= nine)) {
                board += " ";
            }
            board += k + "|";
        }
        board += (numberOfRows > nine) ? "\n 0|" : "\n0|";
        for (i = 0; i <= numberOfRows - 1; i++) {
            for (j = 0; j <= numberOfColumns - 1; j++) {
                BoardPosition temporaryBoardPosition = new BoardPosition(i,j);
                board += whatsAtPos(temporaryBoardPosition);
                board += " |";

                if (j == getNumColumns() - 1 && i < getNumRows() - 1) {
                    board += "\n";
                    board += ((eight >= i) && (i >= zero) && (getNumRows() > nine)) ? " " : "";
                    board += (i + 1) + "|";
                }

            }
        }
        board += "\n"; //adds a new line character after the board has been constructed in order to match formatting
        //of output on project instructions

        return board;
    }
}
