package duke.ui;
import java.util.Scanner;

/**
 * The UI portion of Duke which is in charge of greetings and exit.
 */
public class Ui {

    /** Name of bot */
    private String name;
    /** Scanner to use */
    private Scanner sc;

    /**
     * Constructor for the Ui class.
     *
     * @param name The name of the bot.
     */
    public Ui(String name) {
        this.name = name;
        sc = new Scanner(System.in);
    }

    /**
     * Method to print the welcome message.
     */
    public void printGreetings() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Welcome to: \n" + logo);
        System.out.println("Hello! I'm " + name + " !");
    }

    /**
     * Method to print the exit message.
     */
    public void printExit() {
        System.out.println("BUH BYE!");
    }

    /**
     * Method to read user input for commands.
     *
     * @return String representation of the user's input.
     */
    public String readCommand() {
        System.out.println("What can I do for you today?\n");
        String input = sc.nextLine();
        System.out.println("Received command: " + input);
        return input;
    }

    /**
     * Method to close scanner.
     */
    public void closeScanner() {
        sc.close();
    }
}
