package duke;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents a list containing all tasks.
 */
public class TodoList implements Serializable {
    private ArrayList<Task> todoList;

    public TodoList() {
        this.todoList = new ArrayList<>(100);
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
        todoList.add(newTask);
        return ("Got it. I've added this task:\n" + newTask.toString());
    }

    /**
     * Marks a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public String mark(int index) throws DukeExceptions{
        int todo_list_length = todoList.size();
        boolean isOutOfBound = index < 0 || index > todo_list_length;
        if (isOutOfBound) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todoList.get(index - 1);
        task.markTask();
        return String.format("Nice! I've marked this task as done:\n%s", todoList.get(index-1));
    }

    /**
     * Unmarks a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public String unmark(int index) throws DukeExceptions{
        int todo_list_length = todoList.size();
        boolean isOutOfBound = index < 0 || index > todo_list_length;
        if (isOutOfBound) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todoList.get(index - 1);
        task.unmarkTask();
        return String.format("OK, I've marked this task as not done yet:\n%s", todoList.get(index-1));
    }

    /**
     * Deletes a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public String delete(int index) throws DukeExceptions {
        int todo_list_length = todoList.size();
        boolean isOutOfBound = index < 0 || index > todo_list_length;
        if (isOutOfBound) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todoList.remove(index - 1);
        return String.format("Noted. I've removed this task:\n%s", task);
    }

    /**
     * Returns how many tasks in the list.
     *
     * @return How many tasks in the list.
     */
    public int getNumberOfTasks() {
        return todoList.size();
    }

    public String find(String keyword) {
        String resultList = "";
        int todoListLength = todoList.size();
        int resultIndex = 1;
        for (int i = 1; i <= todoListLength; i++) {
            Task task = todoList.get(i-1);
            if (task.doesContainKeyword(keyword)) {
                resultList += String.format("%d. %s\n", resultIndex++, task);
            }
        }
        return("Here are the matching tasks in your list:\n" + resultList);
    }

    @Override
    public String toString() {
        String shownList = "";
        int todoListLength = todoList.size();
        for (int i = 1; i <= todoListLength; i++) {
            shownList += String.format("%d. %s\n", i, todoList.get(i-1));
        }
        return shownList;
    }
}
