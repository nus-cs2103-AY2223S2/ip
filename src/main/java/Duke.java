import duke.command.Command;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * The main class to run the Duke App.
 *
 * @author SeeuSim
 * AY2223-S2 CS2103T
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke() {
        this.storage = Storage.create();
        this.ui = new Ui();
        this.tasks = TaskList.create(this.storage, this.ui);
    }

    /**
     * The main function that runs the Duke app.
     *
     * @param args Not used as of now. Used mainly to add command line
     *             arguments to the initial app run command.
     */
    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * The main method by which this store operates
     */
    private void run() {
        ui.welcomeMessage();
        boolean exit = false;
        while (!exit) {
            try {
                String command = ui.getCommand();
                Command c = Parser.parse(command);
                exit = c.isExit();
                c.execute(tasks, ui);
            } catch (IllegalStateException e) {
                System.exit(1);
            }
        }
    }
}
