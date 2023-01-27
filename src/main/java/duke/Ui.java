package duke;

import java.util.ArrayList;

public class Ui {
    public void showWelcomeMessage() {
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
    }

    public void showErrorMessage(String error) {
        System.out.println(error);
    }
    public void showTasksMessage(ArrayList<Task> tasks) {
        System.out.println("Here are the tasks in your list:");
        if (tasks.size() != 0) {
            int idx = 1;
            for (Task task : tasks) {
                System.out.printf("%d.%s%n", idx, task);
                idx++;
            }
        } else {
            System.out.println("There are no tasks currently");
        }

    }

    public void showGoodbyeMessage() {
        System.out.print("Bye. Hope to see you again soon!");
    }

    public void addedTaskMessage(Task task, ArrayList<Task> tasks) {
        System.out.println("Got it. I've added this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }

    public void markTaskMessage(Task task) {
        System.out.println("Nice! I've marked this task as done:\n" + task);
    }

    public void unmarkTaskMessage(Task task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void deleteTaskMessage(Task task, ArrayList<Task> tasks) {
        System.out.println("Noted. I've removed this task:\n" + task);
        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
    }
}
