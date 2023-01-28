package panav.task;

import java.util.ArrayList;

public class TaskList {

    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> src) {
        tasks = new ArrayList<>();
        for (Task task : src) {
            tasks.add(task);
        }
    }
    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void printAllTasks() {
        for (Task task : this.tasks) {
            System.out.println(task);
        }
    }

    public int getLength() {
        return this.tasks.size();
    }

    public Task removeTask(int index) {
        Task removed = tasks.remove(index);
        return removed;
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public void markTask(int index) {
        System.out.println("Nice! I've marked this task as done:");
        Task task = tasks.get(index);
        task.markAsDone();
        System.out.println(task);
    }
    public void unmarkTask(int index) {
        System.out.println("OK, I've marked this task as not done yet:");
        Task task = tasks.get(index);
        task.markAsNotDone();
        System.out.println(task);
    }

    public Task getTask(int index) {
        return tasks.get(index);
    }

    /**
     * Method to print all tasks in the list which contain a particular word.
     * @param keyWord the word to be searched.
     */
    public void printTasksContainingKeyword(String keyWord) {
        int counter = 0;
        for (Task task : this.tasks) {
            if (task.toString().matches("(.*)" + keyWord + "(.*)")) {
                counter++;
                System.out.println(counter + "." + task);
            }
        }
    }
}
