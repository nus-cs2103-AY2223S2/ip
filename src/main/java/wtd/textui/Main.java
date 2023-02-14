package wtd.textui;

import wtd.command.Command;
import wtd.exceptions.WtdException;

import wtd.Parser;
import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;

/**
 * Wtd is a personal assistant chatbot that helps a person to keep track of various things.
 * 
 * @author Tan Yu Fei
 * @version 0.1
 * @since 2022-01-25
 */
public class Main {
    /** The storage object that handles the loading and saving of tasks. */
    private Storage storage;
    /** The task list that contains the tasks. */
    private TaskList tasks;
    /** The ui object that handles the user interface. */
    private Ui ui;

    /**
     * Constructor for Wtd.
     * 
     * @param filePath the path of the file that contains the tasks.
     */
    public Main(String filePath) {
        ui = new Ui();
        try {
            storage = new Storage(filePath);
            tasks = new TaskList(storage.load());
        } catch (Exception e) {
            ui.showError(e.getMessage());
            tasks = new TaskList();
        }
    }

    /**
     * Runs the Wtd program.
     */
    public void run() {
        System.out.print(ui.getWelcome());
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                String ret = c.execute(tasks, ui, storage);
                System.out.print(ret);
                isExit = c.isExit();
            } catch (WtdException e) {
                System.out.print(ui.showError(e.getMessage()));
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Main("data/wtd.txt").run();
    }

    /**
     * Gets the response from Wtd.
     * 
     * @param input the input from the user.
     * @return the response from Wtd.
     */
    public String getResponse(String input) {
        return "Wtd heard: " + input;
    }
}
