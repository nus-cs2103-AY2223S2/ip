package duke.Tasks;

import java.util.Scanner;
import java.lang.String;

/**
 * Represents the Monitor class
 * Display the interface to the user
 */

public class Monitor {
    private static Scanner sc = new Scanner(System.in);

    /**
     * Display the logo of duke at the start of the app
     */
    public void displayLogo() {
        String logo =
                  " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }

    /**
     * Show the welcome message
     */
    public void welcome() {
        String start = "    ____________________________________________________________\n" +
                "     Yo this is Duke.\n" +
                "     Give me something exciting to do.\n" +
                "    ____________________________________________________________\n";
        System.out.println(start);
    }

    /**
     * Show bye when exit() is called
     */
    public void bye() {
        String message = "    ____________________________________________________________\n" +
                "     Come back with something better next time.\n" +
                "    ____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Get the command from the user
     * @return String
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * The method displayAdd shows the user when a new task is added
     * @param table the task table
     * @param i the index of the task
     */
    public void displayAdd(TaskTable table, int i) {
        String message = "    ____________________________________________________________\n" +
                "     Got it. I've added this task:\n" +
                "       " + table.get(i) + "\n" +
                "     Now you have " + (i + 1) +  " task(s) in the list.\n" +
                "    ____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Displays information after Task is done
     * @param table the task table
     * @param index the index of the task
     */
    public void displayMark(TaskTable table, int index) {
        String message = "    ____________________________________________________________\n" +
                "    Nice! I've marked this task as done:\n    " +
                table.get(index) + "\n" +
                "    ____________________________________________________________\n";
        System.out.println(message);
    }

    /**
     * Displays information after Task is unmarked
     * @param table the task table
     * @param index the index of the task
     */
    public void displayUnmark(TaskTable table, int index) {
        String message = "    ____________________________________________________________\n" +
                "Okay, I've marked this task as not done:\n    " +
                table.get(index) + "\n" +
                "    ____________________________________________________________\n";
        System.out.println(message);
    }


    /**
     * The method showTable shows the whole table of Duke.Tasks
     * @param table the task table
     */
    public void showTable(TaskTable table) {
        String message;
        if (table.size() == 0) {
            message = "    ____________________________________________________________\n" +
                    "    Yo there is no task in your list, go get some rest!";
        } else {
            message = "    ____________________________________________________________\n" +
                    "    Here are the task(s) in your list:\n";
        }
        System.out.println(message);
        for (int i = 0; i < table.size(); i ++) {
            System.out.println("    " + (i + 1) + "." + "    " + table.get(i));
        }
        System.out.println("    ____________________________________________________________\n");
    }

    /**
     * The method displayDelete shows the message after deletion
     * @param table the task table
     * @param removedJob the removed task
     */
    public void displayDelete(TaskTable table, Task removedJob) {
        String message = "    ____________________________________________________________\n" +
                "     Noted. I've removed this task:\n" +
                "       " + removedJob + "\n" +
                "     Now you have " + table.size() +  " task(s) in the list.\n" +
                "    ____________________________________________________________\n";
        System.out.println(message);

    }

    /**
     * The methods displayLoadingError shows the message when there is loading error
     */
    public void displayLoadingError() {
        System.out.println("Loading Error!");
    }

    /**
     * The method shows the result of find method
     * @param table the task table
     * @param keyword the keyword to be found
     */
    public void displayFind(TaskTable table, String keyword) {
        String message = "    ____________________________________________________________\n" +
                "     Here are the matching tasks in your list:\n";
        System.out.println(message);
        for (int i = 0; i < table.size(); i++) {
            if(table.getTable().get(i).showDesc().contains(keyword)) {
                System.out.println("    " + (i + 1) + "." + "    " + table.get(i));
            }
        }
        System.out.println("    ____________________________________________________________\n");
    }
}
