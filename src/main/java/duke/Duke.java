package duke;

import java.io.IOException;

import duke.exception.DukeException;
import duke.helper.Parser;
import duke.helper.TaskList;
import duke.helper.Ui;
import duke.storage.FileSystem;

/**
 * Main class of the project
 */
public class Duke {

    private final Ui ui;
    private Parser parser;
    private FileSystem db;
    private TaskList tasks;

    /**
     * Constructor for Duke class
     *
     */
    public Duke() {
        this.ui = new Ui();

        try {
            db = new FileSystem("data/dukeTasks.txt");
            this.tasks = new TaskList(db.loadFromFile());
            this.parser = new Parser(tasks, ui);
        } catch (DukeException | IOException e) {
            System.out.println(e);
        }
    }

    /**
     * Gets Duke's response
     *
     * @param input User's input
     * @return String to be printed in GUI
     */
    public String getResponse(String input) {
        String output = "";

        try {
            output = this.parser.parseInputs(input);
        } catch (DukeException e) {
            System.out.println(e);
        } catch (NumberFormatException e) {
            ui.showErrorMsg(input);
        } catch (IndexOutOfBoundsException e) {
            ui.showErrorMsg(tasks.getTasks().size());
        } finally {
            db.updateFile(tasks);
        }
        return output;
    }
}
