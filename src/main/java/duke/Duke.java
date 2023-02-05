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

    private Image dukeImage = new Image(this.getClass().getResourceAsStream("/images/yoda.jpeg"));


    public static void main(String[] args) {
        new Duke().run();
    }

    public void setUp() {
        try {
            taskList = Storage.loadData(FILEPATH);
            //System.out.println(taskList.listItems());
        } catch (IOException e) {
            this.dialogContainer.getChildren()
                .add(DialogBox.getDukeDialog("Transmission error I encountered. "
                    + "Jumping into hyperspace, it might be!", dukeImage));
        }
    }

    public void setDialogBox(VBox dialogContainer) {
        this.dialogContainer = dialogContainer;
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

    /**
     * Handles response from user input to send to dialog box.
     * @param input raw command input from the user
     * @return reply status
     */
    public String handleResponse(String input) {
        try {
            if (isExit && input.isEmpty()) {
                Stage currentStage = (Stage) dialogContainer.getScene().getWindow();
                currentStage.close();
            } else {
                isExit = true;
            }
            Command command = commandParser.parse(input);
            String reply = command.execute(taskList, ui, storage);
            isExit = command.isExit();
            if (isExit) {
                reply += "\n" + saveTasks()
                        + "\nPress Enter again to exit";
            }
            return reply;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Save tasks from task list into the file specified by FILEPATH.
     * @return status message
     */
    public String saveTasks() {
        String status = storage.saveData(FILEPATH, taskList);
        return status;
    }

    public Ui getUi() {
        return ui;
    }
    public TaskList getTaskList() {
        return taskList;
    }
}
