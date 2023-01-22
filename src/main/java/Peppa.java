import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Peppa {
    public static final String DIVIDER = "=============================================";
    public static final String FILE_PATH = "data/todo.txt";
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
        System.out.println(DIVIDER);
        System.out.println("Oink! I'm\n" + logo + art);
        System.out.println("Nice to meet you! How can I assist you today?");
        System.out.println(DIVIDER);
    }

    public static String extractDescription(Command cmd, String input) throws PeppaEmptyDescriptionException {
        int descStartIndex = input.indexOf(" ") + 1;
        if (descStartIndex == 0) {
            throw new PeppaEmptyDescriptionException("Boink! The description of a " +
                    cmd.name().toLowerCase() + " cannot be empty.");
        }
        int descEndIndex = input.indexOf("/") - 1;
        if (descEndIndex < 0) {
            return input.substring(descStartIndex);
        } else {
            return input.substring(descStartIndex, descEndIndex);
        }
    }

    public static void insertTask(Command cmd, String input) throws PeppaEmptyDescriptionException, PeppaFormatException {
        String desc = extractDescription(cmd, input);
        switch (cmd) {
            case TODO:
                addTask(new Todo(desc));
                break;
            case EVENT:
                int fromStartIndex = input.indexOf("/from") + 6;
                int fromEndIndex = input.indexOf("/to") - 1;
                int toStartIndex = input.indexOf("/to") + 4;
                String from = input.substring(fromStartIndex, fromEndIndex);
                String to = input.substring(toStartIndex);
                addTask(new Event(desc, from, to));
                break;
            case DEADLINE:
                int dueStartIndex = input.indexOf("/by") + 4;
                if (dueStartIndex == 3) {
                    throw new PeppaFormatException("Boink! Please ensure that your input is formatted correctly and try again.");
                }
                String due = input.substring(dueStartIndex);
                addTask(new Deadline(desc, due));
        }
    }

    public static void parseCommand(Command cmd, String input) throws PeppaEmptyDescriptionException, PeppaFormatException, IOException {
        switch (cmd) {
            case LIST:
                displayList();
                break;
            case MARK:
                markDone(getTask(input));
                saveChanges();
                break;
            case UNMARK:
                unmarkDone(getTask(input));
                saveChanges();
                break;
            case DELETE:
                 deleteTask(input);
                 saveChanges();
                 break;
            default:
                try {
                    insertTask(cmd, input);
                    saveChanges();
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

    public static void loadData() {
        System.out.print("Initialising data (if any)...... ");
        try {
            File f = new File(FILE_PATH);
            f.getParentFile().mkdirs();
            f.createNewFile();
            Scanner sc = new Scanner(f);
            while (sc.hasNext()) {
                String line = sc.nextLine();
                parseLine(line);
            }
            System.out.println("Done!");
        } catch (IOException e) {
            System.out.println("Boink! There seems to be an I/O error. Please try again.");
        }
    }

    public static void parseLine(String line) {
        String[] fields = line.split(" \\| ");
        String taskType = fields[0], done = fields[1], taskDesc = fields[2];
        switch(taskType) {
        case("T"):
            tasks.add(new Todo(taskDesc));
            break;
        case("E"):
            String[] eventFields = fields[3].split("-");
            tasks.add(new Event(taskDesc, eventFields[0], eventFields[1]));
            break;
        case("D"):
            String deadline = fields[3];
            tasks.add(new Deadline(taskDesc, deadline));
            break;
        }
        if (done.equals("1")) {
            tasks.get(tasks.size()- 1).setDone(true);
        }
    }

    public static void saveChanges() throws IOException {
        FileWriter fw = new FileWriter(FILE_PATH);
        for (Task t : tasks) {
            fw.write(t.toFormatString() + "\n");
        }
        fw.close();
    }

    public static void main(String[] args) {
        loadData();
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
            } catch (PeppaFormatException | PeppaEmptyDescriptionException e) {
                System.out.println(e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("Boink! Peppa couldn't understand that. " +
                        "Please use one of the commands below:");
                printCommands();
            } catch (IOException e) {
                System.out.println("Boink! Peppa failed to save changes to your to-do list. " +
                        "Please try again.");
            } finally {
                System.out.println(DIVIDER);
                input = sc.nextLine();
            }
        }
        System.out.println("Oink oink! See you again :)");
        sc.close();
    }
}