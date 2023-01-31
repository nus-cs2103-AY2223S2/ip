import duke.command.Command;
import duke.dukeexception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;


public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Initialises the Duke object
     * @param filePath A string representing the path of the file containing all tasks
     * @param folderPath A string representing the path of the folder containing the above file
     */
    public Duke(String filePath, String folderPath) {
        ui = new Ui();
        storage = new Storage(filePath, folderPath);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
    }

    /**
     * a function that runs the program by repeatedly accepting user input and respond to them until
     * the user wants to exit the program.
     */
    public void run() {
        ui.welcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String inputLine = ui.readCommand();
                Command c = Parser.parse(inputLine);
                c.execute(tasks, ui, storage);
                isExit = c.isExit();
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * main method that starts running the program
     */
    public static void main(String[] args) {
        new Duke("data/tasks.txt", "data").run();
    }
}
