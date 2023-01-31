package duke.ui;

import duke.task.Task;
import duke.task.TaskList;

import java.util.Scanner;

public class Ui {

    private final Scanner sc = new Scanner(System.in);
    public boolean firstTime = false;
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
    public void showBunny() {
        System.out.println("(\\ (\\\n" +
                "(„• ֊ •„) ♡\n" +
                "━O━O━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
    }

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

    public void mark(Task t) {
        System.out.println("Okie! I've marked this task as done:");
        System.out.println(t);
    }

    public void unmark(Task t) {
        System.out.println("Okie! I've marked this task as not done yet:");
        System.out.println(t);
    }

    public void add(Task t, TaskList tasks) {
        System.out.println("Alright! I've added this task:");
        System.out.println(t);
        showTaskListStatus(tasks);
    }

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