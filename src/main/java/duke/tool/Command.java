package duke.tool;

import java.util.ArrayList;

import duke.task.Task;
import duke.ui.Ui;

/**
 * Constructs a command class that handles all changes to the task list.
 */
public class Command {

    private ArrayList<Task> tasks;
    private final Ui ui;

    /**
     * Constructs a command object.
     * @param tasks The list of tasks to operate upon.
     * @param ui The user interface object for printing.
     */
    public Command(ArrayList<Task> tasks, Ui ui) {
        assert ui != null : "Unable to accept null user interface";
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Marks a task as done.
     * @param tid The id of the task.
     * @return The successful or unsuccessful ui notification message.
     */
    public String mark_as_done(int tid) {
        Task task = new Task();
        try {
            task = this.tasks.get(tid - 1);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        task.markAsDone();
        return ui.print_done_msg(task);
    }

    /**
     * Marks a task as not done.
     * @param tid The id of the task.
     * @return The successful or unsuccessful ui notification message.
     */
    public String mark_as_undone(int tid) {
        Task task = new Task();
        try {
            task = tasks.get(tid - 1);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        task.markAsNotDone();
        return ui.print_undone_msg(task);
    }

    /**
     * Add the task to the task list.
     * @param task The task to add.
     * @return The successful or unsuccessful ui notification message.
     */
    public String add_task(Task task) {
        if (this.check_duplicates(task)) {
            return ui.print_custom_msg("Duplicate tasks detected! \n\t" + task);
        }
        tasks.add(task);
        return ui.print_add_msg(task, tasks.size());
    }

    /**
     * Delete the task to the task list.
     * @param tid The task id to delete.
     * @return The successful or unsuccessful ui notification message.
     */
    public String delete_task(int tid) {
        Task task = new Task();
        try {
            task = tasks.get(tid - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        tasks.remove(task);
        return ui.print_remove_msg(task, tasks.size());
    }

    /**
     * Find the task that contains match string from the task list.
     * @param matchStr The string to match with.
     * @return The contents of tasks found.
     */
    public String find_tasks(String matchStr) {
        assert matchStr != null : "Please provide a match string";
        if (matchStr.isBlank()) {
            return ui.print_custom_msg("Nothing found");
        }
        ArrayList<Task> matchedTasks = new ArrayList<>();
        tasks.forEach((task) -> {
            String description = task.getDescription();
            if (description.contains(matchStr)) {
                matchedTasks.add(task);
            }
        });
        return ui.print_task_list(matchedTasks);
    }

    private boolean check_duplicates(Task task) {
        String taskDescr = task.getDescription();
        for (Task curTask : tasks) {
            if (curTask.getDescription().equals(taskDescr)) {
                return true;
            }
        }
        return false;
    }
}
