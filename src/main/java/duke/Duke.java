package duke;

import java.io.IOException;

import duke.command.Command;

public class Duke {

    private Storage storage;
    private Ui ui;
    private TaskList taskList;
    private boolean isExit = false;

    public Duke() {
        this.storage = new Storage();
        this.ui = new Ui();
        this.taskList = new TaskList();

        try {
            storage.loadTasks(taskList);
        } catch (IOException e) {
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
