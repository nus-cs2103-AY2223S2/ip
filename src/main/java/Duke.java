import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.IOException;

import java.time.LocalDate;

import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    /** Utility class instances */
    private Ui ui;
    private Storage storage;
    private Parser parser;
    private TaskList list;

    /**
     * Constructor for Duke class.
     * @param filePath Path to file containing stored Duke data (task list). Must not have extension.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            list = storage.load();
            if (list.getSize() != 0) {
                new ListCommand().execute(ui, list, "");
            }
        } catch (DukeException de) {
            ui.pixlPrintException(de);
            ui.pixlPrint("Creating a new list...");
            list = new TaskList();
        }
    }

    /**
     * Runs the Duke program.
     */
    public void run() {
        ui.open();
        boolean isExit = false;
        while (!isExit) {
            try {
                String commandStr = ui.getNewCommand();
                Command command = parser.parse(commandStr);
                command.execute(ui, list, commandStr);
                isExit = parser.getIsExit();
            } catch (DukeException de) {
                ui.pixlPrintException(de);
            }
        }
        try {
            storage.save(list);
        } catch (DukeException de) {
            ui.pixlPrintException(de);
        }
        ui.close();

    }

    public static void main(String[] args) {
        new Duke("src/data/Duke").run();
    }
}
