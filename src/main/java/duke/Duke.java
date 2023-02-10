package duke;

import javafx.application.Application;

import java.time.format.DateTimeParseException;
import java.io.IOException;

/**
 * Duke is Personal Assistant Chatbot that helps a person to keep track of various tasks.
 * 
 * @author Bryan Tan
 */
public class Duke {

    /**
     * Main method for Duke.
     *
     * @param args String arguments that are passed in by user upon start up of Duke.
     * @throws IOException when file path not found, IOException is thrown.
     */
    public static void main(String[] args) throws IOException {
        Application.launch(Main.class, args);

    }
}
