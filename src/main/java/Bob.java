import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import org.apache.commons.lang3.StringUtils;

public class Bob {
    private final Scanner scanner = new Scanner(System.in);

    private ArrayList<Task> taskList = new ArrayList<>();
    private static final Integer spacing = 5;

    private static final String wrapper = padLeft("~".repeat(30));

    private static String padLeft (String s) {
        return " ".repeat(spacing) + s;
    }

    // Accepts strings that can be separated by \n
    private void formattedPrint(String s) {
        String[] lines = s.split("\n");
        System.out.println(wrapper);
        for (String line : lines) {
            System.out.println(padLeft(line));
        }
        System.out.println(wrapper);
    }

    private String getTaskDescription(Task t) {
        return String.format("[%s][%s] %s", t.getTaskType(), t.getStatusIcon(), t);
    }
    private void printList() {
        System.out.println(wrapper);
        // Iterate through list items sequentially
        for (int i = 0, n = this.taskList.size(); i < n; i++) {
            Task t = this.taskList.get(i);
            String s = String.format("%d. %s", i + 1, getTaskDescription(t));
            System.out.println(padLeft(s));
        }
        System.out.println(wrapper);
    }

    //Check that the string is a number
    private static boolean isInt(String s) {
        if (s == null) {
            return false;
        }
        // Check that every char is a digit
        for (int i = 0, len = s.length(); i < len; i++) {
            char c = s.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }

        return true;
    }
    private void handleMarkCommand(String s) throws BobException{
        String[] commands = s.split(" ");
        Integer index = Integer.valueOf(commands[1]);

        if (index < 1 || index > taskList.size()) { // Index not in range
            throw new BobException("Index given is out of range!");
        }

        Task t = taskList.get(index - 1);

        if (commands[0].equals("mark")) { // mark task
            t.mark();
            formattedPrint("I've marked this task as done!\n" +
                    getTaskDescription(t));
        } else { // unmark task
            t.unmark();
            formattedPrint("I've unmarked this task as not done!\n" +
                    getTaskDescription(t));
        }
    }

    // Determine if a string can be used to mark/unmark a task in a list of size max
    // Commands are "mark" and "unmark"
    private boolean isMarkCommand(String s) {
        String[] words = s.split(" ");
        String[] markCommands = new String[] {"mark", "unmark"};
        Integer max = taskList.size();

        return words.length == 2
                && Arrays.asList(markCommands).contains(words[0]) // Check if word[0] matches any command
                && isInt(words[1]);
    }

    // Command: todo <description>
    private boolean isTodo(String s) {
        String[] command = s.split(" ");
        return command.length > 1 && command[0].equals("todo");
    }

    private void addTodo(String s) {
        String[] command = s.split(" ", 2);
        Todo t = new Todo(command[1]);
        taskList.add(t);
    }

    // Command: event <description> /from <start> /to <4pm>
    private boolean isEvent(String s) {
        // A valid event would have only 1 /to and /from command
        Boolean validMatches = StringUtils.countMatches(s, " /from ") == 1
                && StringUtils.countMatches(s, " /to ") == 1;

        // A valid command would have 3 different sections with this split
        String[] splitCommand = s.split(" /from | /to ");

        // A valid command has at least 2 sections
        String[] command_desc = splitCommand[0].split(" ");
        return validMatches
                && splitCommand.length == 3
                && command_desc.length > 1
                && command_desc[0].equals("event")
                && s.indexOf("/from") < s.indexOf("/to"); // A valid command has /from before /to
    }

    private void addEvent(String s) {
        String[] command = s.split( " /from | /to ");
        String[] command_desc = command[0].split(" ", 2);
        String description = command_desc[1];
        String start = command[1];
        String end = command[2];

        Event e = new Event(description, start, end);
        taskList.add(e);
    }

    // Command: deadline <description> /by <deadline>
    private boolean isDeadline(String s) {
        String[] splitCommand = s.split(" /by ");
        String[] command_desc = splitCommand[0].split(" ", 2);
        return splitCommand.length == 2 && command_desc.length == 2 && command_desc[0].equals("deadline");
    }

    private void addDeadline(String s) {
        String[] splitCommand = s.split(" /by ");
        String[] command_desc = splitCommand[0].split(" ", 2);
        String description = command_desc[1], deadline = splitCommand[1];
        Deadline d = new Deadline(description, deadline);

        taskList.add(d);
    }
    private void addTask(String input) throws BobException {
        if (isTodo(input)) {
            addTodo(input);
        } else if (isEvent(input)) {
            addEvent(input);
        } else if (isDeadline(input)) {
            addDeadline(input);
        } else { // Invalid command
            throw new BobException("Sorry :( no valid command was entered");
        }

        // Get added task
        Task t = taskList.get(taskList.size() - 1);
        formattedPrint("I've added a new task!\n" + getTaskDescription(t));
    }

    private boolean isDelete(String input) {
        String[] command = input.split(" ");
        return command.length == 2 && command[0].equals("delete") && isInt(command[1]);
    }

    private void deleteTask(String input) throws BobException {
        String[] command = input.split(" ");
        Integer index = Integer.valueOf(command[1]);

        if (index < 1 || index > taskList.size()) {
            throw new BobException("Index given is out of range");
        }
        formattedPrint("Sure, removing this task!\n" +
                       getTaskDescription(taskList.get(index - 1)));
        taskList.remove(index - 1);
    }

    /**
     * Main program for Bob, our chat-bot
     */
    public void start() {
        String flag = "bye";

        //Introduction message
        formattedPrint("Hi, my name is Bob :)\n" +
                "How may I help you?");

        String input = scanner.nextLine();

        while (!input.equals(flag)) {
            try {
                if (input.equals("list")) { // Output list
                    printList();
                } else if (isMarkCommand(input)) { // Marking task
                    handleMarkCommand(input);
                } else if (isDelete(input)) {
                    deleteTask(input);
                } else {  // Add to list
                    addTask(input);
                }
            } catch (BobException e) {
                formattedPrint(e.getMessage());
            }
            input = scanner.nextLine();
        }

        // Goodbye message
        formattedPrint("See you soon!");
    }
}
