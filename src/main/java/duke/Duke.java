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
     * constructs a Duke object with a file path
     * leading to the data text that stores the tasks.
     *
     * @param filePath relative path to data directory for storing tasks.
     * @throws DukeException
     */
    /*public Duke(String filePath) throws DukeException {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            UI.showError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        }
    }*/

    /**
     * starts up the Duke chatbot.
     */
    /*public void run() throws DukeException, IOException {
        UI.showWelcome();
        String cmd = UI.readCommand();
        boolean isTerminated = false;
        while (!isTerminated) {
            if (!cmd.equals("bye")) {
                try {
                    Parser.parse(cmd, tasks);
                    cmd = UI.readCommand();
                } catch (DukeException e) {
                    System.out.println("something went wrong.");
                }
            } else {
                isTerminated = true;
                storage = new Storage("data/duke.txt");
                storage.write("data/duke.txt", tasks);
                UI.showBye();
            }
        }
    }*/

    /*public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/duke.txt").run();
    }*/

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