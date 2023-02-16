import java.io.*;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.loadTxtFile());
        } catch (IOException e) {
            ui.showLoadingError(e.getMessage());
            tasks = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }

    public void run() {

        ui.printGreetingMessage();

        boolean saidBye = false;
        while (!saidBye) {
            String command = ui.getCommand();
            Parser parser = new Parser();
            parser.parse(command, ui, tasks, storage);
        }
    }


}
