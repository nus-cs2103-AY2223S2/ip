package duke.tasks;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import duke.storage.Storage;

/**
 * A class to represent a list of tasks.
 */
public class TaskList implements Serializable {
    private static final long serialVersionUID = 8098680977751428278L;

    private List<Task> taskList = new ArrayList<>();

    public TaskList() {}

    private TaskList(List<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Gets the number of tasks in the task list.
     *
     * @return Number of tasks in the task list
     */
    public int getNumTasks() {
        return taskList.size();
    }

    @Override
    public String toString() {
        String taskListString = "";
        for (int i = 0; i < taskList.size(); i++) {
            String printedString = String.format("%d. %s \n", i + 1, taskList.get(i).toString());
            // taskListString.concat(printedString);
            taskListString += printedString;
        }
        return taskListString;
    }

    public String[] getPrintableTaskList() {

        List<String> printableStringList = new ArrayList<>();
        for (int i = 0; i < taskList.size(); i++) {
            String printedString = String.format("%d. %s ", i + 1, taskList.get(i).toString());
            printableStringList.add(printedString);
        }
        return printableStringList.toArray(new String[0]);

    }

    /**
     * Adds a new task into the list of tasks.
     *
     * @param task a {@code Task} instance.
     */
    public void addTask(Task task) {
        taskList.add(task);
        Storage.writeTaskList(this);
    }

    /**
     * Deletes an existing task from the list of tasks.
     *
     * @param taskIndex the index of the {@code Task} instance obtained through list command.
     * @return The string representation of the deleted task.
     */
    public String deleteTask(int taskIndex) {
        try {
            Task targetTask = taskList.get(taskIndex - 1);
            taskList.remove(taskIndex - 1);
            Storage.writeTaskList(this);
            return targetTask.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("The index is out of bound!");
        }
    }

    /**
     * Marks an existing task as done from the list of tasks.
     *
     * @param taskIndex the index of the {@code Task} instance obtained through list command.
     * @return The string representation of the marked task.
     */
    public String markTask(int taskIndex) {
        try {

            Task targetTask = taskList.get(taskIndex - 1);
            targetTask.mark();
            taskList.set(taskIndex - 1, targetTask);
            Storage.writeTaskList(this);
            return targetTask.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("The index is out of bound!");
        }
    }

    /**
     * Marks an existing task as not done from the list of tasks.
     *
     * @param taskIndex the index of the {@code Task} instance obtained through list command.
     * @return The string representation of the unmarked task.
     */
    public String unmarkTask(int taskIndex) {
        try {
            Task targetTask = taskList.get(taskIndex - 1);
            targetTask.unmark();
            taskList.set(taskIndex - 1, targetTask);
            Storage.writeTaskList(this);
            return targetTask.toString();
        } catch (IndexOutOfBoundsException e) {
            throw new IndexOutOfBoundsException("The index is out of bound!");
        }
    }

    /**
     * Filters tasks that contain a given keyword.
     *
     * @param keyword
     * @return A filtered {@code TaskList} that contains task that contain {@code keyword}.
     */
    public TaskList filterTaskByKeyword(String keyword) {
        List<Task> filteredTaskList = taskList.stream().filter((task) -> task.contains(keyword))
                .collect(Collectors.toList());
        return new TaskList(filteredTaskList);
    }

    /**
     * Filters incomplete tasks that land on a given date.
     *
     * @param dateString
     * @return A filtered {@code TaskList} that contains task that are not done and land on
     *         {@code dateString}.
     */
    public TaskList filterTaskByDate(String dateString) {

        List<Task> filteredTaskList = new ArrayList<>();

        // TODO : Refactor this
        for (Task task : taskList) {
            if (task instanceof Event) {
                Event event = (Event) task;
                boolean isEventWithinDate = event.checkIfEventActiveOnDate(dateString);
                if (isEventWithinDate) {
                    filteredTaskList.add(event);
                }
            }
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                boolean isDeadlineActive = deadline.checkIfDeadlineActive(dateString);
                if (isDeadlineActive) {
                    filteredTaskList.add(deadline);
                }
            }
        }

        return new TaskList(filteredTaskList);
    }


}
