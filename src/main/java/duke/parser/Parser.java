package duke.parser;

import duke.commands.Command;
import duke.commands.CreateDeadline;
import duke.commands.CreateEvent;
import duke.commands.CreateTodo;
import duke.commands.DeleteTask;
import duke.commands.Exit;
import duke.commands.ListTasks;
import duke.commands.MarkTask;
import duke.commands.UnmarkTask;
import duke.exceptions.DukeException;


public class Parser {
    public Command parse(String input) throws DukeException {
        String[] tokens = input.split(" ");
        String command = tokens[0];
        String args = input.substring(command.length()).trim();
        switch (command) {
            case "bye":
                return new Exit();    
            case "todo":
                return prepareCreateTodo(args);  
            case "deadline":
                return prepareCreateDeadline(args);
            case "event":
                return prepareCreateEvent(args);
            case "list":
                return new ListTasks(); 
            case "mark":
                return prepareMarkTask(args);
            case "unmark":
                return prepareUnmarkTask(args);
            case "delete":
                return prepareDeleteTask(args);
            default:
                throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
    }

    private Command prepareCreateTodo(String input) throws DukeException {
        if (input.isEmpty())
            throw new DukeException("The description cannot be empty.");
        return new CreateTodo(input);
    }

    private Command prepareCreateDeadline(String input) throws DukeException {
        if (input.isEmpty())
            throw new DukeException("The description cannot be empty.");
        String[] tokens = input.split("/by");
        if (tokens.length != 2)
            throw new DukeException("The deadline must be specified.");
        String description = tokens[0].trim();
        String deadline = tokens[1].trim();
        return new CreateDeadline(description, deadline);
    }

    private Command prepareCreateEvent(String input) throws DukeException {
        if (input.isEmpty())
            throw new DukeException("The description cannot be empty.");
        String[] tokens = input.split("/from");
        if (tokens.length != 2)
            throw new DukeException("The event must be specified.");
        String description = tokens[0].trim();
        String[] tokens2 = tokens[1].split("/to");
        if (tokens2.length != 2)
            throw new DukeException("The event must be specified.");
        String from = tokens2[0].trim();
        String to = tokens2[1].trim();
        return new CreateEvent(description, from, to);
    }



    private Command prepareMarkTask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            return new MarkTask(index);
        } catch (NumberFormatException e) {
            throw new DukeException("The task id must be a number.");
        }
    }

    private Command prepareUnmarkTask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            return new UnmarkTask(index);
        } catch (NumberFormatException e) {
            throw new DukeException("The task id must be a number.");
        }
    }

    private Command prepareDeleteTask(String input) throws DukeException {
        try {
            int index = Integer.parseInt(input);
            return new DeleteTask(index);
        } catch (NumberFormatException e) {
            throw new DukeException("The task id must be a number.");
        }
    }
}
