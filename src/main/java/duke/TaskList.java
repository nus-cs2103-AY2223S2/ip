package duke;

import java.io.*;
import java.util.ArrayList;

public class TaskList implements Serializable {

    private static final String INVALID_INDEX_EXCEPTION = "Invalid task index given for Mark/Unmark/Delete command.";
    private static final String LIST_RESPONSE = "Current tasks in list:";
    private static final String ADDED_TASK_RESPONSE = "duke.Task added:\n";
    private static final String REMAINING_TASK_RESPONSE ="\nRemaining task count: ";
    private static final String REMOVE_TASK_RESPONSE = "duke.Task removed:\n";
    private static final String LIST_INDEX_SEPARATOR = ". ";
    private ArrayList<Task> list;
    TaskList() {
        list = new ArrayList<>();
    }

    public String addTask(String input) throws DukeException {
        Task task = Task.parseTaskFromInput(input);
        list.add(task);
        int count = list.size() - 1;
        return ADDED_TASK_RESPONSE + list.get(count) + REMAINING_TASK_RESPONSE
                + list.size();
    }

    public String markTask(String info) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(info);
            if (taskIndex > list.size()) {
                throw new DukeException(INVALID_INDEX_EXCEPTION);
            }

            return list.get(taskIndex - 1).mark();
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public String unmarkTask(String info) throws DukeException {
        try {
            int taskIndex = Integer.parseInt(info);
            if (taskIndex > list.size()) {
                throw new DukeException(INVALID_INDEX_EXCEPTION);
            }

            return list.get(taskIndex - 1).unmark();
        } catch (NumberFormatException e) {
            throw new DukeException(INVALID_INDEX_EXCEPTION);
        }
    }

    public String deleteTask(String info) throws  DukeException {
        try {
            int taskIndex = Integer.parseInt(info);
            if (taskIndex > list.size()) {
                throw new DukeException(INVALID_INDEX_EXCEPTION);
            }

            Task toRemove = list.get(taskIndex - 1);
            list.remove(taskIndex - 1);
            return REMOVE_TASK_RESPONSE + toRemove.toString() + REMAINING_TASK_RESPONSE + list.size();
        } catch (NumberFormatException e) {
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
