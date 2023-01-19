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

    private static final String greeting = "Welcome to Lavender Network!\n" +
            "I'm Iris, your favourite teenage chatbot.\n" +
            "I'm here to keep track of your tasks so you don't have to :)\n" +
            "Type \"help\" to learn the commands.\n" +
            "What are you waiting for? Let's get started!";

    private static final String help_text = "Hello! You can use the following commands:\n" +
            "To add a todo task, type \"todo \" + your task.\n" +
            "To add a task with a deadline, type \"deadline \" + your task + \"/by \" + your deadline.\n" +
            "To add an event, type \"event \" + your event + " +
            "\"/from \" + event start time + \"/to \" + event end time.\n" +
            "To see an list of your tasks, type \"list\".\n" +
            "To mark a task as done, type \"mark <task number>\".\n"  +
            "To mark a task as not done, type \"unmark <task number>\".\n"  +
            "A task marked with a X is done. " +
            "To close me, type \"bye\".\n" +
            "Have fun!";

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

    private static void add_item(Task task) {
        items.add(task);
        if (items.size() < 4) {
            output("Added your task: " + task);
        } else if (items.size() > 10) {
            output("What?!! You're going to dieee! Added your task: " + task);
        } else {
            output("Another task? Phew >:(. Added your task: " + task);
        }
        output("You have " + items.size() + " tasks.");
    }

    enum Command {
        help, bye, list, mark, unmark, delete, todo, deadline, event
    }

    public static void main(String[] args) {
        output(greeting);
        loop:
        while (true) {
            getInput();
            Task task;
            Command command = Command.valueOf(input.split(" ")[0]);
            switch (command) {
                case help:
                    output(help_text);
                    break;
                case bye:
                    output(exitGreeting);
                    break loop;
                case list:
                    if (items.size() > 5) {
                        output("You have the following tasks: (So many >:O)!");
                    } else {
                        output("You have the following tasks: (So few ~ good going!)");
                    }

                    for (int i = 0; i < items.size(); i++) {
                        task = items.get(i);
                        output((i + 1) + ". " + task);
                    }
                    break;
                case mark:
                    output("Good job! I've marked this task done:");
                    task = items.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    task.mark();
                    output(task.toString());
                    break;
                case unmark:
                    output("Bummer! Have fun doing this:");
                    task = items.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    task.unmark();
                    output(task.toString());
                    break;
                case delete:
                    int i = Integer.parseInt(input.split(" ")[1]) - 1;
                    output("I've removed this task");
                    output(items.get(i).toString());
                    items.remove(i);
                    break;
                case todo:
                    add_item(new Todo(input));
                    break;
                case deadline:
                    add_item(new Deadline(input));
                    break;
                case event:
                    add_item(new Event(input));
                    break;
                default:
                    add_item(new Task(input));
            }
        }
    }
}
