package duke;

import java.io.IOException;
import java.util.Scanner;

import commands.Command;
import exceptions.DukeException;
import files.Storage;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import parsers.CommandParser;
import tasks.TaskList;
import ui.Ui;

/**
 * The main application runner which allows users to track their tasks.
 */
public class Duke {

    private static final String BANNER = "____________________________________________________________";

    private static final String FILEPATH = "src/main/data/duke.txt";

    private Ui ui = new Ui();
    private Storage storage = new Storage();
    private CommandParser commandParser = new CommandParser();
    private TaskList taskList = new TaskList();
    private VBox dialogContainer;
    private boolean isExit = false;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/yoda.jpeg"));


    public static void main(String[] args) {
        new Duke().run();
    }

    public void setUp() {
        try {
            taskList = Storage.loadData(taskList, FILEPATH);
            //System.out.println(taskList.listItems());
        } catch (IOException e) {
            this.dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog("Transmission error I encountered. "
                    + "Jumping into hyperspace, it might be!", dukeImage));
        }
    }

    public void setDialogBox(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
        //this.dialogContainer.getChildren().add(DialogBox.getDukeDialog("negga negga", dukeImage));
    }

    /**
     * Runs the entire program.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean isExit = false;
        while (!isExit) {
            try {
                String response = ui.readCommand(scanner);
                Command command = commandParser.parse(response);
                command.execute(taskList, ui, storage);
                isExit = command.isExit();
            } catch (DukeException e) {
                ui.showLoadingErrorMessage(e);
            } finally {
                System.out.println(BANNER);
            }
        }
        storage.saveData(FILEPATH, taskList);
        System.out.println(BANNER);
    }
    public String getResponse(String input) {
        return "Duke heard: " + input;
    }
    public String handleResponse(String input) {
        try {
            Command command = commandParser.parse(input);
            String reply = command.execute(taskList, ui, storage);
            isExit = command.isExit();
            return reply;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    public Ui getUi() {
        return ui;
    }
    public TaskList getTaskList() {
        return taskList;
    }
}
