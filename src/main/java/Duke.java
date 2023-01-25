import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
    private Ui ui;
    public static boolean isExit;

    public TaskList tasks;
    public Duke(String filepath) {
        this.ui = new Ui();
        this.storage = new Storage(filepath);
        try {
            this.tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            this.tasks = new TaskList();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }

    public void start() {
        ui.welcomeMessage();
        ui.showListFromStorage(storage);
        isExit = false;
        while (!isExit) {
            try {
                String command = ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(tasks, storage, ui);
                isExit = c.isExit();
            } catch (DukeException e) {
                ui.showError(e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        String filepath = System.getProperty("user.home") + "/data/data.txt";
        File file = new File(filepath);
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        new Duke(filepath).start();
    }
}
