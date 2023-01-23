package sam.parser;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.Map;

import sam.command.AddCommand;
import sam.command.Command;
import sam.command.DeleteCommand;
import sam.command.ExitCommand;
import sam.command.ListCommand;
import sam.command.MarkCommand;
import sam.task.SamMissingTaskTitleException;
import sam.task.SamMissingTaskValueException;
import sam.task.TaskType;

/**
 * Handles deciphering user inputs.
 */
public class Parser {
  /**
   * Parses a string with the format "d/M/yyyy" as a LocalDate.
   * 
   * @param input The date string to be parsed.
   * @return The parsed LocalDate object.
   * @throws SamInvalidDateException If the string is in the wrong format.
   */
  public static LocalDate parseDate(String input) throws SamInvalidDateException {
    try {
      return LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"));
    } catch (DateTimeParseException e) {
      throw new SamInvalidDateException();
    }
  }

  /**
   * Parses a string as an integer.
   * 
   * @param input The string to be parsed.
   * @return The parsed integer.
   */
  public static int parseInt(String input) throws SamInvalidIntException {
    try {
      return Integer.parseInt(input);
    } catch (NumberFormatException e) {
      throw new SamInvalidIntException();
    }
  }

  /**
   * Parses a string of task arguments into a Map.
   * Task arguments should be in the form 'title /key1 val1 /key2 val2 ...'
   * 
   * @param input The string to be parsed.
   * @return A Map of the task arguments.
   * @throws SamMissingTaskTitleException If a title is not provided.
   * @throws SamMissingTaskValueException If any key is missing a value.
   */
  public static Map<String, String> parseTaskArgs(String input)
      throws SamMissingTaskTitleException, SamMissingTaskValueException {
    input = input.strip();
    if (input.isEmpty() || input.charAt(0) == '/') {
      throw new SamMissingTaskTitleException();
    }
    
    Map<String, String> taskArgs = new HashMap<>();
    String[] args = input.split(" +/", 2);
    taskArgs.put("title", args[0]);

    if (args.length > 1) {
      for (String arg : args[1].split(" +/")) {
        String[] keyValue = splitFirst(arg);
        if (keyValue.length <= 1) throw new SamMissingTaskValueException();
        taskArgs.put(keyValue[0], keyValue[1]);
      }
    }

    return taskArgs;
  }

  /**
   * Splits the first word of a string.
   * 
   * @param input The string to split.
   * @return A string array with the first word and the rest of the string.
   */
  public static String[] splitFirst(String input) {
    return input.split(" ", 2);
  }

  /**
   * Parses a string into a command based on the first word.
   * The rest of the string is passed into the command as argument.
   * 
   * @param input The user input.
   * @return A command object representing the given input.
   * @throws SamUnknownCommandException If the input doesn't match any command.
   */
  public static Command parseCommand(String input) throws SamUnknownCommandException {
    String[] commandArgs = splitFirst(input);
    Command c = null;
    String args = commandArgs.length > 1 ? commandArgs[1] : "";
    switch (commandArgs[0]) {
    case "bye":
      c = new ExitCommand(args);
      break;
    case "list":
      c = new ListCommand(args);
      break;
    case "mark":
      c = new MarkCommand(args, true);
      break;
    case "unmark":
      c = new MarkCommand(args, false);
      break;
    case "todo":
      c = new AddCommand(args, TaskType.TODO);
      break;
    case "event":
      c = new AddCommand(args, TaskType.EVENT);
      break;
    case "deadline":
      c = new AddCommand(args, TaskType.DEADLINE);
      break;
    case "delete":
      c = new DeleteCommand(args);
      break;
    default:
      throw new SamUnknownCommandException();
    }
    return c;
  }
}
