package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

import javafx.animation.Timeline;
import javafx.fxml.FXML;

/**
 * Handles the user input, and abstract and encapsulates various information that is needed to store and display
 * results to the user. This includes:
 * <ul>
 * <li> The list of Tasks that the user keys in
 * <li> Reading and writing information from / to the hardisk of the user's
 * machine
 * <li> Various responses by Duke bot customised according to a football
 * character
 * </ul>
 * It has methods for different levels to satsify the iterative
 * implementation. Jar file executes the main class from here, as well as
 * Gradle
 * @author Muhammad Reyaaz
 * @version %I% %G%
 * @see TaskList
 * @see Storage
 * @see Ui
 * @since 11
 */
public class Duke {
    private TaskList<Task> taskList = new TaskList<Task>();
    private PriorityBlockingQueue<Recur> recurList = new PriorityBlockingQueue<>();
    private Storage storage;
    private Ui ui;

    /**
     * Retrieves a response for the input and recurResponse. It calls the readFromMemory method to read
     * the task list from memory, sets up a new print stream, calls the execute method
     * from the Ui class to update the task list,
     * resets the print stream, writes the updated task list to memory, and returns the output of the new print stream.
     * @param input - a string representing the user input
     * @param recurResponse - a list of timelines representing recurring tasks
     * @return - a string representation of the output from the new print stream
     */
    @FXML
    protected String getResponse(String input, List<Timeline> recurResponse) {

        readFromMemory();

        ByteArrayOutputStream storeString = newPrintStream();

        PrintStream oldPrintStream = System.out;

        ui = new Ui(input);
        taskList = ui.execute(taskList, recurResponse);

        resetPrintStream(oldPrintStream);

        storage.writeToFile(taskList.toString());
        return storeString.toString();

    }

    /**
     * Creates a new instance of the Storage class, calls the readFromFile method to read the task list
     * from memory, updates the taskList object with the list of tasks, and calls the createDirectory method to create a
     * directory for storing data if it doesn't already exist.
     */
    void readFromMemory() {
        storage = new Storage();
        storage.readFromFile();
        this.taskList = storage.getTasks();
        storage.createDirectory();
    }

    /**
     * Creates a new ByteArrayOutputStream, creates a new PrintStream with the ByteArrayOutputStream,
     * sets the System.out print stream to the new print stream, and returns the ByteArrayOutputStream.
     * @return - the new ByteArrayOutputStream created in the method
     */
    ByteArrayOutputStream newPrintStream() {
        ByteArrayOutputStream storeString = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(storeString);
        System.setOut(printStream);
        return storeString;
    }

    /**
     * Flushes the current System.out print stream, sets the System.out print stream to the oldPrintStream
     * @param oldPrintStream - the old print stream to reset System.out to
     */
    void resetPrintStream(PrintStream oldPrintStream) {
        System.out.flush();
        System.setOut(oldPrintStream);
    }

    public static void main(String[] args) {

    }

}
