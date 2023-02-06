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
    public void add(String type, String task) throws DukeExceptions{
        Task taskObject = new Task();
        Task newTask = taskObject.createNewTask(type, task);
        System.out.println("Got it. I've added this task:");
        todo_list.add(newTask);
        System.out.println(newTask);
    }

    /**
     * Marks a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public void mark(int index) throws DukeExceptions{
        int todo_list_length = todo_list.size();
        if (index < 0 || index > todo_list_length) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todo_list.get(index - 1);
        task.markTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(todo_list.get(index-1));
    }

    /**
     * Unmarks a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public void unmark(int index) throws DukeExceptions{
        int todo_list_length = todo_list.size();
        if (index < 0 || index > todo_list_length) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todo_list.get(index - 1);
        task.unmarkTask();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(todo_list.get(index-1));

    }

    /**
     * Deletes a numbered task is done.
     *
     * @param index The number order of the task.
     * @throws DukeExceptions If the input syntax is incorrect.
     */
    public void delete(int index) throws DukeExceptions {
        int todo_list_length = todo_list.size();
        if (index < 0 || index > todo_list_length) {
            throw new DukeExceptions("Please use list command to check the index!");
        }
        Task task = todo_list.remove(index - 1);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
    }

    /**
     * Returns how many tasks in the list.
     *
     * @return How many tasks in the list.
     */
    public int number_of_tasks() {
        return todo_list.size();
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
