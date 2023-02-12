package duke;

import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> dukeList;

    public TaskList(ArrayList<Task> dukeList) {
        this.dukeList = dukeList;
    }

    public TaskList() {
        dukeList = new ArrayList<Task>();
    }

    public ArrayList<Task> getDukeList() {
        return dukeList;
    }

    public void addTask(Task t) {
        dukeList.add(t);
        System.out.println("Got it. I've added this task:\n  "
                + t.toString() + "\nNow you have " + dukeList.size()
                + " tasks in the list.");
    }

    /**
     * The method marks or unmarks a task in the task list based on the input given by the user.
     * @param n the task number that the user wants to mark/unmark.
     *
     * @param isDone the boolean that decides whether to mark or unmark.
     */
    public void mark(int n, boolean isDone) {
        Task t = dukeList.get(n);
        if (isDone) {
            t.markAsDone();
            System.out.println("Nice! I've marked this task as done:\n"
                    + "  [" + t.getStatusIcon() + "] " + t.description);
        } else {
            t.unMark();
            System.out.println("OK, I've marked this task as not done yet:\n"
                    + "  [" + t.getStatusIcon() + "] " + t.description);
        }
    }

    public void delete(int n) {
        Task t = dukeList.get(n);
        dukeList.remove(n);
        System.out.println("Noted. I've removed this task:\n  "
                + t.toString()
                + "\nNow you have " + dukeList.size()
                + " tasks in the list.");
    }

    /**
     * The method prints out all the tasks in the list.
     */
    public void recite() {
        if (dukeList.size() == 0) {
            System.out.println("Your list is empty");
        } else {
            System.out.println("Here are the tasks in you list");

            for (int i = 1; i <= dukeList.size(); i++) {
                Task t = dukeList.get(i - 1);
                System.out.println("" + i + ". " + t.toString());
            }
        }
    }
}
