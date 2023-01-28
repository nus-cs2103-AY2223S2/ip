package berry;

import berry.command.Command;
import berry.exception.BerryException;
import berry.exception.IllegalValueException;
import berry.storage.Storage;
import berry.task.TaskList;
import berry.ui.Ui;
import berry.parser.Parser;

import java.io.FileNotFoundException;

public class Berry {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    public Berry(String filePath) throws Storage.InvalidStorageFilePathException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (BerryException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws IllegalValueException {
        new Berry("data/tasks.txt").run();
    }
}
