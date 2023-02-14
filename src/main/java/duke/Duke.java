package duke;

import java.io.IOException;

import duke.gui.Profile;
import duke.gui.StageHandler;
import duke.tasks.TaskList;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class where duke is initialized and runs.
 */
public class Duke extends Application {
    private TaskList tasks;

    public Duke() {
        tasks = new TaskList();
        try {
            Storage.loadFromFile(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void start(Stage stage) {
        StageHandler stageHandler = new StageHandler(stage, tasks);
        stageHandler.displayMessage(Profile.DUKE, "Hello I'm Duke! \nWhat can I do for you?");
    }


}
