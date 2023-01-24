package duke;

import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class Duke {
    private Parser p;
    private Ui ui;
    private Storage storage;
    private TaskList tasks;

    public Duke() {
        ui = new Ui();
        storage = new Storage("./data", "duke.txt");
        tasks = new TaskList(ui, storage);
        p = new Parser(ui, tasks);
        tasks.init();
    }

    public void run() {
        boolean continueLoop = true;
        while (continueLoop) {
            String input = ui.nextLine();
            continueLoop = p.processInput(input);
        }
    }

    public static void main (String[] args) {
        new Duke().run();
    }
}

