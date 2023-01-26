import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import duke_exception.DukeException;
import task_types.Deadline;
import task_types.Event;
import task_types.Task;
import task_types.ToDo;
import utility.TextFileParser;

/**
 * <h1>Duke Chatbot</h1>
 * The Duke chatbot is a bot that is capable to keep
 * track of tasks from the users.
 * 
 * @author Brian Quek
 */

public class Duke {

    private static Set<String> commandMap = new HashSet<>() {
        {
            add("list");
            add("mark");
            add("unmark");
            add("todo");
            add("deadline");
            add("event");
            add("bye");
        }
    };

    /**
     * The entire command list that Duke is capable of responding to.
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
        if (!commandMap.contains(input[0])
                || input.length > 2 && (input[0].equals("mark") || input[0].equals("unmark"))) {
            throw new DukeException(1);
        }

        // Empty parameters
        if (input.length == 1 && !commandMapWithoutParam.contains(input[0])) {
            throw new DukeException(2);
        }

        // Non-numerical parameters
        if (input[0].equals("mark") || input[0].equals("unmark")) {
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
        ArrayList<Task> list = TextFileParser.readData();
        boolean loop = true;

        String bracket = "\t_______________________________________________________";
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        String welcomeMsg = ("Hello from\n" + logo + "\nWhat can I do for you?");
        System.out.println(welcomeMsg);

        TextFileParser.readData();

        while (loop) {
            try {
                String[] input = checkInput(inputScanner.nextLine().split(" ", 10));

                System.out.println(bracket);
                switch (input[0]) {
                    case "list":
                        System.out.println("\t Here are the tasks in your list:");
                        for (int i = 0; i < list.size(); i++) {
                            int index = i + 1;
                            System.out.println("\t " + index + ". " + list.get(i).toString());
                        }
                        break;
                    case "mark":
                        Task markedTask = list.get(Integer.parseInt(input[1]) - 1);
                        markedTask.setStatus(true);
                        System.out.println("Nice! One Task Down!");
                        System.out.println("\t " + markedTask.toString());
                        break;
                    case "unmark":
                        Task unmarkedTask = list.get(Integer.parseInt(input[1]) - 1);
                        unmarkedTask.setStatus(false);
                        System.out.println("I have unmarked the task as not done yet.");
                        System.out.println("\t " + unmarkedTask.toString());
                        break;
                    case "todo":
                        String tName = String.join(" ", Arrays.copyOfRange(input, 1, input.length));
                        ToDo toDoObj = new ToDo(tName);
                        list.add(toDoObj);
                        System.out.println("\tGot it. I've added this task: ");
                        System.out.println("\t\t " + toDoObj.toString());
                        System.out.println(String.format("Now you have %d tasks in the list", list.size()));
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
                        Deadline deadlineObj = new Deadline(dName, dDate);
                        list.add(deadlineObj);
                        System.out.println("\tGot it. I've added this task: ");
                        System.out.println("\t\t " + deadlineObj.toString());
                        System.out.println(String.format("Now you have %d tasks in the list", list.size()));
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
                    }
                    String eName = String.join(" ", Arrays.copyOfRange(input, 1, fromIndex));
                    String eFrom = String.join(" ", Arrays.copyOfRange(input, fromIndex + 1, toIndex));
                    String eTo = String.join(" ", Arrays.copyOfRange(input, toIndex + 1, input.length));
                    Event eventObj = new Event(eName, eFrom, eTo);
                    lists.add(eventObj);
                    System.out.println("\tGot it. I've added this task: ");
                    System.out.println("\t\t "+ eventObj.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", lists.size()));
                    break;
                case "bye":
                    System.out.println("\tBye! See you soon!");
                    loop = false;
                    break;
                default:
                    String combString = String.join(" ", input);
                    ToDo tdObj = new ToDo(combString);
                    lists.add(tdObj);
                    System.out.println("\tGot it. I've added this task: ");
                    System.out.println("\t\t "+ tdObj.toString());
                    System.out.println(String.format("Now you have %d tasks in the list", lists.size()));
            }

        }

        inputScanner.close();
    }
}