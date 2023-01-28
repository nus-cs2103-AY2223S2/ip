import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.util.List; // Import List class
import java.util.LinkedList; // Import LinkedList class
import java.util.Scanner;  // Import the Scanner class
import java.nio.file.Files;
import java.nio.file.Path;

public class Duke {

    private static Scanner getInput = new Scanner(System.in); // Create a static Scanner object

    private static List<Task> storedInputs = new LinkedList<>(); // List to store inputs

    public static void main(String[] args) throws IOException {

        // Print introduction
        System.out.println(intro());

        // Open file
        String home = System.getProperty("user.home"); // Get home directory
        Files.createDirectories(Paths.get(home,"data")); // Create directory if it does not exist
        Path filePath = Paths.get(home, "data", "duke.txt");
        try {
            Files.createFile(filePath); // Create empty file if it does not exist
        } catch (FileAlreadyExistsException ignored) {
        }

        // Load file content into list
        String[] savedTask = Files.readString(filePath).split("\n"); // Read file
        Boolean isTaskDone;
        String taskDetails, taskDate;
        for (String s : savedTask) {
            if (s.isBlank()) {
                break;
            }
            switch (decodeTaskType(s)) {
                case 'T':
                    isTaskDone = getIsTaskDone(s);
                    taskDetails = getTaskDetails(s);
                    storedInputs.add(new ToDo(isTaskDone, taskDetails));
                    break;
                case 'D':
                    isTaskDone = getIsTaskDone(s);
                    taskDetails = getTaskDetails(s);
                    taskDate = getTaskDate(s);
                    storedInputs.add(new Deadline(isTaskDone, taskDetails, taskDate));
                    break;
                case 'E':
                    isTaskDone = getIsTaskDone(s);
                    taskDetails = getTaskDetails(s);
                    taskDate = getTaskDate(s);
                    storedInputs.add(new Event(isTaskDone, taskDetails, taskDate));
                    break;
                }
            }
        System.out.println(printList());

        // Execute inputs
        String userInput;
        EventType curEvent;
        loop: while (true) {

            // Get inputs
            userInput = askForInput();

            // Check if valid
            try {
                curEvent = decodeInput(userInput);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                continue; // skip the rest of the code and go to next iteration
            }

            // If valid keyword, execute input
            switch (curEvent) {
            case BYE:
                break loop;
            case LIST:
                System.out.println("\nHere are the tasks in your list:\n" + printList());
                break;
            case MARK:
                markEvent(userInput);
                replaceFileContents(filePath,saveList());
                break;
            case UNMARK:
                unmarkEvent(userInput);
                replaceFileContents(filePath,saveList());
                break;
            case DELETE:
                deleteEvent(userInput);
                replaceFileContents(filePath,saveList());
                break;
            case TODO:
                todoEvent(userInput);
                replaceFileContents(filePath,saveList());
                break;
            case DEADLINE:
                deadlineEvent(userInput);
                replaceFileContents(filePath,saveList());
                break;
            case EVENT:
                eventEvent(userInput);
                replaceFileContents(filePath,saveList());
                break;
            }
        }

        System.out.println(outro());
    }

    private static char decodeTaskType(String s) {
        return s.charAt(0);
    }

    private static Boolean getIsTaskDone(String s) {
        String line = s.substring(s.indexOf("|") + 1);
        return line.substring(0, line.indexOf("|")).equals("X");
    }

    private static String getTaskDetails(String s) {
        String line = s.substring(s.indexOf("|") + 1);
        line = line.substring(line.indexOf("|") + 1);

        if (!line.contains("|")) {
            return line;
        }

        return line.substring(0, line.indexOf("|"));
    }

    private static String getTaskDate(String s) {
        String line = s.substring(s.indexOf("|") + 1);
        line = line.substring(line.indexOf("|") + 1);
        line = line.substring(line.indexOf("|") + 1);
        return line;
    }

