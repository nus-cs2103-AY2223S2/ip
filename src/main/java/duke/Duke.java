package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;
import javafx.application.Platform;

public class Duke {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke() {
        try {
            this.storage = new Storage("/data/duke.txt");
            this.taskList = new TaskList(storage.loadTasks());
            this.ui = new Ui();
        } catch (DukeException err) {
            System.out.println(err.getMessage());
        }
    }

    public void run() {
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
                ui.showMessage(err.getMessage());
            }
        }

    }

    public String getResponse(String input) {
            try {
                Command inputCommand = Parser.parse(input);
                String response = inputCommand.execute(taskList, ui, storage);
                if (input.equals("bye")) {
                    Platform.exit();
                }
                return response;
            } catch (DukeException err) {
                return err.getMessage();
            }
    }


    public static void main(String[] args) {
        new Duke().run();
    }
}
