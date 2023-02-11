package duke;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Class used to read user's input for Duke chatbot.
 */
public class Ui {
    protected BufferedReader readingInput;

    /**
     * Constructor to initiate a bufferedreader to get user input.
     */
    public Ui() {
        this.readingInput = new BufferedReader(new InputStreamReader(System.in));
    }

    /**
     * Function to read user input.
     *
     * @return String representation of user input.
     * @throws IOException
     */
    public String gettingUserInput() throws IOException {
        String userInput = readingInput.readLine();
        return userInput;
    }

}
