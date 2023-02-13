package duke;

import java.io.File;

import parser.Parser;
import storage.Storage;
import tasklist.TaskList;
import ui.Ui;

/**
 * A chatbot that assists the user in keeping track of his tasks.
 */
public class Duke {
    private static final String DEFAULT_PATH = System.getProperty("user.dir")
            + File.separator + "data" + File.separator + "duke.txt";
    private Storage storage;
    private Ui ui;
    private TaskList list;
    private Parser parser;
    /**
     * Constructor.
     *
     * @param path Path to file.
     */
    public Duke(String path) {
        this.ui = new Ui();
        this.storage = new Storage(path);
        this.list = this.storage.load();
        this.parser = new Parser();
    }

    /**
     * Another Constructor.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage(DEFAULT_PATH);
        this.list = this.storage.load();
        this.parser = new Parser();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    public String getResponse(String input) {
        return this.parser.parse(input, this.ui, this.list, this.storage);
    }


}






