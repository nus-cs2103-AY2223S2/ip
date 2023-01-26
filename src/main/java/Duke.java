package duke;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.Parser;
import duke.command.Command;

import java.io.FileNotFoundException;
import java.time.format.DateTimeParseException;


public class Duke {
    static final String STR = "------------------------------------------------------------";

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadData());
            ui.successfulLoadResponse();
        } catch (FileNotFoundException e) {
            tasks = new TaskList();
            ui.loadingErrorMessage();
        }
    }

    public void run() {
        // Introduction
        ui.welcomeResponse();

        // Load data
        Duke duke = new Duke("./data/duke.txt");
        ui.listTaskResponse(this.tasks);

        boolean isExit = false;
        while (!isExit) {
            try {
                ui.askForTaskResponse();
                String fullCommand = ui.nextInput();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DateTimeParseException e2) {
                System.out.println("this");
                ui.unreadableCommandErrorMessage();
            } catch (IllegalArgumentException e3) {
                ui.incompleteCommandErrorMessage();
            } catch (ArrayIndexOutOfBoundsException e3) {
                ui.incompleteCommandErrorMessage();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }

}
