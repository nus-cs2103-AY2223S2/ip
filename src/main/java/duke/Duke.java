package duke;

import duke.command.Command;
import duke.task.TaskList;
import duke.util.Parser;
import duke.util.Storage;
import duke.util.Ui;

/**
 * The Duke class connects all the components to form the main chatbot logic.
 * @author Junyi
 */
public class Duke {

    private final TaskList taskList;
    private final Storage storage;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor for Duke chatbot.
     * @param filePath Path for data storage file.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList tasks;
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        taskList = tasks;

        parser = new Parser(taskList, storage, ui);
    }

    /**
     * Starts accepting user commands and responding.
     */
    public void run() {
        ui.showWelcomeMessage();

        boolean toExit = false;
        while (!toExit) {
            try {
                String cmd = ui.requestUserInput();
                Command command = parser.parseUserCommand(cmd);
                toExit = command.execute();
            } catch (DukeException e) {
                System.out.println(e);
            }
        }

        ui.close();
    }

    /**
     * Entry point for Duke.
     * @param args Not used.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("./data/data.txt");
        duke.run();
    }
}
