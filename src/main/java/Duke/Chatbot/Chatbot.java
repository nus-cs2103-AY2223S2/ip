package Duke.Chatbot;

import Duke.Exceptions.DukeException;
import Duke.Exceptions.UnimplementedTaskTypeException;
import Duke.Tasks.Task;

import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Chatbot {


    private final Ui ui;
    private final Storage storage;
    private final Parser parser;
    public boolean isActive = true;
    private TaskList tasks;

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

    public void printTasks() {
        if (tasks.numTasks() == 0) {
            ui.showNoTasksMessage();
            return;
        }
        ui.showTasksMessage(tasks.toString());
    }

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

    public void onEditTask() {
        storage.saveTaskList(tasks);
    }


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

    public void removeAllTasks() {
        tasks.removeAllTasks();
        ui.showRemoveAllTasksMessage();
        onEditTask();
    }


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


}
