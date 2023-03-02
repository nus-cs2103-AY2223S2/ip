package chad;

import java.io.IOException;
import java.util.Scanner;

import chad.command.Command;
import chad.exception.DukeException;
import chad.parser.Parser;
import chad.storage.LocalStorage;
import chad.storage.TaskList;
import chad.ui.UI;

/**
 * Duke Chat Bot!
 */
public class Chad {
    private TaskList tasks;
    private LocalStorage localStorage;
    private UI ui;

    /**
     * Constructor for duke.Duke
     */
    public Chad() {
        this.ui = new UI();
        this.tasks = new TaskList();
    }

    /**
     * Constructor to instantiate Duke bot.
     *
     * @param filePath path of local storage file.
     */
    public Chad(String filePath) {
        TaskList tasks = new TaskList();
        this.ui = new UI();
        this.localStorage = new LocalStorage(filePath);
        this.localStorage.loadTasks(tasks);
        this.tasks = tasks;
    }

    /**
     * Function to run Duke bot.
     */
    public void run() {

        ui.greet();

        while (true) {
            try {
                String request = ui.readInput();

                if (request.equals("bye")) {
                    break;
                }

                Command com = new Parser(request, tasks, localStorage).processRequest();
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
     *
     * @param request User's request.
     * @return Response after processing user's request.
     */
    public String getResponse(String request) {
        try {
            Command com = new Parser(request, tasks, this.localStorage).processRequest();
            String response = com.execute(tasks);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }

    /**
     * Function use to handle the user's request and return the response.
     */
    public void handleRequest() {

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        TaskList tasks = new TaskList();

        while (!input.equalsIgnoreCase("bye")) {
            Command com = new Parser(input, tasks, localStorage).processRequest();
            String response = com.execute(tasks);
            ui.printRes(response);
            input = sc.nextLine();
        }

        sc.close();
    }

    public static void main(String[] args) {
        new Chad("./data/duke.txt").run();
    }
}
