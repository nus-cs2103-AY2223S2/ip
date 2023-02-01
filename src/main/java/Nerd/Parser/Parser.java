package Nerd.Parser;

import Nerd.Commands.*;
import Nerd.entities.Deadline;
import Nerd.entities.Event;
import Nerd.entities.Todo;
import Nerd.enums.CommandEnums;
import Nerd.exceptions.NerdException;
import Nerd.entities.TaskList;

/**
 * Represents the Parser of the Chat bot that parses the commands.
 */
public class Parser {

    /**
     * Parses the input string into commands.
     *
     * @param input The full line of the command and arguments.
     * @return The command of the input.
     * @throws NerdException            if the input contains empty descriptions
     * @throws IllegalArgumentException if the input contains an invalid command or arguments
     */
    public Command parseCommand(String input) throws NerdException, IllegalArgumentException {
        String[] split = input.split(" ");
        CommandEnums type;
        try {
            type = CommandEnums.valueOf(split[0].toUpperCase().strip());
        } catch (IllegalArgumentException e) {
            System.out.println("Sorry! I have no idea what that means ??? >:c");
            return null;
        }
        switch (type) {
        case LIST:
            return new ListCommand();
        case MARK:
            if (!isEmptyCommand(split)) {
                return new MarkCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case UNMARK:
            if (!isEmptyCommand(split)) {
                return new UnmarkCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case TODO:
            if (!isEmptyCommand(split)) {
                return new TodoCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case DEADLINE:
            if (!isEmptyCommand(split)) {
                return new DeadlineCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case EVENT:
            if (!isEmptyCommand(split)) {
                return new EventCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case DELETE:
            if (!isEmptyCommand(split)) {
                return new DeleteCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case DATE:
            if (!isEmptyCommand(split)) {
                return new SearchDateCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case FIND:
            if (!isEmptyCommand(split)) {
                return new FindCommand();
            } else {
                throw new NerdException("Sorry! you can't have empty descriptions!");
            }
        case BYE:
            return new ExitCommand();
        default:
            throw new NerdException("Sorry! I have no idea what that means ??? >:c");
        }
    }

    /**
     * Parses the input string taken from the text file into Tasks in the TaskList.
     *
     * @param input The full line of the command and arguments.
     * @param list  The TaskList of the Chat bot.
     * @return A boolean representing the success of the parsing.
     */
    public boolean parseText(String input, TaskList list) {
        String[] split = input.split(" \\| ");
        switch (split[0]) {
        case ("T"):
            Todo todo = new Todo(split[2]);
            if (split[1].equals("1")) {
                todo.setDone();
            } else {
                todo.setUndone();
            }
            list.addTask(todo);
            return true;
        case ("D"):
            Deadline deadline = new Deadline(split[2], split[3]);
            if (split[1].equals("1")) {
                deadline.setDone();
            } else {
                deadline.setUndone();
            }
            list.addTask(deadline);
            return true;
        case ("E"):
            Event event = new Event(split[2], split[3], split[4]);
            if (split[1].equals("1")) {
                event.setDone();
            } else {
                event.setUndone();
            }
            list.addTask(event);
            return true;
        default:
            return false;
        }
    }

    /**
     * Parses the input string into descriptions.
     *
     * @param input The full line of the command and arguments.
     * @return the string representation of the description.
     * @throws NerdException if the input is empty.
     */
    public String parseDescription(String input) throws NerdException {
        String[] split = input.split(" ");
        if (!isEmptyCommand(split)) {
            return split[1];
        } else {
            throw new NerdException("Sorry! you can't have empty descriptions!");
        }
    }

    /**
     * Parses the input string into indices.
     *
     * @param input The full line of the command and arguments.
     * @return The index.
     * @throws NerdException if the input is empty.
     */
    public int parseIndex(String input) throws NerdException {
        String[] split = input.split(" ");
        return Integer.parseInt(split[1]) - 1;
    }

    /**
     * Parses the input string for deadline tasks.
     *
     * @param input The full line of the command and arguments.
     * @return The date of the deadline.
     */
    public String parseDeadline(String input) {
        String[] split = input.split("/by");
        return split[1];
    }

    /**
     * Parses the input string for Event tasks.
     *
     * @param input The full line of the command and arguments.
     * @return A string array containing start and end dates.
     */
    public String[] parseEvent(String input) {
        String[] split = input.split("/from");
        split = split[1].split("/to");
        String[] res = {split[0], split[1]};
        return res;
    }

    public boolean isEmptyCommand(String[] input) {
        if(input.length < 2) {
            return true;
        } else {
            return false;
        }
    }


}
