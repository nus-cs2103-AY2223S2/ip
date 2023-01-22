import command.Command;
import main.Parser;
import main.Storage;
import main.TaskList;
import main.Ui;
import sebastianExceptions.*;

public class Sebastian {

    private final Storage storage;
    private TaskList tasks;
    private final Ui ui;

    public Sebastian(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = storage.formTaskListFromData();
        } catch (CannotLoadDataException e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public void run() {
        ui.greet();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                Command c = Parser.parse(fullCommand);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (SebastianException e) {
                ui.showError(e.getMessage());
            }
        }

    }


    public static void main(String[] args) {
        new Sebastian("SebastianData.txt").run();
    }
}
