import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import duke_exception.DukeException;
import tasklist.TaskList;
import tasklist.task_types.Deadline;
import tasklist.task_types.Event;
import tasklist.task_types.Task;
import tasklist.task_types.ToDo;
import utility.storage.Storage;

/**
 * <h1>Duke Chatbot</h1>
 * The Duke chatbot is a bot that is capable to keep
 * track of tasks from the users.
 * 
 * @author Brian Quek
 */

public class Duke {

    /**
     * Commands that do not require any parameter.
     */
    private static Set<String> commandMapWithoutParam = new HashSet<>() {
        {
            add("list");
            add("bye");
        }
    };

    /**
     * The entire command list that Duke is capable of responding.
     */
    private static Set<String> commandMap = new HashSet<>() {
        {
            add("list");
            add("mark");
            add("unmark");
            add("todo");
            add("deadline");
            add("event");
            add("delete");
            add("bye");
        }
    };

    /**
     * Returns a boolean value after checking if the string contains only numerical
     * value.
     * 
     * <p>
     * This method always return a boolean value, if it contains only numerical
     * characters it will return
     * true else false.
     * 
     * @param str a string to be checked if its numerical
     * @return a boolean whether the string contains only numerical characters
     */
    private static boolean isNumeric(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * @param input an array of string elements that the user input via the Scanner
     *              object.
     * @return an array of string elements that the user input via the Scanner
     *         object.
     * @throws DukeException if it contains any illegal commands.
     */
    public static String[] checkInput(String[] input) throws DukeException {
        // Empty commands
        if (input == null) {
            throw new DukeException(0);
        }

        // Invalid command
        if (!commandMap.contains(input[0]) || input.length > 2
                && (input[0].equals("mark") || input[0].equals("unmark") || input[0].equals("delete"))) {
            throw new DukeException(1);
        }

        // Empty parameters
        if (input.length == 1 && !commandMapWithoutParam.contains(input[0])) {
            throw new DukeException(2);
        }

        // Non-numerical parameters
        if (input[0].equals("mark") || input[0].equals("unmark") || input[0].equals("delete")) {
            if (!isNumeric(input[1]))
                throw new DukeException(3);
        }

        // Missing by, from or to
        if (input[0].equals("deadline") || input[0].equals("event")) {
            boolean hasBy = false, hasFrom = false, hasTo = false;

            for (String s : input) {
                if (s.equals("/by"))
                    hasBy = true;
                if (s.equals("/from"))
                    hasFrom = true;
                if (s.equals("/to"))
                    hasTo = true;
            }

            if ((input[0].equals("deadline") && !hasBy) || (input[0].equals("event") && (!hasFrom || !hasTo))) {
                throw new DukeException(4);
            }
        }

        return input;
    }

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        TaskList list = Storage.readData();
        boolean loop = true;

        String bracket = "\t_______________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMsg = ("Hello from\n" + logo + "\nWhat can I do for you?");
        System.out.println(welcomeMsg);

        while (loop) {
            try {
                String[] input = checkInput(inputScanner.nextLine().split(" ", 10));

                System.out.println(bracket);
                switch (input[0]) {
                    case "list":
                        System.out.println("\t Here are the tasks in your list:");
                        System.out.println(list);
                        break;
                    case "mark":
                        int markedIndex = Integer.parseInt(input[1]) - 1;
                        list.markedTask(markedIndex);
                        System.out.println("Nice! One Task Down!");
                        System.out.println("\t " + list.getTask(markedIndex).toString());
                        break;
                    case "unmark":
                        int unmarkedIndex = Integer.parseInt(input[1]) - 1;
                        list.unmarkedTask(unmarkedIndex);
                        System.out.println("I have unmarked the task as not done yet.");
                        System.out.println("\t " + list.getTask(unmarkedIndex).toString());
                        break;
                    case "todo":
                        String tName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                        ToDo toDoObj = new ToDo(tName);
                        list.addTask(toDoObj);
                        // System.out.println("\tGot it. I've added this task: ");
                        // System.out.println("\t\t " + toDoObj.toString());
                        // System.out.println(list.getTotal());
                        break;
                    case "deadline":
                        int deadlineIndex = 1;
                        for (int i = 0; i < input.length; i++) {
                            if (input[i].equals("/by")) {
                                deadlineIndex = i;
                                break;
                            }
                        }
                        String dName = String.join(" ", Arrays.copyOfRange(input, 1, deadlineIndex));
                        String dDate = String.join(" ", Arrays.copyOfRange(input, deadlineIndex + 1, input.length));
                        try {
                            Deadline deadlineObj = new Deadline(dName, dDate);
                            list.addTask(deadlineObj);
                            System.out.println("\tGot it. I've added this task: ");
                            System.out.println("\t\t " + deadlineObj.toString());
                            System.out.println(list.getTotal());
                        } catch (DateTimeParseException e) {
                            System.out.println("\t Invalid date format. Please input the right format <yyyy-mm-dd>.");
                        }
                        break;
                    case "event":
                        int fromIndex = -1;
                        int toIndex = -1;
                        for (int i = 0; i < input.length; i++) {
                            if (input[i].equals("/from")) {
                                fromIndex = i;
                            }

                            if (input[i].equals("/to")) {
                                toIndex = i;
                            }
                        }
                        String eName = String.join(" ", Arrays.copyOfRange(input, 1, fromIndex));
                        String eFrom = String.join(" ", Arrays.copyOfRange(input, fromIndex + 1, toIndex));
                        String eTo = String.join(" ", Arrays.copyOfRange(input, toIndex + 1, input.length));
                        Event eventObj = new Event(eName, eFrom, eTo);
                        list.addTask(eventObj);
                        System.out.println("\tGot it. I've added this task: ");
                        System.out.println("\t\t " + eventObj.toString());
                        System.out.println(list.getTotal());
                        break;
                    case "delete":
                        int deleteIndex = Integer.parseInt(input[1]) - 1;
                        Task deletedTask = list.getTask(deleteIndex);
                        list.deleteTask(deleteIndex);
                        System.out.println("Following Task has been deleted:");
                        System.out.println("\t " + deletedTask.toString());
                        System.out.println(list.getTotal());
                        break;
                    case "bye":
                        System.out.println("\tBye! See you soon!");
                        Storage.writeData(list);
                        loop = false;
                        break;
                }
                System.out.println(bracket);
            } catch (DukeException e) {
                System.out.println(e);
            }

        }

        inputScanner.close();
    }
}