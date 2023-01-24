package duke;

public class Duke {
    public static void main(String[] args) {
        Ui ui = new Ui();
        ui.showWelcome();
        TaskList taskList = Storage.load();
        Parser parser = new Parser();
        boolean isExit = false;
        while (!isExit) {
            String userLine = ui.readCommand();
            isExit = parser.parse(ui, taskList, userLine);
            Storage.store(taskList);
        }
    }
}
