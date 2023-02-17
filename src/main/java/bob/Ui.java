package bob;

import java.util.ArrayList;

/**
 * Ui is a utility class with methods to return decorated text
 */
public class Ui {
    private String wrapper;

    /**
     * Initialize an instance of Ui with a wrapper string made of character repeating length times
     * @param character Character to repeat
     * @param length Number of times to repeat the character
     */
    public Ui(String character, int length) {
        this.wrapper = character.repeat(length);
    }

    private String getTaskDescription(Task t) {
        assert t != null;
        return String.format("[%s][%s] %s", t.getTaskType(), t.getStatusIcon(), t);
    }

    private String wrapString(String s) {
        return String.format("%s\n%s\n%s\n", wrapper, s, wrapper);
    }

    /**
     * Returns a string that represents a task list
     * @param list Given task list to output
     * @return String representation
     */
    private String printList(ArrayList<Task> list) {
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
        // No matching tasks found
        if (list.size() == 0) {
            return wrapString("No matching tasks found!");
        }

        return wrapString("Matching tasks:") + printList(list);
    }

    public String printTasks(ArrayList<Task> list) {
        assert list != null;
        return wrapString("Current task list: ") + printList(list);
    }

    /**
     * Return list of reminders for printing,
     * highlighting those which are overdue
     * @param reminders ArrayList of Deadline objects
     * @return Multi-line string for printing
     */
    public String printReminders(ArrayList<Deadline> reminders) {
        assert reminders != null;
        // No deadline tasks found
        if (reminders.size() == 0) {
            return wrapString("No deadlines ");
        }

        StringBuilder string = new StringBuilder();
        // Iterate through list items sequentially
        for (int i = 0, n = reminders.size(); i < n; i++) {
            Deadline d = reminders.get(i);
            String s = String.format("%d. %s | %s", i + 1, d.description, d.deadline);
            string.append(s);
            if (d.isOverdue()) {
                string.append(" (overdue!) ");
            }
            string.append("\n");
        }

        return wrapString("Reminders on your deadlines: ") + string;
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
