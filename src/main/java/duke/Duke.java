package duke;

import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main runner class for the Duke program.
 */
public class Duke extends Application {
    private Scanner scanner;
    private String horizontalLine = "************************";
    private TaskList taskList;

    private Storage storage;
    private Response response;
    private Parser parser;
    private DukeGui dukeGui;

    private boolean isExit;

    public Duke() {
        scanner = new Scanner(System.in);

        storage = new Storage("list_storage.txt");
        parser = new Parser();
        taskList = new TaskList(storage);
        response = new Response(taskList);
        dukeGui = new DukeGui(this);
    }

    @Override
    public void start(Stage stage) {
        Duke duke = new Duke();

        dukeGui.start(stage);
    }

    public String getIntro() {
        response.showIntro();
        return response.getTextResponse();
    }

    public String getDukeResponse(String input) {
        try {
            Command c = parser.parseInput(input);
            c.execute(taskList, response);
            isExit = c.getExit();
        } catch (DukeException e) {
            response.showUnknown(e.getMessage());
        }

        return response.getTextResponse();
    }

    public boolean getIsExit() {
        return isExit;
    }

}
