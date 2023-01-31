package duke;

import duke.tasks.Task;

import java.util.Scanner;

public class Ui {
    public Ui() {
    }

    public String readCommand() {
        Scanner bucky = new Scanner(System. in);
        return bucky.nextLine();
    }

    public void showWelcome() {
        System.out.println("Hello! I'm Duke.Duke\nWhat can I do for you?");
    }

    public void showLine() {
        System.out.println("_______________________");
    }

    public void showError(Exception e) {
        System.out.println(e);
    }

    public void showLoadingError() { // ADD STUFF
        System.out.println("File not found");
    }

    public void showAdded(TaskList l) {
        System.out.println("Got it. I've added this task:\n" + l.get(l.size()-1)
                + "\nNow you have " + l.size() + " tasks in the list.");
    }

    public void showDelete(Task t, TaskList l) {
        System.out.println("Noted. I've removed this task:\n" + t +
                "\nNow you have " + l.size() + " tasks in the list.");
    }

    public void showExit() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void showList(TaskList l) {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0 ; i < l.size() ; i++) {
            System.out.println((i+1) + ". " + l.get(i));
        }
    }

    public void showMark(int mark, Task t) {
        if (mark == 1) {
            System.out.println("Nice! I've marked this task as done:\n" + t);
        } else if (mark == 0) {
            System.out.println("OK, I've marked this task as not done yet:\n" + t);
        }
    }

}
