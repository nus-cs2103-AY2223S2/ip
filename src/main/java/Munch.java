import AddTasks.Task;
import Exceptions.IncompleteInputException;
import Exceptions.InvalidInputException;
import Exceptions.MunchException;
import munch.Storage;
import munch.TaskList;
import munch.Ui;
import java.util.ArrayList;
import java.util.*;


/**
 * Main class of Munch.
 */
public class Munch {

    private static Ui ui;
    private Storage storage;
    static ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Constructor for Munch object
     * @param filePath Path of the object file where the task objects are stored in.
     */
    public Munch(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        this.tasks = Storage.load(tasks, filePath);
    }

    /**
     * Runs the program flow.
     * Prints a welcome message when the user starts the chat.
     * Continues the program while taking in commands from the user.
     * Exits the program when user types in "bye".
     * Prints a goodbye message and close thr program.
     * @param args
     * @throws MunchException
     */
    public static void main(String[] args) throws MunchException {

        ui.welcomeMessage();
        String filePath = "src/main/java/data/SavedTaskList.txt";
        new Munch(filePath);

        Boolean exit = true;
        while (exit) {
            try {
                String word = ui.readCommand();
                String[] words = word.split(" ");

                if (word.equals("bye")) {
                    ui.exitMessage();
                    exit = false;
                } else {
                    TaskList.run(tasks, word, words);
                }

                Storage.save(tasks, filePath);
            } catch (IncompleteInputException | InvalidInputException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
