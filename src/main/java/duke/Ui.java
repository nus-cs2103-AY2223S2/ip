package duke;

import duke.task.Task;
import duke.tasklist.ArchivedTaskList;
import duke.tasklist.TaskList;

/**
 * Ui class.
 * Handles interaction with the user.
 */
public class Ui {
    public Ui() {

    }

    /**
     * Prints out the greeting when the program first runs.
     */
    public String showGreeting() {
        return "Meow I'm Toto! What can I do for you?";
    }

    /**
     * Prints out note after user inputs bye.
     */
    public String sayGoodbye() {
        return "Content is saved! Enter exit to quit the program, CATch you later!";
    }

    /**
     * Prints out note after a task is marked.
     * @param task Task that is marked.
     */
    public String showMarked(Task task) {
        return "I've marked this task as done: " + task;
    }

    /**
     * Prints out note after a task is unmarked.
     * @param task Task that is unmarked.
     */
    public String showUnmarked(Task task) {
        return "I've marked this task as not done yet: " + task;
    }

    /**
     * Prints out note after a task is deleted.
     * @param task Task that is deleted.
     */
    public String showDeleted(Task task) {
        return "I've deleted this task: " + task;
    }

    /**
     * Prints out note after a task is added.
     * @param task Task that is added.
     */
    public String showAddTask(Task task) {

        return "Meow! Just added: \n" + task;
    }

    /**
     * Prints out error message when the program runs into an invalid input.
     * @param error Error message.
     */
    public String showError(String error) {
        return error;
    }

    /**
     * Returns string when user wants to find tasks with keyword in task list.
     * @param tasks The task list.
     * @return String Message to reply to user.
     */
    public String showFind(TaskList tasks) {
        return "Here are the tasks with the keyword: \n" + tasks;
    }

    /**
     * Returns the string representation when the user archives all the tasks.
     * @return String after user archives all tasks.
     */
    public String showArchivedAll() {
        return "All your tasks are now archived, your task list is now empty!";
    }

    /**
     * Returns string representation of task list.
     * @param tasks
     * @return String of tasks.
     */
    public String showList(TaskList tasks) {
        String beginning = "Task list ";
        if (tasks instanceof ArchivedTaskList) {
            beginning = "Archive ";
        }
        if (tasks.isEmpty()) {
            return "Oh rats! " + beginning + "is empty!";
        }

        return tasks.toString();
    }
}
