package fideline;

import fideline.exception.*;
import fideline.task.TaskManager;
import fideline.save.Storage;
import fideline.user.Ui;
import fideline.user.Parser;
import fideline.execution.Command;

public class Fideline {
    private TaskManager taskManager;
    private Storage storage;
    private Ui ui;



    public Fideline(String filePath) throws CorruptedDataFileException,
            UnableToCreateDataFileException {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            taskManager = new TaskManager(storage.load());
        } catch (DataFileNotFoundException e) {
            ui.loadError(e.getMessage());
            taskManager = new TaskManager();
            storage.createDataFile();
        }
    }

    public void run() {
        ui.showLine();
        ui.hello();
        ui.showLine();
        boolean isExit = false;
        while (!isExit) {
            try {
                String userInput = ui.getNextCommand();
                ui.showLine();
                Command c = Parser.getCommand(userInput);
                c.execute(taskManager, storage, ui);
                isExit = c.isExit();
            } catch (FidelineException e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) throws CorruptedDataFileException,
            UnableToCreateDataFileException {
        new Fideline("./task-data.txt").run();
    }
}