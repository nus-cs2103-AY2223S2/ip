package Commands;
import Week2.src.main.TaskList;

public class Delete {
    public static String perform(String c, TaskList tasklist) {
        String str = c.substring(c.length() - 1);
        int marking = Integer.parseInt(str);
        Task current = (Task) tasklist.get(marking - 1);
        tasklist.remove(marking - 1);
        String str1 = "Noted. I've removed this task:";
        String str2 = current.toString();
        String str3 = "Now you have " + tasklist.length() + " tasks in the list";
        return str1 + "\n" + str2 + "\n" + str3;
    }
}
