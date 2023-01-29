package duke;

import java.io.IOException;

public class Duke {

    public Ui ui;
    public Storage storage;
    public TaskList listOfTasks;

    public Duke()  {
        ui = new Ui();
        storage = new Storage("/Users/kristen/Documents/NUS/CS2109S/ip/data/duke.txt");

        try {
            listOfTasks = new TaskList();
            listOfTasks.allTasks = storage.loadFile(listOfTasks.getTasks());

        }  catch (IOException i) {
        }
    }

    public void run(String[] launchArgs) {
        start(launchArgs);
    }
    private void start(String[] launchArgs) {
        ui.greet();
        String input = ui.getInput();
        while(!input.equals("bye")) {
            ui.showLine();
            ui.startProgram(input, listOfTasks, storage);
            ui.showLine();
            input = ui.getInput();
        }
    }

    public static void main(String[] args) {
        new Duke().run(args);

    }


}
