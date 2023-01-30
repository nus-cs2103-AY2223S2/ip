import java.util.Scanner;

import duke.commands.Command;
import duke.commands.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The Duke class implements a personal assistant chatbot that helps the user to keep track of various tasks.
 *
 * @author Chia Jeremy
 */

public class Duke {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    /**
     * Initializes a new Duke instance.
     *
     * @param filePath the location of the storage file
     */
    public Duke(String filePath) {
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
        this.ui = new Ui();
    }

    /**
     * Executes the Duke chatbot.
     */
    public void run() {
        this.ui.greet(LOGO);
        Scanner scn = new Scanner(System.in);
        Command command;
        do {
            String input = scn.nextLine();
            command = new Parser().parseCommand(input);
            command.execute(this.storage, this.tasks, this.ui);
        } while (!(command instanceof ExitCommand));
        scn.close();
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
