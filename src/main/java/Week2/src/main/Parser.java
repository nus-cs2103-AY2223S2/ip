package Week2.src.main;
import java.io.IOException;

import Commands.*;
import Exceptions.UnknownCommandException;

/**
 * Deals with making sense of the user command
 */
public class Parser {
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
     * Uses input from user and execute it.
     * Returns a bye message when user enters bye.
     * @param input
     * @return the message to show
     * @throws IOException
     */
    public String runParser(String input) throws IOException, UnknownCommandException {
        isBegin = true;
        String c = input;
        String dup = "Same task already exists in the list.";
        Detect dt = new Detect(this.tasklist);
        try {
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
                if (dt.isDuplicate(doit)) {
                    return dup;
                } else {
                    return Todo.execute(doit, tasklist);
                }

            } else if (c.startsWith("deadline")) {
                String doit = c.substring(9);
                String[] contexts = doit.split("/by");
                if (dt.isDuplicate(contexts[0])) {
                    return dup;
                } else {
                    return Deadline.execute(doit, tasklist);
                }

            } else if (c.startsWith("event")) {
                String doit = c.substring(6);
                String[] froms = doit.split("/from");
                if (dt.isDuplicate(froms[0])) {
                    return dup;
                } else {
                    return Event.execute(doit, tasklist);
                }

            } else if (c.startsWith("find")) {
                String keyword = c.substring(6);
                Find sr = new Find(tasklist);
                return Find.search(keyword);

            } else if (!c.equals("bye")) {
                throw new UnknownCommandException();

            } else {
                Bada.isBye = true;
                return ui.bye();
            }
        } catch(UnknownCommandException e) {
            return "OOPS!!! I'm sorry, but I don't know what that means";
        }
        }
    }
