package windycall;

import windycall.Handler.*;
import windycall.exception.WindyCallException;
import windycall.parser.Parser;
import windycall.storage.Storage;
import windycall.task.Task;
import windycall.ui.Ui;

import java.util.ArrayList;
import java.util.List;

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
            OperationType type = parser.getOperationType(userCommand);
            String[] parts = userCommand.split(" ");
            switch (type) {
            case LIST:
                ListOperationHandler listOperationHandler = new ListOperationHandler();
                return listOperationHandler.handle(tasks, ui);
//                break;
            case MARK:
                MarkOperationHandler markOperationHandler = new MarkOperationHandler();
                return markOperationHandler.handle(parser, tasks, parts, storage);
//                break;
            case UNMARK:
                UnmarkOperationHandler unmarkOperationHandler = new UnmarkOperationHandler();
                return unmarkOperationHandler.handle(parser, tasks, parts, storage);
//                break;
            case DELETE:
                DeleteOperationHandler deleteOperationHandler = new DeleteOperationHandler();
                return deleteOperationHandler.handle(parser, tasks, parts, storage);
//                break;
            case FIND:
                FindOperationHandler findOperationHandler = new FindOperationHandler();
                return findOperationHandler.handle(parts, ui, tasks, userCommand);
//                break;
            case TAG:
                AddTagHandler addTagHandler = new AddTagHandler();
                return addTagHandler.handle(parser, tasks, parts, storage);
//                break;
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
//                break;
            }
//            return "";

    }
    /*
    public static void main(String[] args) {
        WindyCall chatBox = new WindyCall();
        chatBox.run();
    }
     */
}
