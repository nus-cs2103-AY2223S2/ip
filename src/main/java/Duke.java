import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    enum TypeOfTask {
        TODO, DEADLINE, EVENT
    }
    private static List<Task> listOfTasks = new ArrayList<>();
    private static Storage storage;

    public static void loadFile() throws IOException {
        String fileSep = System.getProperty("file.separator");
        String userDir = System.getProperty("user.dir");
        Path foldPath = Paths.get( userDir + fileSep + "data");
        Path filePath = Paths.get(foldPath + fileSep + "duke.txt");
        try {
            if (!Files.isDirectory(foldPath)) {
                Files.createDirectory(foldPath);
                Files.createFile(filePath);
            } else if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
            storage = new Storage(filePath.toString());
            listOfTasks = storage.load();
        } catch (IOException e) {
            System.out.println("Error");
        }
    }

    public static void printLine(){
        System.out.println("\t____________________________________________________________");
    }

    public static void greet() {
        printLine();
        System.out.println("\tHello! I'm C-3PO, Human Cyborg Relations.\n\tWhat can I do for you?");
        printLine();
    }

    public static void exit() {
        printLine();
        System.out.println("\tBye. Hope to see you again soon!");
        printLine();
    }

    public static void saveTask(String command) throws DukeException {
        Task task;
        String description;
        String firstWord = command.split(" ")[0];
        TypeOfTask taskType = null;

        if (firstWord.equalsIgnoreCase("todo")) {
            taskType = TypeOfTask.TODO;
        } else if (firstWord.equalsIgnoreCase("deadline")) {
            taskType = TypeOfTask.DEADLINE;
        } else if (firstWord.equalsIgnoreCase("event")) {
            taskType = TypeOfTask.EVENT;
        }

        try {
            switch (taskType) {
            case TODO:
                try {
                    description = command.substring(5);
                    task = new Todo(description);
                    break;
                } catch(StringIndexOutOfBoundsException e) {
                    throw new DukeException(firstWord);
                }
            case DEADLINE:
                try {
                    int byIdx = command.indexOf("/by");
                    description = command.substring(9, byIdx - 1);
                    String by = command.substring(byIdx + 4);
                    task = new Deadline(description, by);
                    break;
                } catch(StringIndexOutOfBoundsException e) {
                    throw new DukeException(firstWord);
                }
            case EVENT:
                try {
                    int fromIdx = command.indexOf("/from");
                    int toIdx = command.indexOf("/to");
                    description = command.substring(6, fromIdx - 1);
                    String from = command.substring(fromIdx + 6, toIdx - 1);
                    String to = command.substring(toIdx + 4);
                    task = new Event(description, from, to);
                    break;
                } catch(StringIndexOutOfBoundsException e) {
                    throw new DukeException(firstWord);
                }
            default:
                throw new DukeException();
            }
        } catch (NullPointerException e) {
            throw new DukeException();
        }

        listOfTasks.add(task);
        printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t  " + task);
        System.out.println("\tNow you have " + listOfTasks.size() + " tasks in the list.");
        printLine();
    }

    public static void listTasks() {
        Task task;
        printLine();
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= listOfTasks.size(); i++) {
            task = listOfTasks.get(i - 1);
            System.out.println("\t" + i + "." + task);
        }
        printLine();
    }

    public static void markTask(String command) throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsDone();
            printLine();
            System.out.println("\tNice! I've marked this task as done:");
            System.out.println("\t  " + task);
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public static void unmarkTask(String command) throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        try {
            Task task = listOfTasks.get(index - 1);
            task.markAsUndone();
            printLine();
            System.out.println("\tOK, I've marked this task as not done yet:");
            System.out.println("\t  " + task);
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public static void deleteTask(String command) throws DukeException {
        int index = Integer.parseInt(command.split(" ")[1]);
        try {
            Task task = listOfTasks.get(index - 1);
            listOfTasks.remove(index - 1);
            printLine();
            System.out.println("\tNoted. I've removed this task:");
            System.out.println("\t  " + task);
            System.out.println("\tNow you have " + listOfTasks.size() + " tasks in the list.");
            printLine();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(index);
        }
    }

    public static void main(String[] args) throws DukeException, IOException {
        loadFile();
        Scanner input = new Scanner(System.in);
        String command, firstWord;
        greet();
        while (true) {
            try {
                command = input.nextLine().trim();
                firstWord = command.split(" ")[0];
                if (command.equalsIgnoreCase("bye")) {
                    exit();
                    break;
                } else if (command.equalsIgnoreCase("list")) {
                    listTasks();
                } else if (firstWord.equalsIgnoreCase("mark")) {
                    markTask(command);
                    storage.save();
                } else if (firstWord.equalsIgnoreCase("unmark")) {
                    unmarkTask(command);
                    storage.save();
                } else if (firstWord.equalsIgnoreCase("delete")) {
                    deleteTask(command);
                    storage.save();
                } else {
                    saveTask(command);
                    storage.save();
                }
            } catch (IOException e) {
                System.out.println("Error!!!");
            }
        }
        input.close();
    }
}
