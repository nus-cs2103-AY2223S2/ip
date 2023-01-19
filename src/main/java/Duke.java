import java.util.ArrayList;
import java.util.Scanner;

import exception.DukeException;
import task.Task;
import task.ToDo;
import task.Deadline;
import task.Event;

public class Duke {
    private static ArrayList<Task> tasks = new ArrayList<>();
    /**
     * Prints a line 4 spaces away from the left edge of the screen to visually
     * separate Duke's replies from user input.
     */
    private static void printLine() {
        System.out.printf("%64s%n", "    ____________________________________________________________");
    }

    /**
     * Prints Duke's greeting message (bounded by lines above and below).
     */
    private static void greet() {
        printLine();
        System.out.printf("     %s%n", "Hello! I'm Duke");
        System.out.printf("     %s%n", "What can I do for you?");
        printLine();
    }

    /**
     * Mark task (selected by position in list).
     *
     * @param input User input.
     */
    private static void markTask(String input) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.get(taskNumber - 1).markAsDone();
            System.out.printf("     %s%n", "Nice! I've marked this task as done:");
            System.out.printf("       %s%n", tasks.get(taskNumber - 1).toString());
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            System.out.printf("     %s%n", "Please input valid task number.");
        }
    }

    /**
     * Unmark task (selected by position in list).
     *
     * @param input User input.
     */
    private static void unmarkTask(String input) {
        int taskNumber;
        try {
            taskNumber = Integer.parseInt(input.split(" ")[1]);
            tasks.get(taskNumber - 1).markAsNotDone();
            System.out.printf("     %s%n", "OK, I've marked this task as not done yet:");
            System.out.printf("       %s%n", tasks.get(taskNumber - 1).toString());
        } catch (NumberFormatException|IndexOutOfBoundsException e) {
            System.out.printf("     %s%n", "Please input valid task number.");
        }
    }

    /**
     * Prints confirmation of added task and number of tasks currently in list.
     *
     * @param t Task to confirm addition of.
     */
    private static void confirmAddition(Task t) {
        System.out.printf("     %s%n", "Got it. I've added this task:");
        System.out.printf("       %s%n", t.toString());
        System.out.printf("     %s%d%s%n", "Now you have ", tasks.size(), " tasks in the list.");
    }

    /**
     * Add ToDo task to list.
     *
     * @param input User input.
     * @throws DukeException on empty ToDo description.
     */
    private static void addToDo(String input) throws DukeException {
        if (input.length() <= 5) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        String description = input.substring(5);
        if (description.isBlank()) {
            throw new DukeException("The description of a todo cannot be empty.");
        }
        Task t = new ToDo(description);
        tasks.add(t);
        confirmAddition(t);
    }

    /**
     * Add Deadline task to list.
     *
     * @param input User input.
     */
    private static void addDeadline(String input) {
        int byIndex = input.indexOf("/by");
        String description = input.substring(9, byIndex - 1);
        String by = input.substring(byIndex + 4);
        Task t = new Deadline(description, by);
        tasks.add(t);
        confirmAddition(t);
    }

    /**
     * Add Event task to list.
     *
     * @param input User input.
     */
    private static void addEvent(String input) {
        int fromIndex = input.indexOf("/from");
        int toIndex = input.indexOf("/to");
        String description = input.substring(6, fromIndex - 1);
        String from = input.substring(fromIndex + 6, toIndex - 1);
        String to = input.substring(toIndex + 4);
        Task t = new Event(description, from, to);
        tasks.add(t);
        confirmAddition(t);
    }

    /**
     * Replies to user inputs according to requirements.
     * If user inputs "bye", return to exit Duke.
     * If user inputs "list", print current tasks.
     * If user inputs "mark NUMBER" or "unmark NUMBER", update the doneness of that task number.
     * If user inputs a task, add to current tasks.
     */
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            printLine();

            if (input.equals("list")) {
                System.out.printf("     %s%n", "Here are the tasks in your list:");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.printf("     %d.%s%n",
                            i + 1,
                            tasks.get(i).toString());
                }
            } else {
                try {
                    String command = (input.split(" ")[0]).toLowerCase();
                    switch (command) {
                        case "mark":
                            markTask(input);
                            break;
                        case "unmark":
                            unmarkTask(input);
                            break;
                        case "todo":
                            addToDo(input);
                            break;
                        case "deadline":
                            addDeadline(input);
                            break;
                        case "event":
                            addEvent(input);
                            break;
                        default:
                            throw new DukeException("I'm sorry, but I don't know what that means :-(");
                    }
                } catch (DukeException e) {
                    System.out.printf("     ☹ OOPS!!! %s%n", e.getMessage());
                }
            }
            printLine();
            input = sc.nextLine();
            }

        printLine();
        System.out.printf("     %s%n", "Bye. Hope to see you again soon!");
        printLine();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        greet();
        echo();
    }
}