import java.util.ArrayList;
import java.util.Scanner;

/**
 * A teenage chatbot that can store text entered by the user and
 * display the stored text when requested in the form of a list
 *
 * @author lavanya
 * @version 1.0
 */
public class Duke {
    private static final Scanner inputGetter = new Scanner(System.in);

    private static String input = "";

    private static final ArrayList<Task> items= new ArrayList<>();

    private static final String greeting = "Welcome to Lavender Network! \n" +
            "I'm Iris, your favourite teenage chatbot. \n" +
            "I'm here to keep track of your tasks so you don't have to :)\n" +
            "To add a task, type a word or phrase and press enter.\n" +
            "To see an list of your task, type \"list\".\n" +
            "To mark a task as done, type \"mark <task number>\".\n"  +
            "To mark a task as not done, type \"unmark <task number>\".\n"  +
            "A task marked with a X is done. " +
            "To close me, type \"bye\". \n" +
            "What are you waiting for? Let's get started!";

    private static final String exitGreeting = "Bye! Hope to see you soon!";

    /**
     * This method gets input from the user and stores it in the input field
     */
    private static void getInput() {
        input = inputGetter.nextLine();
    }

    /**
     * This method prints out the string out in the terminal
     * @param out the string to be displayed
     */
    private static void output(String out) {
        System.out.println("\033[35m" + out);
    }

    public static void main(String[] args) {
        output(greeting);
        loop:
        while (true) {
            getInput();
            Task task;
            switch (input.split(" ")[0]) {
                case "bye":
                    output(exitGreeting);
                    break loop;
                case "list":
                    if (items.size() > 5) {
                        output("You have the following tasks: (Soooo many >:O)!");
                    } else {
                        output("You have the following tasks: (So few ~ good going!)");
                    }

                    for (int i = 0; i < items.size(); i++) {
                        task = items.get(i);
                        output((i + 1) + ". " + task);
                    }
                    break;
                case "mark":
                    output("Good job! I've marked this task done:");
                    task = items.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    task.mark();
                    output(task.toString());
                    break;
                case "unmark":
                    output("Bummer! Have fun doing this: ");
                    task = items.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    task.unmark();
                    output(task.toString());
                    break;
                default:
                    task = new Task(input);
                    items.add(task);
                    if (items.size() < 4) {
                        output("Added your task: " + task);
                    } else if (items.size() > 10) {
                        output("What?!! You're going to dieee! Added your task: \"" + task);
                    } else {
                        output("Another task? Phew >:(. Added your task: " + task);
                    }
            }
        }
    }
}
