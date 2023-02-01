package duke;

import duke.commands.Command;
import duke.database.Database;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class Duke {

    private final Database database;
    private TaskList tasks;
    private final Ui ui;
    private static final String FRAME = "    ____________________________________________________________\n";
    private boolean isActive;
    private final Parser parser;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.database = new Database(filePath);
        this.isActive = true;
        this.parser = new Parser();
        try {
            tasks = new TaskList(database.load());
        } catch (DukeException e) {
            tasks = new TaskList();
        }
    }

    public void run() {
        System.out.println(FRAME +
                "     Hello! I'm Duke\n" +
                "     What can I do for you?\n" +
                FRAME);
        while (this.isActive && this.ui.hasNextCommand()) {
            String command = this.ui.nextCommand();
            try {
                Command nextCommand = this.parser.parse(command, this.tasks.length());
                nextCommand.execute(this.tasks, this.ui, this.database);
                this.isActive = nextCommand.isActive;
            } catch (DukeException e) {
                ui.response(e.getLocalizedMessage());
            } finally {
                ui.showResponse();
            }
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        new Duke("data/tasks.txt").run();
    }
}
