package duke.utilities;

import java.time.LocalDate;
import java.util.ArrayList;
import duke.exceptions.DukeEmptyUndoHistoryException;
import duke.tasks.DeadlineTask;
import duke.tasks.Task;

/**
 * The list of user tasks.
 */
public class TaskList {

    private final UndoHistory undoHistory;
    private ArrayList<Task> tasks;

    /**
     * Instantiates a new empty {@code TaskList} object.
     */
    public TaskList() {
        tasks = new ArrayList<>();
        undoHistory = new UndoHistory();
    }

    /**
     * Instantiates a new {@code TaskList} object from the {@code taskList} provided.
     */
    public TaskList(ArrayList<Task> taskList) {
        tasks = taskList;
        undoHistory = new UndoHistory();
    }

    /**
     * Adds the specified task to the end of the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        undoHistory.addNewState(tasks);
        tasks.add(task);
    }

    /**
     * Deletes the task at the specified position from the task list.
     *
     * @param index The index of the task to be deleted.
     * @return The task that was deleted.
     */
    public Task deleteTask(int index) {
        undoHistory.addNewState(tasks);
        return tasks.remove(index);
    }

    /**
     * Returns the task at the specified position in the task list.
     *
     * @param index The index of the task.
     * @return The task at the position {@code index}.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Marks the task at the specified position as done.
     *
     * @param index The index of the task to be marked as done.
     * @return The task that was marked as done.
     */
    public Task markTaskAsDone(int index) {
        undoHistory.addNewState(tasks);

        Task task = tasks.get(index);
        task.markDone();
        return task;
    }

    /**
     * Unmarks the task at the specified position as done.
     *
     * @param index The index of the task to be unmarked as done.
     * @return The task that was unmarked as done.
     */
    public Task unmarkTaskAsDone(int index) {
        undoHistory.addNewState(tasks);

        Task task = tasks.get(index);
        task.unmarkDone();
        return task;
    }

    /**
     * Returns all tasks that are due on the specified date.
     *
     * @param dueOnDate The date on which the tasks are due on.
     * @return A list of tasks that are due on {@code dueOnDate}
     */
    public ArrayList<Task> getAllTasksThatAreDueOn(LocalDate dueOnDate) {
        ArrayList<Task> arrayList = new ArrayList<>();

        for (Task task : tasks) {
            if (task instanceof DeadlineTask) {
                DeadlineTask deadlineTask = (DeadlineTask) task;

                if (deadlineTask.isDueOn(dueOnDate)) {
                    arrayList.add(deadlineTask);
                }
            }
        }

        return arrayList;
    }

    public ArrayList<String> findTasks(String name) {
        ArrayList<String> arrayList = new ArrayList<>();

        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);

            if (task.matchName(name)) {
                String indexString = Integer.toString(i + 1);
                arrayList.add(indexString + ". " + task);
            }
        }

        return arrayList;
    }

    public void restorePreviousState() throws DukeEmptyUndoHistoryException {
        tasks = undoHistory.popLastState();
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Checks whether the task list is empty.
     *
     * @return A boolean indicating if the task list is empty.
     */
    public boolean isEmpty() {
        return tasks.size() == 0;
    }

    /**
     * Converts the entire task list to a string. This string is to be stored on the disk.
     *
     * @return A string representing the entire task list.
     */
    public String toDukeFileString() {
        ArrayList<String> output = new ArrayList<>();

        for (Task task : tasks) {
            output.add(task.toDukeFileString() + "\n");
        }

        return String.join("", output);
    }
}
