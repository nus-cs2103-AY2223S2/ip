package duke;

import duke.commands.Command;

/**
 * The main class of this chatting bot programme.
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;

    /**
     * The constructor of this class.
     */
    public Duke() {
        storage = new Storage();
        tasks = storage.load();
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
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
