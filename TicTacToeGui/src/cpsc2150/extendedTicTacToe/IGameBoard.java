package cpsc2150.extendedTicTacToe;

public interface IGameBoard {
    /**
     * GameBoard is abstractly a 2 dimensional board of characters.
     * Indexing starts at 0
     *
     * Initialization ensures:
     *      GameBoard's spaces are blank and is  Z rows by Z columns, and Z number of tokens in a row are needed to win
     *
     * Defines:
     *      number_of_columns: Z
     *      number_of_rows: Z
     *      number_0f_tokens_needed: Z
     *
     * Constraints: minNumberRowsColumns <= number_of_columns <= maxNumberRowsColumns
     *              minNumberRowsColumns <= number_of_rows <= maxNumberRowsColumns
     *              minTokensNeeded <= number_of_tokens_needed <= maxTokensNeeded
     *
     *
     *
     */


    /**
     *@pre 0 <= pos.getRow() < [entered number of Rows given by user] AND
     * 0 <= pos.getColumn() < [the number of Columns given by user]
     *
     * @post checkSpace() = true [if the past in spot on the board doesn't contain an player's marker]
     * OR false [if a player's character is found at the position]
     *
     *
     * @return true [if the past in spot on the board contains doesn't contain an player's marker] AND
     * false [if a player's character is found at the position]
     *
     *
     * @param pos [is the position on the board where the user
     * currently wants to place their marker]
     */
    default public boolean checkSpace(BoardPosition pos) {
        char characterChecker = ' ';

        characterChecker = whatsAtPos(pos);

        if(characterChecker != ' ') {
            return false;
        }
        else {
            return true;
        }
    }

    /**
     *@pre 0 <= marker.getRow() < [entered number of Rows given by user] AND
     * 0 <= marker.getColumn() < [the number of Columns given by user]
     *
     * @post [a marker will be placed at the valid position on the board]
     *
     * @return NONE
     *
     * @param marker [represents the place on the board]
     * @param player [represents which player’s marker is going to
     * get placed]
     */
    public void placeMarker(BoardPosition marker, char player);

