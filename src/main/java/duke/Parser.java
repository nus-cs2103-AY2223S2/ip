package duke;
import duke.commands.AddDeadline;
import duke.commands.AddEvent;
import duke.commands.AddTodo;
import duke.commands.Bye;
import duke.commands.Command;
import duke.commands.Delete;
import duke.commands.List;
import duke.commands.Mark;
import duke.commands.Unmark;

public class Parser {

    // private enum CommandType {
    //     DEADLINE, 
    //     EVENT, 
    //     BYE, 
    //     LIST, 
    //     MARK, 
    //     UNMARK, 
    //     DELETE
    // }

    /**
     * parse takes in the user input and returns a Command for the program to execute
     * 
     * @param input the user input
     * @return A command object
     */
    public static Command parse(String input) throws DukeException{
        String[] inputwords = input.split(" ");
        String commandinput = inputwords[0].toUpperCase();

        switch(commandinput) {

            case "TODO":
                return new AddTodo(input);

            case "DEADLINE":
                return new AddDeadline(input);
    
            case "EVENT":
                return new AddEvent(input);
    
            case "DELETE":
                return new Delete(inputwords[1]);
    
            case "MARK":
                return new Mark(inputwords[1]);
    
            case "UNMARK":
                return new Unmark(inputwords[1]);

            case "BYE":
                return new Bye();

            case "LIST":
                return new List();

            default:
                throw new DukeException("invalid command");
        }

    }
        


    
}
