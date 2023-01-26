package saturday;

import saturday.collections.TaskList;
import saturday.command.Command;
import saturday.exceptions.SaturdayException;
import saturday.utilities.Storage;
import saturday.utilities.Ui;

import java.util.Scanner;
/**
 * The main class for the Saturday application.
 * This class initializes a task list and storage for the application, and handles the main loop for user input and command execution.
 *
 * @author  Titus Lowe
 * @version 0.1
 * @since   2023-01-26
 */
public class Saturday {
    /**
     * A boolean flag to check the active state of the application
     */
    private static boolean isActive = true;
    /**
     * An instance of the Storage class, used for saving and loading the task list
     */
    private static Storage storage;
    /**
     * An instance of the TaskList class, used to store and manage tasks
     */
    private static TaskList taskList;

    /**
     * Constructor for the Saturday class.
     * Initializes the active state, storage and task list for the application.
     *
     * @param filePath The file path for the storage of task list
     */
    public Saturday(String filePath) {
        this.isActive = true;
        this.storage = new Storage(filePath);
        this.taskList = storage.loadTaskList();
    }

    /**
     * The main loop for the Saturday application.
     * This method handles user input, command execution, and storage of the task list.
     */
    public void run() {
        Ui.greet();

        Scanner scanner = new Scanner(System.in);
        while (isActive) {
            String input = scanner.nextLine();
            Ui.divider();
            try {
                Command command = Command.getCommand(input);
                if (command.equals(Command.BYE)) {
                    isActive = false;
                    Ui.output("Bye. Hope to see you again soon!");
                }
                command.execute(taskList, input);
                storage.saveTaskList(taskList);
            } catch (SaturdayException e) {
                Ui.output(e.getMessage());
            }
            Ui.divider();
            Ui.newline();
        }
    }

    /**
     * main method for the Saturday application
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        String filePath = Storage.getFilePath();
        new Saturday(filePath).run();
    }

}
