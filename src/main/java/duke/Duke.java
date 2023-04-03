package duke;

import duke.commands.Command;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.TextUi;

/**
 * Represents a Duke object
 */
public class Duke {

    private TextUi ui;
    private TaskList taskList;
    private Storage storage;

    /**
     * A constructor to initialize a Duke object.
     */
    public Duke() {
        this.ui = new TextUi();
        this.storage = new Storage();
        this.taskList = new TaskList(storage.loadData());

    }

    public void runCommandLoop() {
        boolean isExit = false;
        while (!isExit) {
            String userInput = ui.getUserCommand();
            Command command = new Parser().parseCommand(userInput);
            command.execute(taskList, storage, ui);
            isExit = command.isExit();
        }
    }

    public void exit() {
        System.exit(0);
    }


    /**
     * Runs this Duke program.
     */
    public void run() {
        ui.printGreetings();
        runCommandLoop();
        exit();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
