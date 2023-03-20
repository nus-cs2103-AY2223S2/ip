package DukeHelpfulCode.Commands;

import java.util.Arrays;
import java.util.Locale;

import DukeHelpfulCode.Exceptions.*;

public class Parser {
    /**
     * parses the commands kekw
     *
     */

    public static Command parse(String fullCommand) throws NoTaskTypeException, NoTaskNameException, NoDueTimeException, NoStartTimeException, NoEndTimeException, NoSuchTaskException {
        /**
         * Converts the user input into a Command.
         *
         * @param   fullCommand             The user input
         * @return  command                 The Command that the user wants
         * @throws  NoTaskTypeException     Thrown if parser does not find the task type if adding
         * @throws  NoTaskNameException     Thrown if parser does not find the task name if adding
         * @throws  NoDueTimeException      Thrown if parser does not find the due dateTime if adding deadline
         * @throws  NoStartTimeException    Thrown if parser does not find the start dateTime if adding event
         * @throws  NoEndTimeException      Thrown if parser does not find the end dateTime if adding event
         * @throws  NoSuchTaskException     Thrown if deleting/marking task that isnt there on the list
         */
        Command cmd = new HelpCommand();
        String[] cmdArr = fullCommand.split(" ");
        // System.out.println(Arrays.asList(cmdArr));

        if (cmdArr[0].toLowerCase(Locale.ROOT).equals("bye")){
            cmd = new ExitCommand();

        } else if (cmdArr[0].toLowerCase(Locale.ROOT).equals("help")){
            cmd = new HelpCommand();

        } else if (cmdArr[0].toLowerCase(Locale.ROOT).equals("list")){
            cmd = new ListCommand();

        } else if (cmdArr[0].toLowerCase(Locale.ROOT).equals("add")){
            new AddCommandParser();
            cmd = AddCommandParser.parse(cmdArr);

        } else if (cmdArr[0].toLowerCase(Locale.ROOT).equals("delete")){
            cmd = DeleteCommandParser.parse(cmdArr);

        } else if (cmdArr[0].toLowerCase(Locale.ROOT).equals("mark")){
            cmd = MarkCommandParser.parse(cmdArr);

        } else if (cmdArr[0].toLowerCase(Locale.ROOT).equals("find")) {
            cmd = FindCommandParser.parse(cmdArr);

        }
        return cmd;
    }


}
