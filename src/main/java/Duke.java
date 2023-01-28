import java.io.*;
import java.nio.file.FileAlreadyExistsException;
import java.util.*;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Duke {
    static final String DIRPATH = "./data";
    static final String FILEPATH = "./data/tasks.txt";
    private static final ArrayList<Task> al = new ArrayList<>();
    private static final StringBuilder sb = new StringBuilder();
    private static final Scanner sc = new Scanner(System.in);

    private Ui ui;
    private Storage storage;
    private Parser parser;


    public Duke(String dirPath, String filePath) throws Exception {
        ui = new Ui();
        storage = new Storage(dirPath, filePath);
        parser = new Parser();

    }
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
                    Todo.createTodo(splitCommand);
                    break;
                case "EVENT":
                    Event.createEvent(splitCommand);
                    break;
                case "DEADLINE":
                    Deadline.createDeadline(splitCommand);
                    break;
                case "DELETE":
                    new DeleteCommand(splitCommand[1]).delete();
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
    public static void main(String[] args) throws Exception {
        new Duke(DIRPATH, FILEPATH).run();
    }
}
