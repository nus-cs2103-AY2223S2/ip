package userinteraction;



import command.AddDeadLineCommand;
import command.AddEventCommand;
import command.AddToDoCommand;
import command.ChangeDataSourceCommand;
import command.Command;
import command.DeleteTaskCommand;
import command.ExitDukeCommand;
import command.FindTaskCommand;
import command.ListCommand;
import command.MarkTaskCommand;
import dukeexception.DukeException;

/**
 * Handles making sense of user command.
 */
public class Parser {
    /**
     * All possible commands by user.
     */
    private enum CommandNames {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, CHANGEFILE, BYE
    }

    /**
     * Takes user input and returns command.
     *
     * @param input User input.
     * @return Command.
     */
    public static Command parse(String input) {
        String[] inputArr = input.split(" ", 2);
        try {
            CommandNames commandNames = CommandNames.valueOf(inputArr[0].toUpperCase());
            switch (commandNames) {
            case LIST:
                return new ListCommand(inputArr);
            case MARK:
                return new MarkTaskCommand(inputArr, true);
            case UNMARK:
                return new MarkTaskCommand(inputArr, false);
            case TODO:
                return new AddToDoCommand(inputArr);
            case EVENT:
                return new AddEventCommand(inputArr);
            case DEADLINE:
                return new AddDeadLineCommand(inputArr);
            case DELETE:
                return new DeleteTaskCommand(inputArr);
            case FIND:
                return new FindTaskCommand(inputArr);
            case CHANGEFILE:
                return new ChangeDataSourceCommand(inputArr);
            case BYE:
                return new ExitDukeCommand(inputArr);
            default:
                throw new DukeException("Unknown format");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Please provide numbers");
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid command.Command");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
        return null;
    }
}
