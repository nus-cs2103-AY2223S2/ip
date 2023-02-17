package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class for the output of user interface.
 */
public class Ui {

    private static final String LS = System.lineSeparator();

    private final Scanner in;
    private final PrintStream out;
    private final ArrayList<String> responses;

    public Ui() {
        this(System.in, System.out);
    }

    /**
     * Constructor for ui.
     * @param in the user input.
     * @param out the program output.
     */
    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
        responses = new ArrayList<>();
    }

    /**
     * Show the welcome message.
     * @return the welcome message.
     */
    public String showWelcome() {
        return "My empress,\nHow can I help you?";
    }

    /**
     * Adds the program's response to an array.
     * @param message the response to be added.
     */
    public void showToUser(String... message) {
        for (String m : message) {
            responses.add(m.replace("\n", LS));
        }
    }

    public String getResponses() {
        StringBuilder concatenatedResponse = new StringBuilder();
        for (String r : responses) {
            concatenatedResponse.append(r).append("\n");
            System.out.println(r);
        }
        responses.clear();
        return concatenatedResponse.toString();
    }


    public void showGoodbyeMessage() {
        showToUser("Take care, my empress!");
    }

    public void showError(String e) {
        showToUser(e);
    }

    public void showLoadingError() {
        showToUser("Error Loading");
    }
}
