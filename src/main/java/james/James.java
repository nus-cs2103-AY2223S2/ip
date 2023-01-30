package james;

import james.command.Command;
import james.parser.Parser;
import james.storage.Storage;
import james.task.TaskList;
import james.ui.UI;

/**
 * The main class of the James chatbot.
 * The James program is a bot that allows users to add, delete and mark
 * tasks as done.
 */
public class James {
    /**
     * The main method of the James program.
     */
    public James() throws JamesException {
        Parser parser = new Parser();
        Storage storage = new Storage();
        UI ui = new UI();
        TaskList taskList = storage.loadFile();
        ui.welcome();
        String input = ui.readCommand();
        while (!input.equals("bye"))
        {
            try
            {
                Command command = parser.parseCommand(input);
                command.assign(taskList, ui);
                command.execute();
            } catch (JamesException e)
            {
                ui.printError(e);
            } finally
            {
                input = ui.readCommand();
            }
        }
        storage.writeToFile(taskList);
        ui.exit();
    }
}



