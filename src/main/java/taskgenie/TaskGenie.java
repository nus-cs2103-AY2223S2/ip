package taskgenie;

import java.io.IOException;

import commands.Command;
import exceptions.TaskGenieException;
import parser.Parser;
import storage.Storage;
import storage.TaskList;
import ui.Ui;

/**
 * This program allows you to keep track of your upcoming To Dos, Deadlines and Events.
 */
public class TaskGenie {
    private Ui ui;
    private TaskList tasks;
    private Storage storage;

    /**
     * Constructor for the TaskGenie file
     * @param filePath The file path given in String.
     */
    public TaskGenie(String filePath) {
        assert filePath != null;
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.tasks = new TaskList(storage.load());
    }

    /**
     * Run TaskGenie with the correct file path.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        new TaskGenie("data/taskGenie.txt").run();
    }

    /**
     * Runs the TaskGenie program.
     */
    public void run() {
        boolean isContinueConvo = true;
        while (isContinueConvo) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parseCommand(fullCommand);
                c.execute(tasks, ui, storage);
                isContinueConvo = c.isContinueConvo();
            } catch (TaskGenieException e) {
                ui.showError(e);
            } catch (IOException e) {
                e.printStackTrace();
            }
            ui.showLine();
        }
    }

    /**
     * Returns the response based on the user input.
     * @param input User input.
     * @return Response based on the user input.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            return c.execute(tasks, ui, storage);
        } catch (TaskGenieException e) {
            return e.getMessage();
        }
    }
}
