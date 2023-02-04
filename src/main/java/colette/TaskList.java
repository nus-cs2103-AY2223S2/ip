package colette;

import java.util.ArrayList;
import java.util.Scanner;

import colette.command.CommandType;
import colette.exception.ColetteException;
import colette.exception.IndexErrorType;
import colette.gui.GuiText;
import colette.task.Task;

/** Contains task list */
public class TaskList {

    /** ArrayList with initial capacity of 100 to store tasks */
    private ArrayList<Task> tasks = new ArrayList<>(100);

    /**
     * Empty constructor.
     */
    public TaskList() {

    }

    /**
     * Constructs a TaskList from a String
     * representing all tasks to add to the list.
     *
     * @param taskList String representing all tasks
     *                 to add to the task list.
     */
    public TaskList(String taskList) {
        this.processTaskListString(taskList);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task Task to add.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Processes the task at the given index.
     *
     * @param commandType What to do with the task at the given index.
     * @param index Index the task to process is at.
     * @return Processed task.
     * @throws ColetteException if index is not valid.
     */
    public Task processTaskAtIndex(CommandType commandType, int index) throws ColetteException {
        if (index >= this.getSize()) {
            throw new ColetteException(GuiText.generateIndexErrorMessage(IndexErrorType.OUT_OF_BOUNDS));
        } else if (index < 0) {
            throw new ColetteException(GuiText.generateIndexErrorMessage(IndexErrorType.NEGATIVE));
        }

        Task task = this.getTaskAtIndex(index);
        switch (commandType) {
        case MARK:
            task.setDone(true);
            break;
        case UNMARK:
            task.setDone(false);
            break;
        case DELETE:
            this.tasks.remove(index);
            break;
        default:
            throw new ColetteException(GuiText.generateGenericErrorMessage());
        }
        return task;
    }

    /**
     * Processes a String representing a group of tasks
     * and adds these tasks to the list.
     *
     * @param taskList String representing a group of tasks.
     */
    private void processTaskListString(String taskList) {
        Scanner sc = new Scanner(taskList);
        while (sc.hasNextLine()) {
            String taskStringRepresentation = sc.nextLine();
            this.addTask(Task.createTaskFromStringRepresentation(taskStringRepresentation));
        }
        sc.close();
    }

    /**
     * Converts the task list to a String
     * for storage.
     *
     * @return String representing tasks in task list.
     */
    public String createTaskListString() {
        StringBuffer representation = new StringBuffer();
        for (Task t : tasks) {
            representation.append(t.getFileRepresentation() + "\n");
        }
        return representation.toString();
    }

    public int getSize() {
        return this.tasks.size();
    }

    public boolean isEmpty() {
        return this.tasks.isEmpty();
    }

    @Override
    public String toString() {
        StringBuffer res = new StringBuffer();
        for (int i = 0; i < this.getSize() - 1; i++) {
            res.append((i + 1) + ". " + this.getTaskAtIndex(i) + "\n");
        }
        res.append(this.getSize() + ". " + this.getTaskAtIndex(this.getSize() - 1));
        return res.toString();
    }

    private Task getTaskAtIndex(int index) {
        return this.tasks.get(index);
    }

    public boolean containsTask(Task task) {
        return this.tasks.contains(task);
    }

    /**
     * Finds all tasks in the list that contains the given
     * keyword and returns a new TaskList containing only
     * these tasks.
     *
     * @param keywords The given keywords that tasks must contain.
     * @return New TaskList containing only matching tasks.
     */
    public TaskList findTasksByKeyword(String ... keywords) {
        TaskList filteredTaskList = new TaskList();
        for (Task t : this.tasks) {
            for (String keyword : keywords) {
                if (keyword.isBlank()) {
                    continue;
                }
                if (t.nameContainsKeyword(keyword) && !filteredTaskList.containsTask(t)) {
                    filteredTaskList.addTask(t);
                }
            }
        }
        return filteredTaskList;
    }

}
