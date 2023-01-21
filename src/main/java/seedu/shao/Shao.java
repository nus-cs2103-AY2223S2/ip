package seedu.shao;

import seedu.shao.parser.Parser;
import seedu.shao.storage.Storage;
import seedu.shao.tasklist.TaskList;
import seedu.shao.ui.Ui;

public class Shao {

    private Ui ui;
    private Parser parser;
    private Storage storage;
    private TaskList tasklist;

    public static void main(String[] args) {
        new Shao().run(args);
    }

    public void run(String[] args) {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage();
        tasklist = new TaskList();

        ui.greetUser();
        storage.getFile(tasklist, parser);
        ui.readInput(tasklist, storage, parser);
    }

}
