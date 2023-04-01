package peppa;

import peppa.commands.Command;
import peppa.commands.ExitCommand;
import peppa.commands.FilesCommand;
import peppa.commands.IncorrectCommand;
import peppa.commands.InvalidCommand;
import peppa.commands.SelectCommand;

/**
 * Represents the chatbot application.
 */
public class Peppa {
    public static final String DIR_PATH = "data";
    private Ui screen;
    private TaskList tasks;
    private Storage storage;

    /**
     * Starts the chatbot application.
     */
    public Peppa() {
        this.screen = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(this, DIR_PATH);
    }

    /**
     * Fetches the appropriate response from the chatbot depending on the user input.
     *
     * @param input User input.
     * @return Chatbot response message.
     */
    public String getResponse(String input) {
        try {
            Command c = Parser.parseCommand(input);
            assert (c != null);
            boolean isValidCommandWithoutFile = c instanceof FilesCommand
                    || c instanceof SelectCommand
                    || c instanceof ExitCommand
                    || c instanceof IncorrectCommand
                    || c instanceof InvalidCommand;
            if (!isFileSet() && !isValidCommandWithoutFile) {
                throw new PeppaException("Boink! Peppa did not detect a data source. "
                        + "Please choose a file to load data from and try again.\n ");
            }
            return c.execute(tasks, screen, storage);
        } catch (PeppaException e) {
            return e.getMessage();
        }
    }

    /**
     * Checks if the current chatbot session has a data source defined.
     *
     * @return True if data file is defined, false otherwise.
     */
    public boolean isFileSet() {
        return this.storage.getFile() != null;
    }

    /**
     * Populates the tasklist of the current chatbot session.
     *
     * @param tasks Tasks to populate the tasklist with.
     */
    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }
}
