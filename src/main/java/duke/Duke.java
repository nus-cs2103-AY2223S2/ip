package duke;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Duke {
    private Storage storage;
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
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
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
    public void run() throws DukeException, IOException {
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
                storage = new Storage("data/duke.txt");
                storage.write("data/duke.txt", tasks);
                UI.showBye();
            }
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        new Duke("data/duke.txt").run();
    }
}