import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final String GREETING = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String GOODBYE = "Bye. Hope to see you again soon!";
    private static final ArrayList<Task> TASKS = new ArrayList<>();

    /**
     * Available commands.
     */
    public enum Command {
        LIST, MARK, UNMARK, BYE
    }

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
     * @param inp input string.
     */
    private static void prettifyOut(String inp) {
        borderLine();
        System.out.println(autoIndent(inp));
        borderLine();
    }

    /**
     * @param inp input string.
     * @return False if final command.
     */
    private static boolean executeInput(String inp) {
        Duke.Command cmd;
        try {
            cmd = Duke.Command.valueOf(inp.split(" ")[0].toUpperCase());
        } catch(IllegalArgumentException e) {
            TASKS.add(new Task(inp));
            borderLine();
            System.out.println(autoIndent("added: " + inp));
            borderLine();
            return true;
        }
        switch(cmd) {
            case LIST:
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
            case MARK:
                int taskToMark = Integer.parseInt(inp.split(" ")[1]) - 1;
                if (taskToMark >= TASKS.size()) {
                    prettifyOut("Task does not exist!");
                } else {
                    TASKS.get(taskToMark).markAsDone();
                    prettifyOut("Nice! I've marked this task as done:\n" + TASKS.get(taskToMark));
                }
                break;
            case UNMARK:
                int taskToUnmark = Integer.parseInt(inp.split(" ")[1]) - 1;
                if (taskToUnmark >= TASKS.size()) {
                    prettifyOut("Task does not exist!");
                } else {
                    TASKS.get(taskToUnmark).markAsUndone();
                    prettifyOut("OK, I've marked this task as not done yet:\n" + TASKS.get(taskToUnmark));
                }
                break;
            case BYE:
                prettifyOut(GOODBYE);
                return false;
        }
        return true;
    }

    /**
     * The main function.
     * @param args command line arguments.
     */
    public static void main(String[] args) {
        boolean cont = true;
        prettifyOut(GREETING);

        Scanner input = new Scanner(System.in);
        while (cont) {
            String userInput = input.nextLine();
            if (userInput.isBlank()) {
                borderLine();
                continue;
            }

            cont = executeInput(userInput);
        }
    }
}
