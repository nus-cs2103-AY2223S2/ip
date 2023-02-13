package duke;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    public Ui ui;
    public Storage storage;
    public TaskList listOfTasks;
    public Parser parser;

    /**
     * Constructor for the Duke Class.
     * Initializes Ui, Storage, Parser and TaskList while loading items found in the path file into it.
     */
    public Duke()  {
        ui = new Ui();
        ui.displayLogo();
        storage = new Storage("/Users/kristen/Documents/NUS/CS2109S/ip/data/duke.txt");
        parser = new Parser();
    }

    public void runInput(String input) throws FileNotFoundException {

        assert listOfTasks != null : "TaskList does not exist in Duke";
        Command c = parser.parse(input, listOfTasks, ui);
        c.executeCommand(listOfTasks, storage, ui);
    }

    /**
     * Set the MainWindow in ui and load task saved in storage
     * @param mainWindow
     */
    public void setMainWindow(MainWindow mainWindow) {

        assert mainWindow != null: "MainWindow not found in Duke";

        ui.setMainWindow(mainWindow);

        try {
            listOfTasks = new TaskList();
            listOfTasks.allTasks = storage.loadFile(listOfTasks.getTasks(), ui);

        }  catch (IOException i) {
            ui.printText("Remember that since the file cannot be loaded, you cannot save your file!");
        }
        ui.greet();
    }

}
