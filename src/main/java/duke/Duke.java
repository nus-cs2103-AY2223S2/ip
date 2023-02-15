package duke;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke  {
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filepath) throws IOException {
        ui = new Ui();
        parser = new Parser();
        storage = new Storage(filepath);
        try {
            tasks = new Tasklist(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new Tasklist();
        }
    }

    public String run() {
        return this.ui.welcome();
    }

    public String feedingIntoInterface(String userInput) throws DukeException, FileNotFoundException {
        return this.parser.parse(userInput, tasks, ui, storage);
    }

    public static void main(String[] args) throws DukeException, IOException {
        Duke duke = new Duke("data/tasks.txt");
    }
}
