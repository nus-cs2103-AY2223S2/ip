package duke.ui;
import java.util.Scanner;

/**
 * The UI portion of Duke which is in charge of greetings and exit.
 */
public class Ui {

    /** Name of bot */
    private String botName;
    /** Scanner to use */
    private Scanner sc;

    /**
     * Constructor for the Ui class.
     *
     * @param botName The name of the bot.
     */
    public Ui(String botName) {
        this.botName = botName;
        sc = new Scanner(System.in);
    }

    /**
     * Method to close scanner.
     */
    public void closeScanner() {
        sc.close();
    }
}
