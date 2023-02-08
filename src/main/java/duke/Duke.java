package duke;

import duke.driver.Driver;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * The main entry point of Duke.
 */
public class Duke {

    /**
     * Main function for running Duke.
     */
    public static void main(String[] args) {
        Ui.greet();
        TaskList taskList = Storage.readTaskList();
        Driver driver = new Driver();
        driver.run(taskList);
    }
}
