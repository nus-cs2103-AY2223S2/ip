package duke;

import duke.tasks.Task;

import java.io.IOException;

/**
 * Represents Main class
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private boolean isActive;

    /**
     * The construction of duke
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        try {
            tasks = storage.load();
        } catch (IOException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * The method of run
     * Runs the whole program until exit
     */
    public void run() throws IOException {
        Ui.showLogo();
        Ui.showWelcome();

        String fullCommand;

        do {
            fullCommand = ui.readCommand();
            Parser.parse(fullCommand, tasks);
        } while(!fullCommand.equals("bye"));

        storage.updateFile(tasks);
        Ui.exit();
    }

    /**
     * The method of main
     * @param  args
     */
    public static void main(String[] args) throws IOException {
        //new Duke("data/tasks.txt").run();
    }


    public String getResponse(String input) throws IOException {
        String temp = "";


        try {
            if (input.equals("bye")) {
                temp += Ui.exit();
            } else {
                temp = Parser.parse(input, tasks);
                storage.updateFile(tasks);
            }

            return temp;
        } catch (Exception e) {
            return "Error";
        }
        //return Parser.parse(input, tasks);
    }
}
