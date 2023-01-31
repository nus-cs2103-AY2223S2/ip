package duke;

import task.TaskList;

import java.io.IOException;

public class Duke {
    private final static String SAVED_PATH = "data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private final Parser parser;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        TaskList taskList;
        ui.showWelcomeMessage();
        try {
            taskList = new TaskList(storage.load());
        } catch (DukeException e) {
            Ui.showLoadingErrorMessage();
            taskList = new TaskList();
        }
        parser = new Parser(ui, storage, taskList);
    }

    public void run() throws DukeException {
        boolean isContinueRunning = true;

        while (isContinueRunning) {
            try {
                String input = ui.requestUserInput();
                isContinueRunning = parser.readInput(input);
            } catch (DukeException | IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke(SAVED_PATH).run();
    }
}
