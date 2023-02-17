package Commands;
import Week2.src.main.TaskList;

import java.util.NoSuchElementException;

/**
 * Deletes a task that user wishes to.
 */
public class Delete {
    /**
     * Deletes the task at the index from user input.
     * @param c User command line
     * @param tasklist Currnet task list
     * @return Output line to show
     */
    public static String perform(String c, TaskList tasklist) {
        String str = c.substring(c.length() - 1);
        int marking = Integer.parseInt(str) - 1;
        Task current = (Task) tasklist.get(marking);
        if (marking >= tasklist.length()) {
            return "The task doesn't exist!";
        }
        tasklist.removeT(marking);

        String str1 = "Noted. I've removed this task:";
        String str2 = current.toString();
        String str3 = "Now you have " + tasklist.length() + " tasks in the list";

        return str1 + "\n" + str2 + "\n" + str3;
    }
}
