package duke;
import duke.commands.Command;

public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Duke constructor.
     *
     * @param filePath The relative path of the text file that tasks are saved into.
     */
    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        try {
            this.tasks = storage.loadTaskList();
        } catch (Exception e) {
            this.ui.showLoadingError();
            this.tasks = new TaskList();
        }
    }

    /**
     * Runs the task list program.
     * Prints the welcome message on start.
     * Uses a while loop and accepts user input.
     * Input is converted into a Command object which is executed.
     * Exits from the while loop if the command is a ByeCommand.
     */
    public void run() {
        this.ui.showWelcomeMessage();
        boolean isExit = false;

        while (!isExit) {
            this.ui.showLine();

            try {
                String command = this.ui.readCommand();
                Command c = Parser.parse(command);
                c.execute(this.tasks, this.ui, this.storage);
                isExit = c.isExit();
            } catch (IllegalArgumentException e) {
                System.out.println("    input is not of the correct format!");
            }
        }
    }

    /**
     * Main method that creates an instance of Duke and calls the run() method
     */
    public static void main(String[] args) {
        new Duke("tasks.txt").run();
    }
}
