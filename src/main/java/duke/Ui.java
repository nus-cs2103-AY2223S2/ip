package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Ui {
    private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Prints a greeting message when the program starts.
     */
    public void start() {
        System.out.println("Hello! I'm Duke!\n" +
                "What can I do for you?");
        displayLine();    
    }

    /**
     * Reads the input from the command line and returns an array of String with index 0 as the first word.
     * @return array of String with index 0 as the first word
     * @throws IOException On input error
     */
    public String[] readLine() throws IOException {
        System.out.print("Type your command: ");
        return br.readLine().strip().split(" ",2);
    }

    /**
     * Returns a line to separate user commands and output.
     * @return String Line
     */
    public String displayLine() {
        return "_________________________________________\n";
    }

    /**
     * Prints a goodbye message when a bye command is entered.
     */
    public void goodbye() {
        displayLine();
        System.out.println("Goodbye!");
    }
}
