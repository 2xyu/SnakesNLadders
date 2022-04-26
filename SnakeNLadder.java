/**
 * Program that plays a game of Snakes & Ladders for 1-6 players
 *
 * @author Viet Hoang
 * @date Oct 26, 2020
 * @version 1
 */

import java.util.ArrayList;
import java.util.Scanner;

public class SnakeNLadder {
    public static void main(String[] args) {

        while (true) {
            ArrayList<Player> playerList = new ArrayList<>();

            Scanner in = new Scanner(System.in);

            int amountOfPlayer = 0;
            int playerIndex = 0;
            boolean thereIsWinner;

            System.out.print("\nPlease enter the number of players: ");

            while (in.hasNextLine()) {
                String numTemp = in.nextLine().trim();

                try {
                    if (numTemp.equals("")) {
                        System.out.print("\nPlease enter a proper amount of players: ");
                        continue;
                    }
                    amountOfPlayer = Integer.parseInt(numTemp);
                    break;
                } catch (Exception e) {
                    System.out.print("\nPlease enter a proper amount of players: ");
                }
            }

            for (int i = 0; i < amountOfPlayer; i++) {
                Player certainPlayer = new Player("\u001B[31m" + "P" + i + "\u001B[0m");
                playerList.add(certainPlayer);
            }

            //makes the board
            Board board = new Board(playerList);

            //loop that keeps the game running until there is a winner
            do {

                //adds a player to a list
                Player currentPlayer = playerList.get(playerIndex);

                //gets a roll (1-6) for a player
                int moveSpaces = currentPlayer.takeTurn();

                //checks if a player has won (on 100 or passed it)
                thereIsWinner = board.movePlayer(currentPlayer, moveSpaces);

                System.out.println();
                System.out.println(board.displayBoard(playerList));
                System.out.println("___________________________\n");

                if (thereIsWinner) {
                    System.out.println(currentPlayer + " wins");
                }

                //used for switching turns
                playerIndex++;
                if (playerIndex == amountOfPlayer) {
                    playerIndex = 0;
                }
            } while (!thereIsWinner);

            System.out.print("Would you like to play again? [Y/N]: ");
            boolean selectionProgress = false;

            while (!selectionProgress){
                String action = in.nextLine().trim();
                if (action.equalsIgnoreCase("Y")) {
                    selectionProgress = true;
                } else if (action.equalsIgnoreCase("N")) {
                    System.exit(-1);
                }
                else {
                    System.out.print("\n\nPlease only select [Y/N]\nWould you like to play again?: ");
                }
            }
        }
    }
}