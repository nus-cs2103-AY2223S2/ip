package duke;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.PriorityBlockingQueue;

import javafx.animation.Timeline;
import javafx.fxml.FXML;

/**
 * Duke is the main class that directly handles the user input, and abstract
 * encapsulates various information that is needed to store and display
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
 * @see Scanner
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

    @FXML
    protected String getResponse(String input, List<Timeline> recurResponse) {

        storage = new Storage();
        storage.readFromFile();
        this.taskList = storage.getTasks();
        storage.createDirectory();

        ByteArrayOutputStream storeString = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(storeString);
        PrintStream oldPrintStream = System.out;
        System.setOut(printStream);

        ui = new Ui(input);
        taskList = ui.execute(taskList, recurResponse);

        System.out.flush();
        System.setOut(oldPrintStream);

        storage.writeToFile(taskList.toString());
        System.out.println("Store string " + storeString);
        return storeString.toString();

    }

    public static void main(String[] args) {

    }

}
