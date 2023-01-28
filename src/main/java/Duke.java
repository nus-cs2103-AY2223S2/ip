import java.util.Scanner;

import command.Command;
import dukeException.DukeException;
import parser.Parser;
import storage.LocalStorage;
import storage.TaskList;

public class Duke {

    private TaskList tasks;
    private LocalStorage localStorage;

    /**
     * Constructor to instantiate Duke bot.
     * @param file_path path of local storage file.
     */
    public Duke(String file_path) {
        TaskList tasks = new TaskList();
        this.localStorage = new LocalStorage(file_path);
        this.localStorage.loadTasks(tasks);
        this.tasks = tasks;
    }

    /**
     * Function to run Duke bot.
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
        new Duke("./data/duke.txt").run();
    }
}
