package duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import duke.storage.Storage;

/**
 * Is the main class of the program.
 */
public class Duke {

    private final TaskList taskList;
    private final Storage storage;
    private final Parser parser;
    private final Ui ui;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage(taskList);
        parser = new Parser();
        ui = new Ui();
    }

    private static void assertThis(boolean expectsTrue, String failureMessage) throws DukeException {
        if (!expectsTrue) {
            throw new DukeException(failureMessage);
        }
    }

    private void displayTaskCount() {
        if (taskList.isEmpty()) {
            ui.println("You do not have any task!");
        } else {
            ui.println("Now you have " + taskList.size() + " task(s) in the list.");
        }
    }

    private void displayTasks() {
        if (taskList.size() == 0) {
            ui.println("Your list is empty.");
        } else {
            ui.println("You have the following task(s):");
            for (int i = 0; i < taskList.size(); i++) {
                ui.println("\t" + (i + 1) + ". " + taskList.get(i));
            }
        }
    }

    private void addNewTask(Task task) {
        taskList.add(task);
        ui.println("Got it. I've added this task:");
        ui.println("\t" + task);

        storage.saveDataToFile();
        displayTaskCount();
    }

    /**
     * Is the entry point of the application.
     * @param args Arguments provided through the console when running the program.
     */
    public static void main(String[] args) {

        Ui.printProgramInfo();
        System.out.println("Initialising system . . .");

        //Initialise components
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        // Retrieve saved data (if any)
        duke.storage.loadDataFromFile();

        System.out.println("System is ready!");
        duke.ui.println("\n\n");
        duke.ui.printBufferLine();

        // Program Intro
        duke.ui.println("Hello! I'm Duke! :D");
        duke.ui.println("What can I do for you today?");

        //Initialise variables used inside the program loop
        boolean isContinue = true;
        int taskIdx;
        int descIdx;
        Task activeTask;
        String cmd;
        String userCmd;
        String taskDescription;
        String[] inputs;

        // Program Loop
        while (isContinue) {
            System.out.print("\n > ");
            userCmd = sc.nextLine();

            // Suppress all upper case letters, gets only the first word
            cmd = userCmd.toLowerCase().split(" ")[0];

            try {
                // State handling
                switch (cmd) {
                case "todo":
                    taskDescription = userCmd.substring(4).trim(); // exclude keyword
                    activeTask = new Todo(taskDescription);
                    duke.addNewTask(activeTask);
                    break;

                case "deadline":
                    // Checks for missing due date
                    Duke.assertThis(userCmd.contains(" /by "), "Missing due date.");

                    descIdx = userCmd.indexOf("deadline "); // 9 chars
                    int dueIdx = userCmd.indexOf(" /by "); // 5 chars
                    Duke.assertThis((descIdx + 9) < dueIdx, "Task description cannot be empty.");

                    taskDescription = userCmd.substring(descIdx + 9, dueIdx).trim();
                    String duedate = userCmd.substring(dueIdx + 5).trim();
                    Duke.assertThis(!taskDescription.isEmpty(), "Task description cannot be empty.");
                    Duke.assertThis(!duedate.isEmpty(), "Due date cannot be empty.");

                    duke.addNewTask(new Deadline(taskDescription, duke.parser.parseDateTime(duedate)));
                    break;

                case "event":
                    // Checks for missing inputs
                    Duke.assertThis(userCmd.contains(" /from "), "Missing start date/time.");
                    Duke.assertThis(userCmd.contains(" /to "), "Missing end date/time.");

                    descIdx = userCmd.indexOf("event "); // 6 chars
                    int fromIdx = userCmd.indexOf(" /from "); // 7 chars
                    int toIdx = userCmd.indexOf(" /to "); // 5 chars
                    Duke.assertThis((descIdx + 6) < fromIdx, "Task description cannot be empty.");
                    Duke.assertThis((fromIdx + 7) < toIdx, "Start date/time cannot be empty.");

                    taskDescription = userCmd.substring(descIdx + 6, fromIdx).trim();
                    String start = userCmd.substring(fromIdx + 7, toIdx).trim();
                    String end = userCmd.substring(toIdx + 5).trim();
                    Duke.assertThis(!taskDescription.isEmpty(), "Task description cannot be empty.");
                    Duke.assertThis(!start.isEmpty(), "Start date/time cannot be empty.");
                    Duke.assertThis(!end.isEmpty(), "End date/time cannot be empty.");

                    duke.addNewTask(new Event(
                            taskDescription,
                            duke.parser.parseDateTime(start),
                            duke.parser.parseDateTime(end))
                    );
                    break;

                case "ls":
                case "list":
                    duke.displayTasks();
                    break;

                case "mark":
                case "unmark":
                    boolean isMark = cmd.compareTo("mark") == 0;

                    inputs = userCmd.split(" ");
                    Duke.assertThis(inputs.length > 1, "Please indicate which task(s) to apply to.");

                    if (isMark) {
                        duke.ui.println("Nice I've marked the task(s) as done:");
                    } else {
                        duke.ui.println("OK, I've marked the task(s) as not done yet:");
                    }

                    for (int i = 1; i < inputs.length; i++) {
                        String input = inputs[i].trim();
                        if (input.isEmpty()) {
                            continue; // Blank, do nothing
                        }

                        try {
                            taskIdx = Integer.parseInt(inputs[i]) - 1;
                            Duke.assertThis(taskIdx >= 0 && taskIdx < duke.taskList.size(), "");

                            activeTask = duke.taskList.get(taskIdx);
                            activeTask.setDone(isMark); // Note: false means unmark
                            duke.storage.saveDataToFile();
                            duke.ui.println("\t" + activeTask);
                        } catch (NumberFormatException e) {
                            duke.ui.warn("'" + input + "' is not a number.");
                        } catch (DukeException e) {
                            duke.ui.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
                        }
                    } // for loop
                    break;

                case "delete":
                    inputs = userCmd.split(" ");
                    Duke.assertThis(inputs.length > 1, "Please indicate which task(s) to apply to.");

                    ArrayList<Integer> markedDelete = new ArrayList<>();

                    // Check and note what to delete
                    for (int i = 1; i < inputs.length; i++) {
                        String input = inputs[i].trim();
                        if (input.isEmpty()) {
                            continue; // Blank, do nothing
                        }

                        try {
                            // Parse input to int
                            taskIdx = Integer.parseInt(inputs[i]) - 1;
                            Duke.assertThis(taskIdx >= 0 && taskIdx < duke.taskList.size(), "");

                            markedDelete.add(taskIdx);
                        } catch (NumberFormatException e) {
                            duke.ui.warn("'" + input + "' is not a number.");
                        } catch (DukeException e) {
                            duke.ui.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
                        }
                    }

                    duke.ui.println("Noted. I've removed the task(s):");

                    // Actual delete from tasklist (start from the back)
                    Collections.sort(markedDelete);
                    Collections.reverse(markedDelete);
                    for (int i : markedDelete) {
                        duke.ui.println("\t" + duke.taskList.remove(i));
                    }

                    duke.storage.saveDataToFile();
                    duke.displayTaskCount();
                    break;

                case "find":
                    String searchWords = userCmd.substring(4).trim(); // exclude keyword
                    ArrayList<Task> matches = duke.taskList.search(searchWords);
                    if (matches.isEmpty()) {
                        duke.ui.println("Sorry, no match found.");
                        break;
                    }
                    duke.ui.println("Here are the matching tasks in your list:");
                    for (int i = 0; i < matches.size(); i++) {
                        duke.ui.println("\t" + (i + 1) + ". " + matches.get(i));
                    }
                    // FIXME: since index is seperate from the actual tasklist, user might un/mark/delete wrong idx
                    break;

                case "save":
                    duke.storage.saveDataToFile();
                    duke.ui.println("Your list have been saved.");
                    break;

                case "bye":
                case "goodbye":
                case ":q":
                case "quit":
                case "quit()":
                case "exit":
                case "exit()":
                    duke.ui.println("Saving your list ... ");
                    duke.storage.saveDataToFile();
                    duke.ui.println("Goodbye!");
                    duke.ui.printBufferLine();
                    Ui.printProgramInfo();
                    isContinue = false;
                    break;

                default:
                    duke.ui.warn("Sorry, I don't understand your request :(");
                    duke.ui.println("Did you spell something wrongly?");
                } // switch case
            } catch (DukeException e) {
                duke.ui.warn(e.getMessage());
            } catch (RuntimeException e) {
                duke.ui.warn(e.getMessage());
                e.printStackTrace();
            }
        } // Program while loop
    }
}
