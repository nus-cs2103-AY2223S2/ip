package userinteraction;

import command.*;
import dukeexception.DukeException;

public class Parser {
    private enum CommandNames {
        LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, BYE
    }
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
            case BYE:
                return new ExitDukeCommand(inputArr);
            default:
                throw new DukeException("Unknown format");
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        } catch(NumberFormatException e) {
            System.out.println("Please provide numbers");
        } catch(IllegalArgumentException e) {
            System.out.println("Invalid command.Command");
        } catch (Exception e) {
            System.out.println("Unknown error");
        }
        return null;
    }
}
