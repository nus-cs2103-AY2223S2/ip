package duke;


import duke.exceptions.LoadDukeException;
import duke.functions.Parser;
import duke.functions.Storage;
import duke.functions.Reply;

/**
 * The class that the Duke program will run on.
 * It includes the operation for starting up, receiving
 * and decoding user inputs and shut down.
 */
public class Duke {
    private Storage storage;
    private ToDoList list;

    public Duke(String path) {
        this.storage = new Storage(path);
    }

    public String startUpAndGetMessage() {
        try {
            this.list = this.storage.load();
            String reminderMessage = Reply.getReminderMessage(this.list.remind());
            return "Successfully loaded your tasks!\n\n"
                    + reminderMessage
                    + Reply.getWelcomeMessage();
        } catch (LoadDukeException e) {
            this.list = new ToDoList();
            return e.getMessage() + Reply.getWelcomeMessage();
        }
    }

    public String shutDown() {
        try {
            this.storage.save(this.list);
            return Reply.getExitMessage();
        } catch (Exception e) {
            return  Reply.getErrorMessage(e.getMessage());
        }
    }

    public String getResponse(String userInput) {
        String response;
        try {
            String[] inputs = Parser.handleInput(userInput, " ", 2, 1);
            response = Parser.handleCommand(inputs, this.list, this);
        } catch (Exception e) {
            response = Reply.getErrorMessage(e.getMessage());
        }
        return response;
    }
}
