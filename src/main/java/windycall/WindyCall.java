package windycall;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

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
    public void run() {
        Scanner scan = new Scanner(System.in);
        ui.greeting();
        while(true) {
            String userCommand = scan.nextLine();
            if (userCommand.equals("bye")) {
                ui.byeWords();
                break;
            }
            OperationType type = parser.getOperationType(userCommand);
            Ui.line();
            String[] parts = userCommand.split(" ");
            switch (type) {
            case LIST:
                ListOperationHandler.handle(tasks, ui);
                break;
            case MARK:
                MarkOperationHandler.handle(parser, tasks, parts, storage);
                break;
            case UNMARK:
                UnmarkOperationHandler.handle(parser, tasks, parts, storage);
                break;
            case DELETE:
                DeleteOperationHandler.handle(parser, tasks, parts, storage);
                break;
            case FIND:
                FindOperationHandler.handle(parts, ui, tasks, userCommand);
                break;
            default:
                try {
                    AddTaskHandler.addTask(userCommand, tasks, storage);
                }
                catch (WindyCallException e) {
                    System.out.println(e.getMessage());
                }
                break;
            }
            Ui.line();
        }
    }
    public static void main(String[] args) {
        WindyCall chatBox = new WindyCall();
        chatBox.run();
    }
}
