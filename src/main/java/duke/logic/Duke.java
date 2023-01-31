package duke.logic;

import duke.DukeException;
import duke.gui.Ui;
import javafx.application.Application;

/**
 * Duke class containing all logic components of Duke.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructor for Duke object.
     *
     * @param filePath String path of data text file to load data from and save to.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            storage.load(tasks);
        } catch (DukeException e) {
            System.out.println("loading error");
        }
    }

    public String getResponse(String input) {
        String[] parsed = Parser.parse(input);
        return Parser.execute(parsed[0], parsed[1], tasks);
    }

    /**
     * Start the Duke task tracker software.
     */
    public void save() {

        try {
            storage.save(tasks);
            System.out.println("Saved");
        } catch (DukeException e) {
            System.out.println("saving error");
        }
    }

}
