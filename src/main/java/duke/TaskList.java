package duke;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import duke.tasks.Task;

/**
 * List data structure to store the tasks in Duke
 */
public class TaskList implements Serializable {

    private static final String INVALID_INDEX_EXCEPTION = "Invalid task index given for Mark/Unmark/Delete command.";
    private static final String ADDED_TASK_RESPONSE = "Task added:\n";
    private static final String REMAINING_TASK_RESPONSE = "\nRemaining task count: ";
    private static final String REMOVE_TASK_RESPONSE = "Task removed:\n";
    private static final String LIST_INDEX_SEPARATOR = ". ";

    private ArrayList<Task> listOfTasks;
    public TaskList() {
        listOfTasks = new ArrayList<>();
    }

    /**
     * Add a task to the list
     * @param task Task to be added
     * @return String result of adding task
     */
    public String addTask(Task task) {
        listOfTasks.add(task);

        int count = listOfTasks.size() - 1;
        return ADDED_TASK_RESPONSE + listOfTasks.get(count) + REMAINING_TASK_RESPONSE
                + listOfTasks.size();
    }

    /**
     * Mark task in list as completed
     * @param taskIndex Index of task in list
     * @return Result of marking task
     * @throws DukeException If index is invalid
     */
    public String markTask(int taskIndex) throws DukeException {
        try {
            return listOfTasks.get(taskIndex - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    /**
     * Unmark task in list as completed
     * @param taskIndex Index of task in list
     * @return String result of unmarking task
     * @throws DukeException If index is invalid
     */
    public String unmarkTask(int taskIndex) throws DukeException {
        try {
            return listOfTasks.get(taskIndex - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    /**
     * Delete task in list
     * @param taskIndex Index of task in list
     * @return String result of deleting task
     * @throws DukeException If index is invalid
     */
    public String deleteTask(int taskIndex) throws DukeException {
        try {
            Task toRemove = listOfTasks.get(taskIndex - 1);
            listOfTasks.remove(taskIndex - 1);
            return REMOVE_TASK_RESPONSE + toRemove.toString() + REMAINING_TASK_RESPONSE + listOfTasks.size();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    /**
     * Searches list of tasks for any that has similar keyword
     * @param keyword Word to be searched amongst tasks
     * @return String result of all matched tasks
     */
    public String search(String keyword) {
        Stream<Task> taskStream = listOfTasks.stream();
        Stream<Task> filteredTaskStream = taskStream.filter(task -> task.hasKeyword(keyword));

        List<Task> list = filteredTaskStream.collect(Collectors.toList());
        TaskList filteredTasks = new TaskList();
        ArrayList<Task> arrayList = new ArrayList<>(list);
        filteredTasks.setListOfTasks(arrayList);

        return filteredTasks.toString();
    }

    private void setListOfTasks(ArrayList<Task> list) {
        this.listOfTasks = list;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < listOfTasks.size(); i++) {
            result.append("\n").append(i + 1).append(LIST_INDEX_SEPARATOR).append(listOfTasks.get(i));
        }
        return result.toString();
    }

}
