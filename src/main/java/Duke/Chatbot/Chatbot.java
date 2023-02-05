package Duke.Chatbot;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.UnimplementedTaskTypeException;
import Duke.Tasks.Task;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * Main Representation of Duke
 * Handles connections between ui, storage, parser and TaskList
 */
public class Chatbot {


    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    public boolean isActive = true;
    private TaskList tasks;

    /**
     * Specifies the location of the save file to load from
     *
     * @param fileDirectory directory of the save file
     * @param fileName      name of the save file
     */
    public Chatbot(String fileDirectory, String fileName) {
        this.ui = new Ui();
        this.storage = new Storage(fileDirectory, fileName);
        this.parser = new Parser(this, this.ui);
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }

    }

    /**
     * Starts sensing the input as commands
     */
    public void run() {
        ui.showStartupMessage();
        readInput();
    }

    private void readInput() {
        Scanner input = new Scanner(System.in);
        while (isActive) {

            parser.parse(input.nextLine());

        }
    }

    /**
     * Triggers UI to print the tasks
     */
    public void printTasks() {
        if (tasks.numTasks() == 0) {
            ui.showNoTasksMessage();
            return;
        }
        ui.showTasksMessage(tasks.toString());
    }

    /**
     * Adds a task from Save file Representation form
     *
     * @param type      Tasktype of new task
     * @param arguments arguments like task name and datetime in string
     */

    public void addTask(TaskList.Tasktype type, String... arguments) {
        try {
            Task toAdd = tasks.convertToTask(type, arguments);
            tasks.addTask(toAdd);
            ui.showTaskAddedMessage(toAdd.toString(), tasks.numTasks());
            onEditTask();
        } catch (DateTimeParseException e) {
            ui.showDateTimeParseError();
        } catch (UnimplementedTaskTypeException e) {
            ui.showUnimplementedTaskTypeError();
        }
    }

    /**
     * Called when taskList is updated to save the TaskList to save file
     */
    public void onEditTask() {
        storage.saveTaskList(tasks);
    }

    /**
     * Called to interface with TaskList to remove a task by index
     *
     * @param toRemove index of task to remove
     */
    public void removeTask(int toRemove) {
        if (!isIndexInRange(toRemove)) {
            return;
        }
        Task removedTask = tasks.removeTask(toRemove);
        ui.showRemovedTaskMessage(removedTask.toString(), tasks.numTasks());
        onEditTask();

    }

    private boolean isIndexInRange(int index) {
        if (!tasks.isValidIndex(index)) {
            ui.showIndexError();
            return false;
        }
        return true;
    }

    /**
     * Called to interface with TaskList to remove all current tasks
     */
    public void removeAllTasks() {
        tasks.removeAllTasks();
        ui.showRemoveAllTasksMessage();
        onEditTask();
    }

    /**
     * Called to mark an index as incomplete
     *
     * @param index the index to mark as incomplete
     */
    public void markAsIncomplete(int index) {
        if (!isIndexInRange(index)) {
            return;
        }
        if (!tasks.getTaskState(index)) {
            ui.showTaskStateIncompletedError();
            return;
        }

        tasks.toggleState(index);
        ui.showTaskIncompletedMessage(tasks.getTaskInfo(index));
    }

    /**
     * Called to make an index of task as complete
     *
     * @param index index to mark as complete
     */
    public void markAsComplete(int index) {
        if (!isIndexInRange(index)) {
            return;
        }
        if (tasks.getTaskState(index)) {
            ui.showTaskStateCompletedError();
            return;
        }

        tasks.toggleState(index);
        ui.showTaskCompletedMessage(tasks.getTaskInfo(index));
    }


    public void find(String args) {
        ui.showFindMessage(args);
        TaskList foundTasks = tasks.findKeyword(args);
        ui.showTasksMessage(foundTasks.toString());
    }
}
