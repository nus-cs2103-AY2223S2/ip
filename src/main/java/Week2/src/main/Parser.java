package Week2.src.main;
import java.io.IOException;
import java.util.Scanner;
import Commands.*;

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
     * It receives a TaskList from Duke
     */
    public Parser(TaskList tasklist) {
        this.tasklist = tasklist;
    }

    /**
     * Uses input from user and execute it
     * @param input
     * @return the message to show
     * @throws IOException
     */
    public String runParser(String input) throws IOException {
        isBegin = true;
        String c = input;
        Detect dt = new Detect(this.tasklist);
        if (c.equals("list")) {
            return List.perform(tasklist);

        } else if (c.startsWith("mark")) {
            return Mark.perform(c, tasklist);

        } else if (c.startsWith("unmark")) {
            return Unmark.perform(c, tasklist);

        } else if (c.startsWith("delete")) {
            return Delete.perform(c, tasklist);

        } else if (c.startsWith("todo")) {
            String doit = c.substring(5);
            if(dt.isDuplicate(doit)) {
                return "Same task already exists in the list.";
            } else {
                return Todo.execute(doit, tasklist);
            }

        } else if (c.startsWith("deadline")) {
            String doit = c.substring(9);
            if(dt.isDuplicate(doit)) {
                return "Same task already exists in the list.";
            } else {
                return Deadline.execute(doit, tasklist);
            }

        } else if (c.startsWith("event")) {
            String doit = c.substring(6);
            String[] froms = doit.split("/from");
            if(dt.isDuplicate(froms[0])) {
                return "Same task already exists in the list.";
            } else {
                return Event.execute(doit, tasklist);
            }

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
}
