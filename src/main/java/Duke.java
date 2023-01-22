import java.io.IOException;
import java.util.Scanner;

/**
 * The chatbot that users will be interacting with.
 *
 * @author wz2k
 */
public class Duke {
    /**
     * Storage of user's tasks.
     */
    private TaskList taskList;
    private Storage storage;

    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();

        try {
            this.storage = new Storage(filePath);
            storage.initializeStorage();
            this.taskList = new TaskList(storage.getTasks());
        } catch (IOException exception) {
            this.ui.replyError(exception.getMessage());
        }
    }

    /**
     * This is the main method which starts off the chatbot.
     *
     * @param args Unused.
     */
    public static void main(String[] args) {
        Duke duke = new Duke("data/duke.txt");
        Parser parser = new Parser();
        Scanner scanner = new Scanner(System.in);

        duke.ui.greetUser();
        boolean end = false;

        while (!end) {
            try {
                String input = scanner.nextLine();
                Command command = parser.parseCommand(input, duke.ui, duke.taskList, duke.storage);
                end = command.execute();
            } catch (DukeException exception) {
                duke.ui.replyError(exception.getMessage());
            }
        }
    }
}
