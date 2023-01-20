import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Duke {

    static final String AUTHOR = "lhy-hoyin";
    static final String LOGO
            = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|";


    private ArrayList<Task> taskList;

    enum State {
        LIST,
        TODO, DEADLINE, EVENT,
        MARK, UNMARK,
        DELETE,
        EXIT,
        UNKNOWN,
    }

    public Duke() {
        taskList = new ArrayList<>();
    }

    private Duke displayTaskCount() {
        if (taskList == null)
            return this;

        if (taskList.isEmpty())
            Duke.display("You do not have any task!");
        else
            Duke.display("Now you have " + taskList.size() + " task(s) in the list.");

        return this;
    }

    private Duke displayTasks() {
        if (taskList == null)
            return this;

        if (taskList.size() == 0)
            Duke.display("Your list is empty.");
        else {
            Duke.display("You have the following task(s):");
            for (int i = 0; i < taskList.size(); i++)
                Duke.display("\t" + (i + 1) + ". " + taskList.get(i));
        }

        return this;
    }

    private Duke addNewTask(Task task) {
        taskList.add(task);
        Duke.display("Got it. I've added this task:");
        Duke.display("\t" + task);

        saveDataToFile();
        displayTaskCount();

        return this;
    }

    private Duke saveDataToFile() {
        final String FILE_PATH = "./data/duke/tasks.csv";

        StringBuilder sb = new StringBuilder();
        for (Task t : taskList) {
            sb.append(t.toCsv()).append("\n");
        }

        // Write prepared data to file
        try {
            Path f = Paths.get(FILE_PATH);
            Files.createDirectories(f.getParent()); // Create directory (if not exist)
            if (!Files.exists(f)) {
                Files.createFile(f); // Create non-existing file
            }
            Files.writeString(f, sb.toString()); // Write to file
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return this;
    }

    private static void display(Object obj) {
        System.out.println(obj);
    }
    private static void display(String message) {
        System.out.println(message);
    }
    private static void warn(String message) {
        System.out.println("OOPS! " + message);
    }

    private static void assertThis(boolean expectsTrue, String failureMessage) throws DukeException {
        if (!expectsTrue)
            throw new DukeException(failureMessage);
    }
    private static void displayLogo() {
        Duke.display(LOGO);
    }
    private static void displayLine() {
        Duke.display("____________________________________________________________");
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

        Duke.displayLogo();
        Duke.display("Developed by: " + AUTHOR);
        System.out.println("Initialising system . . .");

        //Initialise components, variables
        int taskIdx, descIdx;
        String[] inputs;
        String userCmd, taskDescription;
        Task activeTask;
        State currentState = State.UNKNOWN;
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        // TODO: Retrieve saved data (if any)


        System.out.println("System is ready!");
        Duke.display("\n\n");
        Duke.displayLine();

        // Program Intro
        Duke.display("Hello! I'm Duke! :D");
        Duke.display("What can I do for you today?");

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
                        taskDescription = userCmd.substring(4).trim(); // exclude "todo" keyword
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

                        duke.addNewTask(new Deadline(taskDescription, duedate));
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

                        duke.addNewTask(new Event(taskDescription, start, end));
                        break;

                    case LIST:
                        duke.displayTasks();
                        break;

                    case MARK:
                    case UNMARK:
                        inputs = userCmd.split(" ");
                        Duke.assertThis(inputs.length > 1, "Please indicate which task(s) to apply to.");

                        if (currentState == State.MARK)
                            Duke.display("Nice I've marked the task(s) as done:");
                        else Duke.display("OK, I've marked the task(s) as not done yet:");

                        for (int i = 1; i < inputs.length; i++) {
                            String input = inputs[i].trim();
                            if (input.isEmpty()) continue; // Blank, do nothing

                            try {
                                taskIdx = Integer.parseInt(inputs[i]) - 1;
                                Duke.assertThis(taskIdx >= 0 && taskIdx < duke.taskList.size(), "");

                                activeTask = duke.taskList.get(taskIdx);
                                activeTask.setDone(currentState == State.MARK); // Note: false means unmark
                                Duke.display("\t" + activeTask);
                            }
                            catch(NumberFormatException e) {
                                Duke.warn("'" + input + "' is not a number.");
                            }
                            catch(DukeException e) {
                                Duke.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
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
                                Duke.warn("'" + input + "' is not a number.");
                            }
                            catch(DukeException e) {
                                Duke.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
                            }
                        }

                        Duke.display("Noted. I've removed the task(s):");

                        // Actual delete from tasklist (start from the back)
                        Collections.sort(markedDelete);
                        Collections.reverse(markedDelete);
                        for (int i : markedDelete) // Note: need to be int and not Integer
                            Duke.display("\t" + duke.taskList.remove(i)); // or else remove(Object o) is used (wrong)
                        // instead of remove(int index)

                        duke.displayTaskCount();
                        break;

                    case UNKNOWN:
                        Duke.warn("Sorry, I don't understand your request :(");
                        Duke.display("Did you spell something wrongly?");
                        //Duke.display("Why not try rephrasing?"); // When chatbot is smarter
                        break;

                    case EXIT:
                        Duke.display("Saving your list ... ");
                        duke.saveDataToFile();
                        Duke.display("Goodbye!");
                        Duke.displayLine();
                        Duke.displayLogo();
                        break;

                    default:
                        // Unknown input is already handled above.
                        // Something is seriously wrong if this code is called.
                        // Throw RuntimeException and print the stack for debugging.
                        throw new RuntimeException("FATAL ERROR: default case is called.");
                }
            }
            catch (DukeException e) {
                Duke.warn(e.getMessage());
            }
            catch (RuntimeException e) {
                Duke.warn(e.getMessage());
                e.printStackTrace();
            }
        }
    }
}
