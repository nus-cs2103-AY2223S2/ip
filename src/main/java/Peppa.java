import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Peppa {
    public static final String divider = "=============================================";
    public static final String[] commands = { "list", "mark", "unmark", "todo", "deadline", "event" };
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

    public static void insertTask(String command, String input) throws PeppaException {
        int descStartIndex = input.indexOf(" ") + 1;
        if (descStartIndex == 0) {
            throw new PeppaException("Boink! The description of a " + command + " cannot be empty.");
        } else {
            if (command.equals("todo")) {
                String desc = input.substring(descStartIndex);
                addTask(new Todo(desc));
            } else {
                int descEndIndex = input.indexOf("/") - 1;
                String desc = input.substring(descStartIndex, descEndIndex);
                if (command.equals("event")) {
                    int fromStartIndex = input.indexOf("/") + 6;
                    int fromEndIndex = input.indexOf("/to") - 1;
                    int toStartIndex = input.indexOf("/to") + 4;
                    String from = input.substring(fromStartIndex, fromEndIndex);
                    String to = input.substring(toStartIndex);
                    addTask(new Event(desc, from, to));
                } else {
                    int dueStartIndex = input.indexOf("/") + 4;
                    String due = input.substring(dueStartIndex);
                    addTask(new Deadline(desc, due));
                }
            }
        }
    }

    public static void parseCommand(String input) throws PeppaException {
        String command = input.split(" ")[0];
        if (Arrays.asList(commands).contains(command)) {
             if (command.equals("list")) {
                displayList();
            } else if (command.startsWith("mark")) {
                markDone(getTask(input));
            } else if (command.startsWith("unmark")) {
                unmarkDone(getTask(input));
            } else {
                try {
                    insertTask(command, input);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Boink! Please ensure that your input is formatted correctly and try again.");
                }
            }
        } else {
            throw new InvalidTaskException("Boink! Peppa couldn't understand that. " +
                    "Please use one of the commands below:");
        }
    }

    public static void main(String[] args) {
        printIntroduction();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Oink oink! See you again :)");
                sc.close();
                break;
            } else {
                try {
                    parseCommand(input);
                } catch (NumberFormatException e) {
                    System.out.println("Boink! Please enter a valid integer.");
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Boink! Task does not exist; please try again.");
                } catch (PeppaException e) {
                    System.out.println(e.getMessage());
                } finally {
                    System.out.println(divider);
                }
            }
        }
    }
}

