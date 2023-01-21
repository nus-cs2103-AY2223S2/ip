import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The Duke class represents a CLI chatbot that performs operations based on CLI user input.
 * <p>
 * Currently, Duke accepts the commands: {@code echo, list, mark, unmark, todo, deadline, event, bye}
 */
public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Storage store;
    private static Ui ui = Ui.getInstance();

    /**
     * The Commands enum represents Duke's available commands.
     */
    public enum Commands {
        ECHO, LIST, PRIORITY, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE;

    }

    /**
     * Initialises and runs the Duke chatbot.
     *
     * @param args The modifiers to run Duke with (currently no modifiers are avaliable).
     */
    public static void main(String[] args) {
        ui.greet();
        loopDukeFunctions();
    }

    /**
     * Repeats the core functions of Duke.
     */
    private static void loopDukeFunctions() {
        Scanner sc = new Scanner(System.in);
        try {
            store = new Storage("src/main/resources/duke.txt");
            loadFromFile();
        } catch (DukeException e) {
            ui.printWithPartition("\t" + e.getMessage() + "\n");
        }
        while (true) {
            try {
                Commands command = readCommand(sc);
                switch (command) {
                case LIST:
                    sc.nextLine(); // throws away the remaining line
                    printDukeList();
                    break;
                case PRIORITY:
                    printPrioritisedDukeList();
                    break;
                case ECHO:
                    echo(sc.nextLine().strip());
                    break;
                case MARK:
                    markTask(sc.nextLine().strip());
                    saveToFile();
                    break;
                case UNMARK:
                    unmarkTask(sc.nextLine().strip());
                    saveToFile();
                    break;
                case TODO:
                    addTodo(sc.nextLine().strip());
                    saveToFile();
                    break;
                case DEADLINE:
                    addDeadline(sc.nextLine().strip());
                    saveToFile();
                    break;
                case EVENT:
                    addEvent(sc.nextLine().strip());
                    saveToFile();
                    break;
                case DELETE:
                    deleteTask(sc.nextLine().strip());
                    saveToFile();
                    break;
                case BYE:
                    sc.close();
                    exit();
                    return;
                default:
                    // this should not run
                    break;
                }
            } catch (DukeException e) {
                ui.printWithPartition("\t" + e.getMessage() + "\n");
            }

        }
    }

    private static void saveToFile() {
        store.saveToFile(tasks);
    }

    private static void loadFromFile() {
        ArrayList<Task> temp = store.loadFromFile();
        tasks = temp == null ? tasks : temp;
    }

    private static Commands readCommand(Scanner sc) throws DukeException {
        try {
            return Commands.valueOf(sc.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            sc.nextLine(); // throw away remaining lines
            throw new DukeException(
                    "I only understand {echo, list, mark, unmark, todo, deadline, event} commands.");
        }
    }

    // region DUKE FUNCTIONS
    // ------------------------------------------------------------------------

    /**
     * Prints the Tasks in Duke's list, including their done status.
     */
    private static void printDukeList() {
        String ls = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            ls = ls + "\t" + Integer.toString(i + 1) + "." + temp.toString() + "\n";
        }
        ui.printWithPartition(ls);
    }

    private static void printPrioritisedDukeList() {
        String ls = "\tHere are the important tasks in your list:\n";
        ArrayList<Task> sortedTasks = new ArrayList<>(tasks);
        sortedTasks.sort(null);
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = sortedTasks.get(i);
            ls = ls + "\t" + Integer.toString(i + 1) + "." + temp.toString() + "\n";
        }
        ui.printWithPartition(ls);
    }

    /**
     * Echoes the user's input.
     *
     * @param w The string to echo.
     */
    private static void echo(String w) {
        ui.printWithPartition("\tDuke: " + w + "\n");
    }

    /**
     * Marks the task as done and notifies the user.
     *
     * @param num The string representation of the index of the task.
     * @throws DukeException When the task number input does not exist.
     */
    private static void markTask(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num) - 1;
            Task task = tasks.get(index);
            task.setAsDone();
            ui.printWithPartition("\tNice! I've marked this task as done:\n\t  " + task.toString() + "\n");
        } catch (Exception e) {
            if (tasks.size() == 0) {
                throw new DukeException("There are no tasks to be marked as done.");
            } else {
                throw new DukeException("Please enter a number from 1 to " + Integer.toString(tasks.size()));
            }
        }
    }

    /**
     * Unmarks the task, making it not done and notifies the user.
     *
     * @param num The string representation of the index of the task.
     * @throws DukeException When the task number input does not exist.
     */
    private static void unmarkTask(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num) - 1;
            Task task = tasks.get(index);
            task.setAsNotDone();
            ui.printWithPartition(
                    "\tOK, I've marked this task as not done yet:" + "\n\t  " + task.toString() + "\n");
        } catch (Exception e) {
            if (tasks.size() == 0) {
                throw new DukeException("There are no tasks to be unmarked.");
            } else {
                throw new DukeException("Please enter a number from 1 to " + Integer.toString(tasks.size()));
            }
        }
    }

    /**
     * Adds a new ToDo task for Duke to track.
     *
     * @param w The name of the ToDo task.
     * @throws DukeException If the input string is empty or if the task list is full.
     */
    private static void addTodo(String w) throws DukeException {
        if (w == "") {
            throw new DukeException("The description of a todo cannot be empty.");
        }

        ToDo task = new ToDo(w);
        tasks.add(task);
        ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + task.toString()
                + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");

    }

    /**
     * Adds a Deadline task for Duke to track.
     *
     * @param input The string containing the name and a "/by".
     * @throws DukeException If the input string is invalid or if the task list is full.
     */
    private static void addDeadline(String input) throws DukeException {
        try {
            String[] sorted = input.split(" /by ");
            String name = sorted[0].strip();
            String date = sorted[1].strip();
            Deadline task = new Deadline(name, date);
            tasks.add(task);
            ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + task.toString()
                    + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(
                    "The deadline command should be used like this:\n" + "\tdeadline {name} /by {date}");
        } catch (DateTimeParseException e) {
            throw new DukeException(
                    "The deadline command should be used like this:\n" + "\tdeadline {name} /by {date}");
        }
    }

    /**
     * Adds an Event tast for Duke to track and prints a notification to the user.
     *
     * @param input The string containing the name, "/from" and a "/to".
     * @throws DukeException If the input string is invalid or if the task list is full.
     */
    private static void addEvent(String input) throws DukeException {
        try {
            String[] sorted = input.split(" /from ");
            String name = sorted[0].strip();
            String[] dates = sorted[1].strip().split(" /to ");
            Event task = new Event(name, dates[0], dates[1]);
            tasks.add(task);
            ui.printWithPartition("\tGot it. I've added this task:\n" + "\t  " + task.toString()
                    + "\n\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The event command should be used like this:\n"
                    + "\tevent {name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
        } catch (DateTimeParseException e) {
            throw new DukeException("The event command should be used like this:\n"
                    + "\tevent {name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param num The string representation of the task number.
     * @throws DukeException If the task number does not exist.
     */
    private static void deleteTask(String num) throws DukeException {
        try {
            int index = Integer.parseInt(num) - 1;
            Task task = tasks.get(index);
            tasks.remove(index);
            ui.printWithPartition("\tNoted. I've removed this task:\n\t  " + task.toString() + "\n"
                    + "\tNow you have " + Integer.toString(tasks.size()) + " tasks in the list.\n");
        } catch (Exception e) {
            if (tasks.size() == 0) {
                throw new DukeException("There are no tasks to be deleted.");
            } else {
                throw new DukeException("Please enter a number from 1 to " + Integer.toString(tasks.size()));
            }
        }
    }

    /**
     * Prints goodbye and Duke shuts down.
     */
    private static void exit() {
        ui.printWithPartition("\tGoodbye!\n");
    }

    // ------------------------------------------------------------------------
    // endregion

}
