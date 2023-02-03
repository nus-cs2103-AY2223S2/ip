package duke;

import duke.command.Command;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filepath) {
        try {
            this.storage = new Storage(filepath);
            this.taskList = new TaskList(storage.loadTasks());
            this.ui = new Ui();
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    public void run() {
        ui.showWelcomeMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputCommandString = ui.readCommand();
                ui.showLine();
                Command inputCommand = Parser.parse(inputCommandString);
                inputCommand.execute(taskList, ui, storage);
                isExit = inputCommand.isExit();
                ui.showLine();
            } catch (DukeException err) {
                System.out.println(err.getMessage());
            }
        }

    }

    public static void main(String[] args) {
        new Duke("../../../data/duke.txt").run();
    }
}
