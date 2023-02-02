package duke;

import java.util.List;

public class Ui {
    // Length of horizontal line
    private static final int HORIZONTAL_LINE_LENGTH = 80;

    /** Print methods */
    public void printHorizontalLine() {
        for (int i = 0; i < HORIZONTAL_LINE_LENGTH; i++) {
            System.out.print("\u2500");
        }

        System.out.println();
    }
    public void showGreeting() {
        printHorizontalLine();
        System.out.println(
            "Karen:\n" +
            "Can I speak to your manager?\n" +
            "Just kidding, this is Karen. How can I help you today?"
        );
        printHorizontalLine();
    }

    public void showExit() {
        printHorizontalLine();
        System.out.println("Karen:\n" + "Bye. This was of great inconvenience.");
        printHorizontalLine();
    }

    public void showKaren() {
        printHorizontalLine();
        System.out.println("Karen:");
    }

    public void showTaskList(List<Task> taskList) {
        for (int i = 0; i < taskList.size(); i++) {
            Task currTask = taskList.get(i);
            System.out.println((i + 1) + ". " + currTask.toString());
        }
    }

    public void showTaskMarkDone(Task task) {
        System.out.println(
            "Congrats, I guess you get a medal?\n"
            + task
        );
    }

    public void showTaskMarkUndone(Task task) {
        System.out.println(
            "Why are you so lazy?\n"
            + task
        );
    }

    public void showDeleteTask(Task task, List<Task> taskList) {
        System.out.println(
            "Okay okay, this has been removed:\n"
            + task.toString()
            + "\nNow you have " + taskList.size() + " tasks left."
        );
    }

    public void showAddTask(Task task, List<Task> taskList) {
        System.out.println(
            "You better finish this soon:\n"
            + task
            + "\nCan you finish all " + taskList.size() + " tasks in your list?"
        );
    }

    public void showFoundTasks(List<Task> taskList) {
        if (taskList.isEmpty()) {
            System.out.println("What are you trying to find?");
        } else {
            System.out.println("Here you go, do you need anything else?");

            for (int i = 0; i < taskList.size(); i++) {
                Task currTask = taskList.get(i);
                System.out.println((i + 1) + ". " + currTask.toString());
            }
        }
    }

    /** Error methods */
    public void showError(String message) {
        System.out.println(message);
    }
}
