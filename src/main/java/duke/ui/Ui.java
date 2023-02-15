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
    private boolean isFirstTime = false;

    public void setFirstTime(boolean b) {
        isFirstTime = b;
    }

    /**
     * Displays a welcome message.
     */
    public void showWelcome() {
        System.out.println("૮₍ ˃ ᵕ ˂ ₎ა");
        System.out.println("Hello! I'm Duke, your favourite pink bunny.");
        System.out.println("What can I do for you today?");
        if (isFirstTime) {
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
                + "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

    /**
     * Displays the error message.
     * @param err Error.
     */
    public String showError(Exception err) {
        String msg = err.getMessage();
        System.out.println(msg);

        String formats = "Here are the appropriate formats to use:\n"
                + "todo DESC\n"
                + "deadline DESC /by DD/MM/YYYY HHMM\n"
                + "event DESC /from DD/MM/YYYY HHMM /to DD/MM/YYYY HHMM\n"
                + "list\n"
                + "find KEYWORD\n"
                + "edit TASK_NUM NEW_DESC\n"
                + "mark TASK_NUM\n"
                + "unmark TASK_NUM\n"
                + "delete TASK_NUM\n"
                + "bye";

        String response = "";
        response = response + msg;

        if (msg.equals("Hmm, your command format's a little off!")) {
            System.out.println(formats);
            response += "\n" + formats;
        }
        return response;
    }

    /**
     * Says bye to the user.
     * @return
     */
    public String sayBye() {
        String response = "Bye bye :( Hope to see you again soon!";
        System.out.println(response);
        return response;
    }

    /**
     * Tells the user that a task has been marked done.
     * @param t The task that has been marked done.
     */
    public String mark(Task t) {
        String response = "Okie! I've marked this task as done:\n" + t;
        System.out.println(response);
        return response;
    }

    /**
     * Tells the user that a task has been marked undone.
     * @param t The task that has been marked done.
     */
    public String unmark(Task t) {
        String response = "Okie! I've marked this task as not done yet:\n" + t;
        System.out.println(response);
        return response;
    }

    /**
     * Tells the user that a task has been added.
     * @param t The task that has been added.
     * @param tasks List of tasks.
     */
    public String add(Task t, TaskList tasks) {

        String response = "Alright! I've added this task:\n"
                + t
                + "\n" + showTaskListStatus(tasks);

        System.out.println(response);
        return response;
    }

    /**
     * Tells the user that a task has been deleted.
     * @param t The task that has been deleted.
     * @param tasks List of tasks.
     */
    public String delete(Task t, TaskList tasks) {
        String response = "Okie! I've removed this task:\n"
                + t
                + "\n" + showTaskListStatus(tasks);
        System.out.println(response);
        return response;
    }

    /**
     * Tells the user that a task has been edited.
     * @param t The task that has been edited.
     */
    public String edit(Task t) {
        String response = "Okie! I've updated your task description:\n"
                + t;
        System.out.println(response);
        return response;
    }

    String showTaskListStatus(TaskList tasks) {
        String response;
        if (tasks.size() == 1) { // grammar
            response = "Now you have 1 task on your list.";
        } else {
            response = String.format("Now you have %s tasks on your list.", tasks.size());
        }
        System.out.println(response);
        return response;
    }

    /**
     * Lists the tasks in the TaskList.
     * @param tasks List of tasks.
     */
    public String list(TaskList tasks) {
        String response;
        if (tasks.isEmpty()) {
            response = "Your list is currently empty!";
        } else {
            response = "Here are all the things on your list!";
            for (int i = 0; i < tasks.size(); i++) {
                Task t = tasks.get(i);
                response = response + "\n" + String.format("%s. %s", i + 1, t);
            }
        }
        System.out.println(response);
        return response;
    }

    /**
     * Finds tasks in the list of tasks that contain the given keyword.
     * @param keyword Keyword to find in list of tasks.
     * @param tasks List of tasks.
     */
    public String find(String keyword, TaskList tasks) {
        String response = "Here are all the things on your list with this keyword!";
        for (int i = 0; i < tasks.size(); i++) {
            Task t = tasks.get(i);
            if (t.getDesc().contains(keyword)) {
                response = response + "\n" + String.format("%s. %s", i + 1, t);
            }
        }
        System.out.println(response);
        return response;
    }
}
