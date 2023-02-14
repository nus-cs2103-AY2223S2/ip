package Commands;
import Week2.src.main.Duke;
import Week2.src.main.TaskList;

import java.io.IOException;

/**
 * Deals with the mark command
 */
public class Mark {
    /**
     * It marks the task as done ("X")
     * and shows it to the user.
     * @param c User input line
     * @param tasklist Current task list
     * @return Output line for user
     * @throws IOException to write on
     */
    public static String perform(String c, TaskList tasklist) throws IOException {
        String str = c.substring(c.length() - 1);
        int marking = Integer.parseInt(str);
        Task current = (Task) tasklist.get(marking - 1);
        current.setDone();

        String str1 = "Nice! I've marked this task as done:";
        String str2 = "[X] " + current.getContent();
        Duke.writeOn(current);

        return str1 + "\n" + str2;
    }
}
