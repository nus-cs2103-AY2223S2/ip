package peppa;

import peppa.commands.*;

/**
 * Represents the chatbot application.
 */
public class Peppa {
    private Ui screen;
    private TaskList tasks;
    private Storage storage;

    public static final String DIR_PATH = "data";


    /**
     * Starts the chatbot application.
     */
    public Peppa() {
        this.screen = new Ui();
        this.tasks = new TaskList();
        this.storage = new Storage(this, DIR_PATH);
    }

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

    public boolean isFileSet() {
        return this.storage.getFile() != null;
        //storage.loadData(tasks, screen);
    }

    public void setTasks(TaskList tasks) {
        this.tasks = tasks;
    }
}
