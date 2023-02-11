package Commands;
import Week2.src.main.TaskList;

/**
 * Calss to deals with list command
 */
public class List {

    /**
     * Returns a list of tasks in the task list.
     * @param tasklist current task list
     * @return list in String
     */
    public static String perform(TaskList tasklist) {
        String rstr = "Here are the tasks in your list:";
        for (int i = 0; i < tasklist.length(); i++) {
            Task current = (Task) tasklist.get(i);
            int curnum = i + 1;
            String liststr = curnum + "." + current.toString();
            rstr = rstr + "\n" + liststr;
        }
        return rstr;
    }
}
