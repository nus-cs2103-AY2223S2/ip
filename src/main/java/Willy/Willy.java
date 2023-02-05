package Willy;

import Willy.parser.Parser;

import java.io.IOException;
import Willy.exception.WillyException;
import Willy.storage.Storage;
import Willy.task.TaskList;
import Willy.ui.Ui;

public class Willy {

    private Storage storage;
    private TaskList tList;
    private Ui ui;

    public Willy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tList = new TaskList(this.storage); // load data done in storage class
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
