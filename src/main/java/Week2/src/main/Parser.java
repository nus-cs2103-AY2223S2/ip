package Week2.src.main;
import java.io.IOException;
import java.util.Scanner;

/**
 * Deals with making sense of the user command
 */
public class Parser {
    Scanner sc = new Scanner(System.in);

    private TaskList tasklist;
    private static boolean isBegin = false;

    Ui ui = new Ui();

    /**
     * Parser constructor
     */
    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    public String runParser(String input) throws IOException {
        isBegin = true;
        String c = input;
        if (c.equals("list")) {
            String rstr = "Here are the tasks in your list:";
            for (int i = 0; i < tasklist.size(); i++) {
                Task current = (Task) tasklist.get(i);
                int curnum = i + 1;
                String liststr = curnum + "." + current.toString();
                rstr = rstr + "\n" + liststr;
            }
            return rstr;
        } else if (c.startsWith("mark")) {
            String str = c.substring(c.length() - 1);
            int marking = Integer.parseInt(str);
            Task current = (Task) tasklist.get(marking - 1);
            current.setDone();
            String str1 = "Nice! I've marked this task as done:";
            String str2 = "[X] " + current.content;
            Duke.writeOn(current);
            return str1 + "\n" + str2;
        } else if (c.startsWith("unmark")) {
            String str = c.substring(c.length() - 1);
            int marking = Integer.parseInt(str);
            Task current = (Task) tasklist.get(marking - 1);
            current.setNotDone();
            String str1 = "OK, I've marked this task as not done yet:";
            String str2 = "[ ]" + current.content;
            Duke.writeOn(current);
            return str1 + "\n" + str2;
        } else if (c.startsWith("delete")) {
            String str = c.substring(c.length() - 1);
            int marking = Integer.parseInt(str);
            Task current = (Task) tasklist.get(marking - 1);
            tasklist.remove(marking - 1);
            String str1 = "Noted. I've removed this task:";
            String str2 = current.toString();
            String str3 = "Now you have " + tasklist.size() + " tasks in the list";
            return str1 + "\n" + str2 + "\n" + str3;
        } else if (c.startsWith("todo")) {
            String doit = c.substring(5, c.length());
            Task current = new Todo(doit);
            tasklist.add(current);
            String str1 = "Got it. I've added this task:";
            String str2 = current.toString();
            String str3 = "Now you have " + tasklist.size() + " tasks in the list";
            Duke.writeOn(current);
            return str1 + "\n" + str2 + "\n" + str3;
        } else if (c.startsWith("deadline")) {
            String doit = c.substring(9, c.length());
            String[] parts = doit.split("/by");
            Task current = new Deadline(parts[0], parts[1]);
            tasklist.add(current);
            String str1 = "Got it. I've added this task:";
            String str2 = current.toString();
            String str3 = "Now you have " + tasklist.size() + " tasks in the list";
            Duke.writeOn(current);
            return str1 + "\n" + str2 + "\n" + str3;
        } else if (c.startsWith("event")) {
            String doit = c.substring(6);
            String[] froms = doit.split("/from");
            String[] fromses = froms[1].split("/to");
            String[] tos = doit.split("/to");
            Task current = new Event(froms[0], fromses[0], tos[1]);
            tasklist.add(current);
            String str1 = "Got it. I've added this task:";
            String str2 = current.toString();
            Duke.writeOn(current);
            return str1 + "\n" + str2;
        } else if (c.startsWith("find")) {
            String keyword = c.substring(6);
            Search sr = new Search(tasklist);
            return Search.find(keyword);
        } else if (!c.equals("bye")) {
            return "OOPS!!! I'm sorry, but I don't know what that means :-(";
        } else {
            Duke.isBye = true;
            return ui.bye();
        }

    }

    public void updateTL() {
        Duke.tasklist = this.tasklist;
    }

}
