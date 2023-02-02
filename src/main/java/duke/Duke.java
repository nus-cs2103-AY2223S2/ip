package duke;

import java.util.Scanner;
import Storage.Storage;
import Task.Task;
import TaskList.TaskList;
import Exception.*;
import Ui.Ui;
import Parser.Parser;


/**
 * Represents the whole program, containing the main program for the whole bot.
 */
public class Duke {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for a Duke Object.
     * Initializes the whole program as well as required objects.
     * If path leads to a file that does not exist, the file will be created.
     *
     * @param filePath String of path to stored list from previous sessions.
     * @return Duke object.
     */
    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (dukeException e) {
            ui.showLoadingError();
        }
    }

    /**
     * Function that runs while waiting for user input.
     */
    public void run() {
        String echo;
        Scanner scan = new Scanner(System.in);

        while (true) {
            echo = scan.nextLine();

            if (echo.equals("bye")) {
                break;
            }

            if(Parser.parseCommands(echo, tasks, ui)) {
                continue;
            }

            Task item;

            // Create a separate function in order to assign to item;
            try {
                item = Parser.parseEcho(echo);
            } catch (dukeException e) {
                // TODO: handle exception
                System.out.println(e.getMessage());
                continue;
            }

            tasks.addTask(item);

            ui.showAddedMessage(item);
            ui.printListNumber(tasks.getList());
        }

        ui.showSavingMessage();

        storage.save(tasks.getList());


        ui.showSavedMessage();

        scan.close();

        ui.showClosingMessage();
    }

    /**
     * Main function of the whole program.
     *
     * @param args command line arguments that are not in use.
     */
    public static void main(String[] args) {
        new Duke("data/duke.txt").run();
    }
}
