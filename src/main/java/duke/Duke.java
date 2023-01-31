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
        storage = new Storage("/Users/risten/Documents/NUS/CS2109S/ip/data/duke.txt");
        parser = new Parser();

        try {
            listOfTasks = new TaskList();
            listOfTasks.allTasks = storage.loadFile(listOfTasks.getTasks());

        }  catch (IOException i) {
            System.out.println(i);
        }
    }

    /**
     * Starts running the entire program.
     * Serves as the starting point of the Duke chatbot.
     * @throws FileNotFoundException the executeCommand of the EndCommand class needs save the data into file.
     */
    private void run() throws FileNotFoundException {
        boolean hasExit = false;
        ui.greet();

        while(!hasExit) {
            String input = ui.getInput();
            ui.showLine();
            Command c = parser.parse(input, listOfTasks);
            c.executeCommand(listOfTasks, storage, ui);
            ui.showLine();

            hasExit = c.isExit();
        }
    }

    /**
     * Main method of the program.
     * Create a Duke object and runs it.
     * @param args
     * @throws FileNotFoundException
     */
    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run();
    }

}
