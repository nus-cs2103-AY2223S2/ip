package parser;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import commands.AddCommand;
import commands.CheckCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ListTasksCommand;
import commands.MarkCommand;
import exceptions.InvalidDateFormatException;
import exceptions.UnknownTaskException;
import commands.EnumCommand;

public abstract class Parser {
    
    /**
     * Parses the commandString input into an executable command
     * 
     * @param commandString
     * @return executable Command object that performs the action requested by the commandString
     * @throws InvalidDateFormatException
     * @throws UnknownTaskException
     */
    public static Command parseCommand(String commandString) throws InvalidDateFormatException, UnknownTaskException {

        String[] commands = commandString.split(" ");
        String j = commands[0].toUpperCase();
        EnumCommand comm;

        try {
            comm = EnumCommand.valueOf(j);
        } catch (IllegalArgumentException e) {
            throw new UnknownTaskException(j);
        }

        switch(comm) {
        case LIST:
            return new ListTasksCommand();
        case CHECK:
            try {
                return new CheckCommand(LocalDate.parse(commands[2]));
            } catch (DateTimeParseException e) {
                throw new InvalidDateFormatException(e.getParsedString());
            }
        case MARK:
        case UNMARK:
        case DELETE:
            int index = Integer.parseInt(commands[1]) - 1;
            switch(comm) {
            case MARK:
                return new MarkCommand(true, index);
            case UNMARK: 
                return new MarkCommand(false, index);
            case DELETE: 
                return new DeleteCommand(index);
            default:
                break; // will not reach here
            }
        default:
            String[] commandDetails = new String[4];
            commandDetails[0] = comm.toString();
            
            String taskString = "";
            for (int i = 1; i < commands.length; i++) {
                taskString += commands[i] + " ";
            }
            String[] args = taskString.split("/");
            for (int i = 0; i < args.length; i++) {
                commandDetails[i+1] = args[i];
            }
            return new AddCommand(commandDetails);
        }
    }

}
