
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;

public class Bob {
    private Scanner scanner = new Scanner(System.in);

    private ArrayList<Task> taskList = new ArrayList<>();
    private static Integer spacing = 5;

    private static String wrapper = padLeft("~".repeat(30));

    /**
     * Main program for Bob, our chat-bot
     */
    public void start() {
        String flag = "bye";

        //Introduction message
        formattedPrint("Hi, my name is Bob :) \n" +
                "How may I help you?");

        String input = scanner.nextLine();

        while (!input.equals(flag)) {
            if (input.equals("list")) { // Output list
                formattedPrint(taskList);
            } else if (isMarkCommand(input)) { // Marking task
                handleMarkCommand(input);
            } else {  // Add to list
                taskList.add(new Task(input));
                formattedPrint("added: " + input);
            }
            input = scanner.nextLine();
        }

        // Goodbye message
        formattedPrint("See you soon!");
    }

    private static String padLeft (String s) {
        return " ".repeat(spacing) + s;
    }

    // Accepts a string that can be separated by \n
    private static void formattedPrint(String s) {
        System.out.println(wrapper);
        outputList(s.split("\n"));
        System.out.println(wrapper);
    }

    private static void formattedPrint(ArrayList<Task> s) {
        System.out.println(wrapper);
        outputList(s);
        System.out.println(wrapper);
    }

    private static String getTaskStatus(Task t) {
        return String.format("[%s] %s", t.getStatusIcon(), t.description);
    }
    private static void outputList(ArrayList<Task> list) {
        // Iterate through list items sequentially
        for (int i = 0, n = list.size(); i < n; i++) {
            Task t = list.get(i);
            String s = String.format("%d. %s", i + 1, getTaskStatus(t));
            System.out.println(padLeft(s));
        }
    }

    // Overloaded method
    private static void outputList(String[] list) {
        for (String s : list) {
            System.out.println(padLeft(s));
        }
    }

    // Determine if a string can be used to mark/unmark a task in a list of size max
    // Commands are "mark" and "unmark"
    private boolean isMarkCommand(String s) {
        String[] words = s.split(" ");
        String[] markCommands = new String[] {"mark", "unmark"};
        Integer max = taskList.size();

        return words.length == 2
                && Arrays.stream(markCommands).anyMatch(words[0]::equals) // Check if word[0] matches any command
                && isInt(words[1]);
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
    private void handleMarkCommand(String s) {
        String[] commands = s.split(" ");
        Integer index = Integer.valueOf(commands[1]);

        if (index < 1 || index >= taskList.size()) { // Index not in range
            formattedPrint("Index given is out of range!");
        } else {
            Task t = taskList.get(index - 1);

            if (commands[0].equals("mark")) { // mark task
                t.mark();
                formattedPrint("I've marked this task as done! \n" +
                        getTaskStatus(t));
            } else { // unmark task
                t.unmark();
                formattedPrint("I've unmarked this task as not done! \n" +
                        getTaskStatus(t));
            }
        }
    }
}
