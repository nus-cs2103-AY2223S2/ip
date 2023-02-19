package Duke;

import Duke.Commands.*;
import Duke.Exception.ProgramException;
import Duke.Tasks.TaskList;

/**
 * Class to handle commands given to Duke.
 * @author Bryan Juniano
 */

public class Handler{

    public CommandList getCommand(String input) throws ProgramException {
        try {
            String stringCommand = input.split(" ")[0];
            CommandList command = CommandList.valueOf(stringCommand.toUpperCase());
            return command;
        }
        catch (IllegalArgumentException e) {
            throw new ProgramException("Command can't be recognized, did you spell it correctly?");
        } catch (NullPointerException e) {
            throw new ProgramException("Command needed!");
        }
    }

    public int parseNumber(String input) throws ProgramException {
        String parameters[] = input.split(" ");
        if(parameters.length==1){
            throw new ProgramException("Index needed!");
        }
        else if(parameters.length>2){
            throw new ProgramException("Too many parameters, pick just one!");
        }
        try{
            int index = Integer.parseInt(parameters[1]);
            return index;
        }
        catch (NumberFormatException e){
            throw new ProgramException("Index must be an Integer!");
        }

    }

    public String parseToDo(String input) throws ProgramException{
        return "1";
    }

    
    public Command processCommand(String input, TaskList taskList) throws ProgramException {
        Command c = null;
        int index = 0;
        CommandList command = getCommand(input);
        switch (command) {
            case LIST:
                c = new ListCommand();
                break;
            case MARK:
                index = parseNumber(input);
                c = new MarkCommand(index);
                break;
            case UNMARK:
                index = parseNumber(input);
                c = new UnmarkCommand(index);
                break;
            case DELETE:
                index = parseNumber(input);
                c = new DeleteCommand(index);
                break;
            case DEADLINE:
                c = new DeadlineCommand("2","2");
                break;
            case EVENT:
                c = new EventCommand("2","2","2");
                break;
            case TODO:
                c = new ToDoCommand("2");
                break;
            case BYE:
                c = new ByeCommand();
                break;
            default:
                //will never reach here, getCommand will throw an error if command is neither of the above
                assert false;
        }
        return c;
    }

}
