import java.io.IOException;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import javafx.stage.Stage;


/**
 * Main Duke class whereby an instance of the Duke chatbot is initialized.
 */
public class Duke {

    private static Parser dukeParser;
    private static Storage dukeStorage;
    private static TaskList dukeTaskList;
    private static Stage stageToControl;

    /**
     * Constructor to create an instance of Duke chatbot.
     * @throws IOException
     * @throws DukeExceptions
     */
    Duke() throws IOException, DukeExceptions {
        dukeParser = new Parser();
        dukeStorage = new Storage("data", "dukedata.txt");

        try {
            dukeTaskList = new TaskList(dukeStorage.loadTask());
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Runs the Duke chatbot program.
     * @throws IOException
     * @throws DukeExceptions
     */
    public void run(MainWindow tool) throws IOException, DukeExceptions {
        String input = tool.getUserInput();
        if (input.equals("bye")) {
            dukeStorage.storeTask(dukeTaskList.getListOfTasks());
            this.stageToControl.close();
        }
        String dukeOutput = dukeParser.readInput(input, dukeTaskList);
        tool.setDukeOutput(dukeOutput);
        dukeStorage.storeTask(dukeTaskList.getListOfTasks());
    }

    public void setStage(Stage stage) {
        this.stageToControl = stage;
    }
}
