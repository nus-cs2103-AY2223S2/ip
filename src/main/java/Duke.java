import java.util.Scanner;

/**
 * Class Duke implements a chatbot encapsulates user's tasks and show it
 * to the user later by processing the inputs.
 *
 * @author hhchinh2002
 */
public class Duke {
    private static final String DIVIDER_LINE = "____________________________________________________\n";
    private TaskList taskList;
    private Parser parser;
    private Ui ui;
    private Storage storage;

    /**
     * Initialize a Duke object with the corresponding object from other supporting classes.
     */
    public Duke() {
        this.ui = new Ui();
        this.storage = new Storage();
        this.taskList = new TaskList(storage);
        this.parser = new Parser();
    }

    /**
     * Runs the chatbot.
     */
    public void run() {
        ui.start();
        taskList.readTasksFile(storage.readFile());
        Scanner input = new Scanner(System.in);
        String command;
        boolean isEnd = false;
        while (!isEnd) {
            try {
                command = input.nextLine();
                isEnd = parser.handleInput(command, taskList);
            } catch (DukeException e) {
                Ui.reply(e.getMessage());
            }
        }
        Ui.displayOutro();
        input.close();
    }

    public static void main(String[] args) {
        new Duke().run();
    }
}
