package duke;

import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.LocalStorage;
import duke.storage.TaskList;
import duke.ui.UI;

/**
 * Duke Chat Bot!
 */
public class Duke {
    private TaskList tasks;
    private LocalStorage localStorage;
    private UI ui;

    /**
     * Constructor for duke.Duke
     */
    public Duke() {
        this.ui = new UI();
        this.tasks = new TaskList();
    }

    /**
     * Constructor to instantiate duke.Duke bot.
     * @param filePath path of local storage file.
     */
    public Duke(String filePath) {
        TaskList tasks = new TaskList();
        this.ui = new UI();
        this.localStorage = new LocalStorage(filePath);
        this.localStorage.loadTasks(tasks);
        this.tasks = tasks;
    }

    /**
     * Function to run duke.Duke bot.
     */
    public void run() {

        ui.greet();

        while (true) {
            try {
                String request = ui.readInput();

                if (request.equals("bye")) {
                    break;
                }

                Command com = new Parser(request, tasks).processRequest();
                String response = com.execute(tasks);
                ui.printRes(response);
            } catch (DukeException error) {
                ui.printRes(error.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        localStorage.saveFile(tasks);
        ui.exit();
    }

    /**
     * Get response function for GUI.
     * @param request User's request.
     * @return Response after processing user's request.
     */
    public String getResponse(String request) {
        try {
            Command com = new Parser(request, tasks).processRequest();
            String response = com.execute(tasks);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Function to handle the user's request
     */
    public void handleRequest() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        TaskList tasks = new TaskList();

        while (!input.equalsIgnoreCase("bye")) {
            Command com = new Parser(input, tasks).processRequest();
            String response = com.execute(tasks);
            ui.printRes(response);
            input = sc.nextLine();
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Duke("./data/duke.txt").run();
    }
}
