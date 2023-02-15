import java.io.InputStream;

import static javafx.application.Platform.exit;

import data.TaskFileReaderWriter;
import data.TaskManager;
import utils.Parser;


/**
 * GUI version of Duke with an UwU personality to help users keep track
 * of tasks such as events, deadlines and to-dos
 * @author Nicholas Lee
 */
public class UWUTaskmaster  {

    private final TaskFileReaderWriter taskReaderWriter;
    private final Parser parser;
    private final TaskManager taskManager;

    /**
     * Initialises the bot
     */
    public UWUTaskmaster() {

        // Create a TaskFileReaderWriter instance to read from the txt file
        this.taskReaderWriter = new TaskFileReaderWriter();
        this.taskManager = taskReaderWriter.loadDataFromFile();

        if (!taskReaderWriter.createTaskFile()) {
            System.out.println("Error creating data file");
        }

        // Create a Parser instance parse user input
        this.parser = new Parser(taskManager);
    }
    /**
     Handles user input by checking for the keyword "bye" and if so, updates the task file and exits the application.
     If the input does not contain "bye", the input is processed by the parser to get a bot response.
     The user input and bot response are added to the main window.
     @param input The user input as a string.
     */
    public String getResponse(String input) {

        if (input.contains("bye")) {

            if (!taskReaderWriter.updateTaskFile(taskManager)) {
                System.out.println("Error updating data file");
            }
            exit();
            return "";
        }

        //        mainWindow.addDialogue(input, botResponse);
        return parser.processInput(input);
    }

}
