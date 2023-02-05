import parser.Parser;

import java.io.IOException;
import exception.WillyException;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class Willy {

    private Storage storage;
    private TaskList tList;
    private Ui ui;

    public Willy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tList = new TaskList(storage); // load data done in storage class
    }

    public void run() throws IOException, WillyException {
        ui.showStartupMessage();
        boolean isExit = false;

        while (!isExit) {
            String command = ui.getCommand();
            Parser p = new Parser(tList);
            p.parseCommand(command);
            isExit = p.getExitStatus();
        }
    }

    public static void main(String[] args) throws IOException, WillyException {
        new Willy("data/Willy.txt").run();
    }
}
