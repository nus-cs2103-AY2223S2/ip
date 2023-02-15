package features;

import commands.CommandBye;
import commands.CommandDeadline;
import commands.CommandDelete;
import commands.CommandEvent;
import commands.CommandFact;
import commands.CommandFind;
import commands.CommandList;
import commands.CommandMark;
import commands.CommandToDo;
import commands.CommandUnmark;

/**
 * Handles user input commands.
 */
public class Parser {
    /**
     * Calls the Command type suitable for the user input.
     * @param parseInput The user input sent into the Parser.
     * @return The message corresponding to the user command.
     * @throws DukeException Thrown by the individual switch cases.
     */
    public String parse(String parseInput) throws DukeException {
        // switch case for future commands
        String[] inputs = parseInput.split(" ", 2);
        if (inputs[0].length() == 0) {
            throw new DukeException("Uh, where's your input?\nYou can't just say nothing at all.");
        }
        switch (inputs[0]) {
        case ("bye"):
            return new CommandBye().handle(inputs);

        // Duke prints a random fun fact when input is "fact"
        case ("fact"):
            return new CommandFact().handle(inputs);

        // Duke lists out all Tasks.Task names in TaskList when input is "list"
        case ("list"):
            return new CommandList().handle(inputs);

        // Duke allows user to mark tasks as done when input is "mark"
        case ("mark"):
            return new CommandMark().handle(inputs);

        // Duke finds and prints tasks that match user search input
        case ("find"):
            return new CommandFind().handle(inputs);

        // Duke allows user to mark tasks as NOT done when input is "unmark"
        case ("unmark"):
            return new CommandUnmark().handle(inputs);

        // Duke deletes task when input is "delete"
        case ("delete"):
            return new CommandDelete().handle(inputs);

        // Duke adds Tasks.Deadline
        case ("deadline"):
            return new CommandDeadline().handle(inputs);

        // Duke adds Tasks.Event
        case ("event"):
            return new CommandEvent().handle(inputs);

        // Duke adds Tasks.ToDo
        case ("todo"):
            return new CommandToDo().handle(inputs);

        // Duke does not understand any other commands (yet).
        default:
            return ("Yeah, i'm sorry. I don't understand that.");
        }
    }

}
