package duke;

import command.DeadlineCommand;
import command.DeleteCommand;
import command.EventCommand;
import command.FindCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnMarkCommand;
import task.TaskList;

/**
 * This class parses the command into an array
 * @author Bryan Ong
 */
public class Parser {

    public String parse(String command) {

        String[] splitCommand = command.split(" ");
        switch(splitCommand[0].toUpperCase()) {
            case "LIST":
                return TaskList.printList();
            case "MARK":
                return new MarkCommand(splitCommand[1]).mark();
            case "UNMARK":
                return new UnMarkCommand(splitCommand[1]).unmark();
            case "TODO":
                return new TodoCommand(splitCommand).create();
            case "EVENT":
                return new EventCommand(splitCommand).create();
            case "DEADLINE":
                return new DeadlineCommand(splitCommand).create();
            case "DELETE":
                return new DeleteCommand(splitCommand[1]).delete();
            case "FIND" :
                return new FindCommand(splitCommand).find();
            case "BYE" :
                TaskList.writeToFile();
                return Ui.printBye();
            default:
                return Ui.printWrongCommand();
        }
    }


}
