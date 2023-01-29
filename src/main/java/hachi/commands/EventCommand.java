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

    /**
     * EventCommand constructor.
     *
     * @param input The user's input string.
     */
    public EventCommand(String input) {
        this.input = input;
    }

    public boolean execute(TaskList toDoList, Ui ui, Storage storage) {
        try {
            int index_e1 = input.indexOf("/");
            int index_e2 = input.lastIndexOf("/");
            if (input.length() > 5 && !input.contains("/")) {
                throw new HachiExceptions("☹ Ohno! seems like you forgot to put the time of the event ");
            }
            if (!input.substring(index_e1 + 1, input.length()).contains("/")) {
                throw new HachiExceptions("☹ Ohno! seems like you forgot to put the ending time of the event ");
            }
            if (input.length() <= 5) {
                throw new HachiExceptions("☹ Ohno! The description cannot be empty.");
            }
            Event eventTask = new Event(input.substring(6, index_e1 - 1),
                    input.substring(index_e1 + 6, index_e2 - 1),
                    input.substring(index_e2 + 4, input.length()));
            System.out.println("   okie dokie. I've added this task:" + "\n" + eventTask);
            toDoList.add(eventTask);
            storage.saveTaskList(toDoList);
            System.out.println("   Now you have " + toDoList.size() + " tasks in the list.");
        } catch (HachiExceptions e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}
