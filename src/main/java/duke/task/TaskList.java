package duke.task;
import java.util.ArrayList;
import java.util.List;

import duke.exception.EmptyDescriptionException;
import duke.exception.WrongCommandException;

/**
 * TaskList to contain all the tasks in a list
 */
public class TaskList {
    private List<Task> taskList;

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
    public String printList() {
        if (taskList.size() == 0) {
            return "There is no task in your task list!";
        }
        String list = "Here are some tasks in your list:";
        for (int i = 1; i < this.taskList.size() + 1; i++) {
            list = list + "\n" + i + "." + (taskList.get(i - 1)).toString();
        }
        return list;
    }

    /**
     * Mark the task stored in the list as completed
     * @param taskStr Task number of task in the list to mark as completed
     */
    public String markTask(String taskStr) {
        try {
            int taskNum = Integer.parseInt(taskStr) - 1;
            Task originalTask = taskList.get(taskNum);
            originalTask.markTask();
            return "Nice! I've marked this task as done: \n  " + originalTask;
        } catch (NumberFormatException e) {
            return "Please enter a task number!";
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
    }

    /**
     * Mark the task stored in the list as not completed
     * @param taskStr Task number of task in the list to mark as completed
     */
    public String unmarkTask(String taskStr) {
        try {
            int taskNum = Integer.parseInt(taskStr) - 1;
            Task originalTask = taskList.get(taskNum);
            originalTask.unmarkTask();
            return "Ok, I've marked this task as not done yet: \n  " + originalTask;
        } catch (NumberFormatException e) {
            return "Please enter a task number!";
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
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
    public String printAddComment(Task task) {
        return "Got it. I've added this task: \n  " + task
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }

    /**
     * Delete the task from the list
     * @param taskStr Task number of task in the list to be deleted
     */
    public String deleteTask(String taskStr) {
        try {
            int taskNum = Integer.parseInt(taskStr) - 1;
            Task taskToRemove = taskList.get(taskNum);
            String removedTaskStr = taskToRemove.toString();
            taskList.remove(taskNum);
            return "Noted. I've removed this task: \n  " + removedTaskStr
                    + "\nNow you have " + taskList.size() + " tasks in the list.";
        } catch (NumberFormatException e) {
            return "Please enter a task number!";
        } catch (IndexOutOfBoundsException e) {
            return "Please enter a valid task number!";
        }
    }

    /**
     * Find the list of tasks containing the targetString
     * @param targetString String to be found
     * @return List of tasks containing the targetString
     */
    public String find(String targetString) {
        List<Task> targetList = new ArrayList<>();
        for (Task t : taskList) {
            if (t.getTask().contains(targetString)) {
                targetList.add(t);
            }
        }

        if (targetList.isEmpty()) {
            return "Oops! :( There are no matching tasks found.";
        } else {
            String listFound = "Here are the matching tasks in your list:";
            for (int i = 1; i < targetList.size() + 1; i++) {
                listFound = listFound + "\n" + i + "." + (targetList.get(i - 1)).toString();
            }
            return listFound;
        }
    }




}
