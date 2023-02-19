package duke;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.parser.Parser;
import duke.exception.DukeException;

public class Duke {
    private final Storage storage;
    private final TaskList tasks;

    public Duke() {
        storage = new Storage();
        tasks = new TaskList(storage.loadTaskList());
    }

    public String getResponse(String input) {
        String formattedInput = input.trim();
        if (formattedInput.equals("bye")) {
            return "bye";
        }

        try {
            return Parser.processCommand(formattedInput, storage, tasks);
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
