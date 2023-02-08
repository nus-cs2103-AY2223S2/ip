package seedu.duke;

import java.io.IOException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private Parser parser;

    /**
     * Loads the list of tasks stored in the text file given by 'filepath'.
     *
     * @param filePath the path of the stored text file
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.readFromFile());
        } catch (IOException e) {
            tasks = new TaskList();
            ui.showLoadingError();
        }
    }

    /**
     * Executes the program managing tasks until user inputs "bye".
     */
    public void run() {
        boolean isEnd = false;
        ui.showWelcome();

        while (!isEnd) {
            try {
                String input = ui.getInput();
                parser = new Parser();
                parser.parse(input, tasks, storage, ui);
                isEnd = parser.checkEnd(input);
            } catch (DukeException e) {
                ui.showGenericError();
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
