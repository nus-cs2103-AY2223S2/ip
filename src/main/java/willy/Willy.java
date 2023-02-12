package willy;

import java.io.IOException;

import willy.exception.WillyException;
import willy.parser.Parser;
import willy.storage.Storage;
import willy.task.TaskList;
import willy.ui.Ui;

/**
 * Represents the Willy class
 */
public class Willy {

    private Storage storage;
    private TaskList tList;
    private Ui ui;

    /**
     * Creates a Willy class with a specified storage location to init/read from
     * @param filePath
     */
    public Willy(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tList = new TaskList(this.storage); // load data done in storage class
    }

    /**
     * Run command to init parser and scan for next command
     * @throws IOException
     * @throws WillyException
     */
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

    /**
     * Main function loop to start the application
     * @param args
     * @throws IOException
     * @throws WillyException
     */
    public static void main(String[] args) throws IOException, WillyException {
        new Willy("data/Willy.txt").run();
    }
}
