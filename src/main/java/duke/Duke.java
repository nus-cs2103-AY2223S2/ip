package duke;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import duke.Main.Flag;
import duke.gui.MainWindow;
import duke.storage.Storage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * Is the main class of the program.
 */
public class Duke extends Application {

    private static ArrayList<Flag> flags = new ArrayList<>();

    public final Ui ui;

    final TaskList taskList;
    final Storage storage;
    final Parser parser;

    /**
     * Constructs an instance of Duke.
     */
    public Duke() {
        taskList = new TaskList();
        storage = new Storage(taskList);
        parser = new Parser();
        ui = new Ui();

        // Retrieve saved data (if any)
        if (!Duke.flags.contains(Flag.NO_LOAD_SAVES)) {
            storage.loadDataFromFile();
        }
    }

    public static void setFlags(ArrayList<Flag> flags) {
        Duke.flags = flags;
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
     * Do certain tasks based in the input (command) provided by the user.
     * @param userCmd String containing the command provided by the user.
     * @return True if user has requested to exit program.
     */
    public boolean executeCommand(String userCmd) {
        int taskIdx;
        int descIdx;
        Task activeTask;
        String taskDescription;
        String[] inputs;

        // Suppress all upper case letters, gets only the first word
        String cmd = userCmd.toLowerCase().split(" ")[0];

        try {
            // State handling
            switch (cmd) {
            case "todo":
                taskDescription = userCmd.substring(4).trim(); // exclude keyword
                activeTask = new Todo(taskDescription);
                addNewTask(activeTask);
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

                addNewTask(new Deadline(taskDescription, parser.parseDateTime(duedate)));
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

                addNewTask(new Event(
                        taskDescription,
                        parser.parseDateTime(start),
                        parser.parseDateTime(end))
                );
                break;

            case "ls":
            case "list":
                displayTasks();
                break;

            case "mark":
            case "unmark":
                boolean isMark = cmd.compareTo("mark") == 0;

                inputs = userCmd.split(" ");
                Duke.assertThis(inputs.length > 1, "Please indicate which task(s) to apply to.");

                if (isMark) {
                    ui.println("Nice I've marked the task(s) as done:");
                } else {
                    ui.println("OK, I've marked the task(s) as not done yet:");
                }

                for (int i = 1; i < inputs.length; i++) {
                    String input = inputs[i].trim();
                    if (input.isEmpty()) {
                        continue; // Blank, do nothing
                    }

                    try {
                        taskIdx = Integer.parseInt(inputs[i]) - 1;
                        Duke.assertThis(taskIdx >= 0 && taskIdx < taskList.size(), "");

                        activeTask = taskList.get(taskIdx);
                        activeTask.setDone(isMark); // Note: false means unmark
                        storage.saveDataToFile();
                        ui.println("\t" + activeTask);
                    } catch (NumberFormatException e) {
                        ui.warn("'" + input + "' is not a number.");
                    } catch (DukeException e) {
                        ui.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
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
                        Duke.assertThis(taskIdx >= 0 && taskIdx < taskList.size(), "");

                        markedDelete.add(taskIdx);
                    } catch (NumberFormatException e) {
                        ui.warn("'" + input + "' is not a number.");
                    } catch (DukeException e) {
                        ui.warn("Task " + Integer.parseInt(inputs[i]) + " does not exist.");
                    }
                }

                ui.println("Noted. I've removed the task(s):");

                // Actual delete from tasklist (start from the back)
                Collections.sort(markedDelete);
                Collections.reverse(markedDelete);
                for (int i : markedDelete) {
                    ui.println("\t" + taskList.remove(i));
                }

                storage.saveDataToFile();
                displayTaskCount();
                break;

            case "find":
                String searchWords = userCmd.substring(4).trim(); // exclude keyword
                ArrayList<Task> matches = taskList.search(searchWords);
                if (matches.isEmpty()) {
                    ui.println("Sorry, no match found.");
                    break;
                }
                ui.println("Here are the matching tasks in your list:");
                for (int i = 0; i < matches.size(); i++) {
                    ui.println("\t" + (i + 1) + ". " + matches.get(i));
                }
                // TODO: since index is separate from the actual tasklist, user might un/mark/delete wrong idx
                break;

            case "save":
                storage.saveDataToFile();
                ui.println("Your list have been saved.");
                break;

            case "bye":
            case "goodbye":
            case ":q":
            case "quit":
            case "quit()":
            case "exit":
            case "exit()":
                ui.println("Saving your list ... ");
                storage.saveDataToFile();
                ui.println("Goodbye!");
                ui.printBufferLine();
                Ui.printProgramInfo();

                Platform.exit();
                return false;

            default:
                ui.warn("Sorry, I don't understand your request :(");
                ui.println("Did you spell something wrongly?");
            } // switch case
        } catch (DukeException e) {
            ui.warn(e.getMessage());
        } catch (Exception e) {
            ui.warn(e.getMessage());
            e.printStackTrace();
        }

        return true;
    }

    void startAsCli() {
        Scanner sc = new Scanner(System.in);

        // Program Intro
        ui.println("Hello! I'm Duke! :D");
        ui.println("What can I do for you today?");

        //Initialise variables used inside the program loop
        boolean isContinue = true;
        String userInput;

        // Program Loop
        while (isContinue) {
            System.out.print("\n > ");
            userInput = sc.nextLine();

            isContinue = this.executeCommand(userInput);
        }

    }

    /**
     * @inheritDoc
     */
    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Duke.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setTitle("Duke");
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setDuke(this);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @inheritDoc
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        storage.saveDataToFile();
    }
}
