package duke;

import javafx.application.Application;

/**
 * Main Duke class
 *
 * @author He Shuimei
 * @version 0.0.1
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;

    /**
     * Main method which executes the JavaFX class
     *
     * @param args arguments
     */
    public static void main(String[] args) {
        Application.launch(JavaFX.class, args);
    }

    /**
     * Initialises a new Duke object
     *
     * @param filePath location of the save file
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFile());
        } catch (Exception e) {
            System.out.println();;
            tasks = new TaskList();
        }
    }

    public Storage getStorage() {
        return this.storage;
    }

    public TaskList getTaskList() {
        return this.tasks;
    }
}
