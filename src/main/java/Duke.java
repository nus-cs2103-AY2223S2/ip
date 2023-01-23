import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private static final Path STORAGE_LOCATION = Paths.get(".", "data", "storage.txt");

    public enum Tasks { TODO, DEADLINE, EVENT }

    private Storage storage;
    private TaskList taskList;
    private Ui ui;


    Duke(Path path) {
        this.taskList = new TaskList();
        this.ui = new Ui();
        try {
            this.storage = new Storage(path);
        } catch (IOException e){
            e.printStackTrace();
        }

    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show divider
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch (Exception e) {
                ui.showError(e.getMessage());
            } finally {
                ui.showLine();
            }
        }

    }

    public static void main(String[] args) {
        Duke duke = new Duke(STORAGE_LOCATION);
        duke.run();
    }
}
