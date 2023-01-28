import java.io.FileNotFoundException;
import java.io.IOException;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String directoryPath, String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(directoryPath, filePath);
        try {
            this.tasks = storage.load();
        } catch (DukeException | FileNotFoundException e){
            ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    public void run() {
        ui.showGreetingsMessage();
        boolean isExit = false;
        while (!isExit) {
            try {
                isExit = ui.acceptCommand(tasks);
            } catch (Exception e) {
                ui.showErrorMessage(e);
            }
        }
        try {
            storage.saveTasksInFile(tasks);
        } catch (IOException e) {
            ui.showSavingError();
        }
        ui.showGoodbyeMessage();
    }

    public static void main(String[] args) {
        new Duke("data", "data/tasks.txt").run();
    }

}
