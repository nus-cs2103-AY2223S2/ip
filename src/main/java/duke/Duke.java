package duke;

import java.io.IOException;

import duke.command.Command;

/**
 * Duke Program for our Chat bot.
 */
public class Duke {

    /**
     * Stores and Loads all the tasks in file.
     */
    private Storage storage;

    /**
     * User Interface for user.
     */
    private Ui ui;

    /**
     * Task List to store all tasks provided by users.
     */
    private TaskList taskList;

    /**
     * Checks if we have to exit the Program.
     */
    private boolean isExit = false;

    /**
     * Creates Duke.
     */
    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList();

        try {
            storage.loadTasks(taskList);
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Duke();
    }

    public Ui getUi() {
        return ui;
    }

    public boolean getIsExit() {
        return isExit;
    }

    public void exitDuke() {
        isExit = true;
    }

    /**
     * Returns the appropriate response for Duke, depending on user input.
     *
     * @param input User input.
     * @return String of Duke's Response to be shown.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.stringToCommand(input);
            String toReturn = c.execute(ui, storage, taskList);
            storage.saveTasks(taskList);

            if (c.isExit()) {
                this.exitDuke();
            }
            return toReturn;
        } catch (DukeException e) {
            return ui.showError(e.getMessage());
        }
    }
}
