package ui;
import command.*;
import command.Commands;
import exception.DukeException;

public class StringParser {
    public Command parse(String answer) throws DukeException {
        String[] wordArr = answer.split(" ", 2);
        Commands command = getCommand(wordArr[0]);
        String desc, by, from, to, keyword;
        int index;
        switch (command) {
            case MARK:
                index = Integer.parseInt(wordArr[1]);
                return new MarkCommand(index - 1);
            case UNMARK:
                index = Integer.parseInt(wordArr[1]);
                return new UnmarkCommand(index - 1);
            case BYE:
                return new ByeCommand();
            case DELETE:
                index = Integer.parseInt(wordArr[1]);
                return new DeleteCommand(index - 1);
            case LIST:
                return new ListCommand();
            case TODO:
                this.parseTodo(answer);
                desc = wordArr[1];
                return new TodoCommand(desc);
            case DEADLINE:
                desc = wordArr[1].split(" /by ")[0];
                by = wordArr[1].split(" /by ")[1];
                return new DeadlineCommand(desc, by);
            case FIND:
                keyword = wordArr[1];
                return new FindCommand(keyword);
            case EVENT:
                desc = wordArr[1].split(" ", 2)[0];
                from = wordArr[1].split(" /from ")[1].split(" /to ")[0];
                to = wordArr[1].split(" /to ", 2)[1];
                return new EventCommand(desc, from, to);
            default:
                throw new DukeException("Sorry, I don't know that command");
        }
    }
    //    if (wordArr[0].equals("mark")) {
    //        int index = Integer.parseInt(wordArr[1]);
    //        return new MarkCommand(index - 1);
    //    } else if (wordArr[0].equals("unmark")) {
    //        int index = Integer.parseInt(wordArr[1]);
    //        return new UnmarkCommand(index - 1);
    //    } else if (answer.equals("bye")) {
    //        return new ByeCommand();
    //    } else if (wordArr[0].equals("delete")) {
    //        int index = Integer.parseInt(wordArr[1]);
    //        return new DeleteCommand(index - 1);
    //    } else if (answer.equals("list")) {
    //        return new ListCommand();
    //    } else if (wordArr[0].equals("todo")) {
    //        this.parseTodo(answer);
    //        String desc = wordArr[1];
    //        return new TodoCommand(desc);
    //    } else if (wordArr[0].equals("deadline")) {
    //        String desc = wordArr[1].split(" /by ")[0];
    //        String by = wordArr[1].split(" /by ")[1];
    //        return new DeadlineCommand(desc, by);
    //    } else if (wordArr[0].equals("event")) {
    //        String desc = wordArr[1].split(" ", 2)[0];
    //        String from = wordArr[1].split(" /from ")[1].split(" /to ")[0];
    //        String to = wordArr[1].split(" /to ", 2)[1];
    //        return new EventCommand(desc, from, to);
    //    } else if (wordArr[0].equals("find")) {
    //        String keyWord = wordArr[1];
    //        return new FindCommand(keyWord);
    //    } else{
    //        throw new DukeException("Sorry, I don't know that command");
    //    }
    //}

    private Commands getCommand(String input) throws DukeException{
        try {
            Commands command = Commands.valueOf(input.toUpperCase());
            return command;
        }  catch (IllegalArgumentException e) {
            throw new DukeException("Sorry that is an invalid command");
        } catch (NullPointerException e) {
            throw new DukeException("Null exception encountered");
        }
    }
    public void parseTodo(String input) throws DukeException{
        try{
            String[] wordArr = input.split(" ", 2);
            String desc = wordArr[1];
        } catch(ArrayIndexOutOfBoundsException e){
            throw new DukeException("Sorry, todo command needs description");
        }
    }
}
