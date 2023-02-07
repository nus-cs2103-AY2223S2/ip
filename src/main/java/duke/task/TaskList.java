package duke.task;

import duke.exception.EmptyDescriptionException;
import duke.exception.WrongCommandException;

import java.util.ArrayList;
import java.util.List;

/**
 * TaskList to contain all the tasks in a list
 */
public class TaskList {
    public List<Task> taskList;

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public List<Task> getTaskList() {
        return this.taskList;
    }

    /**
     * Prints the list of tasks
     */
    public void printList() {
        System.out.println("Here are some tasks in your list:");
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            System.out.println(i + "." + (taskList.get(i - 1)).toString());
        }
    }

    /**
     * Mark the task stored in the list as completed
     * @param taskStr Task number of task in the list to mark as completed
     */
    public void markTask(String taskStr) {
        int taskNum = Integer.parseInt(taskStr) - 1;
        Task originalTask =  taskList.get(taskNum);
        originalTask.markTask();
        System.out.println("Nice! I've marked this task as done: \n  " + originalTask);
    }

    /**
     * Mark the task stored in the list as not completed
     * @param taskStr Task number of task in the list to mark as completed
     */
    public void unmarkTask(String taskStr) {
        int taskNum = Integer.parseInt(taskStr) - 1;
        Task originalTask =  taskList.get(taskNum);
        originalTask.unmarkTask();
        System.out.println("Ok, I've marked this task as not done yet: \n  " + originalTask);
    }

    /**
     * Add the task into the list of task
     * @param task Task to be added
     */
    public void addTask(Task task) {
        taskList.add(task);
    }

    /**
     * Inform user that the task has been successfully added
     * @param task Task that has been added
     */
    public void printAddComment(Task task) {
        System.out.println("Got it. I've added this task: \n  " + task +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Delete the task from the list
     * @param taskStr Task number of task in the list to be deleted
     */
    public void deleteTask(String taskStr) {
        int taskNum = Integer.parseInt(taskStr) - 1;
        Task taskToRemove = taskList.get(taskNum);
        String removedTaskStr = taskToRemove.toString();
        taskList.remove(taskNum);
        System.out.println("Noted. I've removed this task: \n  " + removedTaskStr +
                "\nNow you have " + taskList.size() + " tasks in the list.");
    }

    /**
     * Check if the task description is empty or not
     * @param input user input
     * @throws EmptyDescriptionException If there is only command without task
     */

    public void emptyDes(String input) throws EmptyDescriptionException {
        if ((input.equals("todo")) || (input.equals("deadline")) || (input.equals("event"))) {
            throw new EmptyDescriptionException();
        }
    }

    /**
     * Check if the command exist or not
     * @param command First word of user input
     * @throws WrongCommandException If command does not exist
     */
    public void wrongCommand(String command) throws WrongCommandException {
        if (!(command.equals("mark") || command.equals("unmark") || command.equals("todo") ||
                command.equals("deadline") || command.equals("event") || command.equals("delete"))) {
            throw new WrongCommandException();
        }
    }



}
