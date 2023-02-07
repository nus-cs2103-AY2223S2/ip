package kuromi;

import kuromi.command.Command;
import kuromi.task.TaskList;

/**
 * The main class of Kuromi where the application is instantiated through this class. A <code>Kuromi</code> object
 * has three attributes: Storage, TaskList, and Ui that is needed to run the application.
 */
public class Kuromi {
    /** Access to the storage of tasks in hard disk **/
    private Storage storage;
    /** Current task list **/
    private TaskList tasks;
    /** UI of the application **/
    private Ui ui;

    /**
     * Main constructor (for invocation by main method).
     * Get stored data from previous session.
     *
     * @param filePath The file path to the file stored with data from previous Kuromi session.
     */
    public Kuromi(java.nio.file.Path filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (KuromiException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * Runs Kuromi application to interact with the user.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
                Command c = Parser.parse(fullCommand, ui, tasks);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (KuromiException e) {
                ui.showError(e.getMessage());
            }
            ui.showLine();
            ui.showEnter();
        }
    }

    /**
     * Main method of Kuromi class.
     * When Kuromi is created, the compilation starts from the main method.
     *
     * @param args The command line arguments for the application.
     */
    public static void main(String[] args) {
        String home = System.getProperty("user.home");
        java.nio.file.Path path = java.nio.file.Paths.get(home, "Documents", "kuromi.txt");
        new Kuromi(path).run();
    }
}
