import java.util.Scanner;

/**
 * The Ui class represents Duke's user interface that interacts with the user.
 */
public class Ui {
    private static Ui instanceUi = new Ui();
    private Scanner sc;

    private Ui() {
        sc = new Scanner(System.in);
    }

    public static Ui getInstance() {
        return instanceUi;
    }

    // region Ui for output

    /**
     * Prints Duke's greetings.
     */
    public void greet() {
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
     * Prints the partitions, ----, then prints the string in-between. \n is required for the end of the
     * string. <blockquote> ---------------------
     * <p>
     * your string here
     * <p>
     * --------------------- </blockquote>
     *
     * @param s The string in between the ---- partitions.
     */
    public void printWithPartition(String s) {
        System.out.println("---------------------");
        System.out.print(s);
        System.out.println("---------------------");
    }

    /**
     * Prints the Tasks in Duke's list, including their done status.
     */
    public void printList(TaskList tasks) {
        String ls = "\tHere are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            Task temp = tasks.get(i);
            ls = ls + "\t" + Integer.toString(i + 1) + "." + temp.toString() + "\n";
        }
        printWithPartition(ls);
    }

    /**
     * Prints the next line of input.
     */
    public void printNextInput() {
        printWithPartition("\tDuke: " + sc.nextLine().strip() + "\n");
    }

    public void printException(String message) {
        printWithPartition("\t" + message + "\n");
    }

    public void printGoodbye() {
        printWithPartition("\tGoodbye!\n");
    }

    // endregion

    // region Ui for input
    public void throwAwayInput() {
        sc.nextLine();
    }

    public int getTaskNum() throws DukeException {
        try {
            String s = sc.nextLine().strip();
            int num = Integer.parseInt(s) - 1;
            return num;
        } catch (NumberFormatException e) {
            throw new DukeException("Please input a valid task number");
        }
    }

    public String getCommand() {
        return sc.next();
    }

    public String getName() throws DukeException {
        String name = sc.nextLine().strip();
        if (name == "") {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        return name;
    }

    public String[] getDeadline() throws DukeException {
        try {
            String[] sorted = sc.nextLine().split(" /by ");
            String[] data = new String[] {sorted[0].strip(), sorted[1].strip()};
            return data;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException(
                    "The deadline command should be used like this:\n" + "\tdeadline {name} /by {date}");
        }
    }

    public String[] getEvent() throws DukeException {
        try {
            String[] line = sc.nextLine().split(" /from ", 2);
            String[] dates = line[1].split(" /to ", 2);
            String[] data = new String[] {line[0].strip(), dates[0].strip(), dates[1].strip()};
            return data;
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("The event command should be used like this:\n"
                    + "\tevent {name} /from {YYYY-MM-DD} /to {YYYY-MM-DD}");
        }

    }
    // endregion
}
