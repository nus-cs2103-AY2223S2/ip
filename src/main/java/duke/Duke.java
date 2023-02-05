package duke;

import duke.driver.Driver;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.ui.Ui;

public class Duke {


    public static void main(String[] args) {
        Ui.greet();
        TaskList taskList = Storage.readTaskList();
        Driver driver = new Driver();
        driver.run(taskList);
    }
}
