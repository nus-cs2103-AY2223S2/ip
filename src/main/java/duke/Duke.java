package duke;

import duke.commands.Command;

/**
 * The main class of this chatting bot programme.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * Constructs of this class.
     */
    public Duke() {
        storage = new Storage();
        tasks = storage.load();
    }


    /**
     * Generates a response to user input.
     */
    public String getResponse(String input) {
        String result = null;
        try {
            boolean isExit;
            Command c = Parser.parse(input);
            isExit = c.isExit();
            if (isExit) {
                return null;
            }
            result = c.execute(tasks, storage);
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

}
