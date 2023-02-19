package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

public class Ui {

    public String print_empty_msg() {
        return "Nothing to say about this!!";
    }

    public String print_greet_msg() {
        return "Hello! I'm Duke\n" +
                "     What can I do for you?";
    }

    public String print_bye_msg() {
        return "Bye. Hope to see you again soon!";
    }

    public String print_task_list(ArrayList<Task> tasks) {
        String output = "";
        if (tasks.isEmpty()) {
            output = "None yet.";
        } else {
            int i = 1;
            for (Task t : tasks) {
                output += i + "." + t.toString() + "\n";
                i++;
            }
        }
        return output;
    }

    public String print_mark_as_done_msg(Task task) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully marked.";
            return output;
        }
        output = "Nice! I've marked this task as done: \n\t" + task;
        return output;
    }

    public String print_mask_as_undone_msg(Task task) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully marked.";
            return output;
        }
        output = "OK, I've marked this task as not done yet: \n\t" + task;
        return output;
    }

    public String print_add_task_msg(Task task, int size) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully added.";
            return output;
        }
        output = "Got it. I've added this task: \n\t" + task +
                "\nNow you have " + size + " tasks in the list.";
        return output;
    }

    public String print_remove_task_msg(Task task, int size) {
        String output = "";
        if (task.isNull()) {
            output = "Unsuccessfully removed.";
            return output;
        }
        output = "Noted. I've removed this task: \n\t" + task +
                "\nNow you have " + size + " tasks in the list.";
        return output;
    }
}
