package duke;

/**
 * Class for Duke, a Personal Assistant Chatbot
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;
    private static boolean isExit = false;
    private final String FILE_PATH = ("./data/Duke.txt");

    /**
     * Constructor for Duke Class. If log file does not exist, create a new log file
     */
    public Duke() {
        Storage.logFileExists(FILE_PATH);
        ui = new Ui();
        storage = new Storage(FILE_PATH);
        tasks = new TaskList(storage.loadTasksFromTaskLog());
    }

    /**
     * Runs Duke interface which interprets user input
     */
    public void run() {
        ui.greetUser();
        while (!isExit) {
            String userInput = ui.getInput();
            Command c = new Command(userInput);
            c.execute(tasks, storage, ui);
            isExit = c.getExitStatus();
        }
    }

    /**
     * Initialise Duke chatbot
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}
