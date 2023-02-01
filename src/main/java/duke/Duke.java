package duke;

import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.ui.UI;
import duke.parser.Parser;
import duke.storage.LocalStorage;
import duke.storage.TaskList;

/**
 * duke.Duke Chat Bot!
 */
public class Duke {
    private TaskList tasks;
    private LocalStorage localStorage;

    /**
     * Constructor for duke.Duke
     */
    public Duke() {
        this.tasks = new TaskList();
    }

    /**
     * Constructor to instantiate duke.Duke bot.
     * @param filePath path of local storage file.
     */
    public Duke(String filePath) {
        TaskList tasks = new TaskList();
        this.localStorage = new LocalStorage(filePath);
        this.localStorage.loadTasks(tasks);
        this.tasks = tasks;
    }

    /**
     * Function to run duke.Duke bot.
     */
    public void run() {

        UI.greet();

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {
            try {
                Command com = new Parser(input, tasks).processRequest();
                String response = com.execute(tasks);
                UI.printRes(response);
            } catch (DukeException error) {
                UI.printRes(error.getMessage());
            }
            input = sc.nextLine();
        }

        localStorage.saveFile(tasks);
        sc.close();

        UI.exit();
    }

    /**
     * Function to handle the user's request
     */
    public static void handleRequest() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        TaskList tasks = new TaskList();

        while (!input.equalsIgnoreCase("bye")) {
            Command com = new Parser(input, tasks).processRequest();
            String response = com.execute(tasks);
            UI.printRes(response);
            input = sc.nextLine();
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();;
    }
}
