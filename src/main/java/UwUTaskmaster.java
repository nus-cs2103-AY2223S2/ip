import data.TaskFileReaderWriter;
import data.TaskManager;
import javafx.stage.Stage;
import utils.Parser;


/**
 * GUI version of Duke with an UwU personality to help users keep track
 * of tasks such as events, deadlines and to-dos
 * @author Nicholas Lee
 */
public class UwUTaskmaster {
    private final TaskFileReaderWriter taskReaderWriter;
    private final Parser parser;
    private final TaskManager taskManager;
    private final Stage stage;
    /**
     * Initialises an instance of the bot
     */
    public UwUTaskmaster(Stage stage) {
        this.taskReaderWriter = new TaskFileReaderWriter();
        this.stage = stage;

        if (!taskReaderWriter.createTaskFile()) {
            System.out.println("Error creating data file");
        }

        this.taskManager = taskReaderWriter.loadDataFromFile();

        // Create a Parser instance parse user input
        this.parser = new Parser(taskManager);
    }
    /**
     * Handles user input by checking for the keyword "bye" and if so, updates the task file and exits the application.
     * If the input does not contain "bye", the input is processed by the parser to get a bot response
     * @param input The user input as a string.
     * @return The bot response as a string
     */
    public String getResponse(String input) {

        if (input.contains("bye")) {

            if (!taskReaderWriter.updateTaskFile(taskManager)) {
                System.out.println("Error updating data file");
            }
            stage.close();
            return "";
        }
        return parser.processInput(input);
    }

}
