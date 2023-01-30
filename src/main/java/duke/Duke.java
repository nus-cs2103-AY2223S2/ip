package duke;

import duke.command.Command;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {

    public Ui ui;
    public Storage storage;
    public TaskList listOfTasks;

    public Parser parser;

    public Duke()  {
        ui = new Ui();
        storage = new Storage("/Users/kristen/Documents/NUS/CS2109S/ip/data/duke.txt");
        parser = new Parser(ui);

        try {
            listOfTasks = new TaskList();
            listOfTasks.allTasks = storage.loadFile(listOfTasks.getTasks());

        }  catch (IOException i) {
        }
    }

    public void run(String[] launchArgs) throws FileNotFoundException {
        start(launchArgs);
    }

    private void start(String[] launchArgs) throws FileNotFoundException {
        boolean hasExit = false;
        ui.greet();
        while(!hasExit) {
            String input = ui.getInput();
            ui.showLine();
            Command c = parser.commandExecute(input, listOfTasks, storage);
            c.executeCommand(listOfTasks, storage, ui);
            ui.showLine();

            hasExit = c.isExit();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        new Duke().run(args);
    }


}
