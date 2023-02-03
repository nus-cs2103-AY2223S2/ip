package duke;
import java.util.Scanner;
import duke.commands.Command;
import duke.dukeexceptions.DukeExceptions;
import duke.parsers.Parser;
import duke.tasklist.TaskList;
import duke.storage.Storage;

/**
 * The main duke program.
 */
public class Duke {
    private Storage storage;
    private TaskList taskList;
    private Ui ui;

    public Duke(String filePath) {
        this.ui = new Ui();
        this.storage = new Storage(filePath);
        this.taskList = storage.loadFile();
    }

    /**
     * Begins the execution of the Duke program.
     */
    public void run() {
        this.ui.showWelcome();
        boolean isExit = false;
        Scanner sc= new Scanner(System.in);

        //start of bot
        while(!isExit){
            String fullCommand = sc.nextLine();
            try {
                ui.showLine();
                Command c = Parser.parse(fullCommand);
                c.execute(taskList, ui, storage);
                isExit = c.isExit();
            } catch(DukeExceptions exceptions) {
                ui.showError(exceptions);
            } finally {
                ui.showLine();
            }
        }

        storage.save(taskList);
    }

    public static void main(String[] args) {
        new Duke("./data/").run();
    }
}
