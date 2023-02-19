package duke.tool;

import duke.task.Task;
import duke.ui.Ui;

import java.util.ArrayList;

public class Command {
    public static String mark_as_done(int tid, ArrayList<Task> tasks, Ui ui) {
        Task task = new Task();
        try {
            task = tasks.get(tid - 1);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        task.markAsDone();
        return ui.print_mark_as_done_msg(task);
    }

    public static String mark_as_undone(int tid, ArrayList<Task> tasks, Ui ui) {
        Task task = new Task();
        try {
            task = tasks.get(tid - 1);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        task.markAsNotDone();
        return ui.print_mask_as_undone_msg(task);
    }

    public static String add_task_to_list(Task task, ArrayList<Task> tasks, Ui ui) {
        tasks.add(task);
        return ui.print_add_task_msg(task, tasks.size());
    }

    public static String delete_task(int tid, ArrayList<Task> tasks, Ui ui) {
        Task task = new Task();
        try {
            task = tasks.get(tid - 1);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
        tasks.remove(task);
        return ui.print_remove_task_msg(task, tasks.size());
    }

    public static String find_tasks(String match_str, ArrayList<Task> tasks, Ui ui) {
        if (match_str.isBlank()) {
            return "";
        }
        ArrayList<Task> matched_tasks = new ArrayList<>();
        String decription;
        for (Task task : tasks) {
            decription = task.toString();
            if (decription.contains(match_str)) {
                matched_tasks.add(task);
            }
        }
        return ui.print_task_list(matched_tasks);
    }
}
