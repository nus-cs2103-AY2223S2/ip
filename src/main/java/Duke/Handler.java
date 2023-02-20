package Duke;

import Duke.Commands.*;
import Duke.Exception.ProgramException;
import Duke.Tasks.TaskList;

import java.util.Collections;
import java.util.ArrayList;

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

    public int parseNumber(String input, TaskList taskList) throws ProgramException {
        String parameters[] = input.split(" ");
        if(parameters.length==1){
            throw new ProgramException("Index needed!");
        }
        try{
            int index = Integer.parseInt(parameters[1]);
            if(index > taskList.size() || index < 0){
                throw new ProgramException("Index out of bounds!");
            }
            return index;
        }
        catch (NumberFormatException e){
            throw new ProgramException("Index must be an Integer!");
        }

    }

    public String parseString(String input) throws ProgramException{
        String parameters[] = input.split(" ",2);
        if(parameters.length==1){
            throw new ProgramException("Missing info! I need the task description.");
        }
        return parameters[1];
    }

    public String[] parseDeadline(String input) throws ProgramException{
        String parameters[] = input.split(" ",2);
        if(parameters.length==1){
            throw new ProgramException("Missing info! I need the task description and the end time");
        }
        parameters = parameters[1].split("/by");
        if(parameters.length<2){
            throw new ProgramException("Bad format! /by command needed to set the deadline!");
        }
        if(parameters.length>2){
            throw new ProgramException("Bad format! Use /by only as a command!");
        }
        String name = parameters[0].strip();
        String end = parameters[1].strip();
        parameters = new String[]{name, end};
        return parameters;
    }

    public String[] parseEvent(String input) throws ProgramException{
        String parameters[] = input.split(" ",2);
        if(parameters.length==1){
            throw new ProgramException("Missing info! I need the task description, start and end time");
        }
        parameters = parameters[1].split("/from");
        if(parameters.length<2){
            throw new ProgramException("Bad format! /from command needed to set the deadline!");
        }
        if(parameters.length>2){
            throw new ProgramException("Bad format! Use /from only as a command!");
        }
        String name = parameters[0].strip();
        parameters = parameters[1].split("/to");
        if(parameters.length<2){
            throw new ProgramException("Bad format! /to command needed to set the deadline!");
        }
        if(parameters.length>2){
            throw new ProgramException("Bad format! Use /to only as a command!");
        }
        String start = parameters[0].strip();
        String end = parameters[1].strip();
        parameters = new String[]{name,start, end};
        return parameters;
    }

    public ArrayList<String> parseTags(String input) throws ProgramException{
        ArrayList<String> tags = new ArrayList<>();
        String parameters[] = input.split(" ",3);
        if(parameters.length<3){
            throw new ProgramException("Missing info! I need the index of the task and the tags.");
        }
        parameters = parameters[2].split(" ");
        Collections.addAll(tags,parameters);
        return tags;
    }

    public Command processCommand(String input, TaskList taskList) throws ProgramException {
        Command c = null;
        ArrayList<String> tags = new ArrayList<>();
        String content = null;
        String[] parameters = null;
        int index = 0;
        input = input.strip();
        CommandList command = getCommand(input);
        switch (command) {
            case LIST:
                c = new ListCommand();
                break;
            case MARK:
                index = parseNumber(input,taskList);
                c = new MarkCommand(index);
                break;
            case UNMARK:
                index = parseNumber(input,taskList);
                c = new UnmarkCommand(index);
                break;
            case DELETE:
                index = parseNumber(input,taskList);
                c = new DeleteCommand(index);
                break;
            case DEADLINE:
                parameters = parseDeadline(input);
                c = new DeadlineCommand(parameters[0],parameters[1]);
                break;
            case EVENT:
                parameters = parseEvent(input);
                c = new EventCommand(parameters[0],parameters[1],parameters[2]);
                break;
            case TODO:
                content = parseString(input);
                c = new ToDoCommand(content);
                break;
            case FIND:
                content = parseString(input);
                c = new FindCommand(content);
                break;
            case BYE:
                c = new ByeCommand();
                break;
            case TAG:
                tags = parseTags(input);
                index = parseNumber(input,taskList);
                c = new TagCommand(index,tags);
                break;
            default:
                //will never reach here, getCommand will throw an error if command is neither of the above
                assert false;
        }
        return c;
    }

}
