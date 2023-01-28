package duke;

/**
 * Entry point
 */
public class Duke {
    static final String SAVE_FOLDER = "data/save.txt";

    public static void main(String[] args) {
        Ui ui = new Ui();
        Storage storage = new Storage(SAVE_FOLDER);
        ui.showWelcome();
        TaskList taskList = storage.load();
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            String userLine = ui.readCommand();
            isExit = parser.parse(ui, taskList, userLine);
            storage.store(taskList);
        }
    }
}
