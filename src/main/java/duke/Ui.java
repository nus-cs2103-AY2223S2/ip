package duke;

import duke.tasks.Task;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Ui {
    private Scanner sc;
    private static final String LINE = "__________________________________________________________\n";

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String uiRead() {
        return sc.nextLine();
    }

    public void greet() {
        System.out.println(Ui.LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + Ui.LINE);
    }

    public void exit() {
        System.out.println(Ui.LINE
                + "Bye. Hope to see you again soon!\n"
                + Ui.LINE);
    }

    public void list(TaskList taskList) {
        System.out.println(Ui.LINE + "Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println(i + 1 + "." + taskList.get(i).toString());
        }
        System.out.println(Ui.LINE);
    }

    public void mark(Task changed) {
        System.out.println(Ui.LINE + "Nice! I've marked this task as done:\n"
                + changed + "\n" + Ui.LINE);
    }

    public void unmark(Task changed) {
        System.out.println(Ui.LINE + "Okay. I've unmarked the following task:\n"
                + changed + "\n" + Ui.LINE);
    }

    public void taskAdded(Task added, int size) {
        System.out.println(Ui.LINE + "Got it. I've added this task:\n" + added);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    public void delete(Task removed, int size) {
        System.out.println(Ui.LINE + "Noted. I've removed this task:\n" + removed);
        System.out.println("Now you have " + size + " tasks in the list.");
        System.out.println(Ui.LINE);
    }

    public void through(String input, TaskList taskList) {
        LocalDateTime date = LocalDateTime.parse(input);
        int i = 1;
        System.out.println(Ui.LINE + "Here are the tasks occurring through "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy HHmm")) + ":");
        for (int j = 0; j < taskList.size(); j++) {
            Task t = taskList.get(j);
            if (t.isWithinDate(date)) {
                System.out.println(i + "." + t);
                i++;
            }
        }
        System.out.println(Ui.LINE);
    }

    public void loadError() {
        System.out.println("Task file does not exist");
    }

    public void printError(String e) {
        System.out.println(Ui.LINE + e);
        System.out.println(Ui.LINE);
    }

}
