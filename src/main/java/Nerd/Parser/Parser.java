package Nerd.Parser;

import Nerd.Commands.*;
import Nerd.Nerd;
import Nerd.entities.Deadline;
import Nerd.entities.Event;
import Nerd.entities.Todo;
import Nerd.enums.CommandEnums;
import Nerd.exceptions.NerdException;
import Nerd.entities.TaskList;
import Nerd.entities.Task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Represents the Parser of the Chat bot that parses the commands.
 */
public class Parser {

    /**
     * Parses the input string into commands to be processed subsequently.
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
            throw new IllegalArgumentException("Sorry! I have no idea what that means ??? >:c");
        }

        if (isEmptyCommand(split)) {
            throw new NerdException("According to my calculations, there are empty inputs!\n" +
                    "Please follow the valid command list above :D");
        }
        switch (type) {
        case LIST:
            return new ListCommand();
        case MARK:
            return new MarkCommand(parseIndex(input));
        case UNMARK:
            return new UnmarkCommand(parseIndex(input));
        case TODO:
            return new TodoCommand(parseDescription(input));
        case DEADLINE:
            String[] parsedDeadline = parseDeadline(input);
            return new DeadlineCommand(parsedDeadline[0], parsedDeadline[1]);
        case EVENT:
            String[] eventList = parseEvent(input);
            return new EventCommand(eventList[0], eventList[1], eventList[2]);
        case DELETE:
            return new DeleteCommand(parseIndex(input));
        case DATE:
            return new SearchDateCommand(parseDescription(input));
        case FIND:
            return new FindCommand(parseDescription(input), this);
        case REMINDER:
            return new ReminderCommand(parseIndex(input));
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
     * Searches the given tasklist for any matching descriptions.
     *
     * @param list The current Tasklist of the nerdbot.
     * @param description The description of the task to search for.
     * @return The string representation of the task.
     */
    public String searchDescription(TaskList list, String description) {
        String output = "";
        for (int i = 0; i < list.getSize(); i++) {
            boolean toPrint = false;
            Task currentTask = list.getTask(i);
            String[] split = currentTask.getDescription().split(" ");
            for (int j = 0; j < split.length; j++) {
                if(split[j].equals(description)) {
                    toPrint = true;
                }
            }
            if(toPrint) {
                output += String.format("%s\n",currentTask.toString());
            }
        }
        return output;
    }

    /**
     * Parses the input string into indices.
     *
     * @param input The full line of the command and arguments.
     * @return The index.
     */
    public int parseIndex(String input){
        String[] split = input.split(" ");
        return Integer.parseInt(split[1]) - 1;
    }

    /**
     * Parses the input string for deadline tasks.
     *
     * @param input The full line of the command and arguments.
     * @return An array containing the description and date of the deadline.
     */
    public String[] parseDeadline(String input) {
        String[] split = input.split("/by");
        String description = split[0].replaceFirst("deadline ","");
        String[] result = {description, split[1]};
        return result;
    }

    /**
     * Parses the input string for Event tasks.
     *
     * @param input The full line of the command and arguments.
     * @return A string array containing description, start and end dates.
     */
    public String[] parseEvent(String input) {
        String[] split = input.split("/from");
        String description = split[0].replaceFirst("event ", "");
        split = split[1].split("/to");
        String[] result = {description, split[0], split[1]};
        return result;
    }

    public boolean isEmptyCommand(String[] input) {
        if(input.length < 2) {
            return true;
        } else {
            return false;
        }
    }
}
