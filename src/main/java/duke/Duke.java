package duke;

import duke.ui.Ui;
import javafx.application.Application;

/**
 * Main Duke class
 *
 * @author He Shuimei
 * @version 0
 */
public class Duke {
    Storage storage;
    TaskList tasks;

    /**
     * Main method which executes the JavaFX class
     * @param args arguments
     */
    public static void main(String[] args) {
        Application.launch(JavaFX.class, args);
    }

    /**
     * Initialises a new duke object
     * @param filePath location of save file
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
}
