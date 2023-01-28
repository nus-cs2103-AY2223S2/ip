package panav.parser;

import panav.command.Command;
import panav.command.ListCommand;
import panav.command.TodoCommand;
import panav.command.EventCommand;
import panav.command.DeadlineCommand;
import panav.command.EditCommand;
import panav.command.ExitCommand;
import panav.command.DeleteCommand;

import panav.exception.InvalidInputException;

public class Parser {


    public static Command parse(String fullCommand) {
        String[] temp = fullCommand.split(" ");
        String firstWord = temp[0];
        try {
            switch (firstWord) {
            case "list":
                return new ListCommand();
            case "todo":
                String todoMessage = fullCommand.replace("todo", "").trim();
                return new TodoCommand(todoMessage);
            case "deadline":
                int indexBy = fullCommand.indexOf("/by");
                String by = fullCommand.substring(indexBy + 4);
                String deadlineMessage = fullCommand.substring(9, indexBy - 1);
                return new DeadlineCommand(deadlineMessage, by);
            case "event":
                int fromIndex = 0, toIndex = 0;
                String eventMessage = "", from = "", to = "";
                for (int j = 0; j < temp.length; j++) {
                    if (temp[j].compareTo("/from") == 0) {
                        fromIndex = j;
                    }
                    if (temp[j].compareTo("/to") == 0) {
                        toIndex = j;
                    }
                }
                for (int j = 0; j < temp.length; j++) {
                    if (j < fromIndex && j > 0) {
                        eventMessage += temp[j] + " ";
                    }
                    if (j > fromIndex && j < toIndex) {
                        from += temp[j] + " ";
                    }
                    if (j > toIndex) {
                        to += temp[j];
                    }
                }

                return new EventCommand(eventMessage, from, to);
            case "mark":
                return new EditCommand(fullCommand, true);
            case "unmark":
                return new EditCommand(fullCommand, false);
            case "bye":
                return new ExitCommand();
            case "delete":
                return new DeleteCommand(fullCommand);
            default:
                throw new InvalidInputException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
            }
        } catch (InvalidInputException e) {
            System.out.println(e.getMessage());
            return null;
        }

    }

}
