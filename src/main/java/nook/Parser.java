package nook;

import static commands.CommandType.DEADLINE;
import static commands.CommandType.EVENT;
import static commands.CommandType.TODO;

import java.time.LocalDate;

import commands.AddCommand;
import commands.ByeCommand;
import commands.Command;
import commands.CommandType;
import commands.DeleteCommand;
import commands.FindCommand;
import commands.HelpCommand;
import commands.ListCommand;
import commands.MarkCommand;
import commands.ReprioritiseCommand;
import exceptions.NookException;
import tasks.Deadline;
import tasks.Event;
import tasks.Priority;
import tasks.Todo;
/**
 * Represents the Parser that helps to parse input commands
 * into recognisable commands that Sirius can execute
 */
public class Parser {


    /**
     * Returns the CommandType based on the String input given
     * by iterating through the existing CommandType values
     *
     * @return the CommandType based on the String input given
     */
    public static CommandType getCommandType(String input) {
        for (CommandType c : CommandType.values()) {
            if (c.isEqual(input)) {
                return c;
            }
        }
        return CommandType.UNKNOWN;
    }

    /**
     * Parses the user's input and returns a command that is recognised and
     * executable by Sirius. It will also handle unknown commands by throwing a
     * NookException
     *
     * @param input User's input from the scanner
     * @return A command that represents the input
     * @throws NookException if the input command is not recognised
     */
    public static Command parse(String input) throws NookException {
        Validator validator = new Validator();
        String[] inputArr = input.split(" ", 2);
        CommandType commandType = getCommandType(inputArr[0]);
        int index = 0;
        String taskInput = "";

        switch (commandType) {
        case LIST:
            return new ListCommand();
        case HELP:
            return new HelpCommand();
        case MARK:
        case UNMARK:
            index = Integer.parseInt(inputArr[1]) - 1;
            return new MarkCommand(commandType, index);
        case PRIORITISE:
        case DEPRIORITISE:
            index = Integer.parseInt(inputArr[1]) - 1;
            return new ReprioritiseCommand(commandType, index);
        case DELETE:
            index = Integer.parseInt(inputArr[1]) - 1;
            return new DeleteCommand(index);
        case TODO:
            Todo todo = generateTodo(inputArr, commandType.getValue(), validator);
            return new AddCommand(TODO, todo);
        case DEADLINE:
            Deadline deadline = generateDeadline(inputArr, commandType.getValue(), validator);
            return new AddCommand(DEADLINE, deadline);
        case EVENT:
            return new AddCommand(EVENT, generateEvent(inputArr, commandType.getValue(), validator));
        case FIND:
            return new FindCommand(inputArr[1]);
        case BYE:
            return new ByeCommand();
        default:
            assert commandType == CommandType.UNKNOWN : "Command type is not a declared type";
            throw new NookException(generateDefaultMessage());
        }
    }

    private static String generateDefaultMessage() {
        return "Oh dear, I wasn't expecting that at all... What do you mean by that?";
    }

    private static Todo generateTodo(String[] taskInputArr, String task, Validator validator) throws NookException {
        try {
            validator.validateDescription(taskInputArr, task);
            String taskInput = taskInputArr[1];
            String[] todoInputArr = taskInput.split("/priority", 2);
            String todoDesc = todoInputArr[0].trim();
            if (todoInputArr.length == 2) {
                String priorityStr = todoInputArr[1].trim();
                validator.validatePriority(priorityStr);
                Priority priority = Priority.getPriority(priorityStr);
                return new Todo(todoDesc, priority);
            }
            return new Todo(todoDesc);
        } catch (NookException e) {
            throw new NookException(e.getMessage());
        } catch (Exception e) {
            throw new NookException("Oh my.. seems like you did not give me the correct format to add a todo task"
                    + ":\n\ntodo (task description) \nOr"
                    + "\ntodo (task description) /priority (priority option)");
        }
    }

    private static Event generateEvent(String[] taskInputArr, String task, Validator validator) throws NookException {
        try {
            validator.validateDescription(taskInputArr, task);
            String taskInput = taskInputArr[1];
            String[] eventInputArr = taskInput.split("/");
            validator.validateDescription(eventInputArr[0], task);
            String eventDesc = eventInputArr[0].trim();
            String from = eventInputArr[1].trim().substring(5);
            String to = eventInputArr[2].trim().substring(3);
            if (eventInputArr.length == 4) {
                String priorityStr = eventInputArr[3].trim().substring(9);
                validator.validatePriority(priorityStr);
                Priority priority = Priority.getPriority(priorityStr);
                return new Event(eventDesc, from, to, priority);
            }
            return new Event(eventDesc, from, to);
        } catch (NookException e) {
            throw new NookException(e.getMessage());
        } catch (Exception e) {
            throw new NookException("Oh my.. seems like you did not give me the correct format to add an event task"
                    + ":\n\nevent (task description) /from (from input) /to (to input) \nOr"
                    + "\nevent (task description) /from (from input) /to (to input) /priority (priority option)");
        }
    }

    private static Deadline generateDeadline(String[] taskInputArr, String task, Validator validator)
            throws NookException {
        try {
            validator.validateDescription(taskInputArr, task);
            String taskInput = taskInputArr[1];
            String[] deadlineInputArr = taskInput.split("/");
            validator.validateDescription(deadlineInputArr[0], task);
            String deadlineDesc = deadlineInputArr[0].trim();
            String byStr = deadlineInputArr[1].trim().substring(3);
            validator.validateDate(byStr);
            LocalDate byDate = LocalDate.parse(byStr);
            if (deadlineInputArr.length == 3) {
                String priorityStr = deadlineInputArr[2].trim().substring(9);
                validator.validatePriority(priorityStr);
                Priority priority = Priority.getPriority(priorityStr);
                return new Deadline(deadlineDesc, byDate, priority);
            }
            return new Deadline(deadlineDesc, byDate);
        } catch (NookException e) {
            throw new NookException(e.getMessage());
        } catch (Exception e) {
            throw new NookException("Oh my.. seems like you did not give me the correct format to add a deadline task"
                    + ":\n\ndeadline (task description) /by (yyyy-MM-dd) \nOr"
                    + "\ndeadline (task description) /by (yyyy-MM-dd) /priority (priority option)");
        }
    }
}
