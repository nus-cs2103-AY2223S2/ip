package duke;


import duke.functions.Parser;
import duke.functions.Storage;
import duke.functions.Reply;

/**
 * The main class that the Duke program will run on.
 * It includes the operation for starting up, receiving
 * and decoding user inputs and shut down.
 */
public class Duke {
    private Storage storage;
    private ToDoList list;

    public Duke(String path) {
        this.storage = new Storage(path);
        this.list = startUp();
    }

    private ToDoList startUp() {
        try {
            return this.storage.load();
        } catch (Exception e) {
            return new ToDoList();
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
