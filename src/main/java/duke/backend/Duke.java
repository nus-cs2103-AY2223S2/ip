package duke.backend;

import duke.tasks.Task;

import java.io.IOException;

public class Duke {
    private TaskList tasklist;
//    private Ui ui;
private Parser parser;
    public Duke(TaskList tasklist) {
        this.tasklist = tasklist;
//        this.ui = ui;
        this.parser = new Parser(this.tasklist);
    }

    public static void main(String[] args) throws IOException {
        TaskList taskManager = new TaskList();
//        Ui ui = new Ui(taskManager);
//        ui.welcome();
        //  Flag for program termination
        boolean isProgramEnded = false;

    }

    public String getResponse(String input) {
        return parser.parse(input);
        //TODO: If response = "BYE. Hope to see you..", then close the program.
    }
}
