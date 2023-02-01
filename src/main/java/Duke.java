import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private static ArrayList<Task> TASKS = new ArrayList<>();
    private static final Storage STORAGE = new Storage("data/duke.txt");

    /**
     * @param txt text to indent.
     * @return indented string.
     */
    private static String autoIndent(String txt) {
        return "    " + txt.replace("\n", "\n    ");
    }

    /**
     * prints border line.
     */
    private static void borderLine() {
        System.out.print("    ____________________________________________________________\n");
    }

    /**
     * Prints output in a nice format. (adds borders and indentation).
     *
     * @param inp input string.
     */
    private static void prettifyOut(String inp) {
        borderLine();
        System.out.println(autoIndent(inp));
        borderLine();
    }

    /**
     * Prints out that task was added.
     *
     * @param task task that was added.
     */
    private static void printTaskAdded(Task task) {
        prettifyOut("Got it. I've added this task:\n  " + task + "\nNow you have " + TASKS.size() +
                " tasks in the list.");
    }

    /**
     * Executes the input command.
     * @param inp input string.
     * @return False if input is a bye command.
     *
     * @throws DukeException if input is invalid.
     */
    private static boolean executeInput(String inp) throws DukeException {
        Duke.Command cmd;
        String description = "";
        String[] splitInput = inp.split(" ", 2);
        try {
            cmd = Duke.Command.valueOf(splitInput[0].substring(0, 1).toUpperCase() + splitInput[0].substring(1));
            if (cmd != Command.List && cmd != Command.Bye) {
                if (splitInput.length < 2 || splitInput[1].isBlank()) {
                    throw new DukeException("☹ OOPS!!! The description of a " + cmd.toString().toLowerCase() +
                            " cannot be empty.");
                }
                description = splitInput[1];
            }
        } catch (IllegalArgumentException e) {
            throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        }

        switch (cmd) {
            case List:
                if (TASKS.isEmpty()) prettifyOut("Your list is empty!");
                else {
                    int count = 1;
                    borderLine();
                    System.out.println(autoIndent("Here are the tasks in your list:"));
                    for (Task item : TASKS) {
                        System.out.println(autoIndent(count + "." + item));
                        count++;
                    }
                    borderLine();
                }
                break;
            case Mark:
                int taskToMark = Integer.parseInt(splitInput[1]) - 1;
                if (taskToMark >= TASKS.size()) {
                    prettifyOut("Task does not exist!");
                } else {
                    TASKS.get(taskToMark).markAsDone();
                    prettifyOut("Nice! I've marked this task as done:\n" + TASKS.get(taskToMark));
                }
                break;
            case Unmark:
                int taskToUnmark = Integer.parseInt(splitInput[1]) - 1;
                if (taskToUnmark >= TASKS.size()) {
                    prettifyOut("Task does not exist!");
                } else {
                    TASKS.get(taskToUnmark).markAsUndone();
                    prettifyOut("OK, I've marked this task as not done yet:\n" + TASKS.get(taskToUnmark));
                }
                break;
            case Todo:
                Task td = new ToDo(description);
                TASKS.add(td);
                printTaskAdded(td);
                break;
            case Deadline:
                String[] deadline = description.split(" /by ");
                Task dl = new Deadline(deadline[0], deadline[1]);
                TASKS.add(dl);
                printTaskAdded(dl);
                break;
            case Event:
                String[] event = description.split(" /from ");
                String[] timing = event[1].split(" /to ");
                Task ev = new Event(event[0], timing[0], timing[1]);
                TASKS.add(ev);
                printTaskAdded(ev);
                break;
            case Delete:
                int taskToDelete = Integer.parseInt(splitInput[1]) - 1;
                if (taskToDelete >= TASKS.size()) {
                    prettifyOut("Task does not exist!");
                } else {
                    prettifyOut("Noted. I've removed this task:\n  " + TASKS.get(taskToDelete) +
                            "\nNow you have " + (TASKS.size() - 1) + " tasks in the list.");
                    TASKS.remove(taskToDelete);
                }
                break;
            case Bye:
                prettifyOut(GOODBYE);
                return false;
        }

        return true;
    }

    /**
     * The main function.
     *
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        try {
            boolean toContinue = true;
            TASKS = STORAGE.load();
            prettifyOut(GREETING);

            Scanner input = new Scanner(System.in);
            while (toContinue) {
                String userInput = input.nextLine();
                if (userInput.isBlank()) {
                    borderLine();
                    continue;
                }
                toContinue = executeInput(userInput);
                STORAGE.save(TASKS);
            }
        } catch (DukeException e) {
            prettifyOut(e.getMessage());
        }
    }

    /**
     * Available commands.
     */
    public enum Command {
        List, Mark, Unmark, Todo, Deadline, Event, Delete, Bye
    }
}
