package clippy.ui;

import java.util.Scanner;

/**
 * An abstraction encapsulating the user input/output interfaces.
 *
 * @author chunzkok
 */
public class Ui {
    private String userPrompt;
    private String systemPrompt;
    private Scanner sc;

    /**
     * Creates a Ui instance that is responsible for the user interface.
     *
     * @param userPrompt A string that should be displayed at the start of any 'prettyPrint'
     * @param systemPrompt A string that should be displayed at the start of any 'systemPrint'
     */
    public Ui(String userPrompt, String systemPrompt) {
        this.userPrompt = userPrompt;
        this.systemPrompt = systemPrompt;
        this.sc = new Scanner(System.in);
    }

    /**
     * Prints a given string with a standardised prefix specified in the constructor.
     *
     * @param output The string to be printed.
     */
    public void prettyPrint(String output) {
        System.out.println(userPrompt + " " + output);
    }

    /**
     * Prints a given string with a standardised prefix specified in the constructor.
     * This is used to display system messages.
     *
     * @param output The string to be printed.
     */
    public void systemPrint(String output) {
        System.out.println(systemPrompt + " " + output);
    }

    /**
     * Reads a line from STDIN and returns it to the caller.
     *
     * @return A string containing the next line entered into STDIN.
     */
    public String readCommand() {
        return this.sc.nextLine();
    }
}
