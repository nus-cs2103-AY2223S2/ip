package task;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;

import util.DukeException;
import util.DukeUI;

/**
 * Manages all Task related operations such as updating
 * the completion status of a task, inserting and deleting
 * the task from the list, and displaying the list.
 */
public class TaskManager {
    private final ArrayList<Task> taskArr;

    /**
     * Initialises the ArrayList.
     */
    public TaskManager() {
        this.taskArr = new ArrayList<>();
    }

    /**
     * Marks the task as completed.
     * @param index
     */
    public void markTask(int index) {
        Task task = taskArr.get(index);
        task.markAsDone();
    }

    /**
     * Marks the task as not completed.
     * @param index
     */
    public void unmarkTask(int index) {
        Task task = taskArr.get(index);
        task.markAsUndone();
    }

    /**
     * Removes the task from the list.
     * @param index
     */
    public void deleteTask(int index) {
        taskArr.remove(index);
    }

    /**
     * Adds a task to the list.
     * @param task
     */
    public void addTaskToList(Task task) {
        taskArr.add(task);
    }

    /**
     * Prints task from list at a given index.
     * @param index
     * @return String representation of a task at a given index
     */
    public String printTask(int index) {
        String str = taskArr.get(index).toString();
        return str;
    }

    /**
     * Iterates through task list to display its elements.
     * @return String of tasks in the list
     */
    public String displayList() throws DukeException {
        if (taskArr.isEmpty()) {
            throw new DukeException("Your list is empty, please add a task!");
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < taskArr.size(); i++) {
            Task task = taskArr.get(i);
            sb.append(i + 1 + ". " + task);
            sb.append(System.lineSeparator());
        }
        return sb.toString();
    }

    /**
     * Finds tasks in the list which match a given keyword.
     * @param word
     * @return all matching tasks
     */
    public String findTasks(String word) throws DukeException {
        StringBuilder sb = new StringBuilder();
        int count = 0;
        for (Task task : taskArr) {
            if (!task.getDescription().contains(word)) {
                continue;
            }
            sb.append(++count + ". " + task);
            sb.append(System.lineSeparator());
        }

        if (sb.length() == 0) {
            throw new DukeException(DukeUI.missingTaskErrorMessage());
        }
        return sb.toString();
    }

    public int getTaskArraySize() {
        return taskArr.size();
    }

    public ArrayList<Task> getTaskArr() {
        return taskArr;
    }

    public void sortTasks() {
        Collections.sort(taskArr, (thisTask, comparedTask) -> {
            String comparedTaskDate = comparedTask.getDate();
            String thisTaskDate = thisTask.getDate();
            try {
                LocalDateTime comparedDate = LocalDateTime.parse(comparedTaskDate,
                        DateTimeFormatter.ofPattern("d/M/yy Hmm"));
                LocalDateTime thisDate = LocalDateTime.parse(thisTaskDate,
                        DateTimeFormatter.ofPattern("d/M/yy Hmm"));
                return thisDate.compareTo(comparedDate);
            } catch (DateTimeException e) {
                return 0;
            }
        });
    }

}
