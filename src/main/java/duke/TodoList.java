package duke;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a list containing all tasks.
 */
public class TodoList implements Serializable {
    private ArrayList<Task> todo_list;

    public TodoList() {
        this.todo_list = new ArrayList<>(100);
    }

    /**
     * Creates and adds new task corresponding to the input.
     *
     * @param type Type of task.
     * @param task Description of task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public String add(String type, String task) throws DukeExceptions{
        Task taskObject = new Task();
        Task newTask = taskObject.createNewTask(type, task);
        todo_list.add(newTask);
        return ("Got it. I've added this task:\n" + newTask.toString());
    }

    /**
     * Marks a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public String mark(int index) throws DukeExceptions{
        int todo_list_length = todo_list.size();
        boolean isOutOfBound = index < 0 || index > todo_list_length;
        if (isOutOfBound) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todo_list.get(index - 1);
        task.markTask();
        return String.format("Nice! I've marked this task as done:\n%s", todo_list.get(index-1));
    }

    /**
     * Unmarks a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public String unmark(int index) throws DukeExceptions{
        int todo_list_length = todo_list.size();
        boolean isOutOfBound = index < 0 || index > todo_list_length;
        if (isOutOfBound) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todo_list.get(index - 1);
        task.unmarkTask();
        return String.format("OK, I've marked this task as not done yet:\n%s", todo_list.get(index-1));
    }

    /**
     * Deletes a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public String delete(int index) throws DukeExceptions {
        int todo_list_length = todo_list.size();
        boolean isOutOfBound = index < 0 || index > todo_list_length;
        if (isOutOfBound) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todo_list.remove(index - 1);
        return String.format("Noted. I've removed this task:\n%s", task);
    }

    /**
     * Returns how many tasks in the list.
     *
     * @return How many tasks in the list.
     */
    public int number_of_tasks() {
        return todo_list.size();
    }

    public String find(String keyword) {
        String result_list = "";
        int todo_list_length = todo_list.size();
        int resultIndex = 1;
        for (int i = 1; i <= todo_list_length; i++) {
            Task task = todo_list.get(i-1);
            if (task.doesContainKeyword(keyword)) {
                result_list += String.format("%d. %s\n", resultIndex++, task);
            }
        }
        return("Here are the matching tasks in your list:\n" + result_list);
    }

    @Override
    public String toString() {
        String shown_list = "";
        int todo_list_length = todo_list.size();
        for (int i = 1; i <= todo_list_length; i++) {
            shown_list += String.format("%d. %s\n", i, todo_list.get(i-1));
        }
        return shown_list;
    }
}
