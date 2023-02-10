package duke;
import java.io.FileNotFoundException;

public class Duke {
    private final Storage STORAGE;
    private final Ui UI;
    private TaskList tasks;

    /**
     * constructs a Duke object with a file path
     * leading to the data text that stores the tasks.
     *
     * @param filePath relative path to data directory for storing tasks.
     * @throws DukeException
     */
    public Duke(String filePath) throws DukeException {
        UI = new Ui();
        STORAGE = new Storage(filePath);
        try {
            tasks = new TaskList(STORAGE.load());
        } catch (DukeException e) {
            UI.showError();
            tasks = new TaskList();
        } catch (FileNotFoundException e) {
            throw new DukeException(e.toString());
        }
    }

    /**
     * starts up the Duke chatbot.
     */
    public void run(){
        UI.showWelcome();
        String cmd = UI.readCommand();
        boolean isTerminated = false;
        while (!isTerminated) {
            if (!cmd.equals("bye")) {
                try {
                    Parser.parse(cmd, tasks);
                    cmd = UI.readCommand();
                } catch (DukeException e) {
                    System.out.println("something went wrong.");
                }
            } else {
                isTerminated = true;
                UI.showBye();
            }
        }
    }

    public static void main(String[] args) throws DukeException {
        new Duke("data/duke.txt").run();
    }
}