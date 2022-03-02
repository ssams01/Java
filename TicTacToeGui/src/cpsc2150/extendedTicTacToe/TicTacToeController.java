package cpsc2150.extendedTicTacToe;

/**
 * The TicTacToe controller class will handle communication between our TicTacToeView and our Model (IGameBoard and BoardPosition)
 * <p>
 * This is where you will write code
 * <p>
 * You will need to include your BoardPosition class, the IGameBoard interface
 * and the implementations from previous homeworks
 * If your code was correct you will not need to make any changes to your IGameBoard classes
 */
public class TicTacToeController {

    //our current game that is being played
    private IGameBoard curGame;

    //The screen that provides our view
    private TicTacToeView screen;

    private char[] playerCharacters; //array to hold in the characters that will be used in the game
    private int whosTurn = 0;
    private boolean winnerFound = false;
    private boolean drawOccured = false;
    public static final int MAX_PLAYERS = 10;

    /**
     * @param model the board implementation
     * @param view  the screen that is shown
     * @param np    The number of players for the game
     *
     * @post the controller will respond to actions on the view using the model.
     */
    public TicTacToeController(IGameBoard model, TicTacToeView view, int np) {
        this.curGame = model;
        this.screen = view;
        playerCharacters = new char[np];
        String players = "XOBEMJSHAQ";
        int i = 0;

        for(i = 0; i < np; i++) { //assigns each character from "players" to each element in our list
                                  //of players
            playerCharacters[i] = players.charAt(i);
        }

        // Some code is needed here.
    }

    /**
     * @param row the row of the activated button
     * @param col the column of the activated button
     *
     * @pre row and col are in the bounds of the game represented by the view
     * @post The button pressed will show the right token and check if a player has won.
     */
    public void processButtonClick(int row, int col) {
        BoardPosition currPos = new BoardPosition(row, col); //creates a temporary BoardPosition with
                                                         //the past in row and column

        char currPlayer = playerCharacters[whosTurn]; //assigns a variable with the character for
                                                 //whichever player's turn it is

        if(winnerFound || drawOccured) { //first checks to make sure a winner hasn't been found or a draw
                                        //hasn't occurred already, and if it does, starts a newGame and
                                        //sets winnerFound and drawOccurred back to false
            newGame();
            winnerFound = false;
            drawOccured = false;
        }

        if (curGame.checkSpace(currPos)) { //if the space is available...

            curGame.placeMarker(currPos, currPlayer); //(for next 2 lines): sets the marker on our board and makes sure
                                                      //displays on our GUI screen too
            screen.setMarker(row, col, currPlayer);
            winnerFound = curGame.checkForWinner(currPos);
            drawOccured = curGame.checkForDraw();
            if (winnerFound) { //prints message announcing the winner if a win occurred
                screen.setMessage("Player " + currPlayer + " wins!");
            }
            else if (drawOccured) { //prints message announcing the game ended in a tie/draw
                screen.setMessage("The game ended in a tie!");
            }
            else {  //though if neither a win or draw has occurred, increments whosTurn to move to
                    //the next player
                this.whosTurn++;
                if (this.whosTurn == playerCharacters.length) { //checks to see if all the players have had a
                                                              //turn and if so resets to the first player
                    this.whosTurn = 0;
                }
                screen.setMessage("It's " + playerCharacters[whosTurn] + "'s turn."); //prints who's turn it is
            }
        }
        else { //...though if not prints an error message to the user
            screen.setMessage("That space is unavailable, please pick again");
        }


    }

    private void newGame() {
        // You do not need to make any changes to this code.
        screen.dispose();
        GameSetupScreen screen = new GameSetupScreen();
        GameSetupController controller = new GameSetupController(screen);
        screen.registerObserver(controller);
    }
}