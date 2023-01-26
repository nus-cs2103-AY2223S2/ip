package duke;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import duke.storage.Storage;

public class Duke {

    private TaskList taskList;
    private Storage storage;
    private Parser parser;
    private Ui ui;

    // todo: private
    enum State {
        LIST,
        TODO, DEADLINE, EVENT,
        MARK, UNMARK,
        DELETE,
        SAVE, EXIT,
        UNKNOWN,
    }

    public Duke() {
        taskList = new TaskList();
        storage = new Storage(taskList);
        parser = new Parser();
        ui = new Ui();
    }

    private Duke displayTaskCount() {
        if (taskList == null)
            return this;

        if (taskList.isEmpty())
            ui.println("You do not have any task!");
        else
            ui.println("Now you have " + taskList.size() + " task(s) in the list.");

        return this;
    }

    private Duke displayTasks() {
        if (taskList == null)
            return this;

        if (taskList.size() == 0)
            ui.println("Your list is empty.");
        else {
            ui.println("You have the following task(s):");
            for (int i = 0; i < taskList.size(); i++)
                ui.println("\t" + (i + 1) + ". " + taskList.get(i));
        }

        return this;
    }

    private Duke addNewTask(Task task) {
        taskList.add(task);
        ui.println("Got it. I've added this task:");
        ui.println("\t" + task);

        storage.saveDataToFile();
        displayTaskCount();

        return this;
    }

    private static void assertThis(boolean expectsTrue, String failureMessage) throws DukeException {
        if (!expectsTrue)
            throw new DukeException(failureMessage);
    }

    private static State detectState(String command) {
        // Suppress all upper case letters, gets only the first word
        String cmd = command.toLowerCase().split(" ")[0];

        if (cmd.compareTo("list") == 0)
            return State.LIST;
        else if (cmd.compareTo("todo") == 0)
            return State.TODO;
        else if (cmd.compareTo("deadline") == 0)
            return State.DEADLINE;
        else if (cmd.compareTo("event") == 0)
            return State.EVENT;
        else if (cmd.compareTo("mark") == 0)
            return State.MARK;
        else if (cmd.compareTo("unmark") == 0)
            return State.UNMARK;
        else if (cmd.compareTo("delete") == 0)
            return State.DELETE;
        else if (cmd.compareTo("save") == 0)
            return State.SAVE;

        // multiple exit keywords
        switch (cmd) {
            case "bye":
            case "goodbye":
            case "quit":
            case "quit()":
            case "exit":
            case "exit()":
                return State.EXIT;
        }

        // no matches found
        return State.UNKNOWN;
    }



