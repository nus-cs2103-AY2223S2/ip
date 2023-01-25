package duke;

public class Duke {
    public static void main(String[] args) {
        String SAVE_FOLDER = "data/save.txt";

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
