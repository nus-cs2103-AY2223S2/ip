package duke.task;

import duke.task.Task;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Keeps the list of tasks using an arraylist.
 */
public class TaskList {
    private ArrayList<Task> list;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(ArrayList<Task> a) {
        this.list = a;
    }

    /**
     * Identify the task from its number and mark it as complete.
     *
     * @param taskno - Number for the task on the list.
     */
    public void markTask(int taskno) {
        list.get(taskno).markAsDone();
        System.out.println(list.get(taskno));
        statement();
    }

    /**
     * Identify the task from its number and mark it as incomplete.
     *
     * @param taskno Number for the task on the list.
     */
    public void unmarkTask(int taskno) {
        list.get(taskno).markAsUndone();
        System.out.println(list.get(taskno));
        statement();
    }

    /**
     * Prints the current list of tasks.
     */
    public void printList() {
        System.out.println("Here are the tasks in your list: ");
        for (int x = 0; x < list.size(); x++) {
            System.out.println((x + 1) + "." + list.get(x));
        }
        System.out.println("----------------------------------------------------");
        statement();
    }

    /**
     * Add the input task object to the current object list.
     *
     * @param t Task object to be added.
     */
    public void addToList(Task t) {
        list.add(t);
    }


    /**
     * Identify the task from its number and remove it from the list.
     *
     * @param taskno Number for the task on the list.
     */
    public void deleteTask(int taskno) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(taskno));
        list.remove(taskno);
        statement();
    }

    /**
     * Shows how many tasks is in the list.
     *
     * @return Integer - Number of elements in list.
     */
    public int lengthOflist() {
        return list.size();
    }

    /**
     * Output the statement for the number of tasks in the list.
     */
    public void statement() {
        System.out.print("Now you have " + this.lengthOflist() + " tasks in your list.\n");
    }

    public ArrayList<Task> find(String message) {
        ArrayList<Task> arrStr = new ArrayList<>();
        for (Task t: list) {
            if (t.toString().contains(message)) {
                arrStr.add(t);
            }

        }
        return arrStr;
    }
}
