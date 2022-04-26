/**
 * Program that plays a game of Snakes & Ladders for 1-6 players
 *
 * @author Viet Hoang
 * @date Oct 26, 2020
 * @version 1
 */


import java.util.Scanner;

public class Player {

    private final String name;
    private int currentPlayerPosition;

    /**
     * initialize the instance variable for the Player class
     *
     * @param name - the name of the player
     */
    public Player(String name) {

        this.name = name;
    }

    /**
     * returns the value of name
     *
     * @return name
     */
    public String getName() {

        return this.name;
    }

    /**
     * returns the value of currentPlayerPosition
     *
     * @return currentPlayerPosition
     */
    public int getCurrentPlayerPosition() {

        return this.currentPlayerPosition;
    }

    /**
     * sets the value of currentPlayerPosition
     *
     * @param newPosition - the int value of newPosition
     */
    public void setCurrentPlayerPosition(int newPosition) {

        this.currentPlayerPosition = newPosition;
    }

    /**
     * used for getting a random in value from 1 to 6
     *
     * @return the integer value of roll
     */
    public int takeTurn() {
        Scanner button = new Scanner(System.in);

        System.out.print("\n" + name + "'s turn, enter an input to roll: ");

        //input solely used as a button for the user so that games doesn't just auto finish by itself
        String userInputThatIsNeverUsed = button.nextLine();

        int roll = (int) (Math.random() * (6)) + 1;

        System.out.println(name + " has rolled " + roll);

        return roll;
    }
    /**
     * creates a string representation of Player for printing
     *
     * @return String of Player
     */
    public String toString() {

        return name;
    }
}
