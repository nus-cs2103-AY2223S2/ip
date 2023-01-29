package duke.ui;

import duke.data.TaskList;
import duke.data.task.Task;

import java.util.Scanner;

public class Ui {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static final String DIVIDER = "----------------------------------------------------\n";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String readCommand() {
        String cmd = sc.nextLine();
        return cmd;
    }

    public void showTaskCount(int size) {
        System.out.println("There are now " + Integer.toString(size) + " task(s) in the list.");
    }

    public void showAddTask(Task task) {
        System.out.println("Understood. I have added the task:\n" + task.toString());
    }

    public void showDeleteTask(Task task) {
        System.out.println("Noted. I have removed the task:\n" + task.toString());
    }

    public void showSavedTasks() {
        System.out.println("Tasks have been saved.");
    }

    public void showAllTasks(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("No tasks added yet.");
        } else {
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + ". " + tasks.get(i).toString());
            }
        }
    }

    public void showMarked(Task task) {
        System.out.println(task.outputMarked() + task.toString());
    }

    public void showUnmarked(Task task) {
        System.out.println(task.outputUnmarked() + task.toString());
    }

    public void showWelcome() {
        showLine();
        System.out.println(LOGO + "Hello! I'm Duke. How may I be of assistance?\n");
        showLine();
    }
    public void showGoodbye() {
        showLine();
        System.out.println("Thank you for your patronage. Goodbye!\n");
        showLine();
    }

    public void showError(String error) {
        System.out.println("Error: " + error);
    }

    public void showLine() {
        System.out.println(DIVIDER);
    }

}
