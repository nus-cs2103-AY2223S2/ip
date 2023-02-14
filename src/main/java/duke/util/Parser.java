package duke.util;

import java.util.ArrayList;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/**
 * Responsible for interpreting user inputs.
 */
public class Parser {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Class constructor that takes in a Storage, TaskList and Ui
     * @param storage Interacts with the specified data file.
     * @param tasks Contains all current tasks.
     * @param ui Interacts with user.
     */
    public Parser(Storage storage, TaskList tasks, Ui ui) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Takes in an input from the user and executes the command.
     * @param userInput Input from user stored in a String array.
     * @throws DukeException If the user input is invalid.
     */
    public void parse(String[] userInput) throws DukeException {
        String command = userInput[0];
        switch (command) {
        case "bye":
            ui.displayExitMessage();
            storage.saveData(this.tasks.retrieveList());
            ui.exit();
            break;
        case "list":
            ui.displayTasks(this.tasks);
            break;
        case "mark":
            try {
                int taskNumber = Integer.parseInt(userInput[1]);
                tasks.markTask(taskNumber - 1);
                ui.displayMarkedTask(tasks, taskNumber - 1);
            } catch (Exception e) {
                ui.displayMessage(e.getMessage());
            }
            break;
        case "unmark":
            try {
                int taskNumber = Integer.parseInt(userInput[1]);
                tasks.unmarkTask(taskNumber - 1);
                ui.displayMarkedTask(tasks, taskNumber - 1);
            } catch (Exception e) {
                ui.displayMessage(e.getMessage());
            }
            break;
        case "todo":
            try {
                String description = userInput[1];
                Task newTask = new Todo(description);
                tasks.addTask(newTask);
                ui.displayAddedTask(newTask, tasks);
            } catch (Exception e) {
                throw new DukeException("Enter a valid Todo!");
            }
            break;
        case "deadline":
            try {
                String[] deadlineDetails = userInput[1].split(" /by ", 2);
                String deadlineDescription = deadlineDetails[0];
                String deadline = deadlineDetails.length > 0 ? deadlineDetails[1] : null;

                if (deadline == null) {
                    throw new DukeException("Enter a valid deadline!");
                }

                Task newTask = new Deadline(deadlineDescription, deadline);
                tasks.addTask(newTask);
                ui.displayAddedTask(newTask, tasks);
            } catch (Exception e) {
                throw new DukeException("Enter a description with a deadline!");
            }
            break;
        case "event":
            try {
                String[] eventDetails = userInput[1].split(" /from ", 2);
                String eventDescription = eventDetails[0];
                String eventPeriod = eventDetails.length > 0 ? eventDetails[1] : null;

                if (eventPeriod == null) {
                    throw new DukeException("Enter a valid event period!");
                }

                String[] splitEventPeriod = eventPeriod.split(" /to ");

                if (splitEventPeriod.length < 2) {
                    throw new DukeException("Enter a valid event period!");
                }

                Task newTask = new Event(eventDescription, splitEventPeriod);
                tasks.addTask(newTask);
                ui.displayAddedTask(newTask, tasks);
            } catch (Exception e) {
                throw new DukeException("Enter a description and an event period!");
            }
            break;
        case "delete":
            try {
                int taskNumber = Integer.parseInt(userInput[1]);
                Task taskToDelete = tasks.getTask(taskNumber - 1);
                tasks.deleteTask(taskNumber - 1);
                ui.displayDeletedTask(taskToDelete, tasks);
            } catch (Exception e) {
                throw new DukeException("Enter a valid task number!");
            }
            break;
        case "find":
            String keyword = userInput[1];
            ArrayList<Task> matchedTasks = tasks.findMatchingTasks(keyword);
            ui.displayMatchedTasks(matchedTasks);
            break;
        default:
            throw new DukeException("Enter a valid task!");
        }
    }
}
