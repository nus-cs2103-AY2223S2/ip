package Commands;
import Week2.src.main.Bada;
import Week2.src.main.TaskList;

import java.io.IOException;

/**
 * Deals with unmark command from user
 */
public class Unmark {
    /**
     * Unmarks the task as not done and shows it to the user.
     * @param c User input line
     * @param tasklist Current task list
     * @return Output line to user
     * @throws IOException to write on
     */
    public static String perform(String c, TaskList tasklist) throws IOException {
        String str = c.substring(c.length() - 1);
        int marking = Integer.parseInt(str);
        Task current = (Task) tasklist.get(marking - 1);
        current.setNotDone();

        String str1 = "OK, I've marked this task as not done yet:";
        String str2 = "[ ]" + current.getContent();
        Bada.writeOn(current);

        return str1 + "\n" + str2;
    }
}
