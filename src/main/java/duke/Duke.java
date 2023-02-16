package duke;

import java.io.IOException;
import java.util.ArrayList;

import duke.gui.MainWindow;
import javafx.stage.Stage;

/**
 * Main Duke class whereby an instance of the Duke chatbot is initialized.
 */
public class Duke {

    private static Parser dukeParser;
    private static Storage dukeStorage;
    private static TaskList dukeTaskList;
    private static TagList dukeTagList;
    private static Stage stageToControl;


    /**
     * Constructor to create an instance of Duke chatbot.
     *
     * @throws IOException
     * @throws DukeExceptions
     */
    public Duke() throws IOException, DukeExceptions {
        dukeParser = new Parser();
        dukeStorage = new Storage("data", "dukedata.txt", "duketagdata.txt");

        try {
            dukeTagList = new TagList(dukeStorage.loadTags());
            dukeTaskList = new TaskList(dukeStorage.loadTask(), dukeTagList);
            for (int i = 0; i < dukeTaskList.getListOfTasks().size(); i++) {
                ArrayList<String> tagList = dukeTagList.getTagListFull();
                String toUpdate = tagList.get(i);
                dukeTaskList.getListOfTasks().get(i).setTag(toUpdate);
            }
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }
    /**
     * Runs the Duke chatbot program.
     *
     * @param tool Instance of MainWindow is taken in to get user input and send output.
     * @throws IOException
     * @throws DukeExceptions
     */
    public void run(MainWindow tool) throws IOException, DukeExceptions {
        String input = tool.getUserInput();
        if (input.equals("bye")) {
            dukeStorage.storeTask(dukeTaskList.getListOfTasks());
            dukeStorage.storeTags(dukeTagList.getTagListFull());
            this.stageToControl.close();
        }
        String dukeOutput = dukeParser.readInput(input, dukeTaskList, dukeTagList);
        tool.setDukeOutput(dukeOutput);
        dukeStorage.storeTask(dukeTaskList.getListOfTasks());
        dukeStorage.storeTags(dukeTagList.getTagListFull());
    }

    /**
     * Sets the stage whereby the Duke chatbot will be running its logic.
     *
     * @param stage Stage whereby the Duke chatbot will run.
     */
    public void setStage(Stage stage) {
        this.stageToControl = stage;
    }
}
