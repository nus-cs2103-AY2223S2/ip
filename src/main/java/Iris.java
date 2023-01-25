import java.util.ArrayList;
import java.util.Scanner;

/**
 * A teenage chatbot that can store text entered by the user and
 * display the stored text when requested in the form of a list
 *
 * @author lavanya
 * @version 1.0
 */
public class Iris {
    private static final Scanner inputGetter = new Scanner(System.in);

    private static final ArrayList<Task> items = new ArrayList<>();

    private static final String GREETING = "Welcome to Lavender Network!\n" +
            "I'm Iris, your favourite teenage chatbot.\n" +
            "I'm here to keep track of your tasks so you don't have to :)\n" +
            "Type \"/help\" to see the commands.\n" +
            "What are you waiting for? Let's get started!";

    private static final String HELP_TEXT = "Hello! You can use the following commands:\n" +
            "To add a todo task, type \"todo \" + your task.\n" +
            "To add a task with a deadline, type \"deadline \" + your task + \"/by \" + your deadline.\n" +
            "To add an event, type \"event \" + your event + " +
            "\"/from \" + event start time + \"/to \" + event end time.\n" +
            "To see an list of your tasks, type \"list\".\n" +
            "To mark a task as done, type \"mark <task number>\".\n"  +
            "To mark a task as not done, type \"unmark <task number>\".\n"  +
            "To delete a task, type \"delete <task number>\".\n" +
            "A task marked with a X is done. " +
            "To close me, type \"bye\".\n" +
            "Have fun!";

    private static final String EXIT_GREETING = "Bye! Hope to see you soon!";

    private static String input = "";

    private static void getInput() {
        input = inputGetter.nextLine();
    }

    private static void output(String out) {
        System.out.println("\033[35m" + out);
    }

    private static void addItem(Task task) {
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

    private static void checkNotEmpty(String str, String message) throws MissingFieldException {
        if (str.isEmpty()) {
            throw new MissingFieldException(message);
        }
    }

    enum Command {
        help, bye, list, mark, unmark, delete, todo, deadline, event
    }

    public static void main(String[] args) {
        output(GREETING);
        loop:
        while (true) {
            try {
                String[] arr;
                String name, from, to, by;
                getInput();
                Task task;
                Command command;
                try {
                    command = Command.valueOf(input.split(" ")[0]);
                } catch (IllegalArgumentException e) {
                    throw new NoTaskException();
                }
                switch (command) {
                case help:
                    output(HELP_TEXT);
                    break;
                case bye:
                    output(EXIT_GREETING);
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
                    try {
                        task = items.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    } catch (IndexOutOfBoundsException e) {
                        throw new UnknownTaskException();
                    }
                    task.mark();
                    output("Good job! I've marked this task done:");
                    output(task.toString());
                    break;
                case unmark:
                    try {
                        task = items.get(Integer.parseInt(input.split(" ")[1]) - 1);
                    } catch (IndexOutOfBoundsException e) {
                        throw new UnknownTaskException();
                    }
                    task.unmark();
                    output("Bummer! Have fun doing this:");
                    output(task.toString());
                    break;
                case delete:
                    try {
                        int i = Integer.parseInt(input.split(" ")[1]) - 1;
                        task = items.get(i);
                        items.remove(i);
                    } catch (IndexOutOfBoundsException e) {
                        throw new UnknownTaskException();
                    }
                    output("I've removed this task");
                    output(task.toString());
                    break;
                case todo:
                    try {
                        name = input.substring(5);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingFieldException("Description of Todo task");
                    }
                    checkNotEmpty(name, "Description of Todo task");
                    addItem(new Todo(name));
                    break;
                case deadline:
                    try {
                        arr = input.split("/by");
                        name = arr[0].substring(9);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingFieldException("Description of deadline");
                    }
                    checkNotEmpty(name, "Description of deadline");
                    try {
                        by = arr[1].substring(1);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingFieldException("Deadline date");
                    }
                    checkNotEmpty(by, "Deadline date");
                    addItem(new Deadline(name, by));
                    break;
                case event:
                    try {
                        arr = input.split("/");
                        name = arr[0].substring(6);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingFieldException("Description of event");
                    }
                    checkNotEmpty(name, "Description of event");
                    try {
                        from = arr[1].substring(5);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingFieldException("Start date of event");
                    }
                    checkNotEmpty(from, "Start date of event");
                    try {
                        to = arr[2].substring(3);
                    } catch (IndexOutOfBoundsException e) {
                        throw new MissingFieldException("End date of event");
                    }
                    checkNotEmpty(to, "End date of event");
                    addItem(new Event(name, from, to));
                    break;
                default:
                    throw new NoTaskException();
                }
            } catch (IrisException e) {
                output(e.message);
            }
        }
    }
}
