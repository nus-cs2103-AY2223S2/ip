import java.io.FileNotFoundException;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private TextUi ui;

    public Duke(String filePath) {
        ui = new TextUi();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadFromFile());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
            ui.showGreetMessage();
            boolean isExit = false;
            while (!isExit) {
                try {
                    String fullCommand = ui.readUserCommand();
                    Command c = Parser.parse(fullCommand);
                    c.execute(tasks, ui, storage);
                    isExit = c.isExit();
                } catch (DukeException e) {
                    ui.showErrorMessage(e.getMessage());
                }
            }
        }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}