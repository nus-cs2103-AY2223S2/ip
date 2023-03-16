package brotherbot.commands;

import brotherbot.ui.Ui;
import brotherbot.storage.Deadline;
import brotherbot.storage.Event;
import brotherbot.storage.Task;
import brotherbot.storage.TaskList;
import brotherbot.storage.Todo;

import java.time.DateTimeException;



public class AddTaskCommand extends Command {

    /**
     * Constructor to create an AddTaskCommand object.
     *
     * @param input Input string required for command execution.
     */
    public AddTaskCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     * @param ui Ui object required for command execution.
     */
    public void execute(TaskList storage, Ui ui) {
        if (super.input.substring(0, 4).equalsIgnoreCase("todo")) {
            storage.add(new Todo(input.substring(5)));
        } else if (super.input.substring(0, 5).equalsIgnoreCase("event")) {
            int startIndex = input.indexOf("/from ");
            int x = input.indexOf("/to ");
            int endIndex = x + 4;
            String description = input.substring(6, startIndex - 1);
            String start = input.substring(startIndex + 6, x - 1);
            String end = input.substring(endIndex);
            try {
                storage.add(new Event(description, start, end));
            } catch (DateTimeException e) {
                ui.toUser("Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm");
            }
        } else if (super.input.substring(0, 8).equalsIgnoreCase("deadline")) {
            int startIndex = input.indexOf("/by "); // exception
            String description = input.substring(9, startIndex - 1);
            String deadline = input.substring(startIndex + 4);
            try {
                storage.add(new Deadline(description, deadline));
            } catch (DateTimeException e) {
                ui.toUser("Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm");
            }
        } else {
            storage.add(new Task(input));
            int x = storage.size();
            ui.toUser("added to list my brother: \n" + x + "." + storage.get(x - 1).toString() + "\nNow you have " + x + " tasks!");
        }

    }
    }