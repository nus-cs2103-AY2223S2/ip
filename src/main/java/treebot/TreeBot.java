package treebot;

import commands.Command;
import exception.TreeBotException;

import tasks.TaskFactory;
import tasks.TaskList;
import utils.Parser;
import utils.Storage;

import java.io.FileNotFoundException;

public class TreeBot {
    private TaskList taskList;
    private Storage storage;
    private Parser parser = new Parser(new TaskFactory());

    public TreeBot(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
    }

    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            return c.execute(taskList, storage);
        } catch (TreeBotException e) {
            return e.getMessage();
        }
    }

}
