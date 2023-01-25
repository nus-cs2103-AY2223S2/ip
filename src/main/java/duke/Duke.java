package duke;

import duke.command.Command;
import duke.tasklist.TaskList;
import duke.parser.*;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Represents a chatbot that one can interact with to keep track of tasks.
 */
public class Duke {

    /**
     * Gets the chatbot running.
     */
    public void run() {
        //Prepare components
        Ui userInterface = new Ui();
        Storage storage = new Storage("data", "tasks.txt");
        TaskList taskList = new TaskList();
        Parser parser = new Parser(userInterface, taskList);


        //Prepare data file
        if (!storage.prepareFile()) {
            //Shuts down the bot as data file cannot be created successfully
            userInterface.printShutDownMessage();
            userInterface.cleanUpUi();
            return;
        }

        //Load data from data file
        if (!storage.loadTasksFromFile(taskList)) {
            //Cannot read from data file. Start with new empty task list.
            taskList = new TaskList();
        }

        //Read in and process user commands
        while (true) {
            //Process command
            String rawCommand = userInterface.getUserCommand();
            CommandType commandType = parser.parseRawCommand(rawCommand);
            Command command = parser.parseCommandType(commandType, taskList, storage);

            //Incorrect format
            if (command == null) {
                continue;
            } else {
                //Run command
                command.runCommand();

                //Check for exit command
                if (command.isExit()) {
                    break;
                }
            }
        }
    }


    /**
     * Launches the chatbot.
     *
     * @param args The command line arguments that one can type.
     */
    public static void main(String[] args) {
        new Duke().run();
    }
}



