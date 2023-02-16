package duke;

import java.io.IOException;
import duke.command.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
                Command command = Parser.parse(input);
                command.execute(tasks, storage, ui);
                isEnd = command.checkEnd();
            } catch (DukeException e) {
                ui.showError(e);
            }
        }
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}