package hachi.main;


import hachi.commands.*;


public class Parser {
    private enum Type {
        BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE
    }

    public static Command parse(String input) throws IllegalArgumentException{
        String[] words = input.split(" ");
        Type t = Type.valueOf(words[0].toUpperCase());
        switch(t) {
            case LIST:
                return new ListCommand(input);

            case DEADLINE:
                return new DdlCommand(input);

            case UNMARK:
                return new UnmarkCommand(input);

            case TODO:
                return new TodoCommand(input);

            case EVENT:
                return new EventCommand(input);

            case DELETE:
                return new DeleteCommand(input);

            case MARK:
                return new MarkCommand(input);

            case BYE:
                return new ExitCommand(input);

            default:
                throw new IllegalArgumentException();
        }
    }
}

