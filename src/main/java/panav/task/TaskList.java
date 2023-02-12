package panav.task;

import java.util.ArrayList;

/**
 * Class to represent the list of Tasks in this program. It contains functionality
 * to edit the task list and add/delete changes.
 */
public class TaskList {

    private ArrayList<Task> tasks;

    /**
     * Constructor to initialise ArrayList of tasks and populate it with
     * given list of tasks.
     * @param src list of tasks.
     */
    public TaskList(ArrayList<Task> src) {
        tasks = new ArrayList<>();
        for (Task task : src) {
            tasks.add(task);
        }
    }

    /**
     * Constructor to initialise empty ArrayList of tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Method to print all the tasks in the list.
     */
    public String printAllTasks() {
        String text = "";
        for (Task task : this.tasks) {
            text += task + "\n";
        }
        return text;
    }

    /**
     * Method to return number of tasks in the list.
     * @return length of list.
     */
    public int getLength() {
        return this.tasks.size();
    }

    /**
     * Method to delete a task from the list.
     * @param index the index from where the Task is to be deleted.
     * @return the removed Task.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }

    /**
     * Method to add a task to the list.
     * @param task the task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Method to mark a task as done in the list.
     * @param index the index of task to be marked.
     */
    public String markTask(int index) {
        String text = "Nice! I've marked this task as done:\n";
        Task task = tasks.get(index);
        task.markAsDone();
        text += task + "\n";
        return text;
    }

    /**
     * Method to mark a task as not done in the list.
     * @param index the index of task to be unmarked.
     */
    public String unmarkTask(int index) {
        String text = "Nice! I've marked this task as not done yet:\n";
        Task task = tasks.get(index);
        task.markAsNotDone();
        text += task + "\n";
        return text;
    }

    /**
     * Method to retrieve a Task in the given index.
     * @param index the index of task to be retrieved.
     * @return the Task at that index.
     */
    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Method to print all tasks in the list which contain a particular word.
     * @param keyWord the word to be searched.
     */
    public String printTasksContainingKeyword(String keyWord) {
        int counter = 0;
        String text = "Here are the matching tasks in your list:\n";
        for (Task task : this.tasks) {
            if (task.toString().matches("(.*)" + keyWord + "(.*)")) {
                counter++;
                text += counter + "." + task + "\n";
            }
        }
        return text;
    }

    public String findTasks(String keyword) {
        int counter = 0;
        String text = "Here are all the " + keyword + " tasks in your lists\n";
        for (Task task : this.tasks) {
            if (task.getCommand().compareTo(keyword) == 0) {
                counter++;
                text += counter + "." + task + "\n";
            }
        }
        return text;
    }
}
