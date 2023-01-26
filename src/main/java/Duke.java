import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import java.nio.file.Files;
import java.nio.file.Path;

public class Duke {
    private final static String delimiter = "\\|";
    private final static String bye = "bye";
    private final static String goodbyeMessage = "Bye. Hope to see you again soon!";
    private final static String list = "list";
    private final static String mark = "mark";
    private final static String unmark = "unmark";
    private final static String delete = "delete";
    private static ArrayList<Task> tasks = new ArrayList<>();
    private static Path pathToFile;

    private static void indentedPrintln(String message) {
        String indentedMessage = "     " + message;
        System.out.println(indentedMessage);
    }

    private static void list() {
        indentedPrintln("Here are the tasks in your list:");
        int numOfTasks = tasks.size();
        for (int i = 1; i <= numOfTasks; i++) {
            indentedPrintln(i + "." + tasks.get(i - 1));
        }
    }

    private static void mark(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsDone();
        String message = "Nice! I've marked this task as done:";
        indentedPrintln(message);
        indentedPrintln("  " + task);
    }

    private static void unmark(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        task.markAsNotDone();
        String message = "OK, I've marked this task as not done yet:";
        indentedPrintln(message);
        indentedPrintln("  " + task);
    }

    private static boolean isValidTask(String command) {
        if (command.length() >= 4 && command.substring(0, 4).equals("todo")) {
            if (command.length() <= 5) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            return true;
        } else if (command.length() >= 8 && command.substring(0, 8).equals("deadline")) {
            if (command.length() <= 9) {
                throw new DukeException("The description of a deadline cannot be empty.");
            }
            return true;
        } else if (command.length() >= 5 && command.substring(0, 5).equals("event")) {
            if (command.length() <= 6) {
                throw new DukeException("The description of an event cannot be empty.");
            }
            return true;
        }
        return false;
    }

    private static void addTask(String command) {
        int len = command.length();
        String description;
        Task newTask;
        if (command.substring(0, 4).equals("todo")) {
            description = command.substring(5);
            newTask = new Todo(description);
        } else if (command.substring(0, 8).equals("deadline")) {
            int indexOfBy = -1;
            for (int i = 0; i < len; i++) {
                if (command.charAt(i) == '/') {
                    indexOfBy = i;
                }
            }
            description = command.substring(9, indexOfBy - 1);
            String by = command.substring(indexOfBy + 4);
            newTask = new Deadline(description, by);
        } else {
            int indexOfStart = -1, indexOfEnd = -1;
            for (int i = 0; i < len; i++) {
                if (command.charAt(i) == '/') {
                    if (indexOfStart == -1) {
                        indexOfStart = i;
                    } else {
                        indexOfEnd = i;
                    }
                }
            }
            description = command.substring(6, indexOfStart - 1);
            String start = command.substring(indexOfStart + 6, indexOfEnd - 1);
            String end = command.substring(indexOfEnd + 4);
            newTask = new Event(description, start, end);
        }
        tasks.add(newTask);
        indentedPrintln("Got it. I've added this task:");
        indentedPrintln("  " + newTask);
        indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");

        try {
            addTaskToFile(newTask);
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    private static void deleteTask(int taskNumber) {
        Task task = tasks.get(taskNumber - 1);
        tasks.remove(taskNumber - 1);
        indentedPrintln("Noted. I've removed this task:");
        indentedPrintln("  " + task);
        indentedPrintln("Now you have " + tasks.size() + " tasks in the list.");
    }

    private static Path openDataFile() throws IOException {
        String home = System.getProperty("user.home");
        Path pathToDir = Paths.get(home, "Downloads", "data");
        if (!Files.exists(pathToDir)) {
            Files.createDirectories(pathToDir);
        }
        Path pathToFile = Paths.get(home, "Downloads", "data", "duke.txt");
        if (!Files.exists(pathToFile)) {
            Files.createFile(pathToFile);
        }
        return pathToFile;
    }

    private static String[] readFromDataFile(int lineNumber) throws IOException {
        String line = Files.readAllLines(pathToFile).get(lineNumber);
        String[] lineArray = line.split(delimiter);
        int len = lineArray.length;
        for (int i = 0; i < len; i++) {
            lineArray[i] = lineArray[i].trim();
        }
        return lineArray;
    }

    private static void fileToArrayList() throws IOException {
        int lineNumber = Math.toIntExact(Files.lines(pathToFile).count());
        tasks = new ArrayList<>();
        for (int i = 0; i < lineNumber; i++) {
            String[] s = readFromDataFile(i);
            Task task;
            switch (s[0]) {
            case "T":
                task = new Todo(s[2]);
                break;
            case "D":
                task = new Deadline(s[2], s[3]);
                break;
            case "E":
                String[] date = s[3].split("-"); // to be improved
                task = new Event(s[2], date[0], date[1]);
                break;
            default:
                throw new DukeException("Invalid type of task");
            }
            if (Integer.parseInt(s[1]) == 1) {
                task.markAsDone();
            } else if (Integer.parseInt(s[1]) == 0) {
                task.markAsNotDone();
            } else {
                throw new DukeException("Invalid file format");
            }
            tasks.add(task);
        }
    }

    private static void writeTasksToFile() throws IOException {
        FileWriter fw = new FileWriter(pathToFile.toString());
        for (Task task : tasks) {
            fw.write(task.taskInFileFormat());
            fw.write(System.lineSeparator());
        }
        fw.close();
    }

    private static void addTaskToFile(Task task) throws IOException {
        FileWriter fw = new FileWriter(pathToFile.toString(), true);
        fw.write(task.taskInFileFormat());
        fw.write(System.lineSeparator());
        fw.close();
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        try {
            pathToFile = openDataFile();
            fileToArrayList();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        while (!str.equals(bye)) {
            try {
                if (str.equals(list)) {
                    list();
                } else if (str.length() >= 4 && str.substring(0, 4).equals(mark)) {
                    mark(Character.getNumericValue(str.charAt(5)));
                } else if (str.length() >= 6 && str.substring(0, 6).equals(unmark)) {
                    unmark(Character.getNumericValue(str.charAt(7)));
                } else if (isValidTask(str)) {
                    addTask(str);
                } else if (str.length() >= 6 && str.substring(0, 6).equals(delete)) {
                    deleteTask(Character.getNumericValue(str.charAt(7)));
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.err.println("     " + e);
            } finally {
                System.out.println();
                str = sc.nextLine();
            }
        }
        indentedPrintln(goodbyeMessage);
    }
}
