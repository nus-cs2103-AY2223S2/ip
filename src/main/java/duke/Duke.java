package duke;

import java.util.Objects;

import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


/**
 * A class for managing tasks
 */
public class Duke {
    private Ui ui;
    private TaskList tl;
    private Storage storage;
    private Parser parser;

    /**
     * Creates the necessary objects and populate the tasklist to run Duke
     */
    public Duke() {
        this.ui = new Ui();
        this.tl = new TaskList();
        this.storage = new Storage();
        this.parser = new Parser();
        this.storage.populate(this.tl);
    }

    /**
     * @return String version of introduction
     */
    public String getIntro() {
        return this.ui.getIntro();
    }

    /**
     * Returns the response when given a non null user input
     *
     * @param input User input string
     * @return Output string
     */
    public String getResponse(String input) {
        assert input != null;
        try {
            Command command = this.parser.parse(input);
            if (Objects.equals(command, null)) {
                return this.ui.getOutro();
            }
            return command.execute(tl, ui, storage);
        } catch (DukeException e) {
            return e.toString();
        }
    }

}