    public static void main(String[] args) {

        Ui.printProgramInfo();

        System.out.println("Initialising system . . .");

        //Initialise components, variables
        int taskIdx, descIdx;
        String[] inputs;
        String userCmd, taskDescription;
        Task activeTask;
        State currentState = State.UNKNOWN;
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        // Retrieve saved data (if any)
        duke.storage.loadDataFromFile();

        System.out.println("System is ready!");
        duke.ui.println("\n\n");
        duke.ui.displayLine();

        // Program Intro
        duke.ui.println("Hello! I'm Duke! :D");
        duke.ui.println("What can I do for you today?");

        // Program Loop
        while(currentState != State.EXIT) {
            System.out.print("\n > ");
            userCmd = sc.nextLine();

            // Command detection
            currentState = Duke.detectState(userCmd);

            try {
                // State handling
                switch (currentState) {
                    case TODO:
                        taskDescription = userCmd.substring(4).trim(); // exclude keyword
                        activeTask = new Todo(taskDescription);
                        duke.addNewTask(activeTask);
                        break;

                    case DEADLINE:
                        // Checks for missing input
                        Duke.assertThis(userCmd.contains(" /by "), "Missing due date.");

                        descIdx = userCmd.indexOf("deadline "); // 9 chars
                        int dueIdx = userCmd.indexOf(" /by "); // 5 chars
                        Duke.assertThis(descIdx+9 < dueIdx, "Task description cannot be empty.");

                        taskDescription = userCmd.substring(descIdx + 9, dueIdx).trim();
                        String duedate = userCmd.substring(dueIdx + 5).trim();
                        Duke.assertThis(!taskDescription.isEmpty(), "Task description cannot be empty.");
                        Duke.assertThis(!duedate.isEmpty(), "Due date cannot be empty.");

                        duke.addNewTask(new Deadline(taskDescription, duke.parser.parseDateTime(duedate)));
                        break;

                    case EVENT:
                        // Checks for missing inputs
                        Duke.assertThis(userCmd.contains(" /from "), "Missing start date/time.");
                        Duke.assertThis(userCmd.contains(" /to "), "Missing end date/time.");

                        descIdx = userCmd.indexOf("event "); // 6 chars
                        int fromIdx = userCmd.indexOf(" /from "); // 7 chars
                        int toIdx = userCmd.indexOf(" /to "); // 5 chars
                        Duke.assertThis(descIdx+6 < fromIdx, "Task description cannot be empty.");
                        Duke.assertThis(fromIdx+7 < toIdx, "Start date/time cannot be empty.");

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

                    case LIST:
                        duke.displayTasks();
                        break;

                    case MARK:
                    case UNMARK:
                        inputs = userCmd.split(" ");
                        Duke.assertThis(inputs.length > 1, "Please indicate which task(s) to apply to.");

                        if (currentState == State.MARK)
                            duke.ui.println("Nice I've marked the task(s) as done:");
                        else
                            duke.ui.println("OK, I've marked the task(s) as not done yet:");

                        for (int i = 1; i < inputs.length; i++) {
                            String input = inputs[i].trim();
                            if (input.isEmpty()) continue; // Blank, do nothing

                            try {
                                taskIdx = Integer.parseInt(inputs[i]) - 1;
                                Duke.assertThis(taskIdx >= 0 && taskIdx < duke.taskList.size(), "");

                                activeTask = duke.taskList.get(taskIdx);
                                activeTask.setDone(currentState == State.MARK); // Note: false means unmark
                                duke.storage.saveDataToFile();
                                duke.ui.println("\t" + activeTask);
                            }
                            catch(NumberFormatException e) {
                                duke.ui.warn("'" + input + "' is not a number.");
                            }
                            catch(DukeException e) {
                                duke.ui.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
                            }
                        }
                        break;

                    case DELETE:
                        inputs = userCmd.split(" ");
                        Duke.assertThis(inputs.length > 1, "Please indicate which task(s) to apply to.");

                        ArrayList<Integer> markedDelete = new ArrayList<>();

                        // Check and note what to delete
                        for (int i = 1; i < inputs.length; i++) {
                            String input = inputs[i].trim();
                            if (input.isEmpty()) continue; // Blank, do nothing

                            try {
                                taskIdx = Integer.parseInt(inputs[i]) - 1;
                                Duke.assertThis(taskIdx >= 0 && taskIdx < duke.taskList.size(), "");

                                activeTask = duke.taskList.get(taskIdx);
                                markedDelete.add(taskIdx);
                            }
                            catch(NumberFormatException e) {
                                duke.ui.warn("'" + input + "' is not a number.");
                            }
                            catch(DukeException e) {
                                duke.ui.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
                            }
                        }

                        duke.ui.println("Noted. I've removed the task(s):");

                        // Actual delete from tasklist (start from the back)
                        Collections.sort(markedDelete);
                        Collections.reverse(markedDelete);
                        for (int i : markedDelete)
                            duke.ui.println("\t" + duke.taskList.remove(i));
                        // instead of remove(int index)

                        duke.storage.saveDataToFile();
                        duke.displayTaskCount();
                        break;

                case SAVE:
                    duke.storage.saveDataToFile();
                    duke.ui.println("Your list have been saved.");
                    break;

                    case UNKNOWN:
                        duke.ui.warn("Sorry, I don't understand your request :(");
                        duke.ui.println("Did you spell something wrongly?");
                        //duke.ui..display("Why not try rephrasing?"); // When chatbot is smarter
                        break;

                    case EXIT:
                        duke.ui.println("Saving your list ... ");
                        duke.storage.saveDataToFile();
                        duke.ui.println("Goodbye!");
                        duke.ui.displayLine();
                        duke.ui.printProgramInfo();
                        break;

                    default:
                        // Unknown input is already handled above.
                        // Something is seriously wrong if this code is called.
                        // Throw RuntimeException and print the stack for debugging.
                        throw new RuntimeException("FATAL ERROR: default case is called.");
                }
            }
            catch (DukeException e) {
                duke.ui.warn(e.getMessage());
            }
            catch (RuntimeException e) {
                duke.ui.warn(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
