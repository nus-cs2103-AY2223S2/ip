import java.util.Scanner;

public class Duke {
    private static Task[] dukeList = new Task[100];
    private static int end = 0;

    public static void main(String[] args) {
        dukeLoop();
    }

    /**
     * Runs the core, repeating functions of Duke
     */
    private static void dukeLoop() {
        greet();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] command = input.split(" ", 8);
            switch (command[0]) {
                case "list":
                    printDukeList();
                    break;
                case "echo":
                    echo(input.substring(5));
                    break;
                case "mark":
                    markTask(input.substring(5));
                    break;
                case "unmark":
                    unmarkTask(input.substring(7));
                    break;
                case "todo":
                    addTodo(input.substring(5));
                    break;
                case "deadline":
                    addDeadline(input.substring(9));
                    break;
                case "event":
                    addEvent(input.substring(6));
                    break;
                case "bye":
                    sc.close();
                    exit();
                    return;
                default:
                    addToList(input);
                    break;
            }
        }
    }

    /**
     * Prints the partitions, ----,
     * then prints the string in-between with a tab spacing
     * 
     * @param w - The string in between the ---- partitions
     */
    private static void printWithPartition(String w) {
        System.out.println("---------------------");
        System.out.print(w);
        System.out.println("---------------------");
    }

    // region DUKE FUNCTIONS
    // ---------------------------------------------------------------------------------------

    /**
     * Prints Duke's greetings
     */
    private static void greet() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("What can I do for you?");
        System.out.println("---------------------\n");
    }

    /**
     * Prints the Tasks in Duke's list, including their done status
     */
    private static void printDukeList() {
        String ls = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < end; i++) {
            ls = ls + "\t" + Integer.toString(i + 1) + "." +
                    dukeList[i].toString() + "\n";
        }
        printWithPartition(ls);
    }

    /**
     * Duke echoes the user's input
     * 
     * @param w - The string to echo
     */
    private static void echo(String w) {
        printWithPartition("\tDuke: " + w + "\n");
    }

    /**
     * Duke marks the task as done and notifies the user
     * 
     * @param w - The string representation of the index of the task
     */
    private static void markTask(String w) {
        int index = Integer.parseInt(w) - 1;
        dukeList[index].markDone();
        printWithPartition("\tNice! I've marked this task as done:\n\t  " +
                dukeList[index].toString() + "\n");
    }

    /**
     * Duke unmarks the task, making it not done and notifies the user
     * 
     * @param w - The string representation of the index of the task
     */
    private static void unmarkTask(String w) {
        int index = Integer.parseInt(w) - 1;
        dukeList[index].unmarkDone();
        printWithPartition("\tOK, I've marked this task as not done yet:" +
                "\n\t  " + dukeList[index].toString() + "\n");
    }

    private static void addTodo(String w) {
        if (end < 100) {
            ToDo temp = new ToDo(w);
            dukeList[end] = temp;
            end += 1;
            printWithPartition("\tGot it. I've added this task:\n" +
                    "\t  " + temp.toString() + "\n\tNow you have " +
                    Integer.toString(end) + " tasks in the list.\n");

        } else {
            printWithPartition("\tfailed to add: " + w + "\n");
        }
    }

    private static void addDeadline(String input) {
        String[] sorted = input.split(" /by ");
        String name = sorted[0];
        String date = sorted[1];
        if (end < 100) {
            Deadline temp = new Deadline(name, date);
            dukeList[end] = temp;
            end += 1;
            printWithPartition("\tGot it. I've added this task:\n" +
                    "\t  " + temp.toString() + "\n\tNow you have " +
                    Integer.toString(end) + " tasks in the list.\n");

        } else {
            printWithPartition("\tfailed to add: " + name + "\n");
        }
    }

    private static void addEvent(String input) {
        String[] sorted = input.split(" /from ");
        String name = sorted[0];
        String[] dates = sorted[1].split(" /to ");
        if (end < 100) {
            Event temp = new Event(name, dates[0], dates[1]);
            dukeList[end] = temp;
            end += 1;
            printWithPartition("\tGot it. I've added this task:\n" +
                    "\t  " + temp.toString() + "\n\tNow you have " +
                    Integer.toString(end) + " tasks in the list.\n");

        } else {
            printWithPartition("\tfailed to add: " + name + "\n");
        }
    }

    /**
     * Adds an item to Duke's list and notifies the user in print
     * 
     * @param w - The item to add to the list
     */
    private static void addToList(String w) {
        if (end < 100) {
            dukeList[end] = new Task(w);
            end += 1;
            printWithPartition("\tadded: " + w + "\n");
        } else {
            printWithPartition("\tfailed to add: " + w + "\n");
        }
    }

    /**
     * Duke says goodbye and is shutdowned
     */
    private static void exit() {
        printWithPartition("\tGoodbye!\n");
    }

    // ---------------------------------------------------------------------------------------
    // endregion

}
