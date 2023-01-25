import java.io.*;

import java.time.format.DateTimeParseException;

import command.*;
import exception.*;
import task.*;
import util.*;


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
            } catch (IllegalArgumentException e) {
                System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            } catch (DateTimeParseException e) {
                System.out.println("    ☹ OOPS!!! Invalid datetime. Syntax: YYYY-MM-DD");
            }
        }
    }
    public static void main(String[] args)  {
        new Duke("./data/duke.txt").run();
    }
}
