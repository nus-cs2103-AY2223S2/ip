package duke;

import duke.tasks.Task;

public class Ui {
    public Ui() {
    }

    public String showWelcome() {
        return "Hello! I'm Duke.\nWhat can I do for you?";
    }

    public String showWelcome(Exception e) {
        return e.toString() +
                "\nCreated new empty File.\n\nHello! I'm Duke.\nWhat can I do for you?";
    }

    public String showError(Exception e) {
        return e.toString();
    }

    public String showAdded(TaskList l) {
        return "Got it. I've added this task:\n" + l.get(l.size()-1)
                + "\nNow you have " + l.size() + " tasks in the list.";
    }

    public String showDelete(Task t, TaskList l) {
        return "Noted. I've removed this task:\n" + t +
                "\nNow you have " + l.size() + " tasks in the list.";
    }

    public String showExit() {
        return "Bye. Hope to see you again soon!";
    }

    public String showList(TaskList l) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the tasks in your list:\n");
        for (int i = 0 ; i < l.size() ; i++) {
            s.append(i + 1).append(". ").append(l.get(i)).append("\n");
        }
        return s.toString();
    }

    public String showMatchingList(TaskList l) {
        StringBuilder s = new StringBuilder();
        s.append("Here are the matching tasks in your list:\n");
        for (int i = 0 ; i < l.size() ; i++) {
            s.append(i + 1).append(". ").append(l.get(i)).append("\n");
        }
        return s.toString();
    }

    public String showMark(int mark, Task t) {
        return mark == 1 ?
                "Nice! I've marked this task as done:\n" + t :
                "OK, I've marked this task as not done yet:\n" + t;
    }

}
