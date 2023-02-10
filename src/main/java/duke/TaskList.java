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

    public void find(String keyword) {
        System.out.println("Here are the matching tasks in your list:");
        for (int i = 1; i <= dukeList.size(); i++) {
            Task t = dukeList.get(i - 1);
            String[] s = t.description.split(" ");
            for (int j = 0; j < s.length; j++) {
                if (s[j].equals(keyword)) {
                    System.out.println("" + i + ". " + t.toString());
                    break;
                }
            }
        }
    }
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
