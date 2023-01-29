package duke;

import duke.command.Command;

import java.io.File;

public class Duke {

    private final Storage STORAGE;
    private TaskList taskList;
    private final Ui UI;

    public Duke(String filePath) {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            taskList = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            UI.showError(e.getMessage());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data" + File.separator + "tasks.txt").run();
    }

    public void run() {
        UI.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = UI.readCommand();
                UI.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, UI, STORAGE);
                isExit = c.isExit();
            } catch (DukeException e) {
                UI.showError(e.getMessage());
            } catch (Exception e) {
                UI.showMsg("Unknown command/error not caught!\n Please try again!");
            } finally {
                UI.showLine();
            }
        }
    }
}
