import java.util.Scanner;

/**
 * The Duke class represents a CLI chatbot that performs operations based on CLI user input.
 * <p>
 * Currently, Duke accepts the commands: {@code echo, list, mark, unmark, todo, deadline, event, bye}
 */
public class Duke {
    private static Task[] tasks = new Task[100];
    private static int end = 0;

    /**
     * Initialises and runs the Duke chatbot.
     *
     * @param args The modifiers to run Duke with (currently no modifiers are avaliable).
     */
    public static void main(String[] args) {
        greet();
        loopDukeFunctions();
    }

    /**
     * Prints Duke's greetings.
     */
    private static void greet() {
        // @formatter:off
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        // @formatter:on

        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------\n");
    }

    /**
     * Repeats the core functions of Duke.
     */
    private static void loopDukeFunctions() {
        Scanner sc = new Scanner(System.in);

        while (true) {
            String command = sc.next();

            switch (command) {
            case "list":
                printDukeList();
                break;
            case "echo":
                echo(sc.nextLine().strip());
                break;
            case "mark":
                markTask(sc.nextLine().strip());
                break;
            case "unmark":
                unmarkTask(sc.nextLine().strip());
                break;
            case "todo":
                addTodo(sc.nextLine().strip());
                break;
            case "deadline":
                addDeadline(sc.nextLine().strip());
                break;
            case "event":
                addEvent(sc.nextLine().strip());
                break;
            case "bye":
                sc.close();
                exit();
                return;
            default:
                sc.nextLine(); // throw away whole lines
                unknownCommand();
                break;
            }
        }
    }

    /**
     * Prints the partitions, ----, then prints the string in-between. \n is required for the end of the
     * string. <blockquote> ---------------------
     * <p>
     * your string here
     * <p>
     * --------------------- </blockquote>
     *
     * @param s The string in between the ---- partitions.
     */
    private static void printWithPartition(String s) {
        System.out.println("---------------------");
        System.out.print(s);
        System.out.println("---------------------");
    }

    // region DUKE FUNCTIONS
    // ------------------------------------------------------------------------

    /**
     * Prints the Tasks in Duke's list, including their done status.
     */
    private static void printDukeList() {
        String ls = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < end; i++) {
            ls = ls + "\t" + Integer.toString(i + 1) + "." + tasks[i].toString() + "\n";
        }
        printWithPartition(ls);
    }

    /**
     * Echoes the user's input.
     *
     * @param w The string to echo.
     */
    private static void echo(String w) {
        printWithPartition("\tDuke: " + w + "\n");
    }

    /**
     * Marks the task as done and notifies the user.
     *
     * @param w The string representation of the index of the task.
     */
    private static void markTask(String w) {
        int index = Integer.parseInt(w) - 1;
        tasks[index].setAsDone();
        printWithPartition("\tNice! I've marked this task as done:\n\t  " + tasks[index].toString() + "\n");
    }

    /**
     * Unmarks the task, making it not done and notifies the user.
     *
     * @param w The string representation of the index of the task.
     */
    private static void unmarkTask(String w) {
        int index = Integer.parseInt(w) - 1;
        tasks[index].setAsNotDone();
        printWithPartition(
                "\tOK, I've marked this task as not done yet:" + "\n\t  " + tasks[index].toString() + "\n");
    }

    /**
     * Adds a new ToDo task for Duke to track.
     *
     * @param w The name of the ToDo task.
     */
    private static void addTodo(String w) {
        if (end < 100) {
            ToDo temp = new ToDo(w);
            tasks[end] = temp;
            end += 1;
            printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                    + "\n\tNow you have " + Integer.toString(end) + " tasks in the list.\n");

        } else {
            printWithPartition("\tfailed to add: " + w + "\n");
        }
    }

    /**
     * Adds a Deadline task for Duke to track.
     *
     * @param input The string containing the name and a "/by".
     */
    private static void addDeadline(String input) {
        String[] sorted = input.split(" /by ");
        String name = sorted[0];
        String date = sorted[1];
        if (end < 100) {
            Deadline temp = new Deadline(name, date);
            tasks[end] = temp;
            end += 1;
            printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                    + "\n\tNow you have " + Integer.toString(end) + " tasks in the list.\n");

        } else {
            printWithPartition("\tfailed to add: " + name + "\n");
        }
    }

    /**
     * Adds an Event tast for Duke to track and prints a notification to the user.
     *
     * @param input The string containing the name, "/from" and a "/to".
     */
    private static void addEvent(String input) {
        String[] sorted = input.split(" /from ");
        String name = sorted[0];
        String[] dates = sorted[1].split(" /to ");
        if (end < 100) {
            Event temp = new Event(name, dates[0], dates[1]);
            tasks[end] = temp;
            end += 1;
            printWithPartition("\tGot it. I've added this task:\n" + "\t  " + temp.toString()
                    + "\n\tNow you have " + Integer.toString(end) + " tasks in the list.\n");

        } else {
            printWithPartition("\tfailed to add: " + name + "\n");
        }
    }

    /**
     * Prints goodbye and Duke shuts down.
     */
    private static void exit() {
        printWithPartition("\tGoodbye!\n");
    }

    /**
     * Prints a message informing the user of the possible commands.
     */
    private static void unknownCommand() {
        printWithPartition("\tDuke: Sorry! I only understand "
                + "{echo, list, mark, unmark, todo, deadline, event} commands.\n");
    }

    // ------------------------------------------------------------------------
    // endregion

}
