package bob;

import java.util.ArrayList;

public class Ui {
    private String wrapper;

    public Ui(String deco, int length) {
        this.wrapper = deco.repeat(length);
    }

    private String getTaskDescription(Task t) {
        assert t != null;
        return String.format("[%s][%s] %s", t.getTaskType(), t.getStatusIcon(), t);
    }

    private String wrapString(String s) {
        return String.format("%s\n%s\n%s\n", wrapper, s, wrapper);
    }

    public String printList(ArrayList<Task> list) {
        assert list != null;
        if (list.size() == 0) {
            return "No tasks currently!";
        }

        StringBuilder string = new StringBuilder();
        // Iterate through list items sequentially
        for (int i = 0, n = list.size(); i < n; i++) {
            Task t = list.get(i);
            String s = String.format("%d. %s", i + 1, getTaskDescription(t));
            string.append(s + "\n");
        }
        return string.toString();
    }

    public String errorPrint(BobException e) {
        assert e != null;
        return wrapString("Sorry! An error has occurred :(") + e.getMessage();
    }

    public String printFilteredTasks(ArrayList<Task> list) {
        assert list != null;
        return wrapString("Matching tasks:") + printList(list);
    }

    public String printTasks(ArrayList<Task> list) {
        assert list != null;
        return wrapString("Current task list: ") + printList(list);
    }

    public String printIntroduction() {
        return "Hi, my name is Bob :)\n"
                + "How may I help you?";
    }

    public String printTaskAdded(Task t) {
        assert t != null;
        return wrapString("Successfully added a new task :)")
                + getTaskDescription(t);
    }

    public String printMarkTask(Task t) {
        assert t != null;
        return wrapString("Successfully marked a task :)")
                + getTaskDescription(t);
    }

    public String printUnmarkTask(Task t) {
        assert t != null;
        return wrapString("Successfully unmarked a task :)")
                + getTaskDescription(t);
    }

    public String printDeleteTask(Task t) {
        assert t != null;
        return wrapString("Successfully deleted a task :)")
                + getTaskDescription(t);
    }
}
