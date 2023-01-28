import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Paths;
import java.time.format.DateTimeParseException;
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
                replaceFileContents(filePath, prepareWriteContents());
                break;
            case UNMARK:
                unmarkEvent(userInput);
                replaceFileContents(filePath, prepareWriteContents());
                break;
            case DELETE:
                deleteEvent(userInput);
                replaceFileContents(filePath, prepareWriteContents());
                break;
            case TODO:
                todoEvent(userInput);
                replaceFileContents(filePath, prepareWriteContents());
                break;
            case DEADLINE:
                deadlineEvent(userInput);
                replaceFileContents(filePath, prepareWriteContents());
                break;
            case EVENT:
                eventEvent(userInput);
                replaceFileContents(filePath, prepareWriteContents());
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

        Task newTask = new ToDo(userInput.trim());
        storedInputs.add(newTask);
        printConfirmationMessage(newTask);
        printTotalTasks();
    }

    private static void deadlineEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            System.out.println("EXCUSE ME!!!, 'deadline' " + e.getMessage());
            return;
        }

        Task newTask;
        try {
            String[] info = userInput.split("/by");
            newTask = new Deadline(info[0].trim(), info[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCUSE ME!!!, please follow the format\ndeadline /by dd/mm/yyyy");
            return;
        }
        catch (DateTimeParseException e) {
            System.out.println("EXCUSE ME!!!, please use the correct date format\n dd/mm/yyyy");
            return;
        }

        printConfirmationMessage(newTask);
        printTotalTasks();
    }

    private static void eventEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            System.out.println("EXCUSE ME!!!, 'event' " + e.getMessage());
            return;
        }

        Task newTask;
        try {
            String[] infoA = userInput.split("/from");
            String[] infoB = infoA[1].split("/to");
            newTask = new Event(infoA[0].trim(), infoB[0].trim(), infoB[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("EXCUSE ME!!!, please follow the format\nevent /from dd/mm/yyyy /to dd/mm/yyyy");
            return;
        } catch (DateTimeParseException e) {
            System.out.println("EXCUSE ME!!!, please use the correct date format\n dd/mm/yyyy");
            return;
        }

        printConfirmationMessage(newTask);
        printTotalTasks();
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

    private static void printConfirmationMessage(Task task) {
        System.out.println("\nYAY! Task Added:\n " + task );
    }

    private static void printTotalTasks() {
        System.out.println("Now you have " + Task.count  + " tasks in the list.\n");
    }

    private static String prepareWriteContents() {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < storedInputs.size(); i++) {
            s.append(storedInputs.get(i).writeToFile());
            s.append("\n");
        }
        return s.toString();
    }

    private static void replaceFileContents(Path path, String newContents) throws IOException {
        Files.writeString(path, newContents);
    }
}
