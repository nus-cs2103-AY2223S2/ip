package duke.main;

import java.io.*;

/**
 * Runs the app.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static final String filePath = "data/tasks.txt";

    /**
     * Initialises Storage, TaskList and Ui.
     * Previous tasks are loaded up.
     * Loading error will be shown if file cannot be found/ generated.
     */
    public Duke() {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTxtFile());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the app.
     *
     * @param input Command input by user.
     * @return Response by Duke.
     */
    String getResponse(String input) throws IOException {

        Storage storageArchive = new Storage("data/archive.txt");
        storageArchive.loadTxtFile();

        while (!input.equals("bye")) {
            Parser parser = new Parser();
            return parser.parse(input, ui, tasks, storage, storageArchive);
        }

        return ui.printByeMessage();
    }

}
