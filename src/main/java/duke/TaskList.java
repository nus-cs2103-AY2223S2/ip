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

    public String addTask(Task t) {
        dukeList.add(t);
        return "Got it. I've added this task:\n  "
                + t.toString() + "\nNow you have " + dukeList.size()
                + " tasks in the list.";
    }

    /**
     * The method marks or unmarks a task in the task list based on the input given by the user.
     * @param n the task number that the user wants to mark/unmark.
     *
     * @param isDone the boolean that decides whether to mark or unmark.
     * @return
     */
    public String mark(int n, boolean isDone) throws IndexOutOfBoundsException {
        try {
            Task t = dukeList.get(n);
            if (isDone) {
                t.markAsDone();
                return "Nice! I've marked this task as done:\n"
                        + "  [" + t.getStatusIcon() + "] " + t.description;
            } else {
                t.unMark();
                return "OK, I've marked this task as not done yet:\n"
                        + "  [" + t.getStatusIcon() + "] " + t.description;
            }
        } catch (IndexOutOfBoundsException e) {
            return "The index you mentioned does not exist";
        }
    }

    public String delete(int n) {
        try {
            Task t = dukeList.get(n);
            dukeList.remove(n);
            return "Noted. I've removed this task:\n  "
                    + t.toString()
                    + "\nNow you have " + dukeList.size()
                    + " tasks in the list.";
        } catch (IndexOutOfBoundsException e){
            return "The index you mentioned does not exist";
        }
    }

    /**
     * The method prints out all the tasks in the list.
     */
    public String find(String keyword) {
        String str = "Here are the matching tasks in your list:";
        for (int i = 1; i <= dukeList.size(); i++) {
            Task t = dukeList.get(i - 1);
            String[] s = t.description.split(" ");
            for (int j = 0; j < s.length; j++) {
                if (s[j].equals(keyword)) {
                    str += "\n" + i + ". " + t.toString();
                    break;
                }
            }
        }
        return str;
    }

    public String recite() {
        if (dukeList.size() == 0) {
            return "Your list is empty";
        } else {
            String str = "Here are the tasks in you list";
            for (int i = 1; i <= dukeList.size(); i++) {
                Task t = dukeList.get(i - 1);
                str += "\n" + i + ". " + t.toString();
            }
            return str;
        }
    }

}