    /**
     *@pre 0 <= pos.getRow() < [entered number of Rows given by user] AND
     * 0 <= pos.getColumn() < [the number of Columns given by user]
     *
     * @post checkForWinner() = [true (if lastPos results in winner)
     * OR false (if not)]
     *
     * @return [If the marker placed at the last position results in
     * winner true is returned AND false if not]
     *
     * @param lastPos [represents the last place a marker was placed]
     */
    default public boolean checkForWinner(BoardPosition lastPos) {
        char player = whatsAtPos(lastPos);

        if(checkHorizontalWin(lastPos,player) == true) { //(for if-elseif-else struct): makes calls to the different
            //types of wins that could occur to see if a win did occur, and
            //returns false if none of the calls resulted in true
            return true;
        }
        else if(checkVerticalWin(lastPos, player) == true) {
            return true;
        }
        else if(checkDiagonalWin(lastPos, player) == true) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *@pre [a winner has not been declared already]
     *
     * @post checkForDraw() = [true (if no free board positions are
     * available) OR false (if board positions are available)]
     *
     * @return [true (if no free board positions are available) OR
     * false (if board positions are available)]
     *
     */
    default public boolean checkForDraw() {
        int i = 0;
        int j = 0;
        int numberOfRows = getNumRows();
        int numberOfColumns = getNumColumns();
        int hasBlankSpace = 0;
        BoardPosition functionPos;

        for(i = 0; i < numberOfRows; i++) { //Loop is used to go through the entire board and check...
            for(j = 0; j < numberOfColumns; j++) {
                functionPos = new BoardPosition(i, j);
                if(whatsAtPos(functionPos) == ' ' ) { //...if there is still a blank space on the board...
                    hasBlankSpace++;
                }
            }
        }

        if(hasBlankSpace >= 1) { //...and if so returning false indicating a draw hasn't occurred...
            return false;
        }
        else { //...though true if a draw (no blank spaces found) occurred
            return true;
        }

    }

    /**
     *@pre 0 <= lastPos.getRow() < [entered number of Rows given by user]
     * AND 0 <= lastPos.getColumn() < [entered number of Columns given by user] AND player == [one of the characters
     * entered to represent a player]
     *
     * @post checkHorizontalWin() = [true (if last placed marker
     * resulted in an 5-in-a-row horizontally) OR false (if not)
     * ,as well as a winner]
     *
     * @return [true (if last placed marker resulted in an 5-in-a-row
     * horizontally) OR false (if not)]
     *
     * @param lastPos [represents the last place a marker was placed]
     * @param player [represents which players marker is there (AND potential winner)]
     *
     */
    default public boolean checkHorizontalWin(BoardPosition lastPos, char player) {
        int userRow = 0;
        int charCounter = 0; //counts the amount of tokens belonging to the player
        int i = 0;
        int numberOfColumns = getNumColumns(); //used for loop condition
        int numberToWin = getNumToWin();
        BoardPosition boardChecker;

        userRow = lastPos.getRow();

        for(i = 0; i < numberOfColumns; i++) {
            if(charCounter == numberToWin) { //if after the row has been checked, if there were 5-in-a-row, returns true...

                return true;
            }
            boardChecker = new BoardPosition(userRow, i);
            if(whatsAtPos(boardChecker) == player) { //if what's at the current BoardPosition equals the passed in player's
                //character, our counter is incremented
                charCounter++;
            }
            else if(whatsAtPos(boardChecker) != player){ //sets our counter back to 0 if what's at the current
                //BoardPosition doesn't equal the passed in player's character
                charCounter = 0;
            }
        }
        return false; //returns false if 5-in-a-row of the same character, didn't occur when checking the passed in row
    }

    /**
     * @pre 0 <= lastPos.getRow() < [entered number of Rows given by user] AND
     * 0 <= lastPos.getColumn() < [entered number of Columns given by user] AND player == [one of the characters entered
     * to represent a player]
     *
     * @post checkVerticalWin() = [true (if last placed marker
     * resulted in an 5-in-a-row vertically) OR false (if not)
     * ,as well as a winner]
     *
     * @return [true (if last placed marker resulted in an 5-in-a-row
     * vertically) OR false (if not)]
     *
     * @param lastPos [represents the last place a marker was placed]
     * @param player [represents which players marker is
     * there (AND potential winner)]
     *
     */
    default public boolean checkVerticalWin(BoardPosition lastPos, char player) {
        int userColumn = 0;
        int charCounter = 0; //counts the amount of tokens belonging to the player
        int i = 0;
        int numberOfRows = getNumRows();
        int numberToWin = getNumToWin();
        BoardPosition boardChecker;

        userColumn = lastPos.getColumn();

        for(i = 0; i < numberOfRows; i++) {
            boardChecker = new BoardPosition(i, userColumn);
            if(whatsAtPos(boardChecker) == player) { //if what's at the current BoardPosition equals the passed in player's
                //character, our counter is incremented
                charCounter++;
                if(charCounter == numberToWin) { //if after the column has been checked, if there were 5-in-a-row, returns true...
                    break;
                }
            }
            else if(whatsAtPos(boardChecker) != player){ //sets our counter back to 0 if what's at the current
                //BoardPosition doesn't equal the passed in player's character
                charCounter = 0;
            }
        }
        if(charCounter == numberToWin) { //if after the column has been checked, if there were 5-in-a-row, returns true...
            return true;
        }
        else { //...and false if not
            return false;
        }

    }

    /**
     *@pre 0 <= lastPos.getRow() < [entered number of Rows given by user] AND
     * 0 <= lastPos.getColumn() < [entered number of Columns given by user] AND player == [one of the characters entered
     * to represent a player]
     *
     * @post checkDiagonalWin() = true [if last placed marker
     * resulted in an 5-in-a-row/win diagonally] OR false [if not]
     *
     *
     * @return true [if last placed marker resulted in an 5-in-a-row
     * /win diagonally] OR false [[f not]
     *
     * @param lastPos [represents the last place a marker was placed]
     * @param player [represents which players marker is
     * there (AND potential winner)]
     *
     */
    default public boolean checkDiagonalWin(BoardPosition lastPos, char player) {
        int diagonalWinA = 1; //counter for checks up and to the left and down and to the right
        int diagonalWinB = 1; //counter for checks up and to the right and down and to the left
        int i = 0;
        int userRow;
        int userColumn;


        for (i = 1; (getNumColumns() >= i && getNumRows() >= i && i > 0); i++) { //checks up and to the left
            userRow = lastPos.getRow();
            userColumn = lastPos.getColumn();

            userRow -= i;
            userColumn -= i;

            if (userRow < 0 || userColumn < 0) { //checks to make sure userRow and userColumn are still in bounds...
                break;
            }
            else { //...and if so checks to see if the character at the current position equals the passed in player
                if (whatsAtPos(new BoardPosition(userRow, userColumn)) == player) { //if player is at position increments counter...
                    diagonalWinA++;
                    if (diagonalWinA >= getNumToWin()) { //checks to see if the number needed to win was reached
                        break;
                    }
                }
                else { //...if not returns to top of for loop
                    break;
                }
            }
        }
        for (i = 1; (getNumColumns() >= i && getNumRows() >= i && i > 0); i++) { //checks down and to the right
            userRow = lastPos.getRow();
            userColumn = lastPos.getColumn();

            userRow += i;
            userColumn += i;

            if (userRow >= getNumRows() || userColumn >= getNumColumns()) { //checks to make sure userRow and userColumn are still in bounds...
                break;
            }
            else { //...and if so checks to see if the character at the current position equals the passed in player
                if (whatsAtPos(new BoardPosition(userRow, userColumn)) == player) { //if player is at position increments counter...
                    diagonalWinA++;
                    if (diagonalWinA >= getNumToWin()) { //checks to see if the number needed to win was reached
                        break;
                    }
                }
                else { //...if not returns to top of for loop
                    break;
                }
            }
        }

        for (i = 1; (getNumColumns() >= i && getNumRows() >= i && i > 0); i++) { //checks down and to the left
            userRow  = lastPos.getRow();
            userColumn = lastPos.getColumn();

            userRow += i;
            userColumn -= i;

            if (userRow >= getNumRows() || userColumn < 0) { //checks to make sure userRow and userColumn are still in bounds...
                break;
            }
            else { //...and if so checks to see if the character at the current position equals the passed in player
                if (whatsAtPos(new BoardPosition(userRow, userColumn)) == player) { //if player is at position increments counter...
                    diagonalWinB++;
                    if (diagonalWinB >= getNumToWin()) { //checks to see if the number needed to win was reached
                        break;
                    }
                }
                else { //...if not returns to top of for loop
                    break;
                }
            }
        }

        for (i = 1; (getNumColumns() >= i && getNumRows() >= i && i > 0); i++) { //checks up and to the right
            userRow = lastPos.getRow();
            userColumn = lastPos.getColumn();

            userRow -= i;
            userColumn += i;

            if (userRow < 0 || userColumn >= getNumColumns()) { //checks to make sure userRow and userColumn are still in bounds...
                break;
            }
            else { //...and if so checks to see if the character at the current position equals the passed in player
                if (whatsAtPos(new BoardPosition(userRow, userColumn)) == player) { //if player is at position increments counter...
                    diagonalWinB++;
                    if (diagonalWinB >= getNumToWin()) { //checks to see if the number needed to win was reached
                        break;
                    }
                }
                else { //...if not returns to top of for loop
                    break;
                }
            }
        }

        if(diagonalWinA >= getNumToWin() || diagonalWinB >= getNumToWin()) { //if either counter equals the number needed
            //to win...
            return true; //...returns true...
        }
        else { //...otherwise returns false
            return false;
        }
    }

    /**
     *@pre 0 <= lastPos.getRow() < [entered number of Rows given by user] AND
     * 0 <= lastPos.getColumn() < [entered number of Columns given by user] AND player == [one of the characters entered
     * to represent a player]
     *
     * @post whatsAtPos() = [a character entered by the user to represent a player] OR [a blank space if nothing was at
     * the past in position]
     *
     * @return [returns what’s at the position (a character entered by the user to represent a player) OR (a blank space
     * if nothing is there)]
     *
     * @param pos [represents the current position on the board that
     * is going to get checked]
     */
    public char whatsAtPos(BoardPosition pos);

    /**
     *@pre 0 <= lastPos.getRow() < [entered number of Rows given by user] AND
     * 0 <= lastPos.getColumn() < [entered number of Columns given by user] AND player == [one of the characters entered
     * to represent a player]
     *
     * @post isPlayerAtPos() = true [if the marker for the passed in player is present at the specified
     * position pos] AND false [if not]
     *
     * @return true [if the marker for the passed in player is present at the specified position pos] AND
     * false [if not]
     *
     * @param pos [represents the current position on the board that
     * is getting checked]
     * @param player [represents the player who’s marker is
     * potentially at the current position on the board]
     */
    default public boolean isPlayerAtPos(BoardPosition pos, char player) {
        if(whatsAtPos(pos) == player) {
            return true;
        }
        else {
            return false;
        }
    }

    /**
     *@pre NONE
     *
     * @post getNumRows() = numberOfRows
     *
     * @return [the number of rows in the gameboard]
     *
     */
    public int getNumRows();

    /**
     *@pre NONE
     *
     * @post getNumColumns() = numberOfColumns
     *
     * @return [the number of columns in the gameboard]
     *
     */
    public int getNumColumns();

    /**
     *@pre NONE
     *
     * @post getNumToWin() = tokensNeeded;
     *
     * @return [the number of tokens in row needed to win the game]
     *
     */
    public int getNumToWin();

}




