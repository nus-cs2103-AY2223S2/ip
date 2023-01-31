package duke.ui;

import java.util.Scanner;

import duke.task.Task;
import duke.task.TaskList;

/**
 * A class that deals with interactions with the user.
 * It accepts user input and displays text on the screen.
 */
public class Ui {

    private final Scanner sc = new Scanner(System.in);
    private boolean firstTime = false;

    public void setFirstTime(boolean b) {
        firstTime = b;
    }
    public void showWelcome() {
        System.out.println("૮₍ ˃ ᵕ ˂ ₎ა");
        System.out.println("Hello! I'm Duke, your favourite pink bunny.");
        System.out.println("What can I do for you today?");
        if (firstTime) {
            System.out.println("I don't seem to know anything about you! First time meeting? :D");
        } else {
            System.out.println("I'm so happy we're meeting again!");
        }
    }

    public String readCommand() {
        return sc.nextLine();
    }

    /**
     * Displays a bunny divider.
     */
    public void showBunny() {
        System.out.println("(\\ (\\\n"
                + "(„• ֊ •„) ♡\n"
                + "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Displays the error message.
     * @param err Error.
     */
    public void showError(Exception err) {
        String msg = err.getMessage();
        System.out.println(msg);
        if (msg.equals("Hmm, your command format's a little off!")) {
            System.out.println("Here are the appropriate formats to use:");
            System.out.println("todo DESC");
            System.out.println("deadline DESC /by DD/MM/YYYY HHMM");
            System.out.println("event DESC /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM");
            System.out.println("list");
            System.out.println("find KEYWORD");
            System.out.println("mark TASK_NUM");
            System.out.println("unmark TASK_NUM");
            System.out.println("delete TASK_NUM");
            System.out.println("bye");
        }
    }

    public void sayBye() {
        System.out.println("Bye bye :( Hope to see you again soon!");
    }

    /**
     * Tells the user that a task has been marked done.
     * @param t The task that has been marked done.
     */
    public void mark(Task t) {
        System.out.println("Okie! I've marked this task as done:");
        System.out.println(t);
    }

    /**
     * Tells the user that a task has been marked undone.
     * @param t The task that has been marked done.
     */
    public void unmark(Task t) {
        System.out.println("Okie! I've marked this task as not done yet:");
        System.out.println(t);
    }

    /**
     * Tells the user that a task has been added.
     * @param t The task that has been added.
     * @param tasks List of tasks.
     */
    public void add(Task t, TaskList tasks) {
        System.out.println("Alright! I've added this task:");
        System.out.println(t);
        showTaskListStatus(tasks);
    }

    /**
     * Tells the user that a task has been deleted.
     * @param t The task that has been deleted.
     * @param tasks List of tasks.
     */
    public void delete(Task t, TaskList tasks) {
        System.out.println("Okie! I've removed this task:");
        System.out.println(t);
        showTaskListStatus(tasks);
    }

    void showTaskListStatus(TaskList tasks) {
        if (tasks.size() == 1) { // grammar
            System.out.println("Now you have 1 task on your list.");
        } else {
            System.out.println(String.format("Now you have %s tasks on your list.", tasks.size()));
        }
    }

    /**
     * Lists the tasks in the TaskList.
     * @param tasks List of tasks.
     */
    public void list(TaskList tasks) {
        if (tasks.isEmpty()) {
            System.out.println("Your list is currently empty!");
        } else {
            System.out.println("Here are all the things on your list!");
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                System.out.println(String.format("%s. %s", i + 1, t));
            }
        }
    }

    /**
     * Finds tasks in the list of tasks that contain the given keyword.
     * @param keyword Keyword to find in list of tasks.
     * @param tasks List of tasks.
     */
    public void find(String keyword, TaskList tasks) {
        System.out.println("Here are all the things on your list with this keyword!");
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDesc().contains(keyword)) {
                System.out.println(String.format("%s. %s", i + 1, t));
            }
        }
    }
}