package duke;

import duke.taskType.TaskList;
import duke.commands.*;

/**
 * The main class of this task list management bot.
 */
public class Duke {
    private TaskList lst;
    private Ui ui;
    private Storage storage;

    /**
     * The default and only constructor of Duke class.
     * Initialize UI, Storage, and the list to store tasks.
     *
     * @param filePath
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.lst = storage.load();
    }

    /**
     * The main method to run the bot.
     */
    public void run() {
        ui.hello();

        while (true) {
            String cmd = ui.readCMD();
            ui.printLine();
            Command command = Parser.parse(cmd);
            command.operate(lst, ui, storage);
            ui.printLine();
            System.out.println();
            if (command.isBye()) break;
        }
    }

    /**
     * The main() method.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Duke("tasklist.txt").run();
    }
}
