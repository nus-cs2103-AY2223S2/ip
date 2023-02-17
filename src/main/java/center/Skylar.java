package center;

import command.Parser;
import command.Storage;
import task.TaskList;

import java.io.IOException;

public class Skylar {
    private Parser parser;
    private Storage storage;
    private TaskList tasks;

    //Todo: Let the user input the path to an existing list file that they have
    public Skylar() {
        storage = new Storage();
        try {
            tasks = new TaskList(storage.load());
        } catch (IOException e) {
            tasks = new TaskList();
        }
        parser = new Parser(tasks);
    }

    public String getResponse(String input) {
        return parser.processInput(input);
    }

    public void store() {
        storage.store(tasks);
    }
}
