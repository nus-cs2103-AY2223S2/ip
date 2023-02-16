package treebot;

import commands.Command;
import exception.TreeBotException;

import interfaces.IUndoable;
import tasks.TaskFactory;
import tasks.TaskList;
import utils.Parser;
import utils.Storage;

import java.io.FileNotFoundException;
import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Represents the TreeBot application, a chatbot to help users remember tasks.
 */
public class TreeBot {
    private TaskList taskList;
    private Storage storage;
    private Parser parser = new Parser(new TaskFactory());
    private Deque<IUndoable> history = new ArrayDeque<>();


    public TreeBot(String filePath) {
        storage = new Storage(filePath);
        try {
            taskList = new TaskList(storage.loadTasks());
        } catch (FileNotFoundException e) {
            taskList = new TaskList();
        }
    }

    /**
     * Returns the TreeBot's response to the user input.
     * @param input the user input.
     * @return a response to the given user input.
     */
    public String getResponse(String input) {
        try {
            Command c = parser.parse(input);
            c.injectContext(taskList, storage, history);
            saveToHistory(c);
            assert c.isContextExists() : "Must inject context";
            return c.execute();
        } catch (TreeBotException e) {
            return e.getMessage();
        }
    }

    /**
     * Pushes the given command onto the history stack.
     * @param c the command to be pushed onto the history stack.
     */
    public void saveToHistory(Command c) {
        if (!(c instanceof IUndoable)) {
            return;
        }

        IUndoable undoableCommand = (IUndoable) c;

        this.history.addFirst(undoableCommand);
    }

}
