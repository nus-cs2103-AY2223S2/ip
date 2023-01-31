package duke;

import task.TaskList;

import java.io.IOException;

public class Duke {
    private final static String SAVED_PATH = "data/tasks.txt";
    private Ui ui;
    private Storage storage;
    private final Parser parser;

    /**
     * Constructs a Duke object for program to run.
     * Initialises the ui, storage, parser and tasklist to be used for the program.
     *
     * @param filePath Path in which the file is located relative to project root.
     */
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

        parser = new Parser(storage, taskList);
    }

    /**
     * Accepts user input and checks whether program terminated or continues running.
     */
    public void run() {
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

    public static void main(String[] args) {
        new Duke(SAVED_PATH).run();
    }
}
