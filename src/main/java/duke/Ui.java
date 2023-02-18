package duke;

import java.util.Scanner;

/**
 * This class deals with interactions with the user
 */
public class Ui {
    private Scanner sc;

    /**
     * Gets the next input
     * @return the next input from user
     */
    public String getCommand() {
        return sc.nextLine();
    }

    /**
     * Prints out the list of tasks
     * @return
     */
    public String showList() {
        String list = "Here are the tasks in your list:\n";
        System.out.println("Here are the tasks in your list:\n");
        int listCount = 1;
        for (Task element: Task.tasks) {
            if (element != null) {
                list += "" + listCount + "." + element +"\n";
                System.out.println("" + listCount + "." + element);
                listCount += 1;
            }
        }
        return list;

    }
    /**
     * Tells user that their task has been added to the task list.
     * @param task The task to be added.
     *
     */
    public String addTask(Task task) {
        String addToDoString = "You have added new tasks"
                + "\n added: " + task.toString()
                + "\nyou have " + String.valueOf(Task.actions) + " task(s)";
        return addToDoString;
    }

    /**
     * Returns String in GUI when a Task is mark done
     * @param task task that is marked done
     * @return String that states that a Task is marked done
     */
    public String stringMark(Task task) {
        return "Nice! I've marked this task as done:\n" + task;
    }

    /**
     * Returns String in GUI when a Task is not marked done
     * @param task task that is marked not done
     * @return String representation that states a Task is marked not done
     */
    public String stringUnmark(Task task) {
        return "OK, I've marked this task as not done yet:\n" + task;
    }

    /**
     * Returns String representation of the task that is deleted
     * @param task Task that is deleted from the list
     * @return String representation of the task that is deleted
     */
    public String stringDelete(Task task) {
        String deleted = "Noted. I've removed this task: \n" + task;
        deleted += "\n Now you have " + Task.actions + " tasks in the list";
        return deleted;
    }

    /**
     * Prints the string representation of tag task
     * @param task task that will be tagged
     * @return String representation of tagged task
     */
    public String stringTag(Task task) {
        System.out.println("Task has been tagged:" + task.description);
        return "Task has been tagged:" + task.description;
    }
    /**
     * Prints error
     * @param e
     */
    public void printError(String e) {
        System.out.println(e + "\n");
    }
}
