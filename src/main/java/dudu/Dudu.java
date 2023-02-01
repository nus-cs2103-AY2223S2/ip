package dudu;

import java.util.Scanner;

import dudu.exception.DuduException;
import dudu.exception.InvalidCommandException;
import dudu.task.TaskList;
import dudu.util.Parser;
import dudu.util.Storage;
import dudu.util.Ui;

/**
 * Dudu class
 */
public class Dudu {
    private static boolean isExit = false;
    private TaskList list;
    private Scanner scanner;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for Dudu
     * @param filePath Path to save the data file
     */
    public Dudu(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        scanner = new Scanner(System.in);
        try {
            list = new TaskList(storage.loadTask());
        } catch (DuduException ex) {
            list = new TaskList();
        }
    }

    /**
     * Dudu exit
     */
    public static void exit() {
        isExit = true;
    }

    /**
     * Run Dudu
     */
    public void run() {
        ui.showWelcome();
        while (!isExit && scanner.hasNext()) {
            String input = scanner.nextLine().strip();
            // skip the empty lines
            if (input.isBlank()) {
                continue;
            }
            try {
                Parser.parse(input, list, storage);
            } catch (InvalidCommandException ex) {
                System.out.println(ex);
            } catch (DuduException ex) {
                System.out.println(ex);
            }
            ui.showLine();
        }
    }
    public static void main(String[] args) {
        new Dudu("data/tasks.txt").run();
    }

}
