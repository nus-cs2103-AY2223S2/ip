package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;



/**
 * Duke helps user to keep track of tasks
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for Duke
     */
    public Duke() {
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser(tasks);
        ui = new Ui(parser);
    }

    /**
     * Greet user and perform command for the tasks they entered and save tasks in file
     */
    public String greet() {
        return ui.greet();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        String response = "";
        response = ui.generateReply(input);
        storage.save(this.tasks);
        return response;
    }


}