    private static String logo() {
        return " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    }

    private static String intro() {
        return "Hello! I'm\n" + logo() + "\nWhat can I do for you?";
    }

    private static String outro() {
        return "Good Riddance!";
    }

    private static String askForInput() {
        System.out.print("> ");
        return getInput.nextLine();
    }

    // Throws exception if invalid keyword
    private static EventType decodeInput(String input) throws DukeException {
        String[] arr = input.split(" ");
        
        if (arr[0].equals("bye")) {
            return EventType.BYE;
        }
        if (arr[0].equals("list")) {
            return EventType.LIST;
        }
        if (arr[0].equals("mark")) {
            return EventType.MARK;
        }
        if (arr[0].equals("unmark")) {
            return EventType.UNMARK;
        }
        if (arr[0].equals("delete")) {
            return EventType.DELETE;
        }
        if (arr[0].equals("todo")) {
            return EventType.TODO;
        }
        if (arr[0].equals("deadline")) {
            return EventType.DEADLINE;
        }
        if (arr[0].equals("event")) {
            return EventType.EVENT;
        }
        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }

    private static void markEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t = storedInputs.get(num-1);
        t.markDone();
        System.out.println("\nNice! I've marked this task as done:\n  " + t + "\n");
    }

    private static void unmarkEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t = storedInputs.get(num-1);
        t.markUnDone();
        System.out.println("\nOK, I've marked this task as not done yet:\n  " + t + "\n");
    }

    public static void deleteEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t = storedInputs.remove(num-1);
        System.out.println("\nNoted. I've removed this task:\n  " + t);
        printTotalTasks();
    }

   /**
    * Remove the first word of the user input leaving only the decription 
    * of the task.
    *
    * @param s a string that represent the user input
    * @throws DukeException if there is only a single word
    * @return the same string without the first word
    */
    private static String removeFirstWord(String s) throws DukeException {
        try {
            s = s.substring(s.indexOf(" ")).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
        }
        return s;
    }

    private static String removeKeyword(String s) throws DukeException {
        try {
            s = s.substring(s.indexOf(" ")).trim();
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("details cannot be empty");
        }

        if (s.isEmpty()) {
            throw new DukeException("details cannot be empty");
        }

        return s;
    }

    private static void todoEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            System.out.println("EXCUSE ME!!!, 'todo' " + e.getMessage());
            return;
        }

        Task newTask = new ToDo(userInput);
        storedInputs.add(newTask);
        
        printConfirmationMessage(newTask);
        printTotalTasks();
    }

    /**
    * Remove the first word of every chunk and whitespaces 
    * at the two ends of the String
    *
    * @param s An array of Strings
    */
    private static String[] removeFirstWord(String[] s) throws DukeException {
        // no arguments, guaranteed to have at least 1
        if (s.length == 1) {
            throw new DukeException("Invalid!! Empty Input");
        }
        for (int i = 0; i < s.length; i++) {
            s[i] = s[i].substring(s[i].indexOf(" ")).trim();
        }
        return s;
    }


    private static void deadlineEvent(String userInput) {
        try {
            String[] s = removeFirstWord(userInput.split("/"));
            Task temp = new Deadline(s[0], s[1]);
            storedInputs.add(temp);
            printConfirmation();
            System.out.println("  " + temp);
            printTotalTasks();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static void eventEvent(String userInput) {
        try {
            String[] s = removeFirstWord(userInput.split("/"));
            Task temp = new Event(s[0], s[1], s[2]);
            storedInputs.add(temp);
            printConfirmation();
            System.out.println("  " + temp);
            printTotalTasks();
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            return;
        }
    }

    private static String printList() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= storedInputs.size(); i++) {
            s.append(i).append(". ").append(storedInputs.get(i - 1)).append("\n");
        }
        return s.toString();
    }

    private static String saveList() {
        StringBuilder s = new StringBuilder();
        for (int i = 1; i <= storedInputs.size(); i++) {
            s.append(convertStringToFileFormat(storedInputs.get(i - 1).toString())).append("\n");
        }
        return s.toString();
    }

    private static String convertStringToFileFormat(String line) {
        String task, status, details;
        line = line.substring(line.indexOf("[") + 1);
        task = line.substring(0, line.indexOf("]"));

        line = line.substring(line.indexOf("[") + 1);
        status = line.substring(0, line.indexOf("]"));

        details = line.substring(line.indexOf("]") + 1).trim();

        return task + "|" + status + "|" + details;
    }

    private static void printConfirmation() {
        System.out.println("\nYAY! Task Added:");
    }

    private static void printConfirmationMessage(Task task) {
        System.out.println("\nYAY! Task Added:\n " + task );
    }

    private static void printTotalTasks() {
        System.out.println("Now you have " + Task.count  + " tasks in the list.\n");
    }

    private static void replaceFileContents(Path path, String newContents) throws IOException {
        Files.writeString(path, newContents);
    }
}
