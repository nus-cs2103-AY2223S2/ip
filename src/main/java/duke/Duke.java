package duke;

import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import java.io.IOException;


/**
 * The main class to run the program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public void run() throws IOException {
        ui.greet();
        boolean isExit = false;
        if (tasks.getSize() > 0) {
            ui.saveTaskMsg();
            tasks.print();
            ui.printLine();
        }
        while (!isExit) {
            String fullCommand = ui.readCommand();
            ui.printLine();
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            ui.printLine();
        }
    }

    public static void main(String[] args) throws IOException {
        new Duke("./data/duke.txt").run();
    }
}
