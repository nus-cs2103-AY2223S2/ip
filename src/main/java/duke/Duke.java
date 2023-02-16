package duke;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;

public class Duke  {
    private Storage storage;
    private Tasklist tasks;
    private Ui ui;
    private Parser parser;

    public Duke(String filepath) throws IOException, ParseException {
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

    public String feedingIntoInterface(String userInput) throws DukeException, FileNotFoundException, ParseException {
        return this.parser.parse(userInput, tasks, ui, storage);
    }

    public static void main(String[] args) throws DukeException, IOException {
    }
}
