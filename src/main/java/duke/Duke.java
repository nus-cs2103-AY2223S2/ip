package duke;

import java.io.IOException;

import exceptions.DukeException;
import logic.commands.ByeCommand;
import logic.commands.Command;
import logic.parser.Parser;
import logic.response.Response;
import model.TaskList;
import storage.Storage;




/**
 * Class representing the Duke Application
 */
public class Duke {
    private static final String FILEPATH = "data";
    private static final String FILENAME = "memory.txt";
    private TaskList taskList = new TaskList();
    private Storage storage;


    public String initialize() {
        String response = "";
        this.storage = new Storage(FILEPATH, FILENAME);
        try {
            response += this.storage.initializeStorage();
            this.storage.load(this.taskList);
        } catch (IOException e) {
            this.taskList = new TaskList();
            return Response.getWelcome();
        }
        response += Response.getWelcome();
        return response;
    }

    public String getResponse(String input) {
        try {
            Command cmd = Parser.parse(input);
            String response = cmd.execute(taskList);
            this.storage.save(taskList);
            if (cmd instanceof ByeCommand) {
                System.exit(0);
            }
            return response;
        } catch (DukeException | IOException e) {
            return Response.returnChatError();
        }
    }

    public Duke() {
    }
}