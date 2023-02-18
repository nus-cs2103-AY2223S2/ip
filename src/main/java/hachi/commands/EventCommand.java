package hachi.commands;

import hachi.main.HachiExceptions;
import hachi.main.Storage;
import hachi.main.TaskList;
import hachi.main.Ui;
import hachi.tasks.Event;

/**
 * Encapsulates a user instruction to add an event with starting and ending date to the list.
 */
public class EventCommand extends Command {
    private String input;
    static String separator = "‿୨♡୧‿‿‿‿୨♡୧‿‿‿‿୨♡୧‿";

    /**
     * EventCommand constructor.
     *
     * @param input The user's input string.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    public String execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            int index_e1 = input.indexOf("/");
            int index_e2 = input.lastIndexOf("/");
            if (input.length() > 5 && !input.contains("/")) {
                throw new HachiExceptions(ui.noStartingTime());
            }
            if (!input.substring(index_e1 + 1, input.length()).contains("/")) {
                throw new HachiExceptions(ui.noEndingTime());
            }
            if (input.length() <= 5) {
                throw new HachiExceptions(ui.emptyDescription());
            }
            Event eventTask = new Event(input.substring(6, index_e1 - 1),
                    input.substring(index_e1 + 6, index_e2 - 1),
                    input.substring(index_e2 + 4, input.length()));
            toDoList.add(eventTask);
            return separator + "\n" + "\n" + "   okie dokie. I've added this task:" + "\n" + eventTask +
                    "   Now you have " + toDoList.size() + " tasks in the list.";
        } catch (HachiExceptions e) {
            return e.getMessage();
        }
    }
}
