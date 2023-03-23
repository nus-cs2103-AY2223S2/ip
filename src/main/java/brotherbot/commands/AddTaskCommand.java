package brotherbot.commands;

import brotherbot.storage.*;

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
     */
    public String execute(TaskList storage) {
        String output;
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
                output =  "Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm";
                return output;
            }
        } else if (super.input.substring(0, 8).equalsIgnoreCase("deadline")) {
            int startIndex = input.indexOf("/by "); // exception
            String description = input.substring(9, startIndex - 1);
            String deadline = input.substring(startIndex + 4);
            try {
                storage.add(new Deadline(description, deadline));
            } catch (DateTimeException e) {
                output = "Invalid Date and Time input brother. Here's the correct format:\ndd/MM/yyyy HHmm";
                return output;
            }
        } else {
            storage.add(new Task(input));

        }
        int x = storage.size();
        output = "added to list my brother: \n" + x + "." + storage.get(x - 1).toString() + "\nNow you have " + x + " tasks!";
        return output;

    }
}