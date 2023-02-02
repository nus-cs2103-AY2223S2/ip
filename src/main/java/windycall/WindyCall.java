package windycall;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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
                return ListOperationHandler.handle(tasks, ui);
//                break;
            case MARK:
                return MarkOperationHandler.handle(parser, tasks, parts, storage);
//                break;
            case UNMARK:
                return UnmarkOperationHandler.handle(parser, tasks, parts, storage);
//                break;
            case DELETE:
                return DeleteOperationHandler.handle(parser, tasks, parts, storage);
//                break;
            case FIND:
                return FindOperationHandler.handle(parts, ui, tasks, userCommand);
//                break;
            default:
                String returnedMessage;
                try {
                    returnedMessage = AddTaskHandler.addTask(userCommand, tasks, storage);
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
