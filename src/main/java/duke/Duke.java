package duke;

import java.io.IOException;

import commands.Command;
import parser.Parser;
import storage.Storage;
import tasks.TaskList;
import ui.Ui;



/**
 * The main class to run the program
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for duke
     * @throws IOException
     */
    public Duke() throws IOException {
        ui = new Ui();
        storage = new Storage("./data/duke.txt");
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    /**
     * Duke constructor
     * @param filePath
     * @throws IOException
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            tasks = new TaskList();
        }
    }

    public String greet() {
        return ui.printGreet();
    }

    public String lastSession() {
        return ui.lastSession();
    }

    public boolean isTaskEmpty() {
        return tasks.getSize() == 0;
    }

    public String getResponse(String command) {
        try {
            Command c = Parser.parse(command);
            c.execute(tasks, ui, storage);
            return c.generate(tasks, ui, storage);
        } catch (Exception e) {
            return ui.errParse();
        }
    }

    /**
     * Function to start the program
     * @throws IOException
     */
    public void run() throws IOException {
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
