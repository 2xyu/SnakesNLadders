/**
 * Program that plays a game of Snakes & Ladders for a recommended 1-4 players
 *
 * @author Viet Hoang
 * @date Oct 26, 2020
 * @version 1
 */


import java.util.ArrayList;

public class Board {

    private final int LADDERS = 8;
    private final int SNAKES = 8;
    private final int ROWS = 10;
    private final int COLUMNS = 10;

    private final int[][] gameBoardArray;
    private int[][] snakesPosition;
    private int[][] laddersPosition;

    /** sets the location of all the snakes (based on the SnakeNLadders.png)
     *
     */
    private void setSnakesPosition() {

        this.snakesPosition = new int[SNAKES][2];

        snakesPosition[0][0] = 17;
        snakesPosition[0][1] = 7;
        snakesPosition[1][0] = 54;
        snakesPosition[1][1] = 34;
        snakesPosition[2][0] = 62;
        snakesPosition[2][1] = 19;
        snakesPosition[3][0] = 64;
        snakesPosition[3][1] = 60;
        snakesPosition[4][0] = 87;
        snakesPosition[4][1] = 24;
        snakesPosition[5][0] = 93;
        snakesPosition[5][1] = 73;
        snakesPosition[6][0] = 95;
        snakesPosition[6][1] = 75;
        snakesPosition[7][0] = 99;
        snakesPosition[7][1] = 78;
    }

    /** sets the location of all the ladders (based on the SnakeNLadders.png)
     *
     */
    private void setLaddersPosition() {

        this.laddersPosition = new int[LADDERS][2];

        laddersPosition[0][0] = 4;
        laddersPosition[0][1] = 14;
        laddersPosition[1][0] = 9;
        laddersPosition[1][1] = 31;
        laddersPosition[2][0] = 20;
        laddersPosition[2][1] = 38;
        laddersPosition[3][0] = 28;
        laddersPosition[3][1] = 84;
        laddersPosition[4][0] = 40;
        laddersPosition[4][1] = 59;
        laddersPosition[5][0] = 51;
        laddersPosition[5][1] = 67;
        laddersPosition[6][0] = 63;
        laddersPosition[6][1] = 81;
        laddersPosition[7][0] = 71;
        laddersPosition[7][1] = 91;
    }

    /** Makes the board and it's component (players, ladders, snakes)
     *
     * @param players - the Player ArrayList for players
     */
    public Board(ArrayList<Player> players) {

        this.gameBoardArray = new int[ROWS][COLUMNS];

        //sets the starting position of each player (on 1)
        for (Player player : players) {
            player.setCurrentPlayerPosition(1);
        }

        //Set up the ROWS X COLUMNS board
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLUMNS; col++) {
                gameBoardArray[row][col] = row * ROWS + col + 1;
            }
        }

        //runs the methods that sets the Snakes
        setSnakesPosition();

        //runs the methods that set the Ladders
        setLaddersPosition();
    }

    /** moves the player based on the amount of spaces they've rolled
     *
     * @param player - the player being moved
     * @param spaces - the amount of spaces that's being moved
     * @return true or false depending on whether a player has won (on 100 or past)
     */
    public boolean movePlayer(Player player, int spaces) {

        //finds the new position for the player
        int newPosition = player.getCurrentPlayerPosition();
        newPosition += spaces;

        if (newPosition >= 100) {
            player.setCurrentPlayerPosition(100);
            return true;
        } else {

            System.out.println(player + " has moved from " + player.getCurrentPlayerPosition() + " to " + newPosition);

            //check if the new position is on the head of a snake
            for (int i = 0; i < SNAKES; i++) {
                if (snakesPosition[i][0] == newPosition) {

                    //moves the player to the tail of the snake
                    newPosition = snakesPosition[i][1];
                    player.setCurrentPlayerPosition(newPosition);

                    System.out.println(player + " has touched a snake and has slid from " + snakesPosition[i][0] + " to " + snakesPosition[i][1]);

                    return false;
                }
            }

            //check if the new position is on the bottom of a ladder
            for (int i = 0; i < LADDERS; i++) {
                if (laddersPosition[i][0] == newPosition) {

                    //moves the player to the top of the ladder
                    newPosition = laddersPosition[i][1];
                    player.setCurrentPlayerPosition(newPosition);

                    System.out.println(player + " has reached a ladder and climbs it from " + laddersPosition[i][0] + " to " + laddersPosition[i][1]);

                    return false;
                }
            }

            //if their new position is puts the player to their new position
            player.setCurrentPlayerPosition(newPosition);
            return false;
        }
    }

    /** Used for printing our the game board
     *
     * @param playerList - the Player ArrayList for playerList
     * @return String of Board
     */
    public String displayBoard(ArrayList<Player> playerList) {

        StringBuilder board = new StringBuilder();
        boolean oddRow = true;

        //loop for formatting the board
        for (int row = ROWS - 1; row >= 0; row--) {
            for (int column = 0; column < COLUMNS; column++) {

                boolean hasAnotherPlayer = false;
                StringBuilder takenSpace = new StringBuilder();

                if (oddRow) {
                    for (Player currentPlayer : playerList) {

                        //formats the location so that it would have the player name in it instead of the number for the odd rows (9,7,5,3,1)
                        if (currentPlayer.getCurrentPlayerPosition() == gameBoardArray[row][COLUMNS - column - 1]) {

                            hasAnotherPlayer = true;
                            takenSpace.append(currentPlayer.getName()).append(" ");
                        }
                    }

                    //Check if any of the players occupy the current location of the other player for the odd rows (9,7,5,3,1)
                    if (hasAnotherPlayer) {
                        takenSpace.append("\t");
                        board.append(takenSpace);
                    }
                    else {
                        board.append(gameBoardArray[row][COLUMNS - column - 1]).append("\t");
                    }

                } else {
                    for (Player currentPlayer : playerList) {

                        //formats the location so that it would have the player name in it instead of the number for the even rows (8,6,4,2)
                        if (currentPlayer.getCurrentPlayerPosition() == gameBoardArray[row][column]) {

                            hasAnotherPlayer = true;
                            takenSpace.append(currentPlayer.getName()).append(" ");
                        }
                    }

                    //Check if any of the players occupy the current location of the other player for the even rows (8,6,4,2)
                    if (hasAnotherPlayer) {
                        takenSpace.append("\t");
                        board.append(takenSpace);
                    }
                    else {
                        board.append(gameBoardArray[row][column]).append("\t");
                    }
                }
            }

            //used for alternating between the rows)
            oddRow = !oddRow;
            board.append("\n");
        }
        board.append("\n");
        return board.toString();
    }
}