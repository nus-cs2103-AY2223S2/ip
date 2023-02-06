package duke;

import duke.Command.Command;
import duke.Exceptions.CommandException;
import duke.Exceptions.DukeException;
import duke.Tasks.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        try {
            this.storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke(".\\tasks.txt").run();
    }

    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            String input = this.ui.readCommand();
            ui.showLine();
            try {
                Command command = Parser.parse(input);
                command.execute(this.tasks, this.storage, this.ui);
                isExit = command.isExit();
            } catch (CommandException commandException) {
                ui.showCommandError(input, commandException);
            } catch (DukeException dukeException) {
                System.out.println(dukeException);
            } finally {
                ui.showLine();
            }
        }
        ui.showGoodbye();
    }
}
