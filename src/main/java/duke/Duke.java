package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * receive input and converts it to appropriate response to be parsed.
     * @param input input entered by user.
     * @return a String to be parsed.
     */
    public String getResponse(String input) {
        String response = "";
        if (input.equals("bye")) {
            try {
                response = Parser.parse(input, tasks);
                storage.write("./data/duke.txt", tasks);
            } catch (DukeException e) {
                response = e.toString();
            } catch (IOException e) {
                e.toString();
            }
        } else {
            try {
                response = Parser.parse(input, tasks);
            } catch (Exception e) {
                return e.toString();
            }
        }
        return response;
    }

    /**
     * constructs a Duke object.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}