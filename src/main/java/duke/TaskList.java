package duke;

import duke.tasks.Task;

import java.io.*;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private static final String INVALID_INDEX_EXCEPTION = "Invalid task index given for Mark/Unmark/Delete command.";
    private static final String LIST_RESPONSE = "Current tasks in list:";
    private static final String ADDED_TASK_RESPONSE = "Task added:\n";
    private static final String REMAINING_TASK_RESPONSE ="\nRemaining task count: ";
    private static final String REMOVE_TASK_RESPONSE = "Task removed:\n";
    private static final String LIST_INDEX_SEPARATOR = ". ";


    private ArrayList<Task> list;
    TaskList() {
        list = new ArrayList<>();
    }

    public String addTask(Task task) {
        list.add(task);
        int count = list.size() - 1;
        return ADDED_TASK_RESPONSE + list.get(count) + REMAINING_TASK_RESPONSE
                + list.size();
    }

    public String markTask(int taskIndex) throws DukeException {
        try {
            return list.get(taskIndex - 1).mark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public String unmarkTask(int taskIndex) throws DukeException {
        try {
            return list.get(taskIndex - 1).unmark();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public String deleteTask(int taskIndex) throws DukeException {
        try {
            Task toRemove = list.get(taskIndex - 1);
            list.remove(taskIndex - 1);
            return REMOVE_TASK_RESPONSE + toRemove.toString() + REMAINING_TASK_RESPONSE + list.size();
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        result.append(LIST_RESPONSE);
        for (int i=0; i < list.size(); i++) {
            result.append("\n").append(i + 1).append(LIST_INDEX_SEPARATOR).append(list.get(i));
        }
        return result.toString();
    }

}
