package duke;

import java.io.*;

import java.time.format.DateTimeParseException;

import duke.command.*;
import duke.exception.*;
import duke.task.*;
import duke.util.*;


public class Duke {
    private TaskList taskList;
    private final Storage storage;
    private final Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        ui.printWelcome();
        storage = new Storage(filePath, ui);
        try {
            taskList = storage.load(filePath);
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
    }

    public void run() {
        boolean terminate = false;

        while (!terminate) {
            try {
                ui.printLine();

                String input = ui.readCommand();
                Command cmd = Parser.parse(input, taskList, ui, storage);
                terminate = cmd.execute();

            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }
    public static void main(String[] args)  {
        new Duke("./data/duke.txt").run();
    }
}
