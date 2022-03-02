package cpsc2150.extendedTicTacToe;
import java.util.Scanner;

public class BoardPosition {
    /**
     *@invariants: rowPosition <= [number of Rows entered - 1] AND columnPosition <= [number of Rows entered - 1]
     *
     */
    private int rowPosition;
    private int columnPosition;

    /**
     *@pre 0 <= row <= [number of rows entered by user - 1] AND 0 <= column <= [number of columns entered by user - 1]
     *
     * @post rowPosition AND columnPosition are initialized with
     * values
     *
     * @return NONE
     *
     * @param row represents the row position
     * @param column represents the column position
     */
    public BoardPosition(int row, int column) {
        this.columnPosition = column;
        this.rowPosition = row;
    }

    /**
     *@pre NONE
     *
     * @post getColumn = columnPosition
     *
     * @return the column position
     *
     */
    public int getColumn() {
        return columnPosition;
    }

    /**
     *@pre NONE
     *
     * @post getRow() = rowPosition
     *
     * @return the row position
     *
     */
    public int getRow() {
        return rowPosition;
    }

    /**
     *@pre NONE
     *
     * @post [the value of the row nor the column is changed, it's just now in the format of a String]
     *
     * @return [the BoardPosition formatted as a String]
     *
     * */
    @Override
    public String toString() {
        return "<" + getRow() + ">,<" + getColumn() + ">";
    }

    /**
     *@pre has to be a valid “BoardPosition”
     *
     * @post equals() = true [if the current column and row values match the passed column and row values]
     * AND false [if not]
     *
     * @return true if equal to “BoardPosition”, AND false if not
     *
     */
    public boolean equals(Object boardPosition) {
        BoardPosition temporaryBoardPosition;

        if(!(boardPosition instanceof BoardPosition)) { //first checks to make sure the passed in object is even a
            //BoardPosition and if not returns false
            return false;
        }

        temporaryBoardPosition = (BoardPosition) boardPosition;

        //compares the current value of both the Row and Column with the value of the Row and Column of the passed in position and
        //returns the result
        return(this.getRow() == temporaryBoardPosition.getRow()) && (this.getColumn() == temporaryBoardPosition.getColumn());
    }
}
