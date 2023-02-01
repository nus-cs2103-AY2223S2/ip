package duke;

import command.EventCommand;
import command.DeadlineCommand;
import command.DeleteCommand;
import command.FindCommand;
import command.MarkCommand;
import command.TodoCommand;
import command.UnMarkCommand;

import task.TaskList;

/**
 * The Duke program implements an application that
 * is able to track the tasks that a user has.
 * The task is divided into three different categories
 * Todo, Event and Deadline.
 *
 * @author Bryan Ong
 */
public class Duke {
    static final String DIRPATH = "./data";
    static final String FILEPATH = "./data/tasks.txt";

    private final Ui ui;
    private final Storage storage;
    private final Parser parser;


    public Duke(String dirPath, String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(dirPath, filePath);
        parser = new Parser();
    }

    /**
     * This method runs the main program, taking in of input
     * and running the correct commands accordingly until "bye"
     * is detected.
     */
    public void run() {
        boolean isExit = false;
        while(!isExit) {
            String command = Ui.readCommand();
            String[] splitCommand = parser.parse(command);
            switch(splitCommand[0].toUpperCase()) {
                case "LIST":
                    TaskList.printList();
                    break;
                case "MARK":
                    new MarkCommand(splitCommand[1]).mark();
                    break;
                case "UNMARK":
                    new UnMarkCommand(splitCommand[1]).unmark();
                    break;
                case "TODO":
                    new TodoCommand(splitCommand).create();
                    break;
                case "EVENT":
                    new EventCommand(splitCommand).create();
                    break;
                case "DEADLINE":
                    new DeadlineCommand(splitCommand).create();
                    break;
                case "DELETE":
                    new DeleteCommand(splitCommand[1]).delete();
                    break;
                case "FIND" :
                    new FindCommand(splitCommand).find();
                    break;
                case "BYE" :
                    isExit = true;
                    break;
                default:
                    ui.printWrongCommand();
                    break;
            }
        }
        TaskList.writeToFile();
        ui.printBye();
    }

    /**
     * This method invokes the run method to get the
     * main program from running.
     * @param args command input
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        new Duke(DIRPATH, FILEPATH).run();
    }
}
