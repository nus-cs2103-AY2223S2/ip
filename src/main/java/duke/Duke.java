package duke;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private final Ui UI;
    private TaskList tasks;

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
                storage.write("data/duke.txt", tasks);
            } catch (DukeException e) {
                response = e.toString();
            } catch (IOException e) {
                e.toString();
            }
        } else {
            try {
                response = Parser.parse(input, tasks);
            } catch (DukeException e) {
                return e.toString();
            }
        }
        return response;
    }

    /**
     * constructs a Duke object.
     * @throws DukeException if file is not found.
     */
    public Duke() throws DukeException {
        storage = new Storage("data/duke.txt");
        UI = new Ui();
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            UI.showError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        }
    }
}