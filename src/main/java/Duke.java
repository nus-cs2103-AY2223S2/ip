import java.util.Scanner;
import java.util.List;
/**
 * Duke is a personal assistant chatbot that help to keep track of various stuff.
 */
public class Duke {
    // Attribute
    static final String BORDER = "----------------------------------------";
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) throws DukeException {
        ui = new Ui();
        storage = new Storage(filePath);
        taskList = new TaskList(storage.load());
    }

    public void run() {

    }
    // Methods


    // Read commands, possibly change to public. Return 0 at exit.
    protected int read(Scanner sc) {
        String command = sc.next();
        // Useful variables
        int rank;
        String message;
        try {
            switch (command) {
                case "bye":
                    System.out.println("Bye. Hope to see you again soon!");
                    return 0;
                case "list":
                    taskList.list();
                    break;
                case "mark":
                    try {
                        rank = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! mark must have an integer rank");
                    }
                    taskList.markDone(rank - 1);
                    break;
                case "unmark":
                    try {
                        rank = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! unmark must have an integer rank");
                    }
                    taskList.markUndone(rank - 1);
                    break;
                case "todo":
                    message = sc.nextLine().trim();
                    taskList.addTask(message, 0);
                    break;
                case "deadline":
                    message = sc.nextLine().trim();
                    taskList.addTask(message, 1);
                    break;
                case "event":
                    message = sc.nextLine().trim();
                    taskList.addTask(message, 2);
                    break;
                case "delete":
                    try {
                        rank = Integer.parseInt(sc.nextLine().trim());
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! delete must have an integer rank");
                    }
                    taskList.delete(rank - 1);
                    break;
                default:
                    System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n" + BORDER);
                    sc.nextLine();
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage() + "\n" + BORDER);
        }
        return 1;
    }

    // Driver function
    public static void main(String[] args) throws DukeException {
        Duke duke1 = new Duke("./data/list.txt");
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            if (duke1.read(sc) == 0) {
                return;
            }
        }
    }
}
