import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Peppa {
    public static final String divider = "=============================================";
    public enum Command {
        BYE,
        LIST,
        MARK,
        UNMARK,
        DELETE,
        TODO,
        DEADLINE,
        EVENT
    }

    public static ArrayList<Task> tasks = new ArrayList<>();

    public static void displayList() {
        System.out.println("Here are the tasks in your list currently:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(Integer.toString(i + 1) + ". " + tasks.get(i));
        }
    }

    public static Task getTask(String input) throws NumberFormatException, IndexOutOfBoundsException {
        int id = Integer.parseInt(input.split(" ")[1]);
        Task task = tasks.get(id - 1);
        return task;
    }

    public static void addTask(Task task) {
        tasks.add(task);
        System.out.println("Oink! I've added the following task:");
        System.out.println("> "+ task.toString());
        printSummary();
    }

    public static void deleteTask(String input) {
        int id = Integer.parseInt(input.split(" ")[1]);
        Task task = tasks.get(id - 1);
        tasks.remove(task);
        System.out.println("Oink! I've removed the following task:");
        System.out.println("> "+ task.toString());
        printSummary();
    }

    public static void markDone(Task task) {
        task.setDone(true);
        System.out.println("Oink! I've marked this task as done:");
        System.out.println("> " + task.toString());
    }

    public static void unmarkDone(Task task) {
        task.setDone(false);
        System.out.println("Oink! I've marked this task as undone:");
        System.out.println("> " + task.toString());
    }

    public static void printSummary() {
        System.out.println("You now have " + Integer.toString(tasks.size()) + " tasks in the list.");
    }

    public static void printIntroduction() {
        String logo = " ____  ____  ____  ____   __\n"
                + "(  _ \\(  __)(  _ \\(  _ \\ / _\\\n"
                + " ) __/ ) _)  ) __/ ) __//    \\\n"
                + "(__)  (____)(__)  (__)  \\_/\\_/\n";
        String art = "       _\n"
                + "  <`--'\\>______\n"
                + "  /. .  `'     \\\n"
                + " (`')  ,        @\n"
                + "  `-._,        /\n"
                + "     )-)_/--( >  jv\n"
                + "    ''''  ''''\n";
        System.out.println(divider);
        System.out.println("Oink! I'm\n" + logo + art);
        System.out.println("Nice to meet you! How can I assist you today?");
        System.out.println(divider);
    }

    public static String extractDescription(Command cmd, String input) throws PeppaException {
        int descStartIndex = input.indexOf(" ") + 1;
        if (descStartIndex == 0) {
            throw new PeppaException("Boink! The description of a " + cmd.name().toLowerCase() + " cannot be empty.");
        }
        int descEndIndex = input.indexOf("/") - 1;
        if (descEndIndex < 0) {
            return input.substring(descStartIndex);
        } else {
            return input.substring(descStartIndex, descEndIndex);
        }
    }

    public static void insertTask(Command cmd, String input) throws PeppaException {
        String desc = extractDescription(cmd, input);
        switch (cmd) {
            case TODO:
                addTask(new Todo(desc));
                break;
            case EVENT:
                int fromStartIndex = input.indexOf("/") + 6;
                int fromEndIndex = input.indexOf("/to") - 1;
                int toStartIndex = input.indexOf("/to") + 4;
                String from = input.substring(fromStartIndex, fromEndIndex);
                String to = input.substring(toStartIndex);
                addTask(new Event(desc, from, to));
                break;
            case DEADLINE:
                int dueStartIndex = input.indexOf("/") + 4;
                String due = input.substring(dueStartIndex);
                addTask(new Deadline(desc, due));
        }
    }

    public static void parseCommand(Command cmd, String input) throws PeppaException {
        switch (cmd) {
            case LIST:
                displayList();
                break;
            case MARK:
                markDone(getTask(input));
                break;
            case UNMARK:
                unmarkDone(getTask(input));
                break;
            case DELETE:
                 deleteTask(input);
                 break;
            default:
                try {
                    insertTask(cmd, input);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Boink! Please ensure that your input is formatted correctly and try again.");
                }
        }
    }

    public static void printCommands() {
        for (Command cmd : Command.values()) {
            System.out.println("> " + cmd.name().toLowerCase());
        }
    }

    public static void main(String[] args) {
        printIntroduction();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            try {
                Command cmd = Command.valueOf(input.split(" ")[0].toUpperCase());
                parseCommand(cmd, input);
            } catch (NumberFormatException e) {
                System.out.println("Boink! Please enter a valid integer.");
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Boink! Task does not exist; please try again.");
            } catch (PeppaException e) { // thrown when no description of task is entered
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Boink! Peppa couldn't understand that. " +
                        "Please use one of the commands below:");
                printCommands();
            } finally {
                System.out.println(divider);
                input = sc.nextLine();
            }
        }
        System.out.println("Oink oink! See you again :)");
        sc.close();
    }
}