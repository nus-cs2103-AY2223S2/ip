package duke.ui;

import java.util.ArrayList;

import duke.task.Task;

public class Ui {

    public static void print(String s) {
        System.out.println(s);
    }

    public void print_greet_msg() {
        print("Hello! I'm Duke\n" +
                "     What can I do for you?");
    }

    public void print_bye_msg() {
        print("Bye. Hope to see you again soon!");
    }

    public void print_task_list(ArrayList<Task> tasks) {
        if (tasks.isEmpty()) {
            print("No items yet.");
        } else {
            int i = 1;
            for (Task t : tasks) {
                print(i + "." + t.toString());
                i++;
            }
        }
    }

    public void print_mark_as_done_msg(Task task) {
        if (task.isNull()) {
            print("Unsuccessfully marked.");
            return;
        }
        print("Nice! I've marked this task as done:");
        print("\t" + task);
    }

    public void print_mask_as_undone_msg(Task task) {
        if (task.isNull()) {
            print("Unsuccessfully marked.");
            return;
        }
        print("OK, I've marked this task as not done yet:");
        print("\t" + task);
    }

    public void print_add_task_msg(Task task, int size) {
        if (task.isNull()) {
            print("Unsuccessfully added.");
            return;
        }
        print("Got it. I've added this task:");
        print("\t" + task);
        print("Now you have " + size + " tasks in the list.");
    }

    public void print_remove_task_msg(Task task, int size) {
        if (task.isNull()) {
            print("Unsuccessfully removed.");
            return;
        }
        print("Noted. I've removed this task:");
        print("\t" + task);
        print("Now you have " + size + " tasks in the list.");
    }
}
