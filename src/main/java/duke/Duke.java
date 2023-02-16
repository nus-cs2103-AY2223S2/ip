package duke;

import java.util.Scanner;

/**
 * Duke class
 */
public class Duke{

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for duke
     * @param filePath file path of existing txt file with tasks stored inside
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    public String getResponse(String input) {
        String response = "";
        if (input.equals("bye")) {
            try {
                response = Parser.parse(input, tasks);
            } catch (DukeException e) {
                response = e.getMessage();
            }
        } else {
            try {
                response = Parser.parse(input, tasks);
                storage.save(tasks);
            } catch (DukeException e) {
                response = e.getMessage();
            }
        }
        return response;
    }
}

