package duke;

import java.util.Scanner;

/**

 This class handles all the user interface interactions.
 It provides methods to show a welcome message, read user input, show a separator line, close the Scanner, and display error messages.
 */
public class Ui {
    private Scanner sc;

    /**
     Constructor that initializes the Scanner to read input from the standard input.
     */
    public Ui() {
        sc = new Scanner(System.in);
    }

    /**
     Show the welcome message to the user.
     */
    public void showWelcome(){
        System.out.println("What's up! I'm duuk.What do you want?");
    }

    /**
     Reads a line of input from the user.
     @return the input string read from the user.
     */
    public String readCommand() {
        String input = sc.nextLine();
        return input;
    }

    /**
     Shows a separator line.
     */
    public void showLine() {
        System.out.println("________________________________________");
    }

    /**
     Closes the Scanner object.
     */
    public void closeDuke() {
        this.sc.close();
    }

    /**
     Displays the error message to the user.
     @param message the error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        System.out.println(message);
    }
}
