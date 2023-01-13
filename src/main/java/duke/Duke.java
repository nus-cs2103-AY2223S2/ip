package duke;

import duke.display.Ui;
import duke.exception.*;
import duke.command.*;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private final Storage storage;
    private TaskList list;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        list = new TaskList();

        try {
            list = storage.load();
            System.out.println(list);
        } catch (FileNotFoundException e) {
            ui.displayWithBar("Warning: something went wrong when loading the TaskList\n" +
                    e.getMessage());
            list = new TaskList();
        } catch (InvalidInputException e) {
            ui.displayWithBar(e.getMessage());
            list = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(list, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.displayWithBar(e.getMessage());
            } catch (IOException e) {
                ui.displayWithBar("Warning: there is something wrong when saving ...\n"
                        + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
