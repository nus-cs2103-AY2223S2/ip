import java.io.IOException;
import java.util.ArrayList;

import duke.DukeExceptions;
import duke.Parser;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasktypes.Task;

/**
 * Main Duke class whereby an instance of the Duke chatbot is initialized.
 */
public class Duke {

    private static ArrayList<Task> storedText = new ArrayList<Task>();
    private static Ui dukeUi;
    private static Parser dukeParser;
    private static Storage dukeStorage;
    private static TaskList dukeTaskList;

    /**
     * Constructor to create an instance of Duke chatbot.
     * @throws IOException
     * @throws DukeExceptions
     */
    Duke() throws IOException, DukeExceptions {
        dukeUi = new Ui();
        dukeParser = new Parser();
        dukeStorage = new Storage("data", "dukedata.txt");

        try {
            dukeTaskList = new TaskList(dukeStorage.loadTask());
        } catch (DukeExceptions DE) {
            System.out.println(DE.toString());
        }
    }

    /**
     * Runs the Duke chatbot program.
     * @throws IOException
     * @throws DukeExceptions
     */
    public void run() throws IOException, DukeExceptions {
        String input = dukeUi.gettingUserInput();

        while (!input.equals("bye")) {
            dukeParser.readInput(input, dukeTaskList);
            input = dukeUi.gettingUserInput();
        }
    }

    /**
     * Initializes, runs and stores the resulting list of tasks for Duke chatbot.
     */
    public static void main(String[] args) throws IOException, DukeExceptions {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo + "\nWhat can I do for you?");
        Duke initDuke = new Duke();
        initDuke.run();
        dukeStorage.storeTask(dukeTaskList.getListOfTasks());
        System.out.println("Bye! Hope to see you again soon!");
    }

}
