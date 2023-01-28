package seedu.duke;

import java.io.FileNotFoundException;

public class Duke {

    private final Storage STORAGE;
    private ToDoList todolist;
    private Ui ui;
    private final Parser PARSER;
    boolean isBye;

    public Duke(String dataPath) {
        STORAGE = new Storage(dataPath);
        todolist = new ToDoList();
        ui = new Ui();
        PARSER = new Parser();
        isBye = false;

        // Print previous data
        try {
            STORAGE.loadTasks(todolist);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public void run() {
        AsciiArt asciiArt = new AsciiArt();
        asciiArt.printArt();

        System.out.println("\nPlease enter a command Mr Stark.");

        while(!isBye) {
            String line = ui.getNextCommand();
            isBye = PARSER.parse(line, todolist, STORAGE);
        }
    }

    public static void main(String[] args) {
        new Duke("./data/jarvis.txt").run();
    }
}
