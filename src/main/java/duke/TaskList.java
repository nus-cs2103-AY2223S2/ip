package duke;

import duke.task.Task;

import java.util.ArrayList;

/**
 * TaskList contains the task list and handles operations related to the list.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructor for TaskList.
     * @param tasks current arraylist of tasks
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }
    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Gets current arraylist of tasks.
     * @return arraylist of tasks
     */
    public ArrayList<Task> getArrayList() {
        return this.tasks;
    }

    /**
     * Lists tasks in TaskList.
     */
    public void listTasks() {
        int numTasks = tasks.size();
        System.out.println("Here are the tasks in your list:");
        if (numTasks == 0) {
            System.out.println("You do not have any tasks for now!");
        } else {
            for (int i = 0; i < numTasks; i++) {
                System.out.println(String.format("%d. %s",i+1,tasks.get(i)));
            }
        }
    }

    /**
     * Marks task as done.
     * @param taskIndex index of specified task
     */
    public void markTask(int taskIndex) {
        tasks.get(taskIndex).markAsDone();
        System.out.println("Nice! I've marked this duke.task as done:\n " + tasks.get(taskIndex).toString());
    }

    /**
     * Unmarks task.
     * @param taskIndex index of specified task
     */
    public void unmarkTask(int taskIndex) {
        tasks.get(taskIndex).markUndone();
        System.out.println("OK, I've marked this duke.task as not done yet:\n " + tasks.get(taskIndex).toString());
    }

    /**
     * Deletes task.
     * @param taskIndex index of specified task
     */
    public void deleteTask(int taskIndex) {
        Task taskRemoved = tasks.remove(taskIndex);
        System.out.println("Noted. I've removed this duke.task: \n" + taskRemoved +
                String.format("\n Now you have %d tasks in the list.",tasks.size()));
    }

    /**
     * Adds task.
     * @param newTask task to be added
     */
    public void addTask(Task newTask) {
        tasks.add(newTask);
        System.out.println(newTask);
        System.out.println(String.format("Now you have %d duke.task(s) in the list.", tasks.size()));

    }

    /**
     * Finds and prints tasks in task list that matches keyword.
     * @param keyword
     */
    public void findKeywordMatches(String keyword) {
        int numMatch = 0;
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            String taskDesc = tasks.get(i).getDesc();
            if (taskDesc.contains(keyword)) {
                numMatch++;
                System.out.println(String.format("%d. %s",numMatch,tasks.get(i)));
            }
        }
        if (numMatch == 0) {
            System.out.println("There are no matches!!");
        }
    }

}
