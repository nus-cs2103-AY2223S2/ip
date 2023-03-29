package duke;

import javafx.stage.Stage;
import duke.command.Command;
import duke.task.TaskList;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(Stage stage) {
        storage = new Storage("data/tasks.txt");
        ui = new Ui(stage);

        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    private void runDuke() {
        Ui.showGreeting();

        boolean isExit = false;
        while (!isExit) {
            try {
                String Command = ui.readCommand();
                Command c = Parser.parse(Command);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public String getResponse(String input) throws DukeException {
        try {
            Command c = Parser.parse(input);
            return c.execute(tasks, ui, storage);
        } catch (DukeException e){
            return e.getMessage();
        }
    }
}