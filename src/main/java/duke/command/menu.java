package duke.command;

import duke.task.Deadlines;
import duke.task.Events;
import duke.task.ToDo;
import duke.utilities.Parser;
import java.util.Locale;

public class menu {
    static TaskList manager = new TaskList("/Users/s.f/ip/src/Data/duke.txt");

    /**
     * In out string.
     *
     * @param input the input
     * @return the string
     */
    public static String In_Out(String input) {
        String[] tokens = input.split(" ");
        String without_key = input.replace(tokens[0], "");
        switch (tokens[0].toLowerCase(Locale.ROOT)) {
            case "bye":
                manager.file_writeAll();
                return Parser.BYE_MESSAGE;

            case "list":
                return manager.gui_displayAll() + "\n";

            case "mark":
                return manager.gui_mark(Integer.parseInt(tokens[1]) - 1);

            case "unmark":
                return manager.gui_unmark(Integer.parseInt(tokens[1]) - 1);

            case "todo":
                ToDo todo = new ToDo(without_key, false);
                return manager.gui_add(todo);

            case "deadline":
                Deadlines deadlines = new Deadlines(without_key, false);
                return manager.gui_add(deadlines);


            case "event":
                Events events = new Events(without_key, false);
                return manager.gui_add(events);


            case "delete":
                return manager.gui_delete(Integer.parseInt(tokens[1]) - 1);

            case "find":
                return manager.gui_find(without_key);
            default:
                return Parser.WRONG_INPUT;
        }
    }
}
