package seedu.duke;

import java.io.FileNotFoundException;

/**
 * The Duke chatbot
 */
public class Duke {

    private final Storage storage;
    private ToDoList todolist;
    private Ui ui;
    private final Parser parser;
    private boolean isBye;

    /**
     * Duke Constructor.
     *
     * @param dataPath
     */
    public Duke(String dataPath) {
        storage = new Storage(dataPath);
        todolist = new ToDoList();
        ui = new Ui();
        parser = new Parser();
        isBye = false;

        // Print previous data
        try {
            storage.loadTasks(todolist);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    /**
     * Starts the Duke chatbot.
     */
    public void run() {
        AsciiArt asciiArt = new AsciiArt();
        asciiArt.printArt();

        System.out.println("\nPlease enter a command Mr Stark.");

        while (!isBye) {
            String line = ui.getNextCommand();
            isBye = parser.parse(line, todolist, storage);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/jarvis.txt").run();
    }
}
