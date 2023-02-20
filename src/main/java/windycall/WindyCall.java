package windycall;

import java.util.ArrayList;
import java.util.List;

import windycall.exception.WindyCallException;
import windycall.handler.AddTagHandler;
import windycall.handler.AddTaskHandler;
import windycall.handler.DeleteOperationHandler;
import windycall.handler.FindOperationHandler;
import windycall.handler.ListOperationHandler;
import windycall.handler.MarkOperationHandler;
import windycall.handler.UnmarkOperationHandler;
import windycall.parser.Parser;
import windycall.storage.Storage;
import windycall.task.Task;
import windycall.ui.Ui;

/**
 * Serves as a trigger of the whole WindyCall chatBox application.
 * Stores some important helper classes instance.
 */
public class WindyCall {
    private List<Task> tasks;
    private Storage storage;
    private Ui ui;
    private Parser parser;


    public WindyCall() {
        // handle loading here
        this.tasks = new ArrayList<>();
        this.storage = new Storage();
        this.ui = new Ui();
        this.parser = new Parser();
        this.storage.handleLoad(this.tasks);
    }

    /**
     * Serves as a runner of the application.
     * Requires parser to translate user command and handle different commands accordingly.
     */
    public String run(String userCommand) {
        if (userCommand.isEmpty()) {
            return "Hi, I'm WindyCall, what can I do for you?";
        }
        OperationType type = parser.getOperationType(userCommand);
        String[] parts = userCommand.split(" ");
        switch (type) {
        case LIST:
            ListOperationHandler listOperationHandler = new ListOperationHandler();
            return listOperationHandler.handle(tasks, ui);
        case MARK:
            MarkOperationHandler markOperationHandler = new MarkOperationHandler();
            return markOperationHandler.handle(parser, tasks, parts, storage);
        case UNMARK:
            UnmarkOperationHandler unmarkOperationHandler = new UnmarkOperationHandler();
            return unmarkOperationHandler.handle(parser, tasks, parts, storage);
        case DELETE:
            DeleteOperationHandler deleteOperationHandler = new DeleteOperationHandler();
            return deleteOperationHandler.handle(parser, tasks, parts, storage);
        case FIND:
            FindOperationHandler findOperationHandler = new FindOperationHandler();
            return findOperationHandler.handle(parts, ui, tasks, userCommand);
        case TAG:
            AddTagHandler addTagHandler = new AddTagHandler();
            return addTagHandler.handle(parser, tasks, parts, storage);
        case BYE:
            return "Bye. Always willing to provide my help for you!!!";
        default:
            String returnedMessage;
            try {
                AddTaskHandler addTaskHandler = new AddTaskHandler();
                returnedMessage = addTaskHandler.addTask(userCommand, tasks, storage, parser);
            } catch (WindyCallException e) {
                returnedMessage = e.getMessage();
            }
            return returnedMessage;
        }

    }
    /*
    public static void main(String[] args) {
        WindyCall chatBox = new WindyCall();
        chatBox.run();
    }
     */
}
