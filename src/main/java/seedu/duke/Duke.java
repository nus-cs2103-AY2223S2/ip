package seedu.duke;
import java.util.ArrayList;
import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static ArrayList<Task> list = new ArrayList<>();
    /**
     * Constructs a Duke object and initializes the needed parameters.
     *
     * @param filePath file path of the tasks file.
     * @throws IOException.
     */
    public Duke(String filePath) throws IOException {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.load());
    }

    public void run() throws DukeException, IOException {
        //...
        boolean running = true;
        while (running) {
            try {
                running = ui.getInput(tasks);
                storage.updateFile(tasks);
            } catch (DukeException e) {
                throw new RuntimeException(e);
            }

        }
        storage.close();
    }
    public static void main(String[] args) throws DukeException, IOException {

        new Duke("duke.txt").run();

    }

}

