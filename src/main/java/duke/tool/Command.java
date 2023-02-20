package duke.tool;

import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

/**
 * Constructs a command class that handles all changes to the task list.
 */
public class Command {

    private ArrayList<Task> tasks;
    private final Ui ui;

    public Command(ArrayList<Task> tasks, Ui ui) {
        this.tasks = tasks;
        this.ui = ui;
    }

    /**
     * Marks a task as done.
     * @param tid The id of the task.
     * @return
     */
    public String mark_as_done(int tid) {
        Task task = new Task();
        try {
            task = this.tasks.get(tid - 1);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        task.markAsDone();
        return ui.print_mark_as_done_msg(task);
    }

    public String mark_as_undone(int tid) {
        Task task = new Task();
        try {
            task = tasks.get(tid - 1);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        task.markAsNotDone();
        return ui.print_mask_as_undone_msg(task);
    }

    public String add_task_to_list(Task task) {
        tasks.add(task);
        return ui.print_add_task_msg(task, tasks.size());
    }

    public String delete_task(int tid) {
        Task task = new Task();
        try {
            task = tasks.get(tid - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        tasks.remove(task);
        return ui.print_remove_task_msg(task, tasks.size());
    }

    public String find_tasks(String match_str) {
        // TODO: use Java streams to rewrite.
        if (match_str.isBlank()) {
            return "";
        }
        ArrayList<Task> matched_tasks = new ArrayList<>();
        String description;
        for (Task task : tasks) {
            description = task.toString();
            if (description.contains(match_str)) {
                matched_tasks.add(task);
            }
        }
        return ui.print_task_list(matched_tasks);
    }
}
