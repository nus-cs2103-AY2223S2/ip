package Ava;

import Ava.commands.*;
import Ava.exceptions.AvaException;
import Ava.exceptions.CommandNotFound;



public class Parser {

    public AvaCommand parse (String input) throws AvaException {
        input = input.toLowerCase().trim();
        AvaCommand command = null;
        String[] inputArray = splitInput(input);
        try {
            String indentifier = inputArray[0];
            switch (indentifier) {
            case "enter":
                command = new Enter();
                break;
            case "todo":
                command = new AddTask(parseTodo(inputArray[1]), Ava.TASK_TYPE.TODO);
                break;
            case "deadline":
                command = new AddTask(parseDeadline(inputArray[1]), Ava.TASK_TYPE.DEADLINE);
                break;
            case "event" :
                command = new AddTask(parseEvent(inputArray[1]), Ava.TASK_TYPE.EVENT);
                break;
            case "mark":
                command = new MarkTask(parseMarkUnmark(inputArray[1]));
                break;
            case "unmark":
                command = new UnmarkTask(parseMarkUnmark(inputArray[1]));
                break;
            case "delete":
                command = new DeleteTask(parseDelete(inputArray[1]));
                break;
            case "list" :
                command = new ListTask();
                break;
            case "sort":
                command = new SortTasks();
                break;
            case "bye" :
                command = new Exit();
                break;
            case "find":
                command = new FindTask(parseFindTask(inputArray[1]));
                break;
            default:
                throw new CommandNotFound(input);
            }
            return command;
        } catch (ArrayIndexOutOfBoundsException | AvaException e){

            throw new CommandNotFound(input);
        }
    }
    private String[] splitInput(String input){

        return input.split(" " , 2);
    }

    private String[] parseTodo(String mes){

        return new String[]{mes};
    }

    private String[] parseDeadline(String mes) {

        return mes.split("/by", 2);
    }

    private String[] parseEvent(String mes) {
        String[] output = new String[3];
        String[] messageFromTo = mes.split("/from", 2);
        output[0] = messageFromTo[0];
        String[] fromTo = messageFromTo[1].split("/to", 2);
        output[1] = fromTo[0];
        output[2] = fromTo[1];
        return output;
    }
    private String[] parseFindTask(String mes) {

        return new String[]{mes.trim()};
    }

    private String[] parseMarkUnmark(String mes){

        return new String[]{mes.trim()};
    }

    private String[] parseDelete(String mes){

        return new String[]{mes.trim()};
    }

}
