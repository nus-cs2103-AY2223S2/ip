package duke;

import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    public Duke() {
        ui = new Ui();
        storage = new Storage();
        tasks = new TaskList();
        parser = new Parser(tasks);
    }

    public void run() {
        ui.greet();
        parser.performCommand();
        storage.save(this.tasks);
    }


    public static void main(String[] args) {
        new Duke().run();

    }
}
