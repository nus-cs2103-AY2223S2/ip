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

    private static Ui ui = new Ui();
    private static Storage storage;

    private static Scanner getInput = new Scanner(System.in); // Create a static Scanner object

    private static List<Task> storedInputs; // List to store inputs

    public static void main(String[] args) throws IOException {

        String unixFilePath = "data/duke.txt"; // ~/data/duke

        // Print introduction
        ui.printIntro();

        // Open file
        storage = new Storage(unixFilePath);
        // Load file
        storedInputs = new LinkedList<>(storage.loadFile());

        ui.printList(storedInputs);

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
                ui.printListWithAttitude(storedInputs);
                break;
            case MARK:
                markEvent(userInput);
                storage.overwriteFile(storedInputs);
                break;
            case UNMARK:
                unmarkEvent(userInput);
                storage.overwriteFile(storedInputs);
                break;
            case DELETE:
                deleteEvent(userInput);
                storage.overwriteFile(storedInputs);
                break;
            case TODO:
                todoEvent(userInput);
                storage.overwriteFile(storedInputs);
                break;
            case DEADLINE:
                deadlineEvent(userInput);
                storage.overwriteFile(storedInputs);
                break;
            case EVENT:
                eventEvent(userInput);
                storage.overwriteFile(storedInputs);
                break;
            }
        }

        ui.printOutro();
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

        Task t;
        try {
            t = storedInputs.get(num-1);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundMessage();
            return;
        }
        t.markDone();
        ui.printMarkMessage(t);
    }

    private static void unmarkEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.get(num-1);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundMessage();
            return;
        }
        t.markUnDone();
        ui.printUnMarkMessage(t);
    }

    public static void deleteEvent(String userInput) {
        String[] arr = userInput.split(" ");
        int num = Integer.parseInt(arr[1]);

        Task t;
        try {
            t = storedInputs.remove(num-1);
        } catch (IndexOutOfBoundsException e) {
            ui.printIndexOutOfBoundMessage();
            return;
        }

        ui.printDeleteSuccessfulMessage(t);
        ui.printTotalTask(storedInputs);
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
            ui.printEmptyDetailsMessage("todo");
            return;
        }

        Task newTask = new ToDo(userInput.trim());
        storedInputs.add(newTask);
        ui.printAddTaskSuccessfulMessage(newTask);
        ui.printTotalTask(storedInputs);
    }

    private static void deadlineEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            ui.printEmptyDetailsMessage("deadline");
            return;
        }

        Task newTask;
        try {
            String[] info = userInput.split("/by");
            if (info[0].trim().isEmpty()) {
                ui.printEmptyDetailsMessage("deadline");
                return;
            }
            newTask = new Deadline(info[0].trim(), info[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printDeadlineFormat();
            return;
        }
        catch (DateTimeParseException e) {
            ui.printDateFormat();
            return;
        }

        ui.printAddTaskSuccessfulMessage(newTask);
        ui.printTotalTask(storedInputs);
    }

    private static void eventEvent(String userInput) {
        try {
            userInput = removeKeyword(userInput);
        } catch (DukeException e) {
            ui.printEmptyDetailsMessage("event");
            return;
        }

        Task newTask;
        try {
            String[] infoA = userInput.split("/from");
            String[] infoB = infoA[1].split("/to");
            if (infoA[0].trim().isEmpty()) {
                ui.printEmptyDetailsMessage("event");
                return;
            }
            newTask = new Event(infoA[0].trim(), infoB[0].trim(), infoB[1].trim());
            storedInputs.add(newTask);
        } catch (ArrayIndexOutOfBoundsException e) {
            ui.printEventFormat();
            return;
        } catch (DateTimeParseException e) {
            ui.printDateFormat();
            return;
        }

        ui.printAddTaskSuccessfulMessage(newTask);
        ui.printTotalTask(storedInputs);
    }

}
