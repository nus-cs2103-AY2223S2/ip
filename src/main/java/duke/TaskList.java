package duke;

import java.io.Serializable;
import java.util.ArrayList;

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

    public String addTask(Task task) {
        listOfTasks.add(task);

        int count = listOfTasks.size() - 1;
        return ADDED_TASK_RESPONSE + listOfTasks.get(count) + REMAINING_TASK_RESPONSE
                + listOfTasks.size();
    }

    public String markTask(int taskIndex) throws DukeException {
        try {
            return listOfTasks.get(taskIndex - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public String unmarkTask(int taskIndex) throws DukeException {
        try {
            return listOfTasks.get(taskIndex - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public String deleteTask(int taskIndex) throws DukeException {
        try {
            Task toRemove = listOfTasks.get(taskIndex - 1);
            listOfTasks.remove(taskIndex - 1);
            return REMOVE_TASK_RESPONSE + toRemove.toString() + REMAINING_TASK_RESPONSE + listOfTasks.size();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public String search(String keyword) {
        TaskList filteredTasks = new TaskList();
        for (Task task: listOfTasks) {
            if (task.hasKeyword(keyword)) {
                filteredTasks.addTask(task);
            }
        }
        return filteredTasks.toString();
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
