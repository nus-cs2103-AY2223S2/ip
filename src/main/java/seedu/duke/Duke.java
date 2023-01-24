package seedu.duke;

/**
 * Represents the Duke bot.
 * The Duke bot as a tasklist, a storage, a user interface and a parser.
 */
public class Duke {
    TaskList taskList;
    Storage storage;
    Ui ui;
    Parser parser;

    /**
     * Creates a Duke object.
     */
    public Duke() {
        this.storage = new Storage();
        this.taskList = this.storage.read();
        this.ui = new Ui();
        this.parser = new Parser();
    }

    /**
     * Parses the user input and prints out Duke's responses to the console.
     */
    public void run() {
        ui.spawnBot();
        while (!parser.isBye) {
            parser.parser(taskList, storage, ui, ui.userCommand());
        }
    }

    /**
     * Runs the Duke bot.
     *
     * @param args runs the Duke bot
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}



